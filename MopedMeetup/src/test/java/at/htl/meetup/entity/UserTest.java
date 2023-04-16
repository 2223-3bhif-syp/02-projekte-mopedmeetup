package at.htl.meetup.entity;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.db.api.Assertions.assertThat;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class UserTest {

    @Test
    void testToString() {
        LocalDateTime dateOfBirth = LocalDateTime.now();
        User user = new User("Linus", "Nestler", "ln@email.com", dateOfBirth);

        assertEquals("User{id=null, firstName='Linus', lastName='Nestler', email='ln@email.com', dateOfBirth="+ dateOfBirth.toString()+"}", user.toString());
    }
}