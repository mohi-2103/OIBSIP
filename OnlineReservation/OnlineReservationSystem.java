import java.util.*;

class Reservation {
    int pnr;
    String name;
    int trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;

    Reservation(int pnr, String name, int trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }
}

public class OnlineReservationSystem {
    private static final Map<String, String> userDatabase = new HashMap<>();
    private static final Map<Integer, Reservation> reservations = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int currentPNR = 1000;

    public static void main(String[] args) {
        initializeSystem();
        if (login()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n--- Online Reservation System ---");
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1 -> makeReservation();
                    case 2 -> cancelReservation();
                    case 3 -> {
                        exit = true;
                        System.out.println("Thank you for using the Online Reservation System.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Exiting system.");
        }
    }

    private static void initializeSystem() {
        // Adding some default users for the system
        userDatabase.put("admin", "admin123");
        userDatabase.put("user", "password");
    }

    private static boolean login() {
        System.out.println("\n--- Login Form ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim(); // Trim input to remove unnecessary spaces
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim(); // Trim input to remove unnecessary spaces

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void makeReservation() {
        System.out.println("\n--- Reservation Form ---");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (DD-MM-YYYY): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter to (destination): ");
        String to = scanner.nextLine();

        currentPNR++;
        Reservation reservation = new Reservation(currentPNR, name, trainNumber, trainName, classType, dateOfJourney, from, to);
        reservations.put(currentPNR, reservation);

        System.out.println("Reservation successful! Your PNR is: " + currentPNR);
    }

    private static void cancelReservation() {
        System.out.println("\n--- Cancellation Form ---");
        System.out.print("Enter your PNR number: ");
        int pnr = scanner.nextInt();

        if (reservations.containsKey(pnr)) {
            Reservation reservation = reservations.get(pnr);
            System.out.println("Reservation Details:");
            System.out.println("Name: " + reservation.name);
            System.out.println("Train Number: " + reservation.trainNumber);
            System.out.println("Train Name: " + reservation.trainName);
            System.out.println("Class Type: " + reservation.classType);
            System.out.println("Date of Journey: " + reservation.dateOfJourney);
            System.out.println("From: " + reservation.from);
            System.out.println("To: " + reservation.to);
            System.out.print("Are you sure you want to cancel this reservation? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR. No reservation found.");
        }
    }
}
