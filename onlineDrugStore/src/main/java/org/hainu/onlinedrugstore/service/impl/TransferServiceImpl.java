package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.base.service.BaseServiceImpl;
import org.hainu.onlinedrugstore.dao.TransferDao;
import org.hainu.onlinedrugstore.entity.Transfer;
import org.hainu.onlinedrugstore.service.TransferService;
import org.springframework.stereotype.Service;


@Service
public class TransferServiceImpl extends BaseServiceImpl<TransferDao, Transfer> implements TransferService {
}