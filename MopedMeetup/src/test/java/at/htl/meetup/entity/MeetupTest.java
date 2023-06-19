package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MeetupTest {
    @Test
    void test_getters_after_simple_constructor_ok() {
        //arrange
        LocalDateTime date = LocalDateTime.now();
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        User user = new User("Linus", "Nestler","123", "ln@email.com", 12);
        Meetup meetup = new Meetup(user, location, "description", date);

        // act

        // assert
        assertThat(meetup.getId()).isNull();
        assertThat(meetup.getCreator()).isEqualTo(user);
        assertThat(meetup.getLocation()).isEqualTo(location);
        assertThat(meetup.getDescription()).isEqualTo("description");
        assertThat(meetup.getMeetupDate()).isEqualTo(date);
    }
    @Test
    void test_getters_after_default_constructor_ok() {
        // arrange
        Meetup meetup = new Meetup();

        // act

        // assert
        assertThat(meetup.getId()).isNull();
        assertThat(meetup.getCreator()).isNull();
        assertThat(meetup.getLocation()).isNull();
        assertThat(meetup.getDescription()).isNull();
        assertThat(meetup.getMeetupDate()).isNull();
    }
}