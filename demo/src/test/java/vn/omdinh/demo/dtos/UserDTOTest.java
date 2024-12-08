package vn.omdinh.demo.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class UserTest {
    String id;
    String name;

    public UserTest() {}

    public UserTest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserTest update(UserTest user) throws IllegalAccessException, NoSuchFieldException {
        var updatedDto = new UserTest();

        var fields = user.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            var value = field.get(user);

            if (value != null) {
                field.set(updatedDto, value);
            } else {
                var f = this.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                field.set(updatedDto, f.get(this));
            }
        }

        return updatedDto;
    }
}


class UserDTOTest {


    @Test
    void test() throws IllegalAccessException, NoSuchFieldException {
        var user = new UserTest("id", "name");

        var actual = user.update(new UserTest(null, "update_name"));

        Assertions.assertEquals("id", actual.id);
        Assertions.assertEquals("update_name", actual.name);
    }


}
