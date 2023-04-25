package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.DrugDao;
import org.hainu.onlinedrugstore.entity.Drug;
import org.hainu.onlinedrugstore.service.DrugService;
import org.springframework.stereotype.Service;


@Service
public class DrugServiceImpl extends BaseServiceImpl<DrugDao, Drug> implements DrugService {
}