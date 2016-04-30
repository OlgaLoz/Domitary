package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GovernorSearchByLastNameController implements IController {

    private static final String STUDENTS_ATTRIBUTE = "students";
    //private static final String SETTLED_ATTRIBUTE = "isSettled";
    private static final String STUDENTS_STATUS = "status";

    @Override
    public String run(HttpServletRequest request) {
        String studentStatus = (String)request.getSession().getAttribute(STUDENTS_STATUS);
        ArrayList<Student> students = StudentRepository.readAllByLastName(request.getParameter("lastNameInput"), StudentStatus.valueOf(studentStatus));
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        //request.getSession().setAttribute(SETTLED_ATTRIBUTE, "no");
        return Pages.HOME_GOVERNOR.getPagePath();
    }
}
