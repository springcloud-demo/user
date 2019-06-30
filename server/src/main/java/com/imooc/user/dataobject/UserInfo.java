package com.imooc.user.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName: UserInfo
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 13:08
 **/
@Data
@Entity
public class UserInfo {
    @Id
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String openid;
    @Column
    private Integer role;

}
