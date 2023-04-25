package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.RoleDao;
import org.hainu.onlinedrugstore.entity.Role;
import org.hainu.onlinedrugstore.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {
}