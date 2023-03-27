package at.htl.meetup.controller;

import at.htl.meetup.entities.Location;
import at.htl.meetup.entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class LocationRepository {

    /*private DataSource dataSource = Database.getDataSource();
    public void insert(Location location) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO MM_LOCATION (ADRESS, NAME, ) VALUES (?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, location.getAddress());
            statement.setString(2, location.getName());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }


            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    location.setId(keys.getInt(1));
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
            String sql = "UPDATE MM_LOCATION SET ADRESS=?, NAME=? WHERE LOCATION_ID=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, location.getAddress());
            statement.setString(2, location.getName());
            statement.setInt(3, location.getId());


            if (statement.executeUpdate() == 0) {
                throw new SQLException("Update of MM_LOCATION failed, no rows affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM MM_LOCATION WHERE LOCATION_ID=?";

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
                int id = result.getInt("LOCATION_ID");
                String name = result.getString("NAME");
                String address = result.getString("ADRESS");
                locationList.add(new Location(id,address,name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationList;
    }

    public Location getById(int id){
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM MM_LOCATION WHERE LOCATION_ID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                if(id == result.getInt("LOCATION_ID"))
                //return new Location(result.getInt("LOCATION_ID"), result.getString("ADRESS"), result.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
