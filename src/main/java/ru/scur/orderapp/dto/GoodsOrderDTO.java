package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.scur.orderapp.model.OrderLine;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class GoodsOrderDTO {
    private Long id;
    private String client;
    private Date date;
    private String address;
    private List<OrderLine> orderLines;
}
