package Model;

import java.util.ArrayList;

public class DataForGovernor {

    private ArrayList<Student> students;
    private String isSettled;
    private String status = StudentStatus.Settled.toString();

    public String getIsSettled() {
        return isSettled;
    }
    public void setIsSettled(String isSettled) {
        this.isSettled = isSettled;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

}
