import java.util.Scanner;

/** Console menu for the emergency-room simulation. */
public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        HospitalSystem hospital = new HospitalSystem();
        int choice;
        System.out.println("==============================================");
        System.out.println("     HOSPITAL EMERGENCY TRIAGE SYSTEM");
        System.out.println("==============================================");
        System.out.println("Educational simulation - not for real medical decisions.");

        do {
            showMenu();
            choice = readNumber("Choose an option: ");

            switch (choice) {
                case 1:
                    register(hospital);
                    break;
                case 2:
                    hospital.getArrivalQueue().display();
                    break;
                case 3:
                    searchArrival(hospital);
                    break;
                case 4:
                    deleteArrival(hospital);
                    break;
                case 5:
                    Patient moved = hospital.moveNextToTriage();
                    if (moved == null) System.out.println("No arrivals to move.");
                    else System.out.println("Moved to treatment queue: " + moved.getName());
                    break;
                case 6:
                    hospital.getTreatmentQueue().display();
                    break;
                case 7:
                    searchTreatment(hospital);
                    break;
                case 8:
                    deleteTreatment(hospital);
                    break;
                case 9:
                    Patient treated = hospital.treatNext();
                    if (treated == null) System.out.println("No patients waiting for treatment.");
                    else System.out.println("Now treating: " + treated);
                    break;
                case 10:
                    System.out.println("Waiting at reception: "
                            + hospital.getArrivalQueue().getSize());
                    System.out.println("Waiting for treatment: "
                            + hospital.getTreatmentQueue().getSize());
                    break;
                case 0:
                    System.out.println("Thank you. Program closed.");
                    break;
                default:
                    System.out.println("Please choose a number from 0 to 10.");
            }
        } while (choice != 0);
        input.close();
    }

    private static void showMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println("1. Register a new patient (arrival queue)");
        System.out.println("2. Display arrival queue");
        System.out.println("3. Search arrival queue by ID");
        System.out.println("4. Delete a patient from arrival queue");
        System.out.println("5. Move next arrival to treatment queue");
        System.out.println("6. Display treatment priority queue");
        System.out.println("7. Search treatment queue by ID");
        System.out.println("8. Delete a patient from treatment queue");
        System.out.println("9. Treat highest-priority patient");
        System.out.println("10. Display waiting counts");
        System.out.println("0. Exit");
    }

    private static void register(HospitalSystem hospital) {
        int id = readPositiveNumber("Patient ID (positive number): ");
        String name = readText("Patient name: ");
        int age = readNumberInRange("Age (0-120): ", 0, 120);
        String reason = readText("Reason for visit: ");
        System.out.println("Priority scale: 1=Critical, 2=Very urgent, 3=Urgent,");
        System.out.println("                4=Less urgent, 5=Non-urgent");
        int priority = readNumberInRange("Assigned priority (1-5): ", 1, 5);

        Patient patient = new Patient(id, name, age, reason, priority);
        if (hospital.registerPatient(patient))
            System.out.println("Patient added to ARRIVAL queue successfully.");
        else
            System.out.println("Could not add patient: ID already used or invalid information.");
    }

    private static void searchArrival(HospitalSystem hospital) {
        int id = readPositiveNumber("ID to search: ");
        Patient found = hospital.getArrivalQueue().search(id);
        if (found == null) System.out.println("Patient not found in arrival queue.");
        else System.out.println("Found: " + found);
    }

    private static void deleteArrival(HospitalSystem hospital) {
        int id = readPositiveNumber("ID to delete: ");
        Patient removed = hospital.getArrivalQueue().deleteById(id);
        if (removed == null) System.out.println("Patient not found in arrival queue.");
        else System.out.println("Removed from arrival queue: " + removed.getName());
    }

    private static void searchTreatment(HospitalSystem hospital) {
        int id = readPositiveNumber("ID to search: ");
        Patient found = hospital.getTreatmentQueue().search(id);
        if (found == null) System.out.println("Patient not found in treatment queue.");
        else System.out.println("Found: " + found);
    }

    private static void deleteTreatment(HospitalSystem hospital) {
        int id = readPositiveNumber("ID to delete: ");
        Patient removed = hospital.getTreatmentQueue().deleteById(id);
        if (removed == null) System.out.println("Patient not found in treatment queue.");
        else System.out.println("Removed from treatment queue: " + removed.getName());
    }

    // Read a whole line so names and symptoms can contain spaces.
    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String text = input.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.println("This field cannot be empty.");
        }
    }

    // Keep asking until the user types a valid integer.
    private static int readNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static int readPositiveNumber(String message) {
        int number;
        do {
            number = readNumber(message);
            if (number <= 0) System.out.println("Number must be positive.");
        } while (number <= 0);
        return number;
    }

    private static int readNumberInRange(String message, int min, int max) {
        int number;
        do {
            number = readNumber(message);
            if (number < min || number > max)
                System.out.println("Please enter a number from " + min + " to " + max + ".");
        } while (number < min || number > max);
        return number;
    }
}
