package ru.scur.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String login;
    private String username;
    private List<GoodsOrderDTO> orders;
}
