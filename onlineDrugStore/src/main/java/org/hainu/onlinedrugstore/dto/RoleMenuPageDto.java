package org.hainu.onlinedrugstore.dto;

import lombok.Data;

@Data
public class RoleMenuPageDto {
    RoleMenuDto[] records;
    Long total;
}
