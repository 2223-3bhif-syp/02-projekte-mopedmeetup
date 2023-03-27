package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepositoryTest {

   /* private static String tableName = "MM_LOCATION";
    @BeforeEach
    public void setUp() {
        // to make sure every Table is empty and set up right
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    public void tearDown() {
        // to clear the tables again of all the test values
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String city = "Linz";
        String street = "Stasse 11";
        int zip = 4202;

        Location location = new Location(name, city, street, zip);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String city2 = "Gramastetten";
        String street2 = "Hamberg 12";
        int zip2 = 4201;

        Location location2 = new Location(name2, city2, street2, zip);
        locationRepository.insert(location2);


        assertEquals(location.getId(), 1);

        assertThat(table).column("LOCATION_ID")
                .value().isEqualTo(location .getId());
        assertThat(table).column("ADRESS")
                .value().isEqualTo(location.getAddress());
        assertThat(table).column("NAME")
                .value().isEqualTo(location .getName());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, adress);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String newAdress = "Nirgendwo";
        location.setAddress(newAdress);

        locationRepository.update(location);

        assertEquals(location.getId(), 1);

        assertThat(table).column("LOCATION_ID")
                .value().isEqualTo(location .getId());
        assertThat(table).column("ADRESS")
                .value().isEqualTo(newAdress);
        assertThat(table).column("NAME")
                .value().isEqualTo(location .getName());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String adress = "Hamberg 12, 4201 Gramastetten";

        Location location = new Location(name, adress);

        LocationRepository locationRepository = new LocationRepository();

        locationRepository.insert(location);
        locationRepository.delete(location.getId());

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String adress = "Hamberg 12, 4201 Gramastetten";

        Location location = new Location(name, adress);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String adress2 = "Hamberg 12, 4201 Gramastetten";
        Location location2 = new Location(name2, adress2);
        locationRepository.insert(location2);

        String name3 = "Bajtik Meetup";
        String adress3 = "Hamberg 12, 4201 Gramastetten";
        Location location3 = new Location(name3, adress3);
        locationRepository.insert(location3);

        List<Location> locationList = locationRepository.getAll();

        assertEquals(3, locationList.size());
    }

    @Test
    void getById() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String adress = "Hamberg 12, 4201 Gramastetten";

        Location location = new Location(name, adress);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String adress2 = "Hamberg 12, 4201 Gramastetten";
        Location location2 = new Location(name2, adress2);
        locationRepository.insert(location2);

        String name3 = "Bajtik Meetup";
        String adress3 = "Hamberg 12, 4201 Gramastetten";
        Location location3 = new Location(name3, adress3);
        locationRepository.insert(location3);

        String name4 = "Said Meetup";
        String adress4 = "Hamberg 12, 4201 Gramastetten";
        Location location4 = new Location(name4, adress4);
        locationRepository.insert(location4);

        assertEquals(location.getId(), locationRepository.getById(location.getId()).getId());
        assertEquals(location2.getId(), locationRepository.getById(location2.getId()).getId());
        assertEquals(location3.getId(), locationRepository.getById(location3.getId()).getId());
        assertEquals(location4.getId(), locationRepository.getById(location4.getId()).getId());

    }*/
}