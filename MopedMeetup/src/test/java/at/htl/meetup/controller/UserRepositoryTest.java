package at.htl.meetup.controller;

import at.htl.meetup.database.SqlRunner;
import at.htl.meetup.entity.User;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    private static final String tableName = "MM_USER";
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
        UserRepository userRepository = new UserRepository();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        userRepository.insert(user);

        String firstName2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(firstName2, lastName2, password2, email2, 132);
        userRepository.insert(user2);


        assertEquals(user.getId(), 1);

        assertThat(table).column("U_ID")
                .value().isEqualTo(user .getId());
        assertThat(table).column("U_FIRST_NAME")
                .value().isEqualTo(firstName);
        assertThat(table).column("U_LAST_NAME")
                .value().isEqualTo(lastName);
        assertThat(table).column("U_PASSWORD")
                .value().isEqualTo(password);
        assertThat(table).column("U_EMAIL")
                .value().isEqualTo(email);
        assertThat(table).column("U_AGE")
                .value().isEqualTo(13);
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);
        UserRepository userRepository = new UserRepository();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        userRepository.insert(user);

        String newFirstName = "UgahBugah";
        user.setFirstName(newFirstName);

        userRepository.update(user);

        assertEquals(user.getId(), 1);

        assertThat(table).column("U_ID")
                .value().isEqualTo(user .getId());
        assertThat(table).column("U_FIRST_NAME")
                .value().isEqualTo(newFirstName);
        assertThat(table).column("U_LAST_NAME")
                .value().isEqualTo(user.getLastName());
        assertThat(table).column("U_PASSWORD")
                .value().isEqualTo(password);
        assertThat(table).column("U_EMAIL")
                .value().isEqualTo(user.getEmail());
        assertThat(table).column("U_AGE")
                .value().isEqualTo(13);
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();

        userRepository.insert(user);
        userRepository.delete(Integer.parseInt(user.getId().toString()));

        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void getAll() {
        Table table = new Table(Database.getDataSource(), tableName);
        output(table).toConsole();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();
        userRepository.insert(user);

        String name2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(name2, lastName2, password2, email2, 132);
        userRepository.insert(user2);

        String firstName3 = "Bajtik";
        String lastName3 = "Berg";
        String email3 = "baktik@htl.at";
        String password3= "1234";

        User user3 = new User(firstName3, lastName3, password3, email3, 133);
        userRepository.insert(user3);

        List<User> userList = userRepository.getAll();

        assertEquals(3, userList.size());
        output(table).toConsole();
    }

    @Test
    void getById() {
        Table table = new Table(Database.getDataSource(), tableName);
        output(table).toConsole();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();
        userRepository.insert(user);

        String name2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(name2, lastName2, password2, email2, 132);
        userRepository.insert(user2);

        String firstName3 = "Bajtik";
        String lastName3 = "Berg";
        String email3 = "baktik@htl.at";
        String password3= "1234";
        User user3 = new User(firstName3, lastName3, password3, email3, 133);
        userRepository.insert(user3);

        assertEquals(1, Integer.parseInt(userRepository.getById(1).getId().toString()));
        assertEquals(2, Integer.parseInt(userRepository.getById(2).getId().toString()));
        assertEquals(3, Integer.parseInt(userRepository.getById(3).getId().toString()));
        output(table).toConsole();

    }
}