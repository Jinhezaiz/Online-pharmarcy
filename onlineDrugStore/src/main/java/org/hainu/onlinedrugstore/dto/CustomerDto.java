package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CustomerDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    String phone;
    String address;
    int sex;
    String password;
    String name;
}
