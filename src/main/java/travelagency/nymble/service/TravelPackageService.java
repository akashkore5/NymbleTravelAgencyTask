package travelagency.nymble.service;

import org.springframework.stereotype.Service;
import travelagency.nymble.model.Activity;
import travelagency.nymble.model.Destination;
import travelagency.nymble.model.Passenger;
import travelagency.nymble.model.TravelPackage;
import travelagency.nymble.repository.TravelPackageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing travel packages and providing related functionalities.
 */
@Service
public class TravelPackageService {
    private List<TravelPackage> travelPackages;
    private List<Passenger> passengers;

    /**
     * Constructs a TravelPackageService object.
     */
    public TravelPackageService() {
        this.travelPackages = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    /**
     * Creates a new travel package.
     *
     * @param travelPackage The travel package to create.
     */
    public void createTravelPackage(TravelPackage travelPackage) {
        travelPackages.add(travelPackage);
    }

    /**
     * Retrieves the list of travel packages.
     *
     * @return The list of travel packages.
     */
    public List<TravelPackage> getTravelPackages() {
        return travelPackages;
    }

    /**
     * Adds a destination to a travel package.
     *
     * @param packageName The name of the travel package.
     * @param destination The destination to add.
     */
    public void addDestinationToTravelPackage(String packageName, Destination destination) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            travelPackage.addDestination(destination);
        }
    }

    /**
     * Adds an activity to a destination in a travel package.
     *
     * @param packageName     The name of the travel package.
     * @param destinationName The name of the destination.
     * @param activity        The activity to add.
     */
    public void addActivityToDestination(String packageName, String destinationName, Activity activity) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            Destination destination = travelPackage.findDestinationByName(destinationName);
            if (destination != null) {
                destination.addActivity(activity);
            }
        }
    }

    /**
     * Adds a passenger to a travel package.
     *
     * @param packageName The name of the travel package.
     * @param passenger   The passenger to add.
     */
    public void addPassengerToTravelPackage(String packageName, Passenger passenger) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            travelPackage.addPassenger(passenger);
            passengers.add(passenger);
        }
    }

    /**
     * Prints the itinerary of a travel package.
     *
     * @param packageName The name of the travel package.
     */
    public void printItinerary(String packageName) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            System.out.println("*********************************Package Itinerary*****************************************");
            System.out.println("Travel Package: " + travelPackage.getName());
            for (Destination destination : travelPackage.getDestinations()) {
                System.out.println("Destination: " + destination.getName());
                destination.printActivities();
            }
        }
    }

    /**
     * Prints the passenger list of a travel package.
     *
     * @param packageName The name of the travel package.
     */
    public void printPassengerList(String packageName) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            System.out.println("*********************************Passenger List*****************************************");
            System.out.println("Travel Package: " + travelPackage.getName());
            System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
            System.out.println("Number of Passengers Enrolled: " + travelPackage.getPassengers().size());
            System.out.println("Passenger List:");
            for (Passenger passenger : travelPackage.getPassengers()) {
                System.out.println("- Name: " + passenger.getName() + ", Passenger Number: " + passenger.getPassengerNumber());
            }
        }
    }

    /**
     * Prints the details of a passenger with the given passenger number.
     *
     * @param passengerNumber The passenger number.
     */
    public void printPassengerDetails(int passengerNumber) {
        for (TravelPackage travelPackage : travelPackages) {
            Passenger passenger = travelPackage.findPassengerByNumber(passengerNumber);
            if (passenger != null) {
                passenger.printDetails();
                return; // Exit the method after printing the details
            }
        }
        System.out.println("Passenger with number " + passengerNumber + " not found.");
    }

    /**
     * Prints the list of available activities.
     */
    public void printAvailableActivities() {
        List<Activity> availableActivities = getAvailableActivities();

        if (availableActivities.isEmpty()) {
            System.out.println("No activities available at the moment.");
        } else {
            System.out.println("*********************************Available Activities*****************************************");
            for (Activity activity : availableActivities) {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Destination: " + activity.getDestination().getName());
                System.out.println("Capacity: " + activity.getCapacity());
                System.out.println("-------------------------");
            }
        }
    }


    private TravelPackage findTravelPackageByName(String packageName) {
        for (TravelPackage travelPackage : travelPackages) {
            if (travelPackage.getName().equals(packageName)) {
                return travelPackage;
            }
        }
        return null;
    }

    private Destination findDestinationByName(String destinationName) {
        for (TravelPackage travelPackage : travelPackages) {
            Destination destination = travelPackage.findDestinationByName(destinationName);
            if (destination != null) {
                return destination;
            }
        }
        return null;
    }

    private Activity findActivityByNameAndDestination(String activityName, Destination destination) {
        for (Activity activity : destination.getActivities()) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    private List<Activity> getAvailableActivities() {
        List<Activity> availableActivities = new ArrayList<>();
        for (TravelPackage travelPackage : travelPackages) {
            for (Destination destination : travelPackage.getDestinations()) {
                for (Activity activity : destination.getActivities()) {
                    if (activity.hasAvailability()) {
                        availableActivities.add(activity);
                    }
                }
            }
        }
        return availableActivities;
    }

    private Passenger findPassengerByNumber(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) {
                return passenger;
            }
        }
        return null;
    }
    
    private Passenger findPassengerByNames(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) {
                return passenger;
            }
        }
        return null;
    }

    /**
     * Signs up a passenger for an activity.
     *
     * @param passengerNumber The passenger number.
     * @param activityName    The name of the activity.
     * @param destinationName The name of the destination.
     */
    public void signUpPassengerForActivity(int passengerNumber, String activityName, String destinationName) {
        Passenger passenger = findPassengerByNumber(passengerNumber);
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger with number " + passengerNumber + " not found.");
        }

        Destination destination = findDestinationByName(destinationName);
        if (destination == null) {
            throw new IllegalArgumentException("Destination with name " + destinationName + " not found.");
        }

        Activity activity = findActivityByNameAndDestination(activityName, destination);
        if (activity == null) {
            throw new IllegalArgumentException("Activity with name " + activityName + " not found at destination " + destinationName + ".");
        }

        if (!activity.hasAvailability()) {
            throw new IllegalStateException("Activity " + activity.getName() + " at destination " + destination.getName() + " is already fully booked.");
        }

        if (passenger.signUpForActivity(activity))
            activity.signUpPassenger(passenger);
    }
}
