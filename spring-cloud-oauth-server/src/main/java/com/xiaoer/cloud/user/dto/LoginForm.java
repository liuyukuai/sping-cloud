package com.xiaoer.cloud.user.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class LoginForm {
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Setter
    @Getter
    private String email;
    @NotEmpty(message = "密码不能为空")
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String callback;
}
