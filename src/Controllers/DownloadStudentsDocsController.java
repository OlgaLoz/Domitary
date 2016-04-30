package Controllers;

import Interfaces.IController;
import Model.StudentStatus;
import Utils.CsvGenerationService;
import Utils.ExcelGenerationService;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DownloadStudentsDocsController implements IController {

    private  String DOC_PATH = "http://localhost:8080/files/";
    private final String STUDENT_STATUS = "student_id";
    private final String DOC_TYPE = "doc_type";

    @Override
    public String run(HttpServletRequest request) {
        StudentStatus studentStatus = StudentStatus.valueOf(request.getParameter(STUDENT_STATUS));
        String docType = request.getParameter(DOC_TYPE);

        String res = Pages.HOME_GOVERNOR.getPagePath();
        if(studentStatus != null){
            ArrayList<StudentStatus> statuses = new ArrayList<StudentStatus>();
            statuses.add(studentStatus);
            res = DOC_PATH+studentStatus+"List." + docType;
            try {
                if (docType.equals("xlsx")){
                    ExcelGenerationService.createStudentsSheetsByStatuses(
                            request.getServletContext().getRealPath("/"), studentStatus + "List.xlsx", statuses);
                }else {
                    CsvGenerationService.createCSVListByStudentStatus(
                            request.getServletContext().getRealPath("/"), studentStatus + "List.csv", studentStatus);
                }
            }
            catch(Exception ex) {}
        }
        return res;
    }
}
