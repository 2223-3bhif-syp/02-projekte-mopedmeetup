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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
    void T01_insert_user_check_database_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        UserRepository userRepository = new UserRepository();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        String firstName2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(firstName2, lastName2, password2, email2, 12);

        //act
        userRepository.insert(user);
        userRepository.insert(user2);

        //assert
        assertThat(table).row(0)
                        .value().isEqualTo(user.getId())
                        .value().isEqualTo(firstName)
                        .value().isEqualTo(lastName)
                        .value().isEqualTo(password)
                        .value().isEqualTo(email)
                        .value().isEqualTo(13);

        assertThat(table).row(1)
                        .value().isEqualTo(user2.getId())
                        .value().isEqualTo(firstName2)
                        .value().isEqualTo(lastName2)
                        .value().isEqualTo(password2)
                        .value().isEqualTo(email2)
                        .value().isEqualTo(12);
    }

    @Test
    void T02_insert_user_null_ok(){
        //arrange
        UserRepository userRepository = new UserRepository();

        //act

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userRepository.insert(null));
    }
    @Test
    void T03_update_user_check_database_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        UserRepository userRepository = new UserRepository();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        String newFirstName = "UgahBugah";

        //act
        userRepository.insert(user);
        user.setFirstName(newFirstName);
        userRepository.update(user);

        //assert
        assertThat(table).row(0)
                        .value().isEqualTo(user.getId())
                        .value().isEqualTo(newFirstName)
                        .value().isEqualTo(lastName)
                        .value().isEqualTo(password)
                        .value().isEqualTo(email)
                        .value().isEqualTo(13);
    }

    @Test
    void T04_update_null_ok(){
        //arrange
        UserRepository userRepository = new UserRepository();

        //act

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userRepository.update(null));
    }
    @Test
    void T05_delete_user_from_database_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();

        //act
        userRepository.insert(user);
        userRepository.delete(Integer.parseInt(user.getId().toString()));

        //assert
        assertThat(table).hasNumberOfRows(0);
    }

    @Test
    void T06_delete_user_illegal_id_ok(){
        //arrange
        UserRepository userRepository = new UserRepository();

        //act

        //assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userRepository.delete(-1));
    }

    @Test
    void T07_getAll_list_contains_inserted_values_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        output(table).toConsole();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();

        String name2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(name2, lastName2, password2, email2, 12);

        String firstName3 = "Bajtik";
        String lastName3 = "Berg";
        String email3 = "baktik@htl.at";
        String password3= "1234";

        User user3 = new User(firstName3, lastName3, password3, email3, 11);

        //act
        userRepository.insert(user);
        userRepository.insert(user2);
        userRepository.insert(user3);
        List<User> userList = userRepository.getAll();
        table = new Table(Database.getDataSource(), tableName);

        //assert
        assertEquals(3, userList.size());

        assertThat(table).row(0)
                .value().isEqualTo(user.getId())
                .value().isEqualTo(firstName)
                .value().isEqualTo(lastName)
                .value().isEqualTo(password)
                .value().isEqualTo(email)
                .value().isEqualTo(13);

        assertThat(table).row(1)
                .value().isEqualTo(user2.getId())
                .value().isEqualTo(name2)
                .value().isEqualTo(lastName2)
                .value().isEqualTo(password2)
                .value().isEqualTo(email2)
                .value().isEqualTo(12);

        assertThat(table).row(2)
                .value().isEqualTo(user3.getId())
                .value().isEqualTo(firstName3)
                .value().isEqualTo(lastName3)
                .value().isEqualTo(password3)
                .value().isEqualTo(email3)
                .value().isEqualTo(11);
    }

    @Test
    void T08_getById_find_inserted_values_ok() {
        //arrange
        Table table = new Table(Database.getDataSource(), tableName);
        output(table).toConsole();

        String firstName = "Oliver";
        String lastName = "Daxinger";
        String email = "oliver@htl.at";
        String password = "1234";

        User user = new User(firstName, lastName, password, email, 13);

        UserRepository userRepository = new UserRepository();

        String name2 = "Linus";
        String lastName2 = "Nestler";
        String email2 = "linus@htl.at";
        String password2 = "1234";

        User user2 = new User(name2, lastName2, password2, email2, 132);

        String firstName3 = "Bajtik";
        String lastName3 = "Berg";
        String email3 = "baktik@htl.at";
        String password3= "1234";
        User user3 = new User(firstName3, lastName3, password3, email3, 133);

        //act
        userRepository.insert(user);
        userRepository.insert(user2);
        userRepository.insert(user3);
        long id1 = userRepository.getById(1).getId();
        long id2 = userRepository.getById(2).getId();
        long id3 = userRepository.getById(3).getId();
        table = new Table(Database.getDataSource(), tableName);

        //assert
        assertThat(table).row(0).value().isEqualTo(id1);
        assertThat(table).row(1).value().isEqualTo(id2);
        assertThat(table).row(2).value().isEqualTo(id3);
        output(table).toConsole();
    }
}