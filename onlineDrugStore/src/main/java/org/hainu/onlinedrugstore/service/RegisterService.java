package org.hainu.onlinedrugstore.service;

import org.hainu.onlinedrugstore.dto.DrugDoctorDto;
import org.hainu.onlinedrugstore.dto.RegisterDto;
import org.hainu.onlinedrugstore.entity.User;

public interface RegisterService {
    boolean register(RegisterDto registerDto);
}
