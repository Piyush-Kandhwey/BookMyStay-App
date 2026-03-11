import java.io.*;
import java.util.*;

// Reservation class (Serializable for persistence)
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return "ReservationID: " + reservationId +
                ", Guest: " + guestName +
                ", RoomType: " + roomType;
    }
}

// System state containing inventory and booking history
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<Reservation> reservations;

    public SystemState(Map<String, Integer> inventory, List<Reservation> reservations) {
        this.inventory = inventory;
        this.reservations = reservations;
    }
}

// Persistence service for saving and loading data
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save state to file
    public static void saveState(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load state from file
    public static SystemState loadState() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state restored successfully.");
            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {

            System.out.println("No saved state found. Starting with fresh data.");
            return null;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error restoring system state: " + e.getMessage());
            return null;
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        Map<String, Integer> inventory = new HashMap<>();
        List<Reservation> reservations = new ArrayList<>();

        // Try loading saved state
        SystemState savedState = PersistenceService.loadState();

        if (savedState != null) {

            inventory = savedState.inventory;
            reservations = savedState.reservations;

        } else {

            // Initialize fresh inventory
            inventory.put("Single", 2);
            inventory.put("Double", 2);
            inventory.put("Suite", 1);

            reservations.add(new Reservation("RES101", "Alice", "Single"));
            reservations.add(new Reservation("RES102", "Bob", "Double"));
        }

        // Display current system state
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }

        System.out.println("\nBooking History:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }

        // Save state before shutdown
        System.out.println("\nSaving system state before shutdown...");
        PersistenceService.saveState(new SystemState(inventory, reservations));
    }
}