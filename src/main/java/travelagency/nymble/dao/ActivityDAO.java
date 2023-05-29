package travelagency.nymble.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travelagency.nymble.model.Activity;
import travelagency.nymble.model.Destination;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for accessing activity-related data from the database.
 */
@Repository
public class ActivityDAO {
    private JdbcTemplate jdbcTemplate;

    private DestinationDAO destinationDAO;

    /**
     * Constructor for ActivityDAO.
     *
     * @param jdbcTemplate The JdbcTemplate instance to use for database operations.
     */
    @Autowired
    public ActivityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.destinationDAO = new DestinationDAO(jdbcTemplate);
    }

    /**
     * Retrieves an activity by its ID from the database.
     *
     * @param id The ID of the activity.
     * @return The Activity object corresponding to the given ID, or null if not found.
     */
    public Activity getActivityById(int id) {
        String query = "SELECT * FROM activity WHERE id = ?";
        return jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> mapActivity(resultSet), id);
    }

    private Activity mapActivity(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double cost = resultSet.getDouble("cost");
        int capacity = resultSet.getInt("capacity");
        int destinationId = resultSet.getInt("destination_id");
        Destination destination = destinationDAO.getDestinationById(destinationId);
        return new Activity(name, description, cost, capacity, destination);
    }
}
