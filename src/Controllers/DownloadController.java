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
import flexjson.JSONDeserializer;
import org.apache.struts2.ServletActionContext;
import java.util.ArrayList;

public class DownloadController implements IController {
    private  String DOC_PATH = "http://localhost:8080/files/";
    private String fileName;
    public String getFileName() {
        return fileName;
    }

    private String status;
    private String docType;
    private int studentId;
    private String[] checkedStatuses;

    public void setStatus(String status){
        this.status = status;
    }
    public void setDocType(String docType){
        this.docType = docType;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
    public void setCheckedStatuses(String[] checkedStatuses){
        this.checkedStatuses = checkedStatuses;
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

        if (checkedStatuses == null) {
            return Pages.HOME_GUEST.getPageName();
        }

        String res = "";
        ArrayList<StudentStatus> statuses = new ArrayList<StudentStatus>();
        for (String status : checkedStatuses) {
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
