/** One node in the linked arrival queue. */
public class ArrivalNode {
    Patient patient;
    ArrivalNode next;

    public ArrivalNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}
