package at.htl.meetup.controller;

import at.htl.meetup.entity.Location;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {

    private static DataSource dataSource = Database.getDataSource();
    public void insert(Location location) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_LOCATION (L_STREET, L_CITY, L_ZIP, L_NAME) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, location.getStreet());
            statement.setString(2, location.getCity());
            statement.setString(3, String.valueOf(location.getZip()));
            statement.setString(4, location.getName());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    location.setId(keys.getLong(1));
                } else {
                    throw new SQLException("Insert into MM_LOCATION failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Location location) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE MM_LOCATION SET L_STREET=?, " +
                    "L_CITY=?, " +
                    "L_ZIP=?, " +
                    "L_NAME=? " +
                    "WHERE L_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location.getStreet());
            statement.setString(2, location.getCity());
            statement.setString(3, String.valueOf(location.getZip()));
            statement.setString(4, location.getName());
            statement.setLong(5, location.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM MM_LOCATION WHERE L_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Delete from MM_LOCATION failed, no rows affected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getAll() {
        List<Location> locationList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_LOCATION";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("L_ID");
                String name = result.getString("L_NAME");
                String street = result.getString("L_STREET");
                String city = result.getString("L_CITY");
                int zip = result.getInt("L_ZIP");
                locationList.add(new Location(id, name, city, street, zip));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationList;
    }

    public static Location getById(long id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_LOCATION WHERE L_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
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
