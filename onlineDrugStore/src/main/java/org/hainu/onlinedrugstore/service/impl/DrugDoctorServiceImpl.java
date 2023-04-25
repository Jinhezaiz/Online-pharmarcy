package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.DrugDoctorDao;
import org.hainu.onlinedrugstore.entity.DrugDoctor;
import org.hainu.onlinedrugstore.service.DrugDoctorService;
import org.springframework.stereotype.Service;


@Service
public class DrugDoctorServiceImpl extends BaseServiceImpl<DrugDoctorDao, DrugDoctor> implements DrugDoctorService {
}