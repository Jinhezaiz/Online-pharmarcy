package org.hainu.onlinedrugstore.dto;

import lombok.Data;

@Data
public class DoctorRolePageDTO {
    DoctorRoleDto[] records;
    Long total;
}
