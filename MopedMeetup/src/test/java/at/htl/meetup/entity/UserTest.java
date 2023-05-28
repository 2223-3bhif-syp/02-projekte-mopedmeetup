package at.htl.meetup.entity;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void test_getters_after_simple_constructor_ok() {
        //arrange
        LocalDateTime dateOfBirth = LocalDateTime.now();
        User user = new User("Linus", "Nestler", "ln@email.com", dateOfBirth);
        // act

        // assert
        assertThat(user.getId()).isNull();
        assertThat(user.getFirstName()).isEqualTo("Linus");
        assertThat(user.getLastName()).isEqualTo("Nestler");
        assertThat(user.getEmail()).isEqualTo("ln@email.com");
        assertThat(user.getDateOfBirth()).isEqualTo(dateOfBirth);
    }
}