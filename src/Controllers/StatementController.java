package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;

public class StatementController implements IController {

    StudentRepository studentRepository = new StudentRepository();

    private static final String STATUS_ATTRIBUTE = "student_status";

    @Override
    public String run(HttpServletRequest request) {

        if (request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE) == null){
            return Pages.HOME_GUEST.getPagePath();
        }

        int userId = (Integer)request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        Student student = studentRepository.getStudentByUserId(userId);
        studentRepository.updateStatus(student.getStudentId(), StudentStatus.Candidate);
        request.getSession().setAttribute(STATUS_ATTRIBUTE, StudentStatus.Candidate);

        return Pages.HOME_STUDENT.getPagePath();
    }
}
