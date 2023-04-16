package at.htl.meetup.controller;

import at.htl.meetup.entity.Meetup;
import at.htl.meetup.entity.Participants;
import at.htl.meetup.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParticipantsRepository {
    private DataSource dataSource = Database.getDataSource();
    public void insert(Participants participants) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_PARTICIPANTS (P_U_ID, P_M_ID) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, participants.getUser().getId());
            statement.setLong(2, participants.getMeetup().getId());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_PARTICIPANTS failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    participants.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into MM_PARTICIPANTS failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Participants participants) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE MM_PARTICIPANTS SET P_U_ID=?, " +
                    "P_M_ID=?, " +
                    "WHERE P_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, participants.getUser().getId());
            statement.setLong(2, participants.getMeetup().getId());
            statement.setLong(4, participants.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_PARTICIPANTS failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM MM_PARTICIPANTS WHERE P_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from MM_PARTICIPANTS failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Participants> getAll() {
        List<Participants> participantsList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_PARTICIPANTS";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("P_ID");
                Long userId = result.getLong("P_U_ID");
                Long meetupId = result.getLong("P_M_ID");
                MeetupRepository meetupRepository = new MeetupRepository();
                UserRepository userRepository = new UserRepository();

                User user = userRepository.getById(userId);
                Meetup meetup = meetupRepository.getById(meetupId);

                participantsList.add(new Participants(id, user, meetup));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participantsList;
    }

    public Participants getById(long id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_PARTICIPANTS WHERE P_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getLong("P_ID")){
                    Long userId = result.getLong("P_U_ID");
                    Long meetupId = result.getLong("P_M_ID");
                    MeetupRepository meetupRepository = new MeetupRepository();
                    UserRepository userRepository = new UserRepository();

                    User user = userRepository.getById(userId);
                    Meetup meetup = meetupRepository.getById(meetupId);

                    return new Participants(id, user, meetup);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
