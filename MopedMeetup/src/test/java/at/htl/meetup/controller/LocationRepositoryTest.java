package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.*;
import org.apache.ibatis.jdbc.Null;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.type.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class LocationRepositoryTest {

    private static String tableName = "MM_LOCATION";

    @BeforeEach
    public void setUp() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    public void tearDown() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void T01_insert_locations_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Linz";
        String street = "Strasse 11";
        int zip = 4202;

        String name2 = "Oliver Meetup";
        String city2 = "Gramastetten";
        String street2 = "Hamberg 12";
        int zip2 = 4201;

        Location location = new Location(name, city, street, zip);
        Location location2 = new Location(name2, city2, street2, zip2);

        //act
        locationRepository.insert(location);
        output(table).toConsole();
        locationRepository.insert(location2);

        table = new Table(
                Database.getDataSource(),
                tableName
        );

        output(table).toConsole();

        //assert
        assertThat(table).row(0)
                .value().isEqualTo(location.getId())
                .value().isEqualTo(location.getStreet())
                .value().isEqualTo(location.getCity())
                .value().isEqualTo(location.getZip())
                .value().isEqualTo(location.getName());

        assertThat(table).row(1)
                .value().isEqualTo(location2.getId())
                .value().isEqualTo(location2.getStreet())
                .value().isEqualTo(location2.getCity())
                .value().isEqualTo(location2.getZip())
                .value().isEqualTo(location2.getName());
    }

    @Test
    void T02_insert_location_null_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        Location location = null;

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> locationRepository.insert(location));
    }

    @Test
    void T03_update_location_in_db_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);
        String newName = "UgahBugah Meetup";

        //act
        locationRepository.insert(location);
        output(table).toConsole();
        location.setName(newName);
        locationRepository.update(location);

        table = new Table(
                Database.getDataSource(),
                tableName
        );

        output(table).toConsole();

        //assert
        assertThat(table).row(0)
                .value().isEqualTo(location.getId())
                .value().isEqualTo(location.getStreet())
                .value().isEqualTo(location.getCity())
                .value().isEqualTo(location.getZip())
                .value().isEqualTo(newName);
    }

    @Test
    void T04_update_location_null_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        Location location = null;

        output(table).toConsole();

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> locationRepository.update(location));
    }

    @Test
    void T05_update_location_not_in_db_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        output(table).toConsole();

        //assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> locationRepository.update(location));
    }

    @Test
    void T05_delete_location_in_db_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        //act
        locationRepository.insert(location);
        output(table).toConsole();
        locationRepository.delete(Integer.parseInt(location.getId().toString()));

        table = new Table(
                Database.getDataSource(),
                tableName
        );

        output(table).toConsole();

        //assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void T06_delete_location_null_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        Integer id = null;

        output(table).toConsole();

        //assert
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> locationRepository.delete(id));
    }

    @Test
    void T07_delete_location_invalid_id_invalid() {
        //arrange
        LocationRepository locationRepository = new LocationRepository();
        //act

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> locationRepository.delete((long) -1));
    }
    @Test
    void T08_get_location_by_id_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        //act
        locationRepository.insert(location);
        output(table).toConsole();

        //assert
        Assertions.assertThat(locationRepository.getById(1)).usingRecursiveComparison().isEqualTo(location);
    }

    @Test
    void T09_get_location_by_id_null_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        Integer id = null;

        output(table).toConsole();

        //assert
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> locationRepository.getById(id));
    }

    @Test
    void T10_get_all_locations_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        List<Location> locationlist = new ArrayList<>();

        String name = "Linus Meetup";
        String city = "Gramastetten";
        String street = "Hamberg 12";
        int zip = 4201;

        Location location = new Location(name, city, street, zip);

        String name2 = "Oliver Meetup";
        String city2 = "Linz";
        String street2 = "Theresia-Brandl-Weg";
        int zip2 = 4030;

        Location location2 = new Location(name2, city2, street2, zip2);

        //act
        locationlist.add(location);
        locationlist.add(location2);

        locationRepository.insert(location);
        output(table).toConsole();
        locationRepository.insert(location2);

        table = new Table(
                Database.getDataSource(),
                tableName
        );
        output(table).toConsole();

        //assert
        Assertions.assertThat(locationRepository.getAll()).usingRecursiveComparison().isEqualTo(locationlist);
    }
    @Test
    void T11_get_all_locations_empty() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();
        List<Location> locationlist = new ArrayList<>();

        //act
        output(table).toConsole();

        //assert
        Assertions.assertThat(locationRepository.getAll()).usingRecursiveComparison().isEqualTo(locationlist);
    }

    @Test
    void T12_get_all_locations_null_invalid() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        LocationRepository locationRepository = new LocationRepository();

        //act
        output(table).toConsole();

        //assert
        assertThat(locationRepository.getAll()).isEmpty();
    }
}