package travelagency.nymble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an activity within a travel package.
 */
public class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;
    private List<Passenger> signedUpPassengers;

    /**
     * Constructs an Activity object.
     *
     * @param name        The name of the activity.
     * @param description The description of the activity.
     * @param cost        The cost of the activity.
     * @param capacity    The capacity of the activity.
     * @param destination The destination associated with the activity.
     */
    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.signedUpPassengers = new ArrayList<>();
    }

    /**
     * Gets the name of the activity.
     *
     * @return The name of the activity.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the activity.
     *
     * @return The description of the activity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the cost of the activity.
     *
     * @return The cost of the activity.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the capacity of the activity.
     *
     * @return The capacity of the activity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the destination associated with the activity.
     *
     * @return The destination of the activity.
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * Gets the list of passengers signed up for the activity.
     *
     * @return The list of signed up passengers.
     */
    public List<Passenger> getSignedUpPassengers() {
        return signedUpPassengers;
    }

    /**
     * Adds a passenger to the list of signed up passengers for the activity.
     *
     * @param passenger The passenger to sign up.
     */
    public void signUpPassenger(Passenger passenger) {
        signedUpPassengers.add(passenger);
    }

    /**
     * Sets the capacity of the activity.
     *
     * @param capacity The capacity to set.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Checks if the activity has availability.
     *
     * @return true if there is availability, false otherwise.
     */
    public boolean hasAvailability() {
        return capacity > 0;
    }

    /**
     * Books availability for the activity by reducing the capacity if available.
     */
    public void bookAvailability() {
        if (hasAvailability()) {
            capacity--;
        }
    }

    /**
     * Sets the destination associated with the activity.
     *
     * @param destination The destination to set.
     */
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Prints the details of the activity.
     */
    public void printDetails() {
        System.out.println("Activity: " + name);
        System.out.println("Description: " + description);
        System.out.println("Cost: " + cost);
        System.out.println("Capacity: " + capacity);
        System.out.println("Destination: " + destination.getName());
    }
}
