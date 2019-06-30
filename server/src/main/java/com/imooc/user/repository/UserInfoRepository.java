package com.imooc.user.repository;

import com.imooc.user.dataobject.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserInfoRepository
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 13:11
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByOpenid(String openid);

}
