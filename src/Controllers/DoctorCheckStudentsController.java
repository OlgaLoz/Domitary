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
        String[] positiveResults = request.getParameterValues("checkers");
        String[] negativeResults = request.getParameterValues("uncheckers");


        if(positiveResults != null) {
            for (int i = 0; i < positiveResults.length; i++ ) {
                repository.updateStatus(Integer.parseInt(positiveResults[i]), StudentStatus.BodyCheckPassed);
                for( int j = 0; j < students.size(); j++) {
                    if (students.get(j).getStudentId() == Integer.parseInt(positiveResults[i])){
                        students.remove(j);
                    }
                }
            }
        }

        if(negativeResults != null) {
            for (int i = 0; i < negativeResults.length; i++ ) {
                repository.updateStatus(Integer.parseInt(negativeResults[i]), StudentStatus.BodyCheckNotPassed);
                for( int j = 0; j < students.size(); j++) {
                    if (students.get(j).getStudentId() == Integer.parseInt(negativeResults[i])){
                        students.remove(j);
                    }
                }
            }
        }

        request.getSession().setAttribute(STUDENTS_ATTRIBUTE, students);
        return Pages.HOME_DOCTOR.getPagePath();
    }
}
