/** Simple automated tests without JUnit or built-in collections. */
public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testArrivalQueue();
        testPriorityQueue();
        testConnectedWorkflow();
        System.out.println("\nTOTAL: " + passed + " passed, " + failed + " failed.");
        if (failed > 0) System.exit(1);
    }

    private static void check(boolean result, String description) {
        if (result) {
            System.out.println("PASS | " + description);
            passed++;
        } else {
            System.out.println("FAIL | " + description);
            failed++;
        }
    }

    private static Patient p(int id, int priority) {
        return new Patient(id, "Patient " + id, 20, "Test reason", priority);
    }

    private static void testArrivalQueue() {
        System.out.println("\n-- ARRIVAL QUEUE TESTS --");
        ArrivalQueue queue = new ArrivalQueue();
        check(queue.dequeue() == null, "Dequeue on empty queue returns null");
        check(queue.search(100) == null, "Search missing ID in empty queue");
        check(queue.deleteById(100) == null, "Delete missing ID in empty queue");
        queue.enqueue(p(101, 4));
        queue.enqueue(p(102, 2));
        queue.enqueue(p(103, 1));
        check(queue.getSize() == 3, "Insert three arrivals");
        check(queue.peek().getId() == 101, "Queue keeps first-in-first-out order");
        check(queue.search(102) != null, "Search finds an existing arrival");
        queue.display(); // DISPLAY is tested with real console output.
        check(queue.deleteById(102).getId() == 102, "Delete middle arrival by ID");
        check(queue.deleteById(103).getId() == 103, "Delete rear arrival by ID");
        queue.enqueue(p(104, 3));
        check(queue.dequeue().getId() == 101, "Dequeue removes front arrival");
        check(queue.dequeue().getId() == 104, "Rear pointer still works after deletion");
        check(queue.getSize() == 0, "Arrival queue ends empty");
    }

    private static void testPriorityQueue() {
        System.out.println("\n-- TREATMENT PRIORITY QUEUE TESTS --");
        TreatmentPriorityQueue queue = new TreatmentPriorityQueue();
        check(queue.dequeue() == null, "Treat on empty priority queue returns null");
        check(queue.search(200) == null, "Search missing ID in priority queue");
        queue.enqueue(p(201, 4));
        queue.enqueue(p(202, 1));
        queue.enqueue(p(203, 2));
        queue.enqueue(p(204, 1));
        check(queue.getSize() == 4, "Insert four patients with different priorities");
        check(queue.peek().getId() == 202, "Urgency 1 is served before urgency 4");
        check(queue.search(203) != null, "Search finds a patient in treatment queue");
        queue.display();
        check(queue.dequeue().getId() == 202, "Treat first priority-1 patient");
        check(queue.dequeue().getId() == 204, "Equal priorities keep arrival order");
        check(queue.deleteById(203).getId() == 203, "Delete treatment patient by ID");
        check(queue.dequeue().getId() == 201, "Final lower-priority patient is treated");
        check(queue.getSize() == 0, "Priority queue ends empty");
    }

    private static void testConnectedWorkflow() {
        System.out.println("\n-- BOTH DATA STRUCTURES WORKING TOGETHER --");
        HospitalSystem hospital = new HospitalSystem();
        check(hospital.registerPatient(p(301, 4)), "Register first patient");
        check(hospital.registerPatient(p(302, 1)), "Register second patient");
        check(!hospital.registerPatient(p(301, 2)), "Duplicate patient ID is rejected");
        check(hospital.moveNextToTriage().getId() == 301, "Move first from arrival to treatment");
        check(hospital.getArrivalQueue().search(301) == null, "Moved patient is no longer in arrival queue");
        check(hospital.getTreatmentQueue().search(301) != null, "Moved patient exists in treatment queue");
        check(!hospital.registerPatient(p(301, 3)), "Duplicate across both structures is rejected");
        check(hospital.moveNextToTriage().getId() == 302, "Move second patient to treatment");
        check(hospital.treatNext().getId() == 302, "More urgent patient treated first");
        check(hospital.treatNext().getId() == 301, "Less urgent patient treated next");
        check(hospital.moveNextToTriage() == null, "Empty arrival queue handled safely");
        check(hospital.treatNext() == null, "Empty treatment queue handled safely");
        hospital.getArrivalQueue().display();
        hospital.getTreatmentQueue().display();
    }
}
