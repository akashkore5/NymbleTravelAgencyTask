package travelagency.nymble.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travelagency.nymble.model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for accessing passenger-related data from the database.
 */
@Repository
public class PassengerDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor for PassengerDAO.
     *
     * @param jdbcTemplate The JdbcTemplate instance to use for database operations.
     */
    @Autowired
    public PassengerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a passenger by their ID from the database.
     *
     * @param id The ID of the passenger.
     * @return The Passenger object corresponding to the given ID, or null if not found.
     */
    public Passenger getPassengerById(int id) {
        String query = "SELECT * FROM passenger WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> mapPassenger(resultSet), id);
    }

    private Passenger mapPassenger(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int passengerNumber = resultSet.getInt("passenger_number");
        double balance = resultSet.getDouble("balance");
        String passengerType = resultSet.getString("passenger_type");
        return new Passenger(name, passengerNumber, balance, passengerType);
    }
}
