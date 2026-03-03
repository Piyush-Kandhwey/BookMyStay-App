import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Requested Room: " + roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added: " + reservation);
    }

    public void displayPendingRequests() {
        System.out.println("\n--- Pending Booking Requests (FIFO Order) ---");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation reservation : requestQueue) {
            System.out.println(reservation);
        }
    }

    public Queue<Reservation> getAllRequests() {
        return requestQueue;
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        String appName = "Book My Stay - Hotel Booking Management System";
        String version = "Version 5.0";

        System.out.println("==========================================");
        System.out.println("      Welcome to " + appName);
        System.out.println("               " + version);
        System.out.println("==========================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        bookingQueue.displayPendingRequests();

        System.out.println("\nRequests stored successfully. No inventory updates performed.");
    }
}