package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RegisterDto {
    int auth;
    String userName;
    String password;
    String email;
    String name;
    int sex;
    String phone;
    String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long drugStoreId;
    Long roleId;
}
