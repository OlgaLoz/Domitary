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
    private static final String SETTLED_ATTRIBUTE = "isSettled";
    private static final String STUDENTS_STATUS = "status";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Student> students = StudentRepository.readAllByStatus(StudentStatus.BodyCheckPassed);
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        request.getSession().setAttribute(STUDENTS_STATUS, StudentStatus.BodyCheckPassed.toString());
        request.getSession().setAttribute(SETTLED_ATTRIBUTE, "bodyCheckPassed");
        return Pages.HOME_GOVERNOR.getPagePath();
    }
}
