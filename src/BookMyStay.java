import java.util.*;

// Represents an Add-On Service
class Service {
    String serviceName;
    double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return serviceName + " ($" + cost + ")";
    }
}

public class UseCase7AddOnServiceSelection {

    // Map ReservationID -> List of Services
    private static Map<String, List<Service>> reservationServices = new HashMap<>();

    public static void main(String[] args) {

        String reservationId1 = "RES101";
        String reservationId2 = "RES102";

        // Guest selects services
        addService(reservationId1, new Service("Breakfast", 20));
        addService(reservationId1, new Service("Airport Pickup", 40));
        addService(reservationId1, new Service("Spa Access", 60));

        addService(reservationId2, new Service("Breakfast", 20));
        addService(reservationId2, new Service("City Tour", 80));

        // Display services and total cost
        displayServices(reservationId1);
        displayServices(reservationId2);
    }

    // Add service to reservation
    public static void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);

        System.out.println("Service Added: " + service.getServiceName() +
                " for Reservation " + reservationId);
    }

    // Display services and calculate total cost
    public static void displayServices(String reservationId) {

        System.out.println("\nServices for Reservation: " + reservationId);

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        double totalCost = 0;

        for (Service s : services) {
            System.out.println("- " + s);
            totalCost += s.getCost();
        }

        System.out.println("Total Add-On Cost: $" + totalCost);
    }
}