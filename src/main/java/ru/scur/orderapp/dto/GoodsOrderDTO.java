package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrderDTO {
    private Long id;
    private String clientName;
    private Date date;
    private String address;
    private List<OrderLineDTO> orderLines;
}
