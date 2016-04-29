package Controllers;

import Interfaces.IController;
import Model.Dormitory;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeleteDormitoryController implements IController {

    private static final String DORMITORIES = "dormitories";
    private static final String DORMITORY_TO_DELETE = "dormitoryToDel";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Dormitory> dormitories = DormitoryRepository.readAll();

        String dormitoryToDel = request.getParameter(DORMITORY_TO_DELETE);
        if (dormitoryToDel != null) {
            Integer dormitoryToDelId = Integer.parseInt(dormitoryToDel);

            StudentRepository.removeAllFromDormitory(dormitoryToDelId);
            RoomRepository.deleteByDormitoryId(dormitoryToDelId);
            BlockRepository.deleteByDormitoryId(dormitoryToDelId);
            DormitoryRepository.delete(dormitoryToDelId);

            for( int j = 0; j < dormitories.size(); j++) {
                if (dormitories.get(j).getDormitoryId() == dormitoryToDelId){
                    dormitories.remove(j);
                    break;
                }
            }
        }
        request.getSession().setAttribute(DORMITORIES, dormitories);
        return Pages.EDIT_DORMITORY.getPagePath();
    }
}
