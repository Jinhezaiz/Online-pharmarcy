package org.hainu.onlinedrugstore.service;

import org.hainu.onlinedrugstore.base.service.BaseService;
import org.hainu.onlinedrugstore.entity.User;

import java.util.List;


public interface UserService extends BaseService<User> {
    public List<Long> getIdsByRoleId(long id);
}
