package CollegeSync;

import java.awt.Color;

public class Subject {
    private String s_name;
    public String shortName;
    public String classType;
    public int takenClasses = 0;
    public int remainingClasses = 0;
    private int WeeklyTotalClasses;
    public String subjectCode;
    public Color color;
    
    // FIX: Changed the constructor to public for standard class design.
    public Subject(String s_name, String shortName, String classType, int WeeklyTotalClasses, Color color, String subjectCode) {
        this.s_name = s_name;
        this.shortName = shortName;
        this.classType = classType;
        // Initialize remainingClasses to the total weekly classes
        this.remainingClasses = WeeklyTotalClasses; 
        this.WeeklyTotalClasses = WeeklyTotalClasses;
        this.color = color;
        this.subjectCode = subjectCode;
    }
    
    public Subject(String s_name, String shortName, String classType, int WeeklyTotalClasses, Color color) {
        this.s_name = s_name;
        this.shortName = shortName;
        this.classType = classType;
        // Initialize remainingClasses to the total weekly classes
        this.remainingClasses = WeeklyTotalClasses; 
        this.WeeklyTotalClasses = WeeklyTotalClasses;
        this.color = color;
    }

    /**
     * Updates the number of taken and remaining classes.
     * @param classHours The number of class hours just completed.
     */
    public void updateRemainingClasses(int classHours) {
        if (this.remainingClasses >= classHours) {
            this.remainingClasses -= classHours;
            this.takenClasses += classHours;
        } else {
            // Optional: Add an error message or throw an exception if hours exceed remaining
            System.out.println("Error: Cannot update. Class hours (" + classHours + 
                               ") exceed remaining classes (" + this.remainingClasses + ").");
        }
    }

    public void displaySubjectInfo() {
        System.out.println("Subject: " + s_name + " (" + shortName + ") | Type: " + classType + 
                           "\nWeekly Total: " + WeeklyTotalClasses + 
                           ", Taken: " + takenClasses + 
                           ", Remaining: " + remainingClasses);
    }
    
    // --- Getter Methods for Encapsulation (Good Practice) ---
    public String getS_name() {
        return s_name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getClassType() {
        return classType;
    }

    public int getTakenClasses() {
        return takenClasses;
    }

    public int getRemainingClasses() {
        return remainingClasses;
    }

    public int getWeeklyTotalClasses() {
        return WeeklyTotalClasses;
    }
}