package Controllers;

import Interfaces.IController;
import Model.Student;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GovernorSearchByLastNameController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";
    private static final String SETTLED_ATTRIBUTE = "isSettled";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Student> students = StudentRepository.readAllByLastName(request.getParameter("lastNameInput"));
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        request.getSession().setAttribute(SETTLED_ATTRIBUTE, "no");
        return Pages.HOME_GOVERNOR.getPagePath();
    }
}
