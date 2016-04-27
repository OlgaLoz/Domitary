package Controllers;

import Interfaces.IController;
import Model.Dormitory;
import Repositories.DormitoryRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeleteDormitoryController implements IController {

    private static final String DORMITORIES = "dormitories";
    private static final String DORMITORY_TO_DELETE = "dormitoryToDel";

    @Override
    public String run(HttpServletRequest request) {
        DormitoryRepository repository = new DormitoryRepository();
        ArrayList<Dormitory> dormitories = repository.readAll();

        String dormitoryToDel = request.getParameter(DORMITORY_TO_DELETE);
        if (dormitoryToDel != null) {
            Integer dormitoryToDelId = Integer.parseInt(dormitoryToDel);
            for( int j = 0; j < dormitories.size(); j++) {
                if (dormitories.get(j).getDormitoryId() == dormitoryToDelId){
                    repository.delete(dormitoryToDelId);
                    dormitories.remove(j);
                    break;
                }
            }
        }


        request.getSession().setAttribute(DORMITORIES, dormitories);

        return Pages.EDIT_DORMITORY.getPagePath();
    }
}
