package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DrugStoreDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    String name;
    String announcement;
    String address;
}
