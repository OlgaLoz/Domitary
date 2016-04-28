package Controllers;

import Interfaces.IController;
import Model.Statement;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import flexjson.JSONSerializer;

public class StatementController implements IController {

    StudentRepository studentRepository = new StudentRepository();

    private static final String STATUS_ATTRIBUTE = "student_status";

    @Override
    public String run(HttpServletRequest request) {

        if (request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE) == null){
            return Pages.HOME_GUEST.getPagePath();
        }

        Statement statement = createStatementFromRequest(request);
        JSONSerializer jsonSerializer = new JSONSerializer();
        String serializedStatement = jsonSerializer.serialize(statement);

        int userId = (Integer)request.getSession().getAttribute(CURRENT_USER_ATTRIBUTE);
        Student student = studentRepository.getStudentByUserId(userId);
        studentRepository.updateStatus(student.getStudentId(), StudentStatus.Candidate);
        studentRepository.updateStatement(student.getStudentId(), serializedStatement);
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
}
