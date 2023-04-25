package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.MenuDao;
import org.hainu.onlinedrugstore.entity.Menu;
import org.hainu.onlinedrugstore.service.MenuService;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {
}