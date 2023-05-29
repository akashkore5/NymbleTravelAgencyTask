package travelagency.nymble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a travel destination.
 */
public class Destination {
    private int id;
    private String name;
    private List<Activity> activities;

    /**
     * Constructs a Destination object.
     *
     * @param id   The ID of the destination.
     * @param name The name of the destination.
     */
    public Destination(int id, String name) {
        this.id = id;
        this.name = name;
        this.activities = new ArrayList<>();
    }

    /**
     * Gets the ID of the destination.
     *
     * @return The ID of the destination.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the destination.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the destination.
     *
     * @return The name of the destination.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of activities available at the destination.
     *
     * @return The list of activities.
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Adds an activity to the destination.
     *
     * @param activity The activity to add.
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * Prints the activities available at the destination.
     */
    public void printActivities() {
        System.out.println("Activities available at " + name + ":");
        for (Activity activity : activities) {
            System.out.println("Name: " + activity.getName());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Cost: " + activity.getCost());
            System.out.println("Capacity: " + activity.getCapacity());
            System.out.println("-------------------------");
        }
    }

    /**
     * Checks if the destination has any available activities.
     *
     * @return true if there are available activities, false otherwise.
     */
    public boolean hasAvailableActivities() {
        for (Activity activity : activities) {
            if (activity.hasAvailability()) {
                return true;
            }
        }
        return false;
    }
}
