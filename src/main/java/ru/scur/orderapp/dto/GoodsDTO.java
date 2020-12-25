package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsDTO {
    private Long id;
    private String name;
    private float price;
}
