package Controllers;


import Interfaces.IController;
import Model.Student;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DoctorSearchAllController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        StudentRepository repository = new StudentRepository();
        ArrayList<Student> students = repository.readAll();
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_DOCTOR.getPagePath();
    }
}
