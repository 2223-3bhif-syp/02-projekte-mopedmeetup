package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.*;
import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;
import org.assertj.db.type.Table;

import java.time.LocalDateTime;
import java.util.List;
public class ParticipantsRepositoryTest {
    private static String tableName = "MM_PARTICIPANTS";

    @BeforeEach
    void setUp() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    void tearDown() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void insertTest() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();

        String fName = "Bajtik";
        String lName = "Berg";
        String email = "example@mail.com";
        LocalDateTime dateOfBirth = LocalDateTime.of(2005,9,22,0,0);

        User user1 = new User(fName,lName,email,dateOfBirth);

        String fName2 = "Linus";
        String lName2 = "Nestler";
        String email2 = "example@gmail.com";
        LocalDateTime dob = LocalDateTime.of(2005,7,5,0,0);

        User organisator = new User(fName2, lName2, email2, dob);

        String street = "Limesstraße 8";
        String city = "Leonding";
        int zip = 4060;
        String name = "Meetup";

        Location location = new Location(name, city, street, zip);

        String description = "...";
        LocalDateTime meetupDate = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup = new Meetup(organisator, location, description, meetupDate);

        Participants participants = new Participants(user1, meetup);

        participantsRepository.insert(participants);

        assertEquals(participants.getId(), 1);

        assertThat(table).column("P_U_ID")
                .value().isEqualTo(participants.getUser().getId());
        assertThat(table).column("P_M_ID")
                .value().isEqualTo(participants.getMeetup().getMeetupDate());
    }

    @Test
    void updateTest() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();

        String fName = "Bajtik";
        String lName = "Berg";
        String email = "example@mail.com";
        LocalDateTime dateOfBirth = LocalDateTime.of(2005,9,22,0,0);

        User user1 = new User(fName,lName,email,dateOfBirth);

        String fName2 = "Linus";
        String lName2 = "Nestler";
        String email2 = "example@gmail.com";
        LocalDateTime dob = LocalDateTime.of(2005,7,5,0,0);

        User organisator = new User(fName2, lName2, email2, dob);

        String street = "Limesstraße 8";
        String city = "Leonding";
        int zip = 4060;
        String name = "Meetup";

        Location location = new Location(name, city, street, zip);

        String description = "...";
        LocalDateTime meetupDate = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup = new Meetup(organisator, location, description, meetupDate);

        Participants participants = new Participants(user1, meetup);

        participantsRepository.insert(participants);

        String newUserLastName = "Random";
        participants.getUser().setLastName(newUserLastName);

        participantsRepository.update(participants);

        assertThat(table).column("P_U_ID")
                .value().isEqualTo(participants.getUser().getId());

        assertThat(table).column("P_M_ID")
                .value().isEqualTo(participants.getMeetup().getId());
    }

    @Test
    void deleteTest() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();

        String fName = "Bajtik";
        String lName = "Berg";
        String email = "example@mail.com";
        LocalDateTime dateOfBirth = LocalDateTime.of(2005,9,22,0,0);

        User user1 = new User(fName,lName,email,dateOfBirth);

        String fName2 = "Linus";
        String lName2 = "Nestler";
        String email2 = "example@gmail.com";
        LocalDateTime dob = LocalDateTime.of(2005,7,5,0,0);

        User organisator = new User(fName2, lName2, email2, dob);

        String street = "Limesstraße 8";
        String city = "Leonding";
        int zip = 4060;
        String name = "Meetup";

        Location location = new Location(name, city, street, zip);

        String description = "...";
        LocalDateTime meetupDate = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup = new Meetup(organisator, location, description, meetupDate);

        Participants participants = new Participants(user1, meetup);

        participantsRepository.insert(participants);
        participantsRepository.delete(Integer.parseInt(participants.getId().toString()));

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAllTest() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();

        String fName = "Bajtik";
        String lName = "Berg";
        String email = "example@mail.com";
        LocalDateTime dateOfBirth = LocalDateTime.of(2005,9,22,0,0);

        User user1 = new User(fName,lName,email,dateOfBirth);

        String fName2 = "Linus";
        String lName2 = "Nestler";
        String email2 = "example@gmail.com";
        LocalDateTime dob = LocalDateTime.of(2005,7,5,0,0);

        User organisator1 = new User(fName2, lName2, email2, dob);

        String street = "Limesstraße 8";
        String city = "Leonding";
        int zip = 4060;
        String name = "Meetup";

        Location location = new Location(name, city, street, zip);

        String description = "...";
        LocalDateTime meetupDate = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup = new Meetup(organisator1, location, description, meetupDate);

        Participants participants = new Participants(user1, meetup);

        participantsRepository.insert(participants);

        String fName3 = "Oliver";
        String lName3 = "Daxinger";
        String email3 = "example@mail.com";
        LocalDateTime dateOfBirth3 = LocalDateTime.of(2005,9,22,0,0);

        User user2 = new User(fName3,lName3,email3,dateOfBirth3);

        String fName4 = "Linus";
        String lName4 = "Nestler";
        String email4 = "example@gmail.com";
        LocalDateTime dob4 = LocalDateTime.of(2005,7,5,0,0);

        User organisator2 = new User(fName4, lName4, email4, dob4);

        String street2 = "Hamerlingstraße 8";
        String city2 = "Linz";
        int zip2 = 4020;
        String name2 = "Meetup";

        Location location2 = new Location(name2, city2, street2, zip2);

        String description2 = "...";
        LocalDateTime meetupDate2 = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup2 = new Meetup(organisator2, location2, description2, meetupDate2);

        Participants participants2 = new Participants(user2, meetup2);

        participantsRepository.insert(participants2);

        //Creating list with getAll()
        List<Participants> participantsList = participantsRepository.getAll();

        assertEquals(2, participantsList.size());
    }

    @Test
    void getByIdTest() {
        Table table = new Table(Database.getDataSource(), tableName);
        ParticipantsRepository participantsRepository = new ParticipantsRepository();

        String fName = "Bajtik";
        String lName = "Berg";
        String email = "example@mail.com";
        LocalDateTime dateOfBirth = LocalDateTime.of(2005,9,22,0,0);

        User user1 = new User(fName,lName,email,dateOfBirth);

        String fName2 = "Linus";
        String lName2 = "Nestler";
        String email2 = "example@gmail.com";
        LocalDateTime dob = LocalDateTime.of(2005,7,5,0,0);

        User organisator = new User(fName2, lName2, email2, dob);

        String street = "Limesstraße 8";
        String city = "Leonding";
        int zip = 4060;
        String name = "Meetup";

        Location location = new Location(name, city, street, zip);

        String description = "...";
        LocalDateTime meetupDate = LocalDateTime.of(2023,4,29,0,0);

        Meetup meetup = new Meetup(organisator, location, description, meetupDate);

        Participants participants = new Participants(user1, meetup);

        participantsRepository.insert(participants);

        assertEquals(1, Integer.parseInt(participantsRepository.getById(1).getId().toString()));
    }
}