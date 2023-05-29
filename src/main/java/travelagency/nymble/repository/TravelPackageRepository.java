package travelagency.nymble.repository;

import org.springframework.stereotype.Repository;
import travelagency.nymble.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing and accessing travel packages.
 */
@Repository
public class TravelPackageRepository {
    private List<TravelPackage> travelPackages;

    /**
     * Constructs a TravelPackageRepository object.
     */
    public TravelPackageRepository() {
        this.travelPackages = new ArrayList<>();
    }

    /**
     * Adds a travel package to the repository.
     *
     * @param travelPackage The travel package to add.
     */
    public void addTravelPackage(TravelPackage travelPackage) {
        travelPackages.add(travelPackage);
    }

    /**
     * Retrieves the list of travel packages in the repository.
     *
     * @return The list of travel packages.
     */
    public List<TravelPackage> getTravelPackages() {
        return travelPackages;
    }

    /**
     * Adds a destination to a travel package.
     *
     * @param packageName    The name of the travel package.
     * @param destination    The destination to add.
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
     * @param packageName      The name of the travel package.
     * @param destinationName  The name of the destination.
     * @param activity         The activity to add.
     */
    public void addActivityToDestination(String packageName, String destinationName, Activity activity) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            Destination destination = travelPackage.findDestinationByName(destinationName);
            if (destination != null) {
                destination.addActivity(activity);
                activity.setDestination(destination);
            }
        }
    }

    /**
     * Adds a passenger to a travel package.
     *
     * @param packageName  The name of the travel package.
     * @param passenger    The passenger to add.
     */
    public void addPassengerToTravelPackage(String packageName, Passenger passenger) {
        TravelPackage travelPackage = findTravelPackageByName(packageName);
        if (travelPackage != null) {
            travelPackage.addPassenger(passenger);
        }
    }

    /**
     * Finds a travel package by its name.
     *
     * @param packageName The name of the travel package.
     * @return The found travel package, or null if not found.
     */
    public TravelPackage findTravelPackageByName(String packageName) {
        for (TravelPackage travelPackage : travelPackages) {
            if (travelPackage.getName().equals(packageName)) {
                return travelPackage;
            }
        }
        return null;
    }
}
