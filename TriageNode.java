/** One node in the linked treatment priority queue. */
public class TriageNode {
    Patient patient;
    TriageNode next;

    public TriageNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}
