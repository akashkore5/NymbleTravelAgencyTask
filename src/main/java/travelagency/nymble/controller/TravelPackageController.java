package travelagency.nymble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travelagency.nymble.model.Activity;
import travelagency.nymble.model.Destination;
import travelagency.nymble.model.Passenger;
import travelagency.nymble.model.TravelPackage;
import travelagency.nymble.repository.TravelPackageRepository;
import travelagency.nymble.service.TravelPackageService;

/**
 * Controller class for handling travel package-related API endpoints.
 */
@RestController
@RequestMapping("/api/travel-packages")
public class TravelPackageController {
    private TravelPackageRepository travelPackageRepository;
    private TravelPackageService travelPackageService;

    /**
     * Constructor for TravelPackageController.
     *
     * @param travelPackageRepository Repository for travel packages.
     * @param travelPackageService    Service for managing travel packages.
     */
    @Autowired
    public TravelPackageController(
            TravelPackageRepository travelPackageRepository,
            TravelPackageService travelPackageService
    ) {
        this.travelPackageRepository = travelPackageRepository;
        this.travelPackageService = travelPackageService;
    }

    /**
     * Creates a new travel package.
     *
     * @param travelPackage The travel package to create.
     */
    @PostMapping
    public void createTravelPackage(@RequestBody TravelPackage travelPackage) {
        travelPackageRepository.addTravelPackage(travelPackage);
    }

    /**
     * Adds a destination to a travel package.
     *
     * @param packageName The name of the travel package.
     * @param destination The destination to add.
     */
    @PostMapping("/{packageName}/destinations")
    public void addDestinationToTravelPackage(
            @PathVariable String packageName,
            @RequestBody Destination destination
    ) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByName(packageName);
        if (travelPackage != null) {
            travelPackage.addDestination(destination);
        }
    }

    /**
     * Adds an activity to a destination.
     *
     * @param packageName     The name of the travel package.
     * @param destinationName The name of the destination.
     * @param activity        The activity to add.
     */
    @PostMapping("/{packageName}/destinations/{destinationName}/activities")
    public void addActivityToDestination(
            @PathVariable String packageName,
            @PathVariable String destinationName,
            @RequestBody Activity activity
    ) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByName(packageName);
        if (travelPackage != null) {
            Destination destination = travelPackage.findDestinationByName(destinationName);
            if (destination != null) {
                activity.setDestination(destination);
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
    @PostMapping("/{packageName}/passengers")
    public void addPassengerToTravelPackage(
            @PathVariable String packageName,
            @RequestBody Passenger passenger
    ) {
        TravelPackage travelPackage = travelPackageRepository.findTravelPackageByName(packageName);
        if (travelPackage != null) {
            travelPackage.addPassenger(passenger);
        }
    }

    /**
     * Signs up a passenger for an activity.
     *
     * @param passengerNumber The passenger number.
     * @param activityName    The name of the activity.
     * @param destinationName The name of the destination.
     */
    @PostMapping("/passengers/{passengerNumber}/activities")
    public void signUpPassengerForActivity(
            @PathVariable int passengerNumber,
            @RequestParam String activityName,
            @RequestParam String destinationName
    ) {
        travelPackageService.signUpPassengerForActivity(passengerNumber, activityName, destinationName);
    }

    /**
     * Prints the itinerary for a travel package.
     *
     * @param packageName The name of the travel package.
     */
    @GetMapping("/{packageName}/itinerary")
    public void printItinerary(@PathVariable String packageName) {
        travelPackageService.printItinerary(packageName);
    }

    /**
     * Prints the passenger list for a destination in a travel package.
     *
     * @param packageName    The name of the travel package.
     * @param destinationName The name of the destination.
     */
    @GetMapping("/{packageName}/passenger-list/{destinationName}")
    public void printPassengerList(
            @PathVariable String packageName,
            @PathVariable String destinationName
    ) {
        travelPackageService.printPassengerList(packageName);
    }

    /**
     * Prints the details of a passenger.
     *
     * @param passengerNumber The passenger number.
     */
    @GetMapping("/passengers/{passengerNumber}")
    public void printPassengerDetails(@PathVariable int passengerNumber) {
        travelPackageService.printPassengerDetails(passengerNumber);
    }

    /**
     * Prints the available activities.
     */
    @GetMapping("/available-activities")
    public void printAvailableActivities() {
        travelPackageService.printAvailableActivities();
    }
}
