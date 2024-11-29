package vn.omdinh.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.exceptions.NotFoundException;
import vn.omdinh.demo.repositories.UserRepository;
import vn.omdinh.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        var userRecord = this.userRepository.findByEmail(email);

        if (userRecord == null) {
            throw new NotFoundException("Not found user has email " + email);
        }

        return userRecord.into(UserDTO.class);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        var userRecord = this.userRepository.createUserRecord(userDTO);

        this.userRepository.save(userRecord.into(UserDTO.class));

        return userRecord.into(UserDTO.class);
    }
}
