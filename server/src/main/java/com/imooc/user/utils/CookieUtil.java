package com.imooc.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookieUtil
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 16:16
 **/
public class CookieUtil {
    public static void set(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }
}
