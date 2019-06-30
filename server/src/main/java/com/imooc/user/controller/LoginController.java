package com.imooc.user.controller;

import com.imooc.user.VO.ResultVO;
import com.imooc.user.VO.ResultVOUtil;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.service.UserService;
import com.imooc.user.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 13:14
 **/
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        //1.openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo==null){
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR.getCode(),ResultEnum.LOGIN_ERROR.getMessage());
        }
        //2.判断角色
        if(RoleEnum.BUYER.getCode()!=userInfo.getRole()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR.getCode(),ResultEnum.ROLE_ERROR.getMessage());
        }
        //3.cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletResponse response, HttpServletRequest request){
        //判断是否已登陆
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie!=null&&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.Token_TEMPLATE,cookie.getValue())))){
            return ResultVOUtil.success();
        }
        //1.openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo==null){
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR.getCode(),ResultEnum.LOGIN_ERROR.getMessage());
        }
        //2.判断角色
        if(RoleEnum.SELLER.getCode()!=userInfo.getRole()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR.getCode(),ResultEnum.ROLE_ERROR.getMessage());
        }
        //3.redis设置key=UUID,value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire =  CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.Token_TEMPLATE,token),
               openid,
                expire,
                TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.expire);

        return ResultVOUtil.success();
    }
}
