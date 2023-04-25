package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DoctorRoleDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    int current;
    int size;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long doctorId;
    String name;
    int sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long roleId;
    String roleName;
}
