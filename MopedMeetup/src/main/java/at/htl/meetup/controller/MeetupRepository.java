package at.htl.meetup.controller;

import at.htl.meetup.entity.Location;
import at.htl.meetup.entity.Meetup;
import at.htl.meetup.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetupRepository {
    private DataSource dataSource = Database.getDataSource();
    public void insert(Meetup meetup) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_MEETUP (M_DESCRIPTION, M_U_ID, M_L_ID, M_MEETUP_DATE) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, meetup.getDescription());
            statement.setLong(2, Long.parseLong(String.valueOf(meetup.getCreator().getId())));
            statement.setLong(3, Long.parseLong(String.valueOf(meetup.getLocation().getId())));
            statement.setTimestamp(4, Timestamp.valueOf(meetup.getMeetupDate()));

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_MEETUP failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    meetup.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into MM_MEETUP failed, no ID obtained");
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
            statement.setTimestamp(2, Timestamp.valueOf(meetup.getMeetupDate()));
            statement.setLong(3, Long.valueOf(meetup.getCreator().getId()));
            statement.setLong(4, Long.valueOf(meetup.getLocation().getId()));
            statement.setLong(5, meetup.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_MEETUP failed, no rows affected");
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
                throw new SQLException("Delete from MM_MEETUP failed, no rows affected");
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
                LocalDateTime meetupDate = result.getTimestamp("M_MEETUP_DATE").toLocalDateTime();
                Long creatorId = result.getLong("M_U_ID");
                Long locationId = result.getLong("M_L_ID");
                UserRepository userRepository = new UserRepository();
                User creator = userRepository.getById(creatorId);
                LocationRepository locationRepository = new LocationRepository();
                Location location = locationRepository.getById(locationId);


                meetupList.add(new Meetup(id, creator, location, description, meetupDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meetupList;
    }

    public Meetup getById(long id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_MEETUP WHERE M_L_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("M_L_ID")){
                    String description = result.getString("M_DESCRIPTION");
                    LocalDateTime meetupDate = result.getTimestamp("M_MEETUP_DATE").toLocalDateTime();
                    Long creatorId = result.getLong("M_U_ID");
                    Long locationId = result.getLong("M_L_ID");
                    UserRepository userRepository = new UserRepository();
                    User creator = userRepository.getById(creatorId);
                    LocationRepository locationRepository = new LocationRepository();
                    Location location = locationRepository.getById(locationId);

                    return new Meetup(id, creator, location, description, meetupDate);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
