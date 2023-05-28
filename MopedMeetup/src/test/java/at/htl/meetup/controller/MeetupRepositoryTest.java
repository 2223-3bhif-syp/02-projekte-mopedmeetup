package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.Location;
import at.htl.meetup.entity.Meetup;
import at.htl.meetup.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.output.Outputs.output;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class MeetupRepositoryTest {

    private static final String tableName = "MM_Meetup";
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
    void test_insert_meetup_check_Database_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);

        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        User creator = new User("Oliver", "Nestler", "password", "l@.net", 1);
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        Meetup meetup = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act
        locationRepository.insert(location);
        userRepository.insert(creator);
        meetupRepository.insert(meetup);


        // assert
        assertThat(meetup.getId().equals(1));

        assertThat(table).row(0)
                .value().isEqualTo(meetup.getId())
                .value().isEqualTo(meetup.getDescription())
                .value().isEqualTo(meetup.getMeetupDate())
                .value().isEqualTo(meetup.getCreator().getId())
                .value().isEqualTo(meetup.getLocation().getId());

        output(table).toConsole();
    }

    @Test
    void test_insert_meetup_without_location_and_creator_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);

        MeetupRepository meetupRepository = new MeetupRepository();
        Meetup meetup = new Meetup(null, null, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act
        meetupRepository.insert(meetup);

        // assert
        assertEquals(meetup.getId(), 1);

        assertThat(table).column("M_ID")
                .value().isEqualTo(meetup .getId());
        assertThat(table).column("M_DESCRIPTION")
                .value().isEqualTo(meetup.getDescription());
        assertThat(table).column("M_MEETUP_DATE")
                .value().isEqualTo(meetup .getMeetupDate());
        assertThat(table).column("M_U_ID")
                .value().isEqualTo(meetup.getCreator().getId());
        assertThat(table).column("M_L_ID")
                .value().isEqualTo(meetup .getLocation().getId());

        output(table).toConsole();
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2005, 10, 22, 0, 0);

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location);

        User creator = new User("Oliver", "Nestler", "password", "l@.net",4);
        userRepository.insert(creator);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);

        String newDescription = "UgahBugah Meetup";
        meetup.setDescription(newDescription);

        meetupRepository.update(meetup);

        assertEquals(meetup.getId(), 1);

        assertThat(table).column("M_ID")
                .value().isEqualTo(meetup .getId());
        assertThat(table).column("M_DESCRIPTION")
                .value().isEqualTo(meetup.getDescription());
        assertThat(table).column("M_MEETUP_DATE")
                .value().isEqualTo(meetup .getMeetupDate());
        assertThat(table).column("M_U_ID")
                .value().isEqualTo(meetup.getCreator().getId());
        assertThat(table).column("M_L_ID")
                .value().isEqualTo(meetup .getLocation().getId());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2005, 10, 22, 0, 0);

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location);

        User creator = new User("Oliver", "Nestler", "password", "l@.net",15);
        userRepository.insert(creator);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);
        meetupRepository.delete(Integer.parseInt(meetup.getId().toString()));

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2005, 10, 22, 0, 0);

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location);

        User creator = new User("Oliver", "Nestler", "password", "l@.net", 16);
        userRepository.insert(creator);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);

        String description2 = "Linus's Meetup";
        LocalDateTime meetupDate2 = LocalDateTime.of(2020, 5, 5, 12, 0);
        Location location2 = new Location("meetup44", "Linz", "street1", 4020);
        locationRepository.insert(location2);

        User creator2 = new User("Oliver1", "Nestle1r", "password", "l@.net", 16);
        userRepository.insert(creator2);


        Meetup meetup2 = new Meetup(creator2, location2, description2, meetupDate2);
        meetupRepository.insert(meetup2);

        String description3 = "Said's Meetup";
        LocalDateTime meetupDate3 = LocalDateTime.of(2020, 1, 5, 12, 0);
        Location location3 = new Location("meetup44", "Linz", "street1", 4020);
        locationRepository.insert(location3);

        User creator3 = new User("Oliver1", "Nestle1r", "password", "l@.net", 15);
        userRepository.insert(creator3);

        Meetup meetup3 = new Meetup(creator3, location3, description3, meetupDate3);
        meetupRepository.insert(meetup3);

        List<Meetup> meetupList = meetupRepository.getAll();

        assertEquals(3, meetupList.size());
    }

    @Test
    void getById() {
        Table table = new Table(Database.getDataSource(), tableName);

        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2005, 10, 22, 0, 0);

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location);

        User creator = new User("Oliver", "Nestler", "password", "l@.net", 15);
        userRepository.insert(creator);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);

        String description2 = "Linus's Meetup";
        LocalDateTime meetupDate2 = LocalDateTime.of(2020, 5, 5, 12, 0);
        Location location2 = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location2);

        User creator2 = new User("Oliver", "Nestler", "password", "l@.net", 16);
        userRepository.insert(creator2);

        Meetup meetup2 = new Meetup(creator2, location2, description2, meetupDate2);
        meetupRepository.insert(meetup2);

        String description3 = "Said's Meetup";
        LocalDateTime meetupDate3 = LocalDateTime.of(2020, 1, 5, 12, 0);
        Location location3 = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location3);

        User creator3 = new User("Oliver", "Nestler", "password", "l@.net", 17);
        userRepository.insert(creator3);

        Meetup meetup3 = new Meetup(creator3, location3, description3, meetupDate3);
        meetupRepository.insert(meetup3);

        String description4 = "Bajtik's Meetup";
        LocalDateTime meetupDate4 = LocalDateTime.of(2020, 1, 30, 12, 0);
        Location location4 = new Location("meetup1", "Linz", "street1", 4020);
        locationRepository.insert(location4);

        User creator4 = new User("Oliver", "Nestler", "password", "l@.net",16);
        userRepository.insert(creator4);

        Meetup meetup4 = new Meetup(creator4, location4, description4, meetupDate4);
        meetupRepository.insert(meetup4);



        assertEquals(1, Integer.parseInt(meetup.getId().toString()));
        assertEquals(2, Integer.parseInt(meetup2.getId().toString()));
        assertEquals(3, Integer.parseInt(meetup3.getId().toString()));
        assertEquals(4, Integer.parseInt(meetup4.getId().toString()));
    }
}