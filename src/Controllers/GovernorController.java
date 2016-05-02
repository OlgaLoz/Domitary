package Controllers;

import Interfaces.IController;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

public class GovernorController extends ActionSupport implements IController{
    private ArrayList<Student> students;
    private String lastName;
    private static String isSettled;
    private static String status = StudentStatus.Settled.toString();
    private String[] checkers;
    private String[] uncheckers;

    public ArrayList<Student> getStudents() { return students; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setIsSettled(String isSettled) { this.isSettled = isSettled; }

    public String getIsSettled() { return isSettled; }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() { return status; }

    public void setCheckers(String[] checkers) { this.checkers = checkers; }

    public void setUncheckers(String[] uncheckers) { this.uncheckers = uncheckers; }

    /*public String searchAllStudents() {
        students = StudentRepository.readAllByStatus(StudentStatus.BodyCheckPassed);
        status = StudentStatus.BodyCheckPassed.toString();
        isSettled = "bodyCheckPassed";
        return Pages.HOME_GOVERNOR.getPageName();
    }*/

    public String searchStudentsByLastName() {
        students = StudentRepository.readAllByLastName(lastName, StudentStatus.valueOf(status));
        return Pages.HOME_GOVERNOR.getPageName();
    }

    public String searchStudentsByStatus() {
        students = StudentRepository.readAllByStatus(StudentStatus.valueOf(status));
        isSettled = (StudentStatus.valueOf(status) == StudentStatus.BodyCheckPassed) ? "bodyCheckPassed" : "settled";
        return Pages.HOME_GOVERNOR.getPageName();
    }

    public String checkStudents() {
        students = StudentRepository.readAllByStatus(StudentStatus.BodyCheckPassed);
        int studentsCount = students.size();

        if(checkers != null) {
            for (int i = 0; i < checkers.length; i++ ) {
                for( int j = 0; j < studentsCount; j++) {
                    if (students.get(j).getStudentId() == Integer.parseInt(checkers[i])){
                        StudentRepository.updateStatus(Integer.parseInt(checkers[i]), StudentStatus.Settled);
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
                        StudentRepository.updateStatus(Integer.parseInt(uncheckers[i]), StudentStatus.NotSettled);
                        students.remove(j);
                        studentsCount--;
                        break;
                    }
                }
            }
        }

        return Pages.HOME_GOVERNOR.getPageName();
    }
}

