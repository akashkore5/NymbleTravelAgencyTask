package travelagency.nymble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import travelagency.nymble.dao.*;
import travelagency.nymble.model.*;
import travelagency.nymble.service.TravelPackageService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Main application class for the Nymble Travel Agency system.
 */
@SpringBootApplication
@EntityScan("travelagency.nymble.model")
public class NymbleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NymbleApplication.class, args);
		// Obtain the database connection
		try {
			// Obtain the DataSource
			DataSource dataSource = createDataSource();

			// Create an instance of JdbcTemplate using the DataSource
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			// Create instances of the DAO classes and pass the JdbcTemplate
			TravelPackageDAO travelPackageDAO = new TravelPackageDAO(jdbcTemplate);
			DestinationDAO destinationDAO = new DestinationDAO(jdbcTemplate);
			ActivityDAO activityDAO = new ActivityDAO(jdbcTemplate);
			PassengerDAO passengerDAO = new PassengerDAO(jdbcTemplate);

			// Example usage of the travel agency system
			TravelPackageService travelPackageService = new TravelPackageService();
			TravelPackage travelPackage1 = travelPackageDAO.getTravelPackageById(1);
			TravelPackage travelPackage2 = travelPackageDAO.getTravelPackageById(2);

			// Create a travel package
			travelPackageService.createTravelPackage(travelPackage1);
			travelPackageService.createTravelPackage(travelPackage2);

			// Fetch destinations from the database
			Destination destination1 = destinationDAO.getDestinationById(1);
			Destination destination2 = destinationDAO.getDestinationById(2);
			Destination destination3 = destinationDAO.getDestinationById(3);
			Destination destination4 = destinationDAO.getDestinationById(4);
			Destination destination5 = destinationDAO.getDestinationById(5);
			Destination destination6 = destinationDAO.getDestinationById(6);
			Destination destination7 = destinationDAO.getDestinationById(7);

			// Add destinations to travel packages
			travelPackageService.addDestinationToTravelPackage(travelPackage1.getName(), destination1);
			travelPackageService.addDestinationToTravelPackage(travelPackage1.getName(), destination2);
			travelPackageService.addDestinationToTravelPackage(travelPackage1.getName(), destination3);
			travelPackageService.addDestinationToTravelPackage(travelPackage1.getName(), destination4);
			travelPackageService.addDestinationToTravelPackage(travelPackage1.getName(), destination5);
			travelPackageService.addDestinationToTravelPackage(travelPackage2.getName(), destination6);
			travelPackageService.addDestinationToTravelPackage(travelPackage2.getName(), destination7);

			// Fetch activities from the database
			Activity activity1 = activityDAO.getActivityById(1);
			Activity activity2 = activityDAO.getActivityById(2);
			Activity activity3 = activityDAO.getActivityById(3);
			Activity activity4 = activityDAO.getActivityById(4);
			Activity activity5 = activityDAO.getActivityById(5);
			Activity activity6 = activityDAO.getActivityById(6);
			Activity activity7 = activityDAO.getActivityById(7);

			// Add activities to destinations for Package 1
			travelPackageService.addActivityToDestination(travelPackage1.getName(), destination1.getName(), activity1);
			travelPackageService.addActivityToDestination(travelPackage1.getName(), destination2.getName(), activity2);
			travelPackageService.addActivityToDestination(travelPackage1.getName(), destination3.getName(), activity3);
			travelPackageService.addActivityToDestination(travelPackage1.getName(), destination4.getName(), activity4);
			travelPackageService.addActivityToDestination(travelPackage1.getName(), destination5.getName(), activity5);

			// Add activities to destinations for Package 2
			travelPackageService.addActivityToDestination(travelPackage2.getName(), destination6.getName(), activity6);
			travelPackageService.addActivityToDestination(travelPackage2.getName(), destination7.getName(), activity7);

			// Fetch passengers from the database
			Passenger passenger1 = passengerDAO.getPassengerById(1);
			Passenger passenger2 = passengerDAO.getPassengerById(2);
			Passenger passenger3 = passengerDAO.getPassengerById(3);
			Passenger passenger4 = passengerDAO.getPassengerById(4);
			Passenger passenger5 = passengerDAO.getPassengerById(5);

			// Add passengers to the travel package
			travelPackageService.addPassengerToTravelPackage(travelPackage1.getName(), passenger1);
			travelPackageService.addPassengerToTravelPackage(travelPackage1.getName(), passenger2);
			travelPackageService.addPassengerToTravelPackage(travelPackage1.getName(), passenger3);
			travelPackageService.addPassengerToTravelPackage(travelPackage1.getName(), passenger4);
			travelPackageService.addPassengerToTravelPackage(travelPackage1.getName(), passenger5);

			// Sign up passengers for activities
			travelPackageService.signUpPassengerForActivity(passenger1.getPassengerNumber(), activity1.getName(), destination1.getName());
			travelPackageService.signUpPassengerForActivity(passenger2.getPassengerNumber(), activity2.getName(), destination2.getName());
			travelPackageService.signUpPassengerForActivity(passenger3.getPassengerNumber(), activity3.getName(), destination3.getName());
			travelPackageService.signUpPassengerForActivity(passenger4.getPassengerNumber(), activity4.getName(), destination4.getName());
			travelPackageService.signUpPassengerForActivity(passenger5.getPassengerNumber(), activity5.getName(), destination5.getName());

			// Print itinerary for Package 1
			travelPackageService.printItinerary(travelPackage1.getName());

			// Print itinerary for Package 2
			travelPackageService.printItinerary(travelPackage2.getName());

			// Print passenger list for Package 1
			travelPackageService.printPassengerList(travelPackage1.getName());

			// Print passenger list for Package 2
			travelPackageService.printPassengerList(travelPackage2.getName());

			// Print passenger details
			travelPackageService.printPassengerDetails(passenger3.getPassengerNumber());

			// Print available activities
			travelPackageService.printAvailableActivities();

			// Rest of the code...
		} catch (SQLException e) {
			System.out.println("An error occurred while connecting to the database: " + e.getMessage());
		}
	}

	/**
	 * Creates a DataSource for connecting to the database.
	 *
	 * @return DataSource object representing the database connection
	 * @throws SQLException if an error occurs while creating the DataSource
	 */
	@Bean
	private static DataSource createDataSource() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/travelagency");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
