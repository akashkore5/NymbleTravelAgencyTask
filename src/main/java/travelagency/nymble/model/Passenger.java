package travelagency.nymble.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a passenger in the travel agency system.
 */
public class Passenger {
    private String name;
    private int passengerNumber;
    private String passengerType;
    private double balance;
    private List<Activity> activities;

    /**
     * Constructs a Passenger object.
     *
     * @param name            The name of the passenger.
     * @param passengerNumber The passenger number.
     * @param balance         The balance of the passenger.
     * @param passengerType   The type of the passenger.
     */
    public Passenger(String name, int passengerNumber, double balance, String passengerType) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
        this.passengerType = passengerType;
        this.activities = new ArrayList<>();
    }

    /**
     * Gets the name of the passenger.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the passenger number.
     *
     * @return The passenger number.
     */
    public int getPassengerNumber() {
        return passengerNumber;
    }

    /**
     * Gets the type of the passenger.
     *
     * @return The type of the passenger.
     */
    public String getPassengerType() {
        return passengerType;
    }

    /**
     * Gets the balance of the passenger.
     *
     * @return The balance of the passenger.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the list of activities signed up by the passenger.
     *
     * @return The list of activities.
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Signs up the passenger for an activity.
     *
     * @param activity The activity to sign up for.
     * @return true if the sign-up is successful, false otherwise.
     */
    public boolean signUpForActivity(Activity activity) {
        if (!activity.hasAvailability()) {
            System.out.println("Sorry, the activity is already at full capacity.");
            return false;
        }

        if (passengerType.equals("STANDARD")) {
            if (balance >= activity.getCost()) {
                balance -= activity.getCost();
                activity.setCapacity(activity.getCapacity() - 1);
                activities.add(activity);
                System.out.println(name + " signed up for " + activity.getName() + " at " + activity.getDestination().getName() + ".");
                return true;
            } else {
                System.out.println(name + " does not have sufficient balance to sign up for " + activity.getName() + ".");
                return false;
            }
        } else if (passengerType.equals("GOLD")) {
            double discountedCost = activity.getCost() * 0.9;
            if (balance >= discountedCost) {
                balance -= discountedCost;
                activity.setCapacity(activity.getCapacity() - 1);
                activities.add(activity);
                System.out.println(name + " signed up for " + activity.getName() + " at " + activity.getDestination().getName() + " with a 10% discount.");
                return true;
            } else {
                System.out.println(name + " does not have sufficient balance to sign up for " + activity.getName() + ".");
                return false;
            }
        } else if (passengerType.equals("PREMIUM")) {
            activity.setCapacity(activity.getCapacity() - 1);
            activities.add(activity);
            System.out.println(name + " signed up for " + activity.getName() + " at " + activity.getDestination().getName() + " for free.");
            return true;
        }

        return false;
    }

    /**
     * Prints the details of the passenger.
     */
    public void printDetails() {
        System.out.println("*********************************Passenger Details*****************************************");
        System.out.println("Passenger: " + name);
        System.out.println("Passenger Number: " + passengerNumber);
        System.out.println("Passenger Type: " + passengerType);
        System.out.println("Balance: " + balance);

        if (!activities.isEmpty()) {
            System.out.println("Activities signed up:");
            for (Activity activity : activities) {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Destination: " + activity.getDestination().getName());
                System.out.println("Cost: " + activity.getCost());
            }
        } else {
            System.out.println("No activities signed up yet.");
        }
    }
}
