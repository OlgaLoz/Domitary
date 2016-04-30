package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GovernorDownloadController implements IController {
    private static final String STUDENTS_ATTRIBUTE = "students";
    private static final String SETTLED_ATTRIBUTE = "isSettled";
    private static final String STUDENTS_STATUS = "status";

    @Override
    public String run(HttpServletRequest request) {
        String studentStatus = request.getParameter(STUDENTS_STATUS);
        ArrayList<Student> students = StudentRepository.readAllByStatus(StudentStatus.valueOf(studentStatus));
        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        if (StudentStatus.valueOf(studentStatus) == StudentStatus.BodyCheckPassed){
            request.getSession().setAttribute(SETTLED_ATTRIBUTE, "bodyCheckPassed");
        } else {
            request.getSession().setAttribute(SETTLED_ATTRIBUTE, "settled");
        }
        request.getSession().setAttribute(STUDENTS_STATUS, studentStatus);
        return Pages.DOCUMENTS_PAGE.getPagePath();
    }
}
