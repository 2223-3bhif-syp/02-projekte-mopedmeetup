package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.Location;
import at.htl.meetup.entity.Meetup;
import at.htl.meetup.entity.Participants;
import at.htl.meetup.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParticipantsRepositoryTest {
    private static final String tableName = "MM_PARTICIPANTS";

    @BeforeEach
    void setUp() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    void tearDown() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void T01_insert_participants_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = new User("Bajtik", "Berg", "1234", "bb@htl.at", 11);
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);

        //act
        userRepository.insert(user);
        userRepository.insert(creator);
        locationRepository.insert(location);
        meetupRepository.insert(meetup);
        participantsRepository.insert(participants);
        output(table).toConsole();

        //assert

        assertThat(table).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1);
    }

    @Test
    void T02_insert_participants_fail() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = null;
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);

        //act
        userRepository.insert(creator);
        locationRepository.insert(location);
        meetupRepository.insert(meetup);
        output(table).toConsole();

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> participantsRepository.insert(participants));
    }

    @Test
    void T03_update_participants_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = new User("Bajtik", "Berg", "1234", "bb@htl.at", 11);
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);
        String newUserLastName = "UgahBugah";

        //act
        userRepository.insert(user);
        userRepository.insert(creator);
        locationRepository.insert(location);
        meetupRepository.insert(meetup);
        participantsRepository.insert(participants);
        participants.getUser().setLastName(newUserLastName);
        participantsRepository.update(participants);

        //assert
        assertThat(table).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1);
    }

    @Test
    void T04_delete_participant_ok() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = new User("Bajtik", "Berg", "1234", "bb@htl.at", 11);
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);

        //act
        userRepository.insert(user);
        userRepository.insert(creator);
        locationRepository.insert(location);
        meetupRepository.insert(meetup);
        participantsRepository.insert(participants);
        output(table).toConsole();
        participantsRepository.delete(Integer.parseInt(participants.getId().toString()));
        table = new Table(Database.getDataSource(), tableName);
        output(table).toConsole();

        //assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void T05_get_all_participants_ok() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = new User("Bajtik", "Berg", "1234", "bb@htl.at", 11);
        User user2 = new User("Said", "Nurceski", "1234", "sn@htl.at", 16);
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        User creator2 = new User("Oliver", "Daxinger", "1234", "od@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Location location2 = new Location("TestMeetup02", "Linz", "Mariastraße 3", 4020);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Meetup meetup2 = new Meetup(creator2, location2, "Test2", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);
        Participants participants2 = new Participants(user2, meetup2);

        //act
        userRepository.insert(user);
        userRepository.insert(user2);
        userRepository.insert(creator);
        userRepository.insert(creator2);
        locationRepository.insert(location);
        locationRepository.insert(location2);
        meetupRepository.insert(meetup);
        meetupRepository.insert(meetup2);
        participantsRepository.insert(participants);
        participantsRepository.insert(participants2);
        output(table).toConsole();

        //assert
        assertThat(table).hasNumberOfRows(2);
    }

    @Test
    void T06_get_participant_by_id_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();
        UserRepository userRepository = new UserRepository();
        MeetupRepository meetupRepository = new MeetupRepository();
        LocationRepository locationRepository = new LocationRepository();

        User user = new User("Bajtik", "Berg", "1234", "bb@htl.at", 11);
        User creator = new User("Linus", "Nestler", "1234", "ln@htl.at", 16);
        Location location = new Location("TestMeetup01", "Leonding", "Limesstraße 6", 4060);
        Meetup meetup = new Meetup(creator, location, "Test", LocalDateTime.of(2023, 4, 29, 0, 0));
        Participants participants = new Participants(user, meetup);

        //act
        userRepository.insert(user);
        userRepository.insert(creator);
        locationRepository.insert(location);
        meetupRepository.insert(meetup);
        participantsRepository.insert(participants);
        output(table).toConsole();

        //assert
        assertEquals(1, Integer.parseInt(participantsRepository.getById(1).getId().toString()));
    }
}