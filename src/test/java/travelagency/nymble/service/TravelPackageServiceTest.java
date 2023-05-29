package travelagency.nymble.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import travelagency.nymble.model.Activity;
import travelagency.nymble.model.Destination;
import travelagency.nymble.model.Passenger;
import travelagency.nymble.model.TravelPackage;

import static org.junit.jupiter.api.Assertions.*;

public class TravelPackageServiceTest {

    private TravelPackageService travelPackageService;

    @BeforeEach
    public void setup() {
        travelPackageService = new TravelPackageService();
    }

    @Test
    public void createTravelPackage_shouldAddTravelPackageToList() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);

        // Act
        travelPackageService.createTravelPackage(travelPackage);

        // Assert
        assertEquals(1, travelPackageService.getTravelPackages().size());
        assertEquals(travelPackage, travelPackageService.getTravelPackages().get(0));
    }

    @Test
    public void addDestinationToTravelPackage_shouldAddDestinationToTravelPackage() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);

        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");

        // Act
        travelPackageService.addDestinationToTravelPackage(travelPackage.getName(), destination);

        // Assert
        assertEquals(1, travelPackage.getDestinations().size());
        assertEquals(destination, travelPackage.getDestinations().get(0));
    }

    @Test
    public void addActivityToDestination_shouldAddActivityToDestination() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);

        // Act
        travelPackageService.addActivityToDestination(travelPackage.getName(), destination.getName(), activity);

        // Assert
        assertEquals(1, destination.getActivities().size());
        assertEquals(activity, destination.getActivities().get(0));
    }

    @Test
    public void addPassengerToTravelPackage_shouldAddPassengerToTravelPackageAndPassengerList() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Passenger passenger = new Passenger("John Smith", 1, 500, "STANDARD");

        // Act
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Assert
        assertEquals(1, travelPackage.getPassengers().size());
        assertEquals(passenger, travelPackage.getPassengers().get(0));
    }

    @Test
    public void signUpPassengerForActivity_shouldSignUpPassengerForActivityAndBookAvailability() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("John Smith", 1, 500, "STANDARD");
//        travelPackage.addPassenger(passenger);
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act
        travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), activity.getName(), destination.getName());

        // Assert
        assertEquals(1, activity.getSignedUpPassengers().size());
        assertEquals(passenger, activity.getSignedUpPassengers().get(0));
        assertEquals(9, activity.getCapacity());
    }
    @Test
    public void signUpStandardPassengerForActivity_shouldDeductActivityCostFromBalance() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("John Smith", 1, 500, "STANDARD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act
        travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), activity.getName(), destination.getName());

        // Assert
        assertEquals(1, activity.getSignedUpPassengers().size());
        assertEquals(passenger, activity.getSignedUpPassengers().get(0));
        assertEquals(9, activity.getCapacity());
        assertEquals(450, passenger.getBalance()); // Activity cost deducted from the standard passenger's balance
    }

    @Test
    public void signUpGoldPassengerForActivity_shouldDeductDiscountedActivityCostFromBalance() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("Emily Johnson", 2, 1000, "GOLD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act
        travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), activity.getName(), destination.getName());

        // Assert
        assertEquals(1, activity.getSignedUpPassengers().size());
        assertEquals(passenger, activity.getSignedUpPassengers().get(0));
        assertEquals(9, activity.getCapacity());
        assertEquals(955, passenger.getBalance()); // Discounted activity cost deducted from the gold passenger's balance
    }

    @Test
    public void signUpPassengerWithInsufficientBalanceForActivity_shouldNotSignUpPassenger() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 500, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("Michael Davis", 3, 200, "STANDARD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act
        travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), activity.getName(), destination.getName());

        // Assert
        assertEquals(0, activity.getSignedUpPassengers().size()); // Passenger should not be signed up for the activity
        assertEquals(10, activity.getCapacity()); // Capacity should remain unchanged
        assertEquals(200, passenger.getBalance()); // Balance should remain unchanged as it's insufficient
    }

    @Test
    public void signUpPassengerForActivityWithReachedCapacity_shouldNotSignUpPassenger() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 2, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger1 = new Passenger("John Smith", 1, 500, "STANDARD");
        Passenger passenger2 = new Passenger("Emily Johnson", 2, 1000, "GOLD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger1);
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger2);

        // Act
        travelPackageService.signUpPassengerForActivity(passenger1.getPassengerNumber(), activity.getName(), destination.getName());
        travelPackageService.signUpPassengerForActivity(passenger2.getPassengerNumber(), activity.getName(), destination.getName());
//        travelPackageService.signUpPassengerForActivity(passenger2.getPassengerNumber(), activity.getName(), destination.getName()); // Attempt to sign up third passenger

        // Assert
        assertEquals(2, activity.getSignedUpPassengers().size()); // Only two passengers should be signed up
        assertEquals(0, activity.getCapacity()); // Capacity should reach 0 and not allow more sign-ups
        assertEquals(450, passenger1.getBalance()); // Activity cost deducted from the standard passenger's balance
        assertEquals(955, passenger2.getBalance()); // Discounted activity cost deducted from the gold passenger's balance
    }

    @Test
    public void signUpPassengerForActivity_withInvalidPassengerNumber_shouldThrowIllegalArgumentException() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            travelPackageService.signUpPassengerForActivity(10, activity.getName(), destination.getName());
        });

        assertEquals("Passenger with number 10 not found.", exception.getMessage());
    }

    @Test
    public void signUpPassengerForActivity_withInvalidActivityName_shouldThrowIllegalArgumentException() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("John Smith", 1, 500, "STANDARD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), "Scuba Diving", destination.getName());
        });

        assertEquals("Activity with name Scuba Diving not found at destination Paris.", exception.getMessage());
    }

    @Test
    public void signUpPassengerForActivity_withInvalidDestinationName_shouldThrowIllegalArgumentException() {
        // Arrange
        TravelPackage travelPackage = new TravelPackage(1, "Package 1", 20);
        travelPackageService.createTravelPackage(travelPackage);
        Destination destination = new Destination(1, "Paris");
        travelPackageService.addDestinationToTravelPackage("Package 1", destination);
        Activity activity = new Activity("Eiffel Tower Tour", "Visit the iconic Eiffel Tower", 50, 10, destination);
        travelPackage.addDestination(destination);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("John Smith", 1, 500, "STANDARD");
        travelPackageService.addPassengerToTravelPackage(travelPackage.getName(), passenger);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            travelPackageService.signUpPassengerForActivity(passenger.getPassengerNumber(), activity.getName(), "Goa");
        });

        assertEquals("Destination with name Goa not found.", exception.getMessage());
    }

}

