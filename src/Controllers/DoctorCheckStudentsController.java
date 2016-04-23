package Controllers;


import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DoctorCheckStudentsController implements IController {
    private static final String STUDENTS_ATTRIBUTE = "students";

    @Override
    public String run(HttpServletRequest request) {
        StudentRepository repository = new StudentRepository();

        ArrayList<Student> students = (ArrayList<Student>)request.getSession().getAttribute(STUDENTS_ATTRIBUTE);
        String[] results = request.getParameterValues("checkers");


        if(results != null) {
            for (int i = 0; i < results.length; i++ ) {
                repository.updateStatus(Integer.parseInt(results[i]), StudentStatus.BodyCheckPassed);
                for( int j = 0; j < students.size(); j++) {
                    if (students.get(j).getStudentId() == Integer.parseInt(results[i])){
                        students.remove(j);
                    }
                }
            }
        }

        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_DOCTOR.getPagePath();
    }
}
