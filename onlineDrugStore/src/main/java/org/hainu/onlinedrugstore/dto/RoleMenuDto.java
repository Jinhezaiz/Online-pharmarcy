package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class RoleMenuDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long menuId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long roleId;
    String roleName;
    String menuName;
}
