package Controllers;

import Interfaces.IController;
import Model.Student;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DoctorSearchByLastNameController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Student> students = StudentRepository.readAllByLastName(request.getParameter("lastNameInput"));
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_DOCTOR.getPagePath();
    }
}
