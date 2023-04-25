package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.DrugStoreDao;
import org.hainu.onlinedrugstore.entity.DrugStore;
import org.hainu.onlinedrugstore.service.DrugStoreService;
import org.springframework.stereotype.Service;


@Service
public class DrugStoreServiceImpl extends BaseServiceImpl<DrugStoreDao, DrugStore> implements DrugStoreService {
}