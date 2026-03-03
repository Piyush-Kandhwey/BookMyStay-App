import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void registerRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() +
                    " | Available Units: " + entry.getValue());
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        String appName = "Book My Stay - Hotel Booking Management System";
        String version = "Version 3.0";

        System.out.println("==========================================");
        System.out.println("      Welcome to " + appName);
        System.out.println("               " + version);
        System.out.println("==========================================");

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Single Room", 5);
        inventory.registerRoomType("Double Room", 3);
        inventory.registerRoomType("Suite Room", 2);

        inventory.displayInventory();

        System.out.println("\nUpdating availability for Double Room...");
        inventory.updateAvailability("Double Room", 4);

        inventory.displayInventory();

        System.out.println("\nApplication terminated successfully.");
    }
}