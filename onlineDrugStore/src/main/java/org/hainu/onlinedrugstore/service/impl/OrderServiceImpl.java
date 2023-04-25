package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.OrderDao;
import org.hainu.onlinedrugstore.entity.Order;
import org.hainu.onlinedrugstore.service.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDao, Order> implements OrderService {
}