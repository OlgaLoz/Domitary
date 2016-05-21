package Model;

import java.util.ArrayList;

public class DataForDeanery {

    private String status = StudentStatus.Settled.toString();
    private ArrayList<Student> students;
    private ArrayList<Room> rooms;

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

    public ArrayList<Room> getRooms() { return rooms; }
    public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms; }

}
