package travelagency.nymble.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travelagency.nymble.model.TravelPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for accessing travel package-related data from the database.
 */
@Repository
public class TravelPackageDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor for TravelPackageDAO.
     *
     * @param jdbcTemplate The JdbcTemplate instance to use for database operations.
     */
    @Autowired
    public TravelPackageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a travel package by its ID from the database.
     *
     * @param id The ID of the travel package.
     * @return The TravelPackage object corresponding to the given ID, or null if not found.
     */
    public TravelPackage getTravelPackageById(int id) {
        String query = "SELECT * FROM travel_package WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> mapTravelPackage(resultSet), id);
    }

    private TravelPackage mapTravelPackage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int passengerCapacity = resultSet.getInt("passenger_capacity");
        return new TravelPackage(id, name, passengerCapacity);
    }
}
