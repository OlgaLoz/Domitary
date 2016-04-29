package Controllers;

import Interfaces.IController;
import Model.Room;
import Model.Student;
import Model.StudentStatus;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeaneryCheckStudentsController implements IController {
    private static final String STUDENT = "student";
    private static final String STUDENTS_ATTRIBUTE = "students";
    private static final String ROOMS_ATTRIBUTE = "rooms";
    private static final String ROOM = "room";

    @Override
    public String run(HttpServletRequest request) {

        String student =request.getParameter(STUDENT);
        String room = request.getParameter(ROOM);

        ArrayList<Student> students = StudentRepository.readAllByStatus(StudentStatus.Candidate);
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);

        ArrayList<Room> rooms = RoomRepository.readAll();
        request.getSession().setAttribute(ROOMS_ATTRIBUTE, rooms);

        if(student == null || room == null)
            return Pages.DISTR_CANDIDATES.getPagePath();

        for( int j = 0; j < students.size(); j++) {
            Integer studentId  = students.get(j).getStudentId();
            if ( studentId == Integer.parseInt(student)){
                StudentRepository.updateStatus(studentId,StudentStatus.DeaneryPassed);
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


        return Pages.DISTR_CANDIDATES.getPagePath();
    }
}
