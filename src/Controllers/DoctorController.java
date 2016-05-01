package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

public class DoctorController extends ActionSupport implements  IController {
    private ArrayList<Student> students;
    private String lastNameInput;
    private String[] checkers;
    private String[] uncheckers;

    public void setUncheckers(String[] uncheckers){
        this.uncheckers = uncheckers;
    }

    public void setCheckers(String[] checkers){
        this.checkers = checkers;
    }

    public void setLastNameInput(String lastNameInput){
        this.lastNameInput = lastNameInput;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String searchAll() {
        students = StudentRepository.readAllByStatus(StudentStatus.DeaneryPassed);
        return Pages.HOME_DOCTOR.getPageName();
    }

    public String searchByLastName() {
        students = StudentRepository.readAllByLastName(lastNameInput, StudentStatus.DeaneryPassed);
        return Pages.HOME_DOCTOR.getPageName();
    }

    public String checkStudents() {
        students = StudentRepository.readAllByStatus(StudentStatus.DeaneryPassed);
        int studentsCount = students.size();

            if(checkers != null) {
                for (int i = 0; i < checkers.length; i++ ) {
                    for( int j = 0; j < studentsCount; j++) {
                        if (students.get(j).getStudentId() == Integer.parseInt(checkers[i])){
                            StudentRepository.updateStatus(Integer.parseInt(checkers[i]), StudentStatus.BodyCheckPassed);
                            students.remove(j);
                            studentsCount--;
                            break;
                        }
                    }
                }
            }

        if(uncheckers != null) {
            for (int i = 0; i < uncheckers.length; i++ ) {
                for( int j = 0; j < studentsCount; j++) {
                    if (students.get(j).getStudentId() == Integer.parseInt(uncheckers[i])){
                        StudentRepository.updateStatus(Integer.parseInt(uncheckers[i]), StudentStatus.BodyCheckNotPassed);
                        students.remove(j);
                        studentsCount--;
                        break;
                    }
                }
            }
        }
        return Pages.HOME_DOCTOR.getPageName();
    }
}
