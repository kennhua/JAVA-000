package com.kennhua.rpcfx.demo.provider;

import com.kennhua.rpcfx.demo.api.User;
import com.kennhua.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
