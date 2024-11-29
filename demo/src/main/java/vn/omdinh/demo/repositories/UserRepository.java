package vn.omdinh.demo.repositories;

import nu.studer.sample.tables.records.UsersRecord;
import org.jooq.Record;
import vn.omdinh.demo.dtos.UserDTO;

public interface UserRepository {
    Record findByEmail(String email);
    void save(UserDTO userDTO);
    UsersRecord createUserRecord(UserDTO userDTO);
}
