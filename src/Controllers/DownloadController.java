package Controllers;

import Interfaces.IController;
import Model.Contract;
import Model.Statement;
import Model.Student;
import Model.StudentStatus;
import Repositories.StudentRepository;
import Utils.CsvGenerationService;
import Utils.ExcelGenerationService;
import Utils.Pages;
import Utils.PdfGenerationService;
import com.opensymphony.xwork2.ActionContext;
import flexjson.JSONDeserializer;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.Map;

public class DownloadController implements IController {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    private  String DOC_PATH = "http://localhost:8080/files/";
    private final String STUDENT_STATUS = "student_status";
    private final String CHECKED_STATUSES = "chStatus";
    private final String DOC_TYPE = "doc_type";
    private final String STUDENT_ID = "student_ID";

    private String status;
    private String docType;

    public void setStatus(String status){
        this.status = status;
    }
    public void setDocType(String docType){
        this.docType = docType;
    }

    public String downloadSingleList() {
        StudentStatus studentStatus = StudentStatus.valueOf(status);

        if (studentStatus == null) {
            return Pages.HOME_GUEST.getPageName();
        }

        ArrayList<StudentStatus> statuses = new ArrayList<StudentStatus>();
        statuses.add(studentStatus);
        try {
            if (docType.equals("xlsx")) {
                ExcelGenerationService.createStudentsSheetsByStatuses(
                        ServletActionContext.getServletContext().getRealPath("/"), studentStatus + "List.xlsx", statuses);
            } else {
                CsvGenerationService.createCSVListByStudentStatus(
                        ServletActionContext.getServletContext().getRealPath("/"), studentStatus + "List.csv", studentStatus);
            }
        } catch (Exception ex) { }

        fileName = DOC_PATH + studentStatus + "List." + docType;
        return Pages.DOWNLOAD_FILE.getPageName();
    }

    public String downloadMultiList() {

        Map<String, Object> request = ActionContext.getContext().getParameters();
        String[] positiveResults = (String[]) request.get(CHECKED_STATUSES);

        if (positiveResults == null) {
            return Pages.HOME_GUEST.getPageName();
        }

        String res = "";
        ArrayList<StudentStatus> statuses = new ArrayList<StudentStatus>();
        for (String status : positiveResults) {
            statuses.add(StudentStatus.valueOf(status));
            res += status.toCharArray()[0];
        }
        res += "List.xlsx";

        try {
            ExcelGenerationService.createStudentsSheetsByStatuses(
                    ServletActionContext.getServletContext().getRealPath("/"), res, statuses);
        } catch (Exception ex) {
        }

        fileName = DOC_PATH + res;
        return Pages.DOWNLOAD_FILE.getPageName();
    }

    public String downloadPdfDocuments() {
        Map<String, Object> request = ActionContext.getContext().getParameters();
        String docType = ((String[]) request.get(DOC_TYPE))[0];
        Integer studentId = Integer.parseInt(((String[]) request.get(STUDENT_ID))[0]);
        Student student = StudentRepository.read(studentId);
        String res = docType + studentId + ".pdf";
        try {
            if (docType.equals("statement")) {
                JSONDeserializer<Statement> deserializer = new JSONDeserializer<Statement>();
                Statement statement = deserializer.deserialize(student.getStatement());
                PdfGenerationService.createStudentStatement(
                        ServletActionContext.getServletContext().getRealPath("/"), res, statement);
            } else if (docType.equals("contract")) {
                JSONDeserializer<Contract> deserializer = new JSONDeserializer<Contract>();
                Contract contract = deserializer.deserialize(student.getContract());
                PdfGenerationService.createStudentContract(
                        ServletActionContext.getServletContext().getRealPath("/"), res, contract, student);
            } else {
                int dormitoryNumber = StudentRepository.getDormitoryNumberByStudentId(studentId);
                int blockNumber = StudentRepository.getBlockNumberByStudentId(studentId);
                int roomNumber = StudentRepository.getRoomNumberByStudentId(studentId);
                PdfGenerationService.createStudentOrder(ServletActionContext.getServletContext().getRealPath("/"),
                        res, student, dormitoryNumber, blockNumber, roomNumber);
            }
        } catch (Exception ex) {
            return Pages.DOCUMENTS_PAGE.getPageName();
        }

        fileName = DOC_PATH + res;
        return Pages.DOWNLOAD_FILE.getPageName();
    }

}
