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

    private static final String STATUS_ATTRIBUTE = "student_status";

    public String apply() {

        Map<String, Object> session = ActionContext.getContext().getSession();
        Map<String, Object> request = ActionContext.getContext().getParameters();

        if (session.getOrDefault(CURRENT_USER_ATTRIBUTE, null) == null) {
            return Pages.HOME_GUEST.getPageName();
        }

        Statement statement = createStatementFromRequest(request);
        Contract contract = createContractFromRequest(request);
        JSONSerializer jsonSerializer = new JSONSerializer();
        String serializedStatement = jsonSerializer.serialize(statement);
        String serializedContract = jsonSerializer.serialize(contract);

        int userId = (Integer)session.get(CURRENT_USER_ATTRIBUTE);
        Student student = StudentRepository.getStudentByUserId(userId);
        StudentRepository.updateStatus(student.getStudentId(), StudentStatus.Candidate);
        StudentRepository.updateStatement(student.getStudentId(), serializedStatement);
        StudentRepository.updateContract(student.getStudentId(), serializedContract);
        session.put(STATUS_ATTRIBUTE, StudentStatus.Candidate);

        return Pages.HOME_STUDENT.getPageName();
    }

    private Statement createStatementFromRequest(Map<String, Object> request) {
        Statement statement = new Statement();
        try {
            statement.setDean(((String[])request.get("dean"))[0]);
            statement.setGroup(((String[])request.get("group"))[0]);
            statement.setLastName(((String[])request.get("lastname"))[0]);
            statement.setFirstName(((String[])request.get("firstname"))[0]);
            statement.setMidName(((String[])request.get("midname"))[0]);
            statement.setRegion(((String[])request.get("region"))[0]);
            statement.setCity(((String[])request.get("city"))[0]);
            statement.setStreet(((String[])request.get("street"))[0]);
            statement.setHouse(((String[])request.get("house"))[0]);
            statement.setFlat(((String[])request.get("flat"))[0]);
            statement.setTraining(((String[])request.get("training"))[0]);
            statement.setMobilePhone(((String[])request.get("mobile_phone"))[0]);
            statement.setHomePhone(((String[])request.get("home_phone"))[0]);
            statement.setMother(((String[])request.get("mother"))[0]);
            statement.setFather(((String[])request.get("father"))[0]);
            statement.setFillingDate(((String[])request.get("filling_date"))[0]);
        }
        catch (Exception ex) {
            statement = null;
        }
        return statement;
    }

    private Contract createContractFromRequest(Map<String, Object> request) {
        Contract contract = new Contract();
        try {
            contract.setFillingDate(((String[]) request.get("filling_date"))[0]);
            contract.setFaculty(((String[]) request.get("faculty"))[0]);
            contract.setChair(((String[]) request.get("chair"))[0]);
            contract.setCity(((String[]) request.get("city"))[0]);
            contract.setStreet(((String[]) request.get("street"))[0]);
            contract.setHouse(((String[]) request.get("house"))[0]);
            contract.setFlat(((String[]) request.get("flat"))[0]);
            contract.setPassport(((String[]) request.get("passportSeries"))[0] + " " + ((String[]) request.get("passportNumber"))[0]);
            contract.setPassportDateOfIssue(((String[]) request.get("passportDateOfIssue"))[0]);
        }
        catch (Exception ex) {
            contract = null;
        }
        return contract;
    }

}
