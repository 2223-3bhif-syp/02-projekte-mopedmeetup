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

class MeetupRepositoryTest {

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
        MeetupRepository meetupRepository = new MeetupRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2020, 12, 12, 12, 0);
        User creator = UserRepository.getById(2);
        Location location = LocationRepository.getById(2);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);

        String description2 = "Linus's Meetup";
        LocalDateTime meetupDate2 = LocalDateTime.of(2020, 5, 5, 12, 0);
        User creator2 = UserRepository.getById(1);
        Location location2 = LocationRepository.getById(1);

        Meetup meetup2 = new Meetup(creator2, location2, description2, meetupDate2);
        meetupRepository.insert(meetup2);


        assertEquals(meetup.getId(), 1);

        assertThat(table).column("M_ID")
                .value().isEqualTo(meetup .getId());
        assertThat(table).column("M_DESCRIPTION")
                .value().isEqualTo(meetup.getDescription());
        assertThat(table).column("M_MEETUP_DATE")
                .value().isEqualTo(meetup .getMeetupDate());
        assertThat(table).column("M_CREATOR")
                .value().isEqualTo(meetup .getCreator());
        assertThat(table).column("M_LOCATION")
                .value().isEqualTo(meetup .getLocation());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);
        MeetupRepository meetupRepository = new MeetupRepository();

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2020, 12, 12, 12, 0);
        User creator = UserRepository.getById(2);
        Location location = LocationRepository.getById(2);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        meetupRepository.insert(meetup);

        String newDescription = "UgahBugah Meetup";
        meetup.setDescription(newDescription);

        meetupRepository.update(meetup);

        assertEquals(meetup.getId(), 1);

        assertThat(table).column("M_ID")
                .value().isEqualTo(meetup .getId());
        assertThat(table).column("M_DESCRIPTION")
                .value().isEqualTo(newDescription);
        assertThat(table).column("M_MEETUP_DATE")
                .value().isEqualTo(meetup .getMeetupDate());
        assertThat(table).column("M_CREATOR")
                .value().isEqualTo(meetup .getCreator());
        assertThat(table).column("M_LOCATION")
                .value().isEqualTo(meetup .getLocation());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2020, 12, 12, 12, 0);
        User creator = UserRepository.getById(2);
        Location location = LocationRepository.getById(2);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        MeetupRepository meetupRepository = new MeetupRepository();

        meetupRepository.insert(meetup);
        meetupRepository.delete(Integer.parseInt(meetup.getId().toString()));

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2020, 12, 12, 12, 0);
        User creator = UserRepository.getById(2);
        Location location = LocationRepository.getById(2);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        MeetupRepository meetupRepository = new MeetupRepository();
        meetupRepository.insert(meetup);

        String description2 = "Linus's Meetup";
        LocalDateTime meetupDate2 = LocalDateTime.of(2020, 5, 5, 12, 0);
        User creator2 = UserRepository.getById(1);
        Location location2 = LocationRepository.getById(1);
        Meetup meetup2 = new Meetup(creator2, location2, description2, meetupDate2);
        meetupRepository.insert(meetup2);

        String description3 = "Said's Meetup";
        LocalDateTime meetupDate3 = LocalDateTime.of(2020, 1, 5, 12, 0);
        User creator3 = UserRepository.getById(1);
        Location location3 = LocationRepository.getById(2);
        Meetup meetup3 = new Meetup(creator3, location3, description3, meetupDate3);
        meetupRepository.insert(meetup3);

        List<Meetup> meetupList = meetupRepository.getAll();

        assertEquals(3, meetupList.size());
    }

    @Test
    void getById() {
        Table table = new Table(Database.getDataSource(), tableName);

        String description = "Oliver's Meetup";
        LocalDateTime meetupDate = LocalDateTime.of(2020, 12, 12, 12, 0);
        User creator = UserRepository.getById(2);
        Location location = LocationRepository.getById(2);

        Meetup meetup = new Meetup(creator, location, description, meetupDate);

        MeetupRepository meetupRepository = new MeetupRepository();
        meetupRepository.insert(meetup);

        String description2 = "Linus's Meetup";
        LocalDateTime meetupDate2 = LocalDateTime.of(2020, 5, 5, 12, 0);
        User creator2 = UserRepository.getById(1);
        Location location2 = LocationRepository.getById(1);

        Meetup meetup2 = new Meetup(creator2, location2, description2, meetupDate2);
        meetupRepository.insert(meetup2);

        String description3 = "Said's Meetup";
        LocalDateTime meetupDate3 = LocalDateTime.of(2020, 1, 5, 12, 0);
        User creator3 = UserRepository.getById(1);
        Location location3 = LocationRepository.getById(2);

        Meetup meetup3 = new Meetup(creator3, location3, description3, meetupDate3);
        meetupRepository.insert(meetup3);

        String description4 = "Bajtik's Meetup";
        LocalDateTime meetupDate4 = LocalDateTime.of(2020, 1, 30, 12, 0);
        User creator4 = UserRepository.getById(1);
        Location location4 = LocationRepository.getById(2);

        Meetup meetup4 = new Meetup(creator4, location4, description4, meetupDate4);
        meetupRepository.insert(meetup4);



        assertEquals(1, Integer.parseInt(meetup.getId().toString()));
        assertEquals(2, Integer.parseInt(meetup2.getId().toString()));
        assertEquals(3, Integer.parseInt(meetup3.getId().toString()));
        assertEquals(4, Integer.parseInt(meetup4.getId().toString()));
    }
}