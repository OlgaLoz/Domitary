package Controllers;

import Interfaces.IController;
import Model.Contract;
import Model.Statement;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionContext;
import flexjson.JSONSerializer;

import java.util.Map;

public class StudentController implements IController {

    private Statement statement = new Statement();
    private Contract contract = new Contract();
    private StudentStatus studentStatus;
    public StudentStatus getStudentStatus(){
        return studentStatus;
    }

    public String apply() {

        Map<String, Object> session = ActionContext.getContext().getSession();

        if (session.getOrDefault(CURRENT_USER_ATTRIBUTE, null) == null) {
            return Pages.HOME_GUEST.getPageName();
        }

        JSONSerializer jsonSerializer = new JSONSerializer();
        String serializedStatement = jsonSerializer.serialize(statement);
        String serializedContract = jsonSerializer.serialize(contract);

        int userId = (Integer)session.get(CURRENT_USER_ATTRIBUTE);
        Student student = StudentRepository.getStudentByUserId(userId);
        StudentRepository.updateStatus(student.getStudentId(), StudentStatus.Candidate);
        StudentRepository.updateStatement(student.getStudentId(), serializedStatement);
        StudentRepository.updateContract(student.getStudentId(), serializedContract);
        studentStatus = StudentStatus.Candidate;

        return Pages.HOME_STUDENT.getPageName();
    }

    public void setDean(String dean){
        statement.setDean(dean);
    }
    public void setGroup(String group){
        statement.setGroup(group);
    }
    public void setLastname(String lastname){
        statement.setLastName(lastname);
    }
    public void setFirstname(String firstname){
        statement.setFirstName(firstname);
    }
    public void setMidname(String midname){
        statement.setMidName(midname);
    }
    public void setRegion(String region){
        statement.setRegion(region);
    }
    public void setCity(String city){
        statement.setCity(city);
        contract.setCity(city);
    }
    public void setStreet(String street){
        statement.setStreet(street);
        contract.setStreet(street);
    }
    public void setHouse(String house){
        statement.setHouse(house);
        contract.setHouse(house);
    }
    public void setFlat(String flat){
        statement.setFlat(flat);
        contract.setFlat(flat);
    }
    public void setTraining(String training){
        statement.setTraining(training);
    }
    public void setMobile_phone(String mobile_phone){
        statement.setMobilePhone(mobile_phone);
    }
    public void setHome_phone(String home_phone){
        statement.setHomePhone(home_phone);
    }
    public void setMother(String mother){
        statement.setMother(mother);
    }
    public void setFather(String father){
        statement.setFather(father);
    }
    public void setFilling_date(String filling_date){
        statement.setFillingDate(filling_date);
        contract.setFillingDate(filling_date);
    }
    public void setFaculty(String faculty){
        contract.setFaculty(faculty);
    }
    public void setChair(String chair){
        contract.setChair(chair);
    }
    public void setPassport(String passport){
        contract.setPassport(passport);
    }
    public void setPassportDateOfIssue(String passportDateOfIssue){
        contract.setPassportDateOfIssue(passportDateOfIssue);
    }

}
