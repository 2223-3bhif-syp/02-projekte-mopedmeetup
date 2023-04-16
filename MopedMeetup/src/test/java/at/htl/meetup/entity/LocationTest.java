package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testToString() {
        Location location = new Location("meetup1", "Linz", "street1", 4020);

        assertEquals("Location{id=null, street='street1', city='Linz', zip=4020, name='meetup1'}", location.toString());
    }
}