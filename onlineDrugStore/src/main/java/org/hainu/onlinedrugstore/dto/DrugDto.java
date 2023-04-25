package org.hainu.onlinedrugstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DrugDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id;
    int current;
    int size;
}
