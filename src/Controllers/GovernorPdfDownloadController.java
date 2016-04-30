package Controllers;

import Interfaces.IController;
import Model.Contract;
import Model.Statement;
import Model.Student;
import Repositories.StudentRepository;
import Utils.Pages;
import Utils.PdfGenerationService;
import flexjson.JSONDeserializer;

import javax.servlet.http.HttpServletRequest;

public class GovernorPdfDownloadController implements IController {
    private  String DOC_PATH = "http://localhost:8080/files/";
    private final String DOC_TYPE = "doc_type";
    private final String STUDENT_ID = "student_ID";

    @Override
    public String run(HttpServletRequest request) {
        String docType = request.getParameter(DOC_TYPE);
        Integer studentId = Integer.parseInt(request.getParameter(STUDENT_ID));
        Student student = StudentRepository.read(studentId);
        String res = docType + studentId + ".pdf";
        try {
            if (docType.equals("statement")) {
                JSONDeserializer<Statement> deserializer = new JSONDeserializer<Statement>();
                Statement statement = deserializer.deserialize(student.getStatement());
                PdfGenerationService.createStudentStatement(
                        request.getServletContext().getRealPath("/"), res, statement);
            } else if (docType.equals("contract")) {
                JSONDeserializer<Contract> deserializer = new JSONDeserializer<Contract>();
                Contract contract = deserializer.deserialize(student.getContract());
                PdfGenerationService.createStudentContract(
                        request.getServletContext().getRealPath("/"), res, contract, student);
            } else {
                int dormitoryNumber = StudentRepository.getDormitoryNumberByStudentId(studentId);
                int blockNumber = StudentRepository.getBlockNumberByStudentId(studentId);
                int roomNumber = StudentRepository.getRoomNumberByStudentId(studentId);
                PdfGenerationService.createStudentOrder(
                        request.getServletContext().getRealPath("/"), res, student, dormitoryNumber, blockNumber, roomNumber);
            }
        } catch (Exception ex) {
            return Pages.DOCUMENTS_PAGE.getPagePath();
        }
        return DOC_PATH + res;
    }
}
