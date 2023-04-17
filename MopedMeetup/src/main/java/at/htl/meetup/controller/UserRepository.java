package at.htl.meetup.controller;
import  at.htl.meetup.entity.*;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public class UserRepository {
    private DataSource dataSource = Database.getDataSource();
    public void insert(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_USER (U_FIRST_NAME, U_LAST_NAME, U_EMAIL, U_DATE_OF_BIRTH) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            LocalDateTime dateTime = user.getDateOfBirth();
            statement.setTimestamp(4, Timestamp.valueOf(dateTime));


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into MM_LOCATION failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE MM_USER SET U_FIRST_NAME=?, " +
                    "U_LAST_NAME=?, " +
                    "U_EMAIL=?, " +
                    "U_DATE_OF_BIRTH=? " +
                    "WHERE U_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            LocalDateTime dateTime = user.getDateOfBirth();
            statement.setTimestamp(4, Timestamp.valueOf(dateTime));
            statement.setLong(5, user.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM MM_USER WHERE U_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from MM_LOCATION failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_USER";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("U_ID");
                String firstName = result.getString("U_FIRST_NAME");
                String lastName = result.getString("U_LAST_NAME");
                String email = result.getString("U_EMAIL");
                LocalDateTime dateOfBirth = result.getTimestamp("U_DATE_OF_BIRTH").toLocalDateTime();
                userList.add(new User(id, firstName, lastName, email, dateOfBirth));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User getById(long id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_USER WHERE U_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("U_ID"))
                    return new User(result.getLong("U_ID"),
                            result.getString("U_FIRST_NAME"),
                            result.getString("U_LAST_NAME"),
                            result.getString("U_EMAIL"),
                            result.getTimestamp("U_DATE_OF_BIRTH").toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
