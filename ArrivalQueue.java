/** A first-in, first-out queue made with our own linked nodes. */
public class ArrivalQueue {
    private ArrivalNode front;
    private ArrivalNode rear;
    private int size;

    // INSERT: A new patient always joins at the rear.
    public void enqueue(Patient patient) {
        ArrivalNode newNode = new ArrivalNode(patient);
        if (front == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // DELETE (normal queue rule): Remove the patient at the front.
    public Patient dequeue() {
        if (front == null) return null;

        Patient removed = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return removed;
    }

    // SEARCH: Look through the nodes for a matching patient ID.
    public Patient search(int id) {
        ArrivalNode current = front;
        while (current != null) {
            if (current.patient.getId() == id) return current.patient;
            current = current.next;
        }
        return null;
    }

    // DELETE BY ID: Also allow staff to cancel a patient registration.
    public Patient deleteById(int id) {
        ArrivalNode current = front;
        ArrivalNode previous = null;

        while (current != null && current.patient.getId() != id) {
            previous = current;
            current = current.next;
        }
        if (current == null) return null;

        if (previous == null) {
            front = current.next;
        } else {
            previous.next = current.next;
        }
        if (current == rear) rear = previous;
        size--;
        return current.patient;
    }

    // DISPLAY: Print the records from front to rear.
    public void display() {
        if (front == null) {
            System.out.println("Arrival queue is empty.");
            return;
        }
        System.out.println("\nARRIVAL QUEUE (first in, first out)");
        ArrivalNode current = front;
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
