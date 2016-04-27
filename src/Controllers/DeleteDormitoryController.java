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
        DormitoryRepository dormitoryRepository = new DormitoryRepository();
        BlockRepository blockRepository = new BlockRepository();
        RoomRepository roomRepository = new RoomRepository();
        StudentRepository studentRepository = new StudentRepository();

        ArrayList<Dormitory> dormitories = dormitoryRepository.readAll();

        String dormitoryToDel = request.getParameter(DORMITORY_TO_DELETE);
        if (dormitoryToDel != null) {
            Integer dormitoryToDelId = Integer.parseInt(dormitoryToDel);

            studentRepository.removeAllFromDormitory(dormitoryToDelId);
            roomRepository.deleteByDormitoryId(dormitoryToDelId);
            blockRepository.deleteByDormitoryId(dormitoryToDelId);
            dormitoryRepository.delete(dormitoryToDelId);

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
