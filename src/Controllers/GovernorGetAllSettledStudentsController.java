package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GovernorGetAllSettledStudentsController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";
    private static final String STUDENTS_STATUS = "status";
    private static final String SETTLED_ATTRIBUTE = "isSettled";

    @Override
    public String run(HttpServletRequest request) {
        String studentStatus = request.getParameter(STUDENTS_STATUS);
        ArrayList<Student> students = StudentRepository.readAllByStatus(StudentStatus.valueOf(studentStatus));
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        request.getSession().setAttribute(SETTLED_ATTRIBUTE, "yes");
        return Pages.HOME_DOCTOR.getPagePath();
    }
}
