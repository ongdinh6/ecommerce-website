package vn.omdinh.demo.repositories.impl;

import vn.omdinh.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.omdinh.demo.dtos.UserDTO;
import vn.omdinh.demo.repositories.UserRepository;

import static vn.omdinh.tables.Users.USERS;
import static vn.omdinh.tables.UserRole.USER_ROLE;
import static vn.omdinh.tables.Roles.ROLES;
import static org.jooq.impl.DSL.groupConcat;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dslContext;

    @Autowired
    public UserRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    /**
    * Got a problem with this code when using group_concat in MySQL.
    * Discussed at: https://blog.jooq.org/mysqls-allowmultiqueries-flag-with-jdbc-and-jooq/
    * Solution: turn on "allowMultiQueries=true" in the JDBC connection URL
    **/
    @Override
    public Record findByEmail(String email) {
        return this.dslContext
            .select(USERS.asterisk(), groupConcat(ROLES.NAME).as("roles"))
            .from(USERS)
            .innerJoin(USER_ROLE).on(USER_ROLE.EMAIL.eq(USERS.EMAIL))
            .innerJoin(ROLES).on(ROLES.NAME.eq(USER_ROLE.ROLE))
            .where(USERS.EMAIL.eq(email))
            .groupBy(USERS.EMAIL)
            .fetchOne();
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
