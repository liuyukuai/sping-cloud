package com.xiaoer.cloud.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "SYS_USER")
public class User implements Serializable {
    @Id
    @Column(name = "ID")
    @Setter
    @Getter
    private String id;

    @Column(name = "EMAIL")
    @Setter
    @Getter
    private String email;

    @Column(name = "PASSWORD")
    @Setter
    @Getter
    private String password;

    @Column(name = "NICK_NAME")
    @Setter
    @Getter
    private String nickName;

    @Column(name = "CREATE_TIME")
    @Setter
    @Getter
    private Timestamp createTime;

    @Column(name = "SIGN")
    @Setter
    @Getter
    private String sign;

    @Column(name = "PHOTO")
    @Setter
    @Getter
    private String photo;

    @Column(name = "ROLE")
    @Setter
    @Getter
    private String role;
}
