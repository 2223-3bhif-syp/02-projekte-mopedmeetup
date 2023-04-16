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
}
