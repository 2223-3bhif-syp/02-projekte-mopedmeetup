package at.htl.meetup.controller;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Test
    void testUser() {
        Table table = new Table(Database.getDataSource(), "MM_USER");
        output(table).toConsole();
    }

}