package Controllers;


import Interfaces.IController;
import Model.StudentStatus;
import Utils.ExcelGenerationService;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DownloadSingleListController implements IController{
    private  String DOC_PATH = "http://localhost:8080/files/";
    private final String STUDENT_STATUS = "student_status";

    @Override
    public String run(HttpServletRequest request) {
        StudentStatus studentStatus = StudentStatus.valueOf(request.getParameter(STUDENT_STATUS));

        String res = Pages.HOME_GOVERNOR.getPagePath();
        if(studentStatus != null){
            ArrayList<StudentStatus> statuses = new ArrayList<StudentStatus>();
            statuses.add(studentStatus);
            res = DOC_PATH+studentStatus+"List.xlsx";
            try {
                ExcelGenerationService.createStudentsSheetsByStatuses(request.getServletContext().getRealPath("/"), studentStatus + "List.xlsx", statuses);
            }
            catch(Exception ex) {}
        }
        return res;
    }


}
