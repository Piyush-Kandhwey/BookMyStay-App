import java.util.*;

// Booking Request Model
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Booking Processor with thread-safe methods
class BookingProcessor {

    private Queue<BookingRequest> bookingQueue;
    private Map<String, Integer> inventory;

    public BookingProcessor(Queue<BookingRequest> bookingQueue,
                            Map<String, Integer> inventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
    }

    // Critical section: synchronized booking processing
    public synchronized void processBooking() {

        if (bookingQueue.isEmpty()) {
            return;
        }

        BookingRequest request = bookingQueue.poll();

        if (request == null) {
            return;
        }

        String roomType = request.roomType;

        System.out.println(Thread.currentThread().getName()
                + " processing booking for " + request.guestName);

        if (inventory.getOrDefault(roomType, 0) > 0) {

            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booking Confirmed for "
                    + request.guestName + " | Room Type: " + roomType);

        } else {

            System.out.println("Booking Failed for "
                    + request.guestName + " | No " + roomType + " rooms available");
        }
    }
}

// Worker Thread
class BookingThread extends Thread {

    private BookingProcessor processor;

    public BookingThread(BookingProcessor processor, String name) {
        super(name);
        this.processor = processor;
    }

    public void run() {

        while (true) {

            synchronized (processor) {
                processor.processBooking();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        Queue<BookingRequest> bookingQueue = new LinkedList<>();

        // Sample booking requests
        bookingQueue.add(new BookingRequest("Alice", "Single"));
        bookingQueue.add(new BookingRequest("Bob", "Single"));
        bookingQueue.add(new BookingRequest("Charlie", "Double"));
        bookingQueue.add(new BookingRequest("David", "Suite"));
        bookingQueue.add(new BookingRequest("Emma", "Single"));

        Map<String, Integer> inventory = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        BookingProcessor processor =
                new BookingProcessor(bookingQueue, inventory);

        // Multiple threads simulating concurrent users
        Thread t1 = new BookingThread(processor, "Thread-1");
        Thread t2 = new BookingThread(processor, "Thread-2");
        Thread t3 = new BookingThread(processor, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}