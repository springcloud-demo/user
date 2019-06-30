package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 13:12
 **/
public interface UserService {
    UserInfo findByOpenid(String openid);
}
