package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.type.Table;

import java.util.List;

class LocationRepositoryTest {

   private static String tableName = "MM_LOCATION";
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
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Linz";
        String street = "Stasse 11";
        int zip = 4202;

        Location location = new Location(name, city, street, zip);

        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String city2 = "Gramastetten";
        String street2 = "Hamberg 12";
        int zip2 = 4201;

        Location location2 = new Location(name2, city2, street2, zip2);
        locationRepository.insert(location2);


        assertEquals(location.getId(), 1);

        assertThat(table).column("L_ID")
                .value().isEqualTo(location .getId());
        assertThat(table).column("L_NAME")
                .value().isEqualTo(location.getName());
        assertThat(table).column("L_CITY")
                .value().isEqualTo(location .getCity());
        assertThat(table).column("L_STREET")
                .value().isEqualTo(location .getStreet());
        assertThat(table).column("L_ZIP")
                .value().isEqualTo(location .getZip());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        locationRepository.insert(location);

        String newName = "UgahBugah Meetup";
        location.setName(newName);

        locationRepository.update(location);

        assertEquals(location.getId(), 1);

        assertThat(table).column("L_ID")
                .value().isEqualTo(location .getId());
        assertThat(table).column("L_NAME")
                .value().isEqualTo(newName);
        assertThat(table).column("L_CITY")
                .value().isEqualTo(location .getCity());
        assertThat(table).column("L_STREET")
                .value().isEqualTo(location .getStreet());
        assertThat(table).column("L_ZIP")
                .value().isEqualTo(location .getZip());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        LocationRepository locationRepository = new LocationRepository();

        locationRepository.insert(location);
        locationRepository.delete(Integer.parseInt(location.getId().toString()));

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String city2 = "Linz";
        String street2 = "Theresia-Brandl-Weg";
        int zip2 = 4030;
        Location location2 = new Location(name2, city2, street2, zip2);
        locationRepository.insert(location2);

        String name3 = "Bajtik Meetup";
        String city3 = "Linz";
        String street3 = "Kleinmünchen";
        int zip3 = 4030;
        Location location3 = new Location(name3, city3, street3, zip3);
        locationRepository.insert(location3);

        List<Location> locationList = locationRepository.getAll();

        assertEquals(3, locationList.size());
    }

    @Test
    void getById() {
        Table table = new Table(Database.getDataSource(), tableName);

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        LocationRepository locationRepository = new LocationRepository();
        locationRepository.insert(location);

        String name2 = "Oliver Meetup";
        String city2 = "Linz";
        String street2 = "Theresia-Brandl-Weg";
        int zip2 = 4030;
        Location location2 = new Location(name2, city2, street2, zip2);
        locationRepository.insert(location2);

        String name3 = "Bajtik Meetup";
        String city3 = "Kleinmünchen";
        String street3 = "Dauphinenstraße";
        int zip3 = 4030;
        Location location3 = new Location(name3, city3, street3, zip3);
        locationRepository.insert(location3);

        String name4 = "Said Meetup";
        String city4 = "Linz";
        String street4 = "Helmholzstraße";
        int zip4 = 4020;
        Location location4 = new Location(name4, city4, street4, zip4);
        locationRepository.insert(location4);

        assertEquals(1, Integer.parseInt(location.getId().toString()));
        assertEquals(2, Integer.parseInt(location2.getId().toString()));
        assertEquals(3, Integer.parseInt(location3.getId().toString()));
        assertEquals(4, Integer.parseInt(location4.getId().toString()));
    }
}