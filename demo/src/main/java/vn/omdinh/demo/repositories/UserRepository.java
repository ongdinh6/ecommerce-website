package vn.omdinh.demo.repositories;

import vn.omdinh.tables.records.UsersRecord;
import org.jooq.Record;
import vn.omdinh.demo.dtos.UserDTO;

public interface UserRepository {
    Record findByEmail(String email);
    void save(UserDTO userDTO);
    UsersRecord createUserRecord(UserDTO userDTO);
}
