package org.hainu.onlinedrugstore.service.impl;

import com.alibaba.fastjson.JSON;
import org.hainu.onlinedrugstore.dao.BossDao;
import org.hainu.onlinedrugstore.dao.CustomerDao;
import org.hainu.onlinedrugstore.dao.DrugDoctorDao;
import org.hainu.onlinedrugstore.dao.UserDao;
import org.hainu.onlinedrugstore.entity.User;
import org.hainu.onlinedrugstore.service.LoginService;
import org.hainu.onlinedrugstore.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDao userMapper;
    @Autowired
    BossDao bossDao;
    @Autowired
    DrugDoctorDao drugDoctorDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    Md5Util md5Util;

    public String login(User user, String firstLoginTime) throws Exception {
        User uCurrent = userMapper.getUserMessage(user.getUserName());
        if (uCurrent == null || !user.getPassword().equals(uCurrent.getPassword()))
            return "账号或密码错误";
        Map<String, String> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "MD5");
        String head = JSON.toJSONString(header);
        Map<String, String> payload = new HashMap<>();
        payload.put("id",Long.toString(uCurrent.getId()));
        payload.put("userName", user.getUserName());
        payload.put("firstLoginTime", firstLoginTime);
        payload.put("auth", Integer.toString(uCurrent.getAuth()));
        payload.put("roleId", Long.toString(uCurrent.getRoleId()));
        if(uCurrent.getAuth()==1){
            payload.put("name",bossDao.selectById(uCurrent.getId()).getName());
        } else if (uCurrent.getAuth()==2) {
            payload.put("name",drugDoctorDao.selectById(uCurrent.getId()).getName());
        } else if (uCurrent.getAuth()==3) {
            payload.put("name",customerDao.selectById(uCurrent.getId()).getName());
        }
        String payl = JSON.toJSONString(payload);
        String base64_header = Base64.getEncoder().encodeToString(head.getBytes("utf-8"));
        String base64_payload = Base64.getEncoder().encodeToString(payl.getBytes("utf-8"));
        String token = md5Util.generateToken(base64_header, base64_payload);
        return token;
    }
}
