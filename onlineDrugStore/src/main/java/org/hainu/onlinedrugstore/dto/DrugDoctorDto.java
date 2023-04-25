package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DrugDoctorDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    int current;
    int size;
    String name;
    int sex;
    String userName;
    String password;
    String email;
}
