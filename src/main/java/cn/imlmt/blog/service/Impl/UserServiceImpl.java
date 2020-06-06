package cn.imlmt.blog.service.Impl;

import cn.imlmt.blog.entities.User;
import cn.imlmt.blog.mapper.UserMapper;
import cn.imlmt.blog.service.UserService;
import cn.imlmt.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
