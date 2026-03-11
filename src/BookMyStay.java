import java.util.*;

// Represents a confirmed reservation
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType;
    }
}

// Maintains booking history
class BookingHistory {

    private List<Reservation> reservations = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation stored in booking history: "
                + reservation.getReservationId());
    }

    // Retrieve reservation list
    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Generates reports from booking history
class BookingReportService {

    public static void generateReport(List<Reservation> reservations) {

        System.out.println("\n===== Booking History Report =====");

        if (reservations.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("\nTotal Confirmed Bookings: " + reservations.size());

        // Example summary: count by room type
        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomTypeCount.put(
                    r.getRoomType(),
                    roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\nRoom Type Summary:");
        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + ": " + roomTypeCount.get(type));
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("RES101", "Alice", "Single"));
        history.addReservation(new Reservation("RES102", "Bob", "Double"));
        history.addReservation(new Reservation("RES103", "Charlie", "Suite"));
        history.addReservation(new Reservation("RES104", "David", "Single"));

        // Admin requests report
        BookingReportService.generateReport(history.getReservations());
    }
}