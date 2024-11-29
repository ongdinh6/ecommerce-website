package vn.omdinh.demo.repositories.impl;

import nu.studer.sample.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.repositories.UserRepository;

import static nu.studer.sample.tables.Users.USERS;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dslContext;

    @Autowired
    public UserRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Record findByEmail(String email) {
        return this.dslContext.select().from(USERS).where(USERS.EMAIL.eq(email)).fetchOne();
    }

    @Override
    public void save(UserDTO userDTO) {
        this.createUserRecord(userDTO).store();
    }

    @Override
    public UsersRecord createUserRecord(UserDTO userDTO) {
        return this.dslContext.newRecord(USERS, userDTO);
    }
}
