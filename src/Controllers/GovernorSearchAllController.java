package Controllers;


import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GovernorSearchAllController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        StudentRepository repository = new StudentRepository();
        ArrayList<Student> students = repository.readAllByStatus(StudentStatus.BodyCheckPassed);
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_GOVERNOR.getPagePath();
    }
}
