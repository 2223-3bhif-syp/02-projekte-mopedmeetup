package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    @Test
    void test_getters_after_simple_constructor_ok() {
        //arrange
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime dateOfBirth = LocalDateTime.now();
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        User user = new User("Linus", "Nestler", "ln@email.com", dateOfBirth);
        Meetup meetup = new Meetup(user, location, "description", date);

        Participants participants = new Participants(user, meetup);
        // act

        // assert
        assertThat(participants.getUser()).isEqualTo(user);
        assertThat(participants.getMeetup()).isEqualTo(meetup);
        assertThat(participants.getId()).isNull();
    }
}