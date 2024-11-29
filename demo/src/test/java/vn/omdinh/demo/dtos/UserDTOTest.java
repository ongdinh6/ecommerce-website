package vn.omdinh.demo.dtos;

import org.jooq.JSON;
import org.junit.jupiter.api.Test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void should_init_correct() throws ParseException {
        var dto = new UserDTO("id", "username", "password", "email", "fullName", new SimpleDateFormat("mm-dd-yyyy").parse("11-26-2024"));
    }

}