import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Booking Service with validation
class BookingService {

    private Map<String, Integer> inventory;

    public BookingService() {
        inventory = new HashMap<>();

        // Initial room inventory
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    // Method to process booking
    public void bookRoom(String guestName, String roomType) throws InvalidBookingException {

        validateRoomType(roomType);
        validateAvailability(roomType);

        // Allocation logic
        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking confirmed for " + guestName +
                " | Room Type: " + roomType);
    }

    // Validate room type
    private void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }
    }

    // Validate inventory availability
    private void validateAvailability(String roomType) throws InvalidBookingException {
        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for type: " + roomType);
        }
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {

            service.bookRoom("Alice", "Single");
            service.bookRoom("Bob", "Suite");

            // Invalid room type
            service.bookRoom("Charlie", "Luxury");

            // Exhaust inventory example
            service.bookRoom("David", "Suite");

        } catch (InvalidBookingException e) {

            // Graceful failure message
            System.out.println("Booking Failed: " + e.getMessage());

        }

        service.showInventory();
    }
}