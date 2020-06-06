package cn.imlmt.blog.service;

import cn.imlmt.blog.entities.User;

public interface UserService {

    User checkUser(String username, String password);
}
