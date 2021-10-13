package ru.scur.orderapp.converter;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.UserDTO;
import ru.scur.orderapp.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getUsername(),
                user.getAddress()
        );
    }

    public List<UserDTO> toGoodsDTOList(List<User> list){
        return list.stream().map(this::toUserDTO).collect(Collectors.toList());
    }
}
