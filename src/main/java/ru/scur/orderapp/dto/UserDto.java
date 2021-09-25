package ru.scur.orderapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<GoodsOrderDTO> orders;
}
