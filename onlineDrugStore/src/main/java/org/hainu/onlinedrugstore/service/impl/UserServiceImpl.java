package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.UserDao;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public List<Long> getIdsByRoleId(long id) {
        return userDao.getIdsByRoleId(id);
    }
}
