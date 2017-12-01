package com.xiaoer.cloud.user.service;

import com.xiaoer.cloud.user.entity.User;

public interface UserService {
    User getByEmail(String email);
}