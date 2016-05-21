package Controllers;

import Interfaces.IController;
import Model.DataForGovernor;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.ArrayList;
import java.sql.Date;

public class GovernorController extends ActionSupport implements IController {
    private ArrayList<Student> students;
    private String lastName;
    private static String isSettled;
    private static String status = StudentStatus.Settled.toString();
    private String[] checkers;
    private String[] uncheckers;
    private DataForGovernor dataForGovernor = new DataForGovernor();

    public ArrayList<Student> getStudents() { return students; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setIsSettled(String isSettled) {
        this.isSettled = isSettled;
        dataForGovernor.setIsSettled(isSettled);
    }

    public String getIsSettled() { return isSettled; }

    public void setStatus(String status) {
        this.status = status;
        dataForGovernor.setStatus(status);
    }

    public String getStatus() { return status; }

    public void setCheckers(String[] checkers) { this.checkers = checkers; }

    public void setUncheckers(String[] uncheckers) { this.uncheckers = uncheckers; }

    public DataForGovernor getDataForGovernor() {
        return dataForGovernor;
    }

    public String searchAllStudents() {
        students = StudentRepository.readAllByStatus(StudentStatus.BodyCheckPassed);
        status = StudentStatus.BodyCheckPassed.toString();
        isSettled = "BodyCheckPassed";

        dataForGovernor.setStudents(students);
        dataForGovernor.setStatus(status);
        dataForGovernor.setIsSettled(isSettled);

        return Pages.HOME_GOVERNOR.getPageName();
    }

    public String searchStudentsByLastName() {
        students = StudentRepository.readAllByLastName(lastName, StudentStatus.valueOf(status));
        dataForGovernor.setStudents(students);
        return Pages.HOME_GOVERNOR.getPageName();
    }

    public String searchStudentsByStatus() {
        students = StudentRepository.readAllByStatus(StudentStatus.valueOf(status));
        isSettled = (StudentStatus.valueOf(status) == StudentStatus.BodyCheckPassed) ? "BodyCheckPassed" : "Settled";

        dataForGovernor.setStudents(students);
        dataForGovernor.setIsSettled(isSettled);

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
                        StudentRepository.updateDateOfSettlement(Integer.parseInt(checkers[i]), new Date(Calendar.getInstance().getTimeInMillis()));
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

        dataForGovernor.setStudents(students);
        return Pages.HOME_GOVERNOR.getPageName();
    }

    public String governorDownload() {
        students = StudentRepository.readAllByStatus(StudentStatus.valueOf(status));
        dataForGovernor.setStudents(students);
        return Pages.DOCUMENTS_PAGE.getPageName();
    }
}

