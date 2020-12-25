package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String client;
    private Date date;
    private String address;
}
