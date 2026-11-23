/** Stores the information about one patient. */
public class Patient {
    private int id;
    private String name;
    private int age;
    private String reason;
    private int priority; // 1 = most urgent, 5 = least urgent

    public Patient(int id, String name, int age, String reason, int priority) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.reason = reason;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getReason() {
        return reason;
    }

    public int getPriority() {
        return priority;
    }

    public String getPriorityLabel() {
        if (priority == 1) return "Critical";
        if (priority == 2) return "Very urgent";
        if (priority == 3) return "Urgent";
        if (priority == 4) return "Less urgent";
        return "Non-urgent";
    }

    public String toString() {
        return "ID: " + id + " | " + name + " | Age: " + age
                + " | Reason: " + reason + " | Priority: " + priority
                + " (" + getPriorityLabel() + ")";
    }
}
