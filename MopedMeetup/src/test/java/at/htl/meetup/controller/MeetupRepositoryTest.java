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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;


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
    void test_insert_meetup_check_database_ok() {
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
        MeetupRepository meetupRepository = new MeetupRepository();
        Meetup meetup = new Meetup(null, null, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.insert(meetup));
    }

    @Test
    void test_insert_meetup_without_location_and_creator_present_in_DB_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();
        User creator = new User("Oliver", "Nestler", "password", "l@.net", 1);
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        Meetup meetup = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.insert(meetup));
    }

    @Test
    void test_insert_meetup_null_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();
        //act

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.insert(null));
    }

    @Test
    void test_update_meetup_check_database_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        User creator = new User("Oliver", "Nestler", "password", "l@.net", 1);
        Location location = new Location("meetup1", "Linz", "street1", 4020);
        Location location2 = new Location("3", "4", "5", 4020);
        Meetup meetup = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act
        locationRepository.insert(location);
        locationRepository.insert(location2);
        userRepository.insert(creator);
        meetupRepository.insert(meetup);

        meetup.setDescription("eee");
        meetup.setLocation(location2);

        meetupRepository.update(meetup);
        // assert
         assertThat(table).row(0)
                .value().isEqualTo(meetup.getId())
                .value().isEqualTo("eee")
                .value().isEqualTo(meetup.getMeetupDate())
                .value().isEqualTo(meetup.getCreator().getId())
                .value().isEqualTo(location2.getId());

        output(table).toConsole();
    }

    @Test
    void test_update_meetup_set_location_null_ok() {
        //arrange
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

        meetup.setLocation(null);

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.update(meetup));
    }

    @Test
    void test_update_meetup_set_creator_null_ok() {
        //arrange

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

        meetup.setCreator(null);

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.update(meetup));
    }

    @Test
    void test_update_null_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();

        //act

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.update(null));
    }

    @Test
    void test_delete_meetup_from_database_ok() {
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
        meetupRepository.delete(meetup.getId());

        //assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void test_delete_meetup_illegal_id_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();
        //act

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> meetupRepository.delete((long) -1));
    }

    @Test
    void test_getAll_list_contains_inserted_values_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        User creator = new User("Oliver", "Nestler", "password", "l@.net", 1);

        Meetup meetup1 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));
        Meetup meetup2 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));
        Meetup meetup3 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act
        locationRepository.insert(location);
        userRepository.insert(creator);
        meetupRepository.insert(meetup1);
        meetupRepository.insert(meetup2);
        meetupRepository.insert(meetup3);

        //assert
        assertThat(meetupRepository.getAll()).hasSize(3)
                .usingRecursiveFieldByFieldElementComparator().
                contains(meetup1, meetup2, meetup3);

    }

    @Test
    void test_getById_find_inserted_values_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();
        UserRepository userRepository = new UserRepository();

        Location location = new Location("meetup1", "Linz", "street1", 4020);
        User creator = new User("Oliver", "Nestler", "password", "l@.net", 1);

        Meetup meetup1 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));
        Meetup meetup2 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));
        Meetup meetup3 = new Meetup(creator, location, "Oliver's Meetup", LocalDateTime.of(2005, 10, 22, 0, 0));

        //act
        locationRepository.insert(location);
        userRepository.insert(creator);
        meetupRepository.insert(meetup1);
        meetupRepository.insert(meetup2);
        meetupRepository.insert(meetup3);

        //assert
        assertThat(meetupRepository.getById(1)).usingRecursiveComparison().isEqualTo(meetup1);
        assertThat(meetupRepository.getById(2)).usingRecursiveComparison().isEqualTo(meetup2);
        assertThat(meetupRepository.getById(3)).usingRecursiveComparison().isEqualTo(meetup3);
    }

    @Test
    void test_getById_with_id_not_in_database_ok() {
        //arrange
        MeetupRepository meetupRepository = new MeetupRepository();

        //act

        //assert
        assertThat(meetupRepository.getById(1)).isNull();
    }
}