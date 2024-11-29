package vn.omdinh.demo.services;

import org.springframework.stereotype.Component;
import vn.omdinh.demo.dtos.UserDTO;

@Component
public interface UserService {
    UserDTO getUserByEmail(String email);
    UserDTO save(UserDTO userDTO);
}
