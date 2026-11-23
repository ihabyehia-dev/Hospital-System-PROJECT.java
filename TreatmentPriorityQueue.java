/** A priority queue made with sorted linked nodes (not Java PriorityQueue). */
public class TreatmentPriorityQueue {
    private TriageNode front;
    private int size;

    // INSERT: Lower numbers mean greater urgency (1 comes first).
    // Patients with equal priority stay in arrival order.
    public void enqueue(Patient patient) {
        TriageNode newNode = new TriageNode(patient);

        if (front == null || patient.getPriority() < front.patient.getPriority()) {
            newNode.next = front;
            front = newNode;
        } else {
            TriageNode current = front;
            while (current.next != null
                    && current.next.patient.getPriority() <= patient.getPriority()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // DELETE (normal priority queue rule): Treat the most urgent patient.
    public Patient dequeue() {
        if (front == null) return null;
        Patient removed = front.patient;
        front = front.next;
        size--;
        return removed;
    }

    // SEARCH: Find the patient with this ID.
    public Patient search(int id) {
        TriageNode current = front;
        while (current != null) {
            if (current.patient.getId() == id) return current.patient;
            current = current.next;
        }
        return null;
    }

    // DELETE BY ID: Remove a patient who leaves before treatment.
    public Patient deleteById(int id) {
        TriageNode current = front;
        TriageNode previous = null;
        while (current != null && current.patient.getId() != id) {
            previous = current;
            current = current.next;
        }
        if (current == null) return null;
        if (previous == null) front = current.next;
        else previous.next = current.next;
        size--;
        return current.patient;
    }

    // DISPLAY: Patients are already sorted by medical priority.
    public void display() {
        if (front == null) {
            System.out.println("Treatment priority queue is empty.");
            return;
        }
        System.out.println("\nTREATMENT QUEUE (most urgent first)");
        TriageNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public Patient peek() {
        if (front == null) return null;
        return front.patient;
    }

    public int getSize() {
        return size;
    }
}
