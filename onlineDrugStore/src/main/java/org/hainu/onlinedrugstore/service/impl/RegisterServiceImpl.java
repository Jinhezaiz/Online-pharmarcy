package org.hainu.onlinedrugstore.service.impl;

import org.hainu.onlinedrugstore.dao.BossDao;
import org.hainu.onlinedrugstore.dao.CustomerDao;
import org.hainu.onlinedrugstore.dao.DrugDoctorDao;
import org.hainu.onlinedrugstore.dao.UserDao;
import org.hainu.onlinedrugstore.dto.DrugDoctorDto;
import org.hainu.onlinedrugstore.dto.RegisterDto;
import org.hainu.onlinedrugstore.entity.Boss;
import org.hainu.onlinedrugstore.entity.Customer;
import org.hainu.onlinedrugstore.entity.DrugDoctor;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserDao userDao;
    @Autowired
    BossDao bossDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    DrugDoctorDao drugDoctorDao;

    @Override
    public boolean register(RegisterDto registerDto) {
        User user = new User();
        user.setAuth(registerDto.getAuth());
        user.setUserName(registerDto.getUserName());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        user.setRoleId(registerDto.getRoleId());
        User user1 = userDao.getUserMessage(user.getUserName());
        User user2 = userDao.getUserByEmail(user.getEmail());
        if (user1 != null || user2 != null) {
            return false;
        }
        userDao.insert(user);
        user = userDao.getUserMessage(user.getUserName());
        if (user.getAuth() == 1) {
            Boss boss = new Boss();
            boss.setName(registerDto.getName());
            boss.setSex(registerDto.getSex());
            boss.setId(user.getId());
            bossDao.insert(boss);
            return true;
        } else if (user.getAuth() == 2) {
            DrugDoctor drugDoctor = new DrugDoctor();
            drugDoctor.setDrugStoreId(registerDto.getDrugStoreId());
            drugDoctor.setName(registerDto.getName());
            drugDoctor.setSex(registerDto.getSex());
            drugDoctor.setId(user.getId());
            drugDoctorDao.insert(drugDoctor);
            return true;
        } else if (user.getAuth() == 3) {
            Customer customer = new Customer();
            customer.setName(registerDto.getName());
            customer.setSex(registerDto.getSex());
            customer.setPhone(Long.parseLong(registerDto.getPhone()));
            customer.setAddress(registerDto.getAddress());
            customer.setId(user.getId());
            customerDao.insert(customer);
            return true;
        }
        return false;
    }

}
