package Controllers;

import Interfaces.IController;
import Model.Room;
import Model.Student;
import Model.StudentStatus;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

public class DeaneryWorkerController extends ActionSupport implements IController {
    private String student;
    private String room;
    private String status;
    private String lastName;
    private ArrayList<Student> students;
    private ArrayList<Room> rooms;

    public void setStudent(String student) { this.student = student; }

    public String getStudent() { return student; }

    public void setRoom(String room) { this.room = room; }

    public String getRoom() { return room; }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() { return status; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLastName() { return lastName; }

    public void setStudents(ArrayList<Student> students) { this.students = students; }

    public ArrayList<Student> getStudents() { return students; }

    public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms; }

    public ArrayList<Room> getRooms() { return rooms; }

    public String checkStudents() {
        students = StudentRepository.readAllByStatus(StudentStatus.Candidate);
        rooms = RoomRepository.readAll();

        if(student == null || room == null)
            return Pages.DISTR_CANDIDATES.getPageName();

        for( int j = 0; j < students.size(); j++) {
            Integer studentId  = students.get(j).getStudentId();
            if ( studentId == Integer.parseInt(student)){
                StudentRepository.updateStatus(studentId, StudentStatus.DeaneryPassed);
                StudentRepository.updateRoomId(studentId, Integer.parseInt(room));
                students.remove(j);
                break;
            }
        }

        for( int i = 0; i < rooms.size(); i++) {
            Integer roomId  = rooms.get(i).getRoomId();
            if (roomId == Integer.parseInt(room)){
                rooms.get(i).setFreePlacesCount(rooms.get(i).getFreePlacesCount() - 1);
                RoomRepository.updateFreePlacesCount(roomId, rooms.get(i).getFreePlacesCount());
                if (rooms.get(i).getFreePlacesCount() == 0){
                    rooms.remove(i);
                }
                break;
            }
        }

        return Pages.DISTR_CANDIDATES.getPageName();
    }

    public String searchAllStudents() {
        students = StudentRepository.readAll();
        return Pages.HOME_DEANERY.getPageName();
    }

    public String searchStudentByLastName() {
        students = StudentRepository.readAllByLastName(lastName);
        return Pages.HOME_DEANERY.getPageName();
    }

    public String searchAllByStatus() {
        students = StudentRepository.readAllByStatus(StudentStatus.valueOf(status));
        return Pages.HOME_DEANERY.getPageName();
    }
}
