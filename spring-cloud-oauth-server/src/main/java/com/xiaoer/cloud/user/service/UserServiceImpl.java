package com.xiaoer.cloud.user.service;

import com.xiaoer.cloud.user.entity.User;
import com.xiaoer.cloud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByEmail(String loginName) {
        return userRepository.findByEmail(loginName);
    }

}
