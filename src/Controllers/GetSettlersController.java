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

public class GetSettlersController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        StudentRepository repository = new StudentRepository();
        ArrayList<Student> students = repository.readAllByStatus(StudentStatus.Candidate);
        request.setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_SETTLERS.getPagePath();
    }
}
