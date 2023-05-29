package travelagency.nymble.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travelagency.nymble.model.Destination;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for accessing destination-related data from the database.
 */
@Repository
public class DestinationDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor for DestinationDAO.
     *
     * @param jdbcTemplate The JdbcTemplate instance to use for database operations.
     */
    @Autowired
    public DestinationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a destination by its ID from the database.
     *
     * @param id The ID of the destination.
     * @return The Destination object corresponding to the given ID, or null if not found.
     */
    public Destination getDestinationById(int id) {
        String query = "SELECT * FROM destination WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> mapDestination(resultSet), id);
    }

    private Destination mapDestination(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Destination(id, name);
    }
}
