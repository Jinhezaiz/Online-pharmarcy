package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.CustomerDao;
import org.hainu.onlinedrugstore.entity.Customer;
import org.hainu.onlinedrugstore.service.CustomerService;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerDao, Customer> implements CustomerService {
}