package at.htl.meetup.controller;

import at.htl.meetup.entities.Location;
import at.htl.meetup.entities.Meetup;
import at.htl.meetup.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetupRepository {
    private DataSource dataSource = Database.getDataSource();
    public void insert(Meetup meetup) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_MEETUP (M_DESCRIPTION, M_MEETUP_DATE, M_U_ID, M_L_ID) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, meetup.getDescription());
            statement.setString(2, String.valueOf(meetup.getMeetupDate()));
            statement.setString(3, String.valueOf(meetup.getCreator()));
            statement.setString(4, String.valueOf(meetup.getLocation()));


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    meetup.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into MM_LOCATION failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Meetup meetup) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE MM_MEETUP SET M_DESCRIPTION=?, " +
                    "M_MEETUP_DATE=?, " +
                    "M_U_ID=?, " +
                    "M_L_ID=? " +
                    "WHERE M_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, meetup.getDescription());
            statement.setString(2, String.valueOf(meetup.getMeetupDate()));
            statement.setString(3, String.valueOf(meetup.getCreator()));
            statement.setString(4, String.valueOf(meetup.getLocation()));
            statement.setLong(5, meetup.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM MM_MEETUP WHERE M_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from MM_LOCATION failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Meetup> getAll() {
        List<Meetup> meetupList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sqlM = "SELECT * FROM MM_MEETUP";
            PreparedStatement statement = connection.prepareStatement(sqlM);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("M_ID");
                String description = result.getString("M_DESCRIPTION");
                Date meetupDate = result.getDate("M_MEETUP_DATE");
                User creator = new User();


                   
                meetupList.add(new Meetup(id, description, meetupDate, creatorId, locationId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meetupList;
    }

    public Location getById(int id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_LOCATION WHERE L_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("L_ID"))
                    return new Location(result.getLong("L_ID"),
                            result.getString("L_NAME"),
                            result.getString("L_CITY"),
                            result.getString("L_STREET"),
                            result.getInt("L_ZIP"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
