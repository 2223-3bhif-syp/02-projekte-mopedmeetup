package at.htl.meetup.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void test_getters_after_simple_constructor_ok() {
        // arrange
        Location location = new Location("meetup1", "Linz", "street1", 4020);

        // act

        //assert
        assertThat(location.getId()).isNull();
        assertThat(location.getName()).isEqualTo("meetup1");
        assertThat(location.getCity()).isEqualTo("Linz");
        assertThat(location.getStreet()).isEqualTo("street1");
        assertThat(location.getZip()).isEqualTo(4020);
    }
    @Test
    void test_getters_after_default_constructor_ok() {
        // arrange
        Location location = new Location();

        // act

        // assert
        assertThat(location.getId()).isNull();
        assertThat(location.getName()).isNull();
        assertThat(location.getCity()).isNull();
        assertThat(location.getStreet()).isNull();
        assertThat(location.getZip()).isEqualTo(0);
    }
}