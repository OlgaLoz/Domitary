package Controllers;

import Interfaces.IController;
import Model.Contract;
import Model.Statement;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import flexjson.JSONSerializer;

public class StatementAndContractController implements IController {

    private static final String STATUS_ATTRIBUTE = "student_status";

    @Override
    public String run(HttpServletRequest request) {

        if (request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE) == null){
            return Pages.HOME_GUEST.getPagePath();
        }

        Statement statement = createStatementFromRequest(request);
        Contract contract = createContractFromRequest(request);
        JSONSerializer jsonSerializer = new JSONSerializer();
        String serializedStatement = jsonSerializer.serialize(statement);
        String serializedContract = jsonSerializer.serialize(contract);

        int userId = (Integer)request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        Student student = StudentRepository.getStudentByUserId(userId);
        StudentRepository.updateStatus(student.getStudentId(), StudentStatus.Candidate);
        StudentRepository.updateStatement(student.getStudentId(), serializedStatement);
        StudentRepository.updateContract(student.getStudentId(), serializedContract);
        request.getSession().setAttribute(STATUS_ATTRIBUTE, StudentStatus.Candidate);

        return Pages.HOME_STUDENT.getPagePath();
    }

    private Statement createStatementFromRequest(HttpServletRequest request) {
        Statement statement = new Statement();
        statement.setDean(request.getParameter("dean"));
        statement.setGroup(request.getParameter("group"));
        statement.setLastName(request.getParameter("lastname"));
        statement.setFirstName(request.getParameter("firstname"));
        statement.setMidName(request.getParameter("midname"));
        statement.setRegion(request.getParameter("region"));
        statement.setCity(request.getParameter("city"));
        statement.setStreet(request.getParameter("street"));
        statement.setHouse(request.getParameter("house"));
        statement.setFlat(request.getParameter("flat"));
        statement.setTraining(request.getParameter("training"));
        statement.setMobilePhone(request.getParameter("mobile_phone"));
        statement.setHomePhone(request.getParameter("home_phone"));
        statement.setMother(request.getParameter("mother"));
        statement.setFather(request.getParameter("father"));
        statement.setFillingDate(request.getParameter("filling_date"));
        return statement;
    }

    private Contract createContractFromRequest(HttpServletRequest request) {
        Contract contract = new Contract();
        contract.setFillingDate(request.getParameter("filling_date"));
        contract.setFaculty(request.getParameter("faculty"));
        contract.setChair(request.getParameter("chair"));
        contract.setCity(request.getParameter("city"));
        contract.setStreet(request.getParameter("street"));
        contract.setHouse(request.getParameter("house"));
        contract.setFlat(request.getParameter("flat"));
        contract.setPassport(request.getParameter("passportSeries") + " " + request.getParameter("passportNumber"));
        contract.setPassportDateOfIssue(request.getParameter("passportDateOfIssue"));
        return contract;
    }

}
