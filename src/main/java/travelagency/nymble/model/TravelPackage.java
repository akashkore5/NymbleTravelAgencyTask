package travelagency.nymble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a travel package offered by the travel agency.
 */
public class TravelPackage {
    private int id;
    private String name;
    private int passengerCapacity;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    /**
     * Constructs a TravelPackage object.
     *
     * @param id                The ID of the travel package.
     * @param name              The name of the travel package.
     * @param passengerCapacity The passenger capacity of the travel package.
     */
    public TravelPackage(int id, String name, int passengerCapacity) {
        this.id = id;
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    /**
     * Gets the ID of the travel package.
     *
     * @return The ID of the travel package.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the travel package.
     *
     * @param id The ID of the travel package.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the travel package.
     *
     * @return The name of the travel package.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the passenger capacity of the travel package.
     *
     * @return The passenger capacity of the travel package.
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * Gets the list of destinations included in the travel package.
     *
     * @return The list of destinations.
     */
    public List<Destination> getDestinations() {
        return destinations;
    }

    /**
     * Gets the list of passengers booked for the travel package.
     *
     * @return The list of passengers.
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Adds a destination to the travel package.
     *
     * @param destination The destination to add.
     */
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    /**
     * Adds a passenger to the travel package.
     *
     * @param passenger The passenger to add.
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * Finds a destination in the travel package by its name.
     *
     * @param destinationName The name of the destination.
     * @return The found destination, or null if not found.
     */
    public Destination findDestinationByName(String destinationName) {
        for (Destination destination : destinations) {
            if (destination.getName().equals(destinationName)) {
                return destination;
            }
        }
        return null;
    }

    /**
     * Finds a passenger in the travel package by their passenger number.
     *
     * @param passengerNumber The passenger number.
     * @return The found passenger, or null if not found.
     */
    public Passenger findPassengerByNumber(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) {
                return passenger;
            }
        }
        return null;
    }
}
