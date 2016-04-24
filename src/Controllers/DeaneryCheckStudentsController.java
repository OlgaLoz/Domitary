package Controllers;


import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeaneryCheckStudentsController implements IController {
    private static final String STUDENTS_STATUS = "status";
    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        StudentRepository repository = new StudentRepository();
      //  String studentStatus = request.getParameter(STUDENTS_STATUS);
        ArrayList<Student> students = repository.readAllByStatus(StudentStatus.Candidate);
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);

        return Pages.DISTR_CANDIDATES.getPagePath();
    }
}
