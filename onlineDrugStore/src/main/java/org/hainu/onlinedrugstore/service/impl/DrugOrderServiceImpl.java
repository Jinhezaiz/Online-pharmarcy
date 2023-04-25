package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.DrugOrderDao;
import org.hainu.onlinedrugstore.entity.DrugOrder;
import org.hainu.onlinedrugstore.service.DrugOrderService;
import org.springframework.stereotype.Service;


@Service
public class DrugOrderServiceImpl extends BaseServiceImpl<DrugOrderDao, DrugOrder> implements DrugOrderService {
}