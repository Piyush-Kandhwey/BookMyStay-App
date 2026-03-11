import java.util.*;

// Reservation class
class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String toString() {
        return "ReservationID: " + reservationId +
                ", Guest: " + guestName +
                ", RoomType: " + roomType +
                ", RoomID: " + roomId;
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> reservations;
    private Map<String, Integer> inventory;
    private Stack<String> rollbackStack;

    public CancellationService(Map<String, Reservation> reservations,
                               Map<String, Integer> inventory,
                               Stack<String> rollbackStack) {

        this.reservations = reservations;
        this.inventory = inventory;
        this.rollbackStack = rollbackStack;
    }

    public void cancelBooking(String reservationId) {

        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation does not exist.");
            return;
        }

        Reservation res = reservations.get(reservationId);

        // Push released room ID to stack
        rollbackStack.push(res.roomId);

        // Restore inventory
        inventory.put(res.roomType, inventory.get(res.roomType) + 1);

        // Remove reservation from active bookings
        reservations.remove(reservationId);

        System.out.println("Booking Cancelled Successfully.");
        System.out.println("Released Room ID: " + res.roomId);
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        // Active reservations
        Map<String, Reservation> reservations = new HashMap<>();

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);

        // Stack for rollback
        Stack<String> rollbackStack = new Stack<>();

        // Sample confirmed reservations
        Reservation r1 = new Reservation("RES101", "Alice", "Suite", "S-001");
        Reservation r2 = new Reservation("RES102", "Bob", "Single", "SI-002");

        reservations.put(r1.reservationId, r1);
        reservations.put(r2.reservationId, r2);

        CancellationService service =
                new CancellationService(reservations, inventory, rollbackStack);

        // Attempt cancellation
        service.cancelBooking("RES101");

        // Invalid cancellation example
        service.cancelBooking("RES999");

        // Display inventory after rollback
        System.out.println("\nUpdated Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }

        // Display rollback stack
        System.out.println("\nRollback Stack (Recently Released Rooms):");
        System.out.println(rollbackStack);
    }
}