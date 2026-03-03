import java.util.*;

abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void displayDetails() {
        System.out.println("Room Type       : " + roomType);
        System.out.println("Beds            : " + numberOfBeds);
        System.out.println("Price Per Night : $" + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 80.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 140.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 300.0);
    }
}

class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }

    public void registerRoomType(String roomType, int count) {
        availability.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return Collections.unmodifiableMap(availability);
    }
}

class RoomSearchService {

    private RoomInventory inventory;
    private Map<String, Room> roomCatalog;

    public RoomSearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.roomCatalog = new HashMap<>();

        for (Room room : rooms) {
            roomCatalog.put(room.getRoomType(), room);
        }
    }

    public void searchAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");

        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {

            String roomType = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                Room room = roomCatalog.get(roomType);
                room.displayDetails();
                System.out.println("Available Units : " + count);
                System.out.println("-----------------------------------");
            }
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        String appName = "Book My Stay - Hotel Booking Management System";
        String version = "Version 4.0";

        System.out.println("==========================================");
        System.out.println("      Welcome to " + appName);
        System.out.println("               " + version);
        System.out.println("==========================================");

        RoomInventory inventory = new RoomInventory();
        inventory.registerRoomType("Single Room", 5);
        inventory.registerRoomType("Double Room", 0);
        inventory.registerRoomType("Suite Room", 2);

        List<Room> rooms = Arrays.asList(
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        );

        RoomSearchService searchService = new RoomSearchService(inventory, rooms);

        searchService.searchAvailableRooms();

        System.out.println("\nSearch completed. Inventory state unchanged.");
    }
}