package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MeetupTest {

    @Test
    void testToString() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime dateOfBirth = LocalDateTime.now();
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        User user = new User("Linus", "Nestler", "ln@email.com", dateOfBirth);
        Meetup meetup = new Meetup(user, location, "description", date);

        assertEquals("Meetup{id=null, creator=User{id=null, " +
                "firstName='Linus', lastName='Nestler', email='ln@email.com', dateOfBirth="+ dateOfBirth.toString()+"}, " +
                "location=Location{id=null, street='street1', city='Linz', zip=4020, name='meetup1'}, " +
                "description='description', "  +
                "meetupDate="+ date.toString() +"}", meetup.toString() );

    }
}