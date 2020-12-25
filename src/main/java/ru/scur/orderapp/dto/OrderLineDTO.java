package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLineDTO {
    private Long id;
    private Long orderId;
    private Long goodsId;
    private int count;
}
