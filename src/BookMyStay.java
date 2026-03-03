abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.sizeInSqFt = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSizeInSqFt() {
        return sizeInSqFt;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type       : " + roomType);
        System.out.println("Number of Beds  : " + numberOfBeds);
        System.out.println("Size (Sq Ft)    : " + sizeInSqFt);
        System.out.println("Price Per Night : $" + pricePerNight);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 180.0, 80.0);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 300.0, 140.0);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 550.0, 300.0);
    }
}

public class BookMyStay{

    public static void main(String[] args) {

        String appName = "Book My Stay - Hotel Booking Management System";
        String version = "Version 2.0";

        System.out.println("==========================================");
        System.out.println("      Welcome to " + appName);
        System.out.println("               " + version);
        System.out.println("==========================================");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        System.out.println("\n--- Room Details & Availability ---\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Units : " + singleRoomAvailability);
        System.out.println("------------------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Units : " + doubleRoomAvailability);
        System.out.println("------------------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("Available Units : " + suiteRoomAvailability);
        System.out.println("------------------------------------------");

        System.out.println("\nApplication terminated successfully.");
    }
}
