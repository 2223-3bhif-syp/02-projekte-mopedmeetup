package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void test_getters_after_simple_constructor_ok() {
        //arrange
        LocalDateTime dateOfBirth = LocalDateTime.now();
        User user = new User("Linus", "Nestler", "123","ln@email.com", dateOfBirth);
        // act

        // assert
        assertThat(user.getId()).isNull();
        assertThat(user.getFirstName()).isEqualTo("Linus");
        assertThat(user.getLastName()).isEqualTo("Nestler");
        assertThat(user.getEmail()).isEqualTo("ln@email.com");
        assertThat(user.getPassword()).isEqualTo("123");
        assertThat(user.getDateOfBirth()).isEqualTo(dateOfBirth);
    }
}