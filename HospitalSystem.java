/** Connects the arrival queue and treatment priority queue. */
public class HospitalSystem {
    private ArrivalQueue arrivalQueue;
    private TreatmentPriorityQueue treatmentQueue;

    public HospitalSystem() {
        arrivalQueue = new ArrivalQueue();
        treatmentQueue = new TreatmentPriorityQueue();
    }

    public boolean registerPatient(Patient patient) {
        if (patient == null || patient.getId() <= 0 || patient.getAge() < 0
                || patient.getAge() > 120 || patient.getPriority() < 1
                || patient.getPriority() > 5 || patient.getName() == null
                || patient.getName().trim().isEmpty()) return false;

        // IDs must be unique in both queues.
        if (arrivalQueue.search(patient.getId()) != null
                || treatmentQueue.search(patient.getId()) != null) return false;
        arrivalQueue.enqueue(patient);
        return true;
    }

    // IMPORTANT: This is the interaction between BOTH data structures.
    public Patient moveNextToTriage() {
        Patient patient = arrivalQueue.dequeue();
        if (patient != null) treatmentQueue.enqueue(patient);
        return patient;
    }

    public Patient treatNext() {
        return treatmentQueue.dequeue();
    }

    public ArrivalQueue getArrivalQueue() {
        return arrivalQueue;
    }

    public TreatmentPriorityQueue getTreatmentQueue() {
        return treatmentQueue;
    }
}
