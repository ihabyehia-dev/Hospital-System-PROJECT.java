# Hospital Emergency Triage System

Java console application for CMPS347 Data Structures at Beirut Arab University. It simulates hospital reception and treatment waiting lines. **Educational project only — not for real medical decisions.**

## How it works

The program uses **two custom data structures**, both built with our own linked nodes (no Java collection classes):

1. **ArrivalQueue** — a first-in, first-out (FIFO) queue. New patients join at the rear and the first arrival moves next.
2. **TreatmentPriorityQueue** — a sorted linked priority queue. Smaller priority numbers represent higher urgency (1 = critical, 5 = non-urgent). Patients with equal priority keep the order in which they joined the treatment queue.

`HospitalSystem.moveNextToTriage()` connects both structures: it dequeues the next arrival and enqueues that patient in the treatment queue. `treatNext()` removes the highest-priority waiting patient.

## Features

- Register patients with ID, name, age, reason for visit, and urgency level
- Search or delete a patient by ID in either queue
- Display both queues and their current sizes
- Move patients from reception into the treatment priority queue
- Treat the most urgent patient first
- Reject invalid patient data and duplicate IDs currently in either queue

## Files

| File | Purpose |
| --- | --- |
| `Patient.java` | Patient data and priority descriptions |
| `ArrivalNode.java`, `ArrivalQueue.java` | FIFO arrival queue |
| `TriageNode.java`, `TreatmentPriorityQueue.java` | Sorted priority queue |
| `HospitalSystem.java` | Coordinates both queues |
| `Main.java` | Console menu and program entry point |

## Run it

Requires a Java Development Kit (JDK 8 or later). In a terminal, from the repository folder:

```bash
javac *.java
java Main
```

In Eclipse, create a Java Project, add these seven `.java` classes to the default package under `src`, and run `Main.java` as a Java Application.

## Example demonstration

1. Register patient 101 with priority 4.
2. Register patient 102 with priority 1.
3. Choose the menu option to move each patient to treatment.
4. Display the treatment queue: patient **102** appears before 101.
5. Treat the next patient: patient **102** is treated first.

## Big O complexity (worst case)

Let `n` be the number of patients in the relevant queue.

| Operation | Arrival queue | Treatment queue |
| --- | --- | --- |
| Insert (`enqueue`) | O(1) | O(n) |
| Search by ID | O(n) | O(n) |
| Delete by ID | O(n) | O(n) |
| Remove from front (`dequeue`) | O(1) | O(1) |
| Display | O(n) | O(n) |

## Testing and limitations

Test an empty queue, missing ID, duplicate ID, and equal-priority patients. Records exist in memory only and are lost when the program closes. The priority is manually entered for demonstration, not determined through clinical assessment.
