package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrderDTO {
    private Long id;
    private String client;
    private LocalDateTime date;
    private String address;
    private Long userId;
    private List<OrderLineDTO> orderLines;
}
