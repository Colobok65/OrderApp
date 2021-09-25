package ru.scur.orderapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.UserDTO;
import ru.scur.orderapp.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    @Autowired
    private GoodsOrderConverter goodsOrderConverter;

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getOrders().stream().map(goodsOrderConverter::toOrderDTO).collect(Collectors.toList())
        );
    }

    public List<UserDTO> toGoodsDTOList(List<User> list){
        return list.stream().map(this::toUserDTO).collect(Collectors.toList());
    }
}
