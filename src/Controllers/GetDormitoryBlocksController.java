package Controllers;

import Interfaces.IController;
import Model.Block;
import Model.Dormitory;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class GetDormitoryBlocksController implements IController {
    private static final String DORMITORIES = "dormitories";
    private static final String BLOCKS = "blocks";
    private static final String DORMITORY_ID = "dormitoryId";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Dormitory> dormitories = DormitoryRepository.readAll();
        ArrayList<Block> blocks = null;
        String dormitoryId = request.getParameter(DORMITORY_ID);
        if (dormitoryId != null) {
            blocks = BlockRepository.readAllByDormitoryId(Integer.parseInt(dormitoryId));
        }

        request.getSession().setAttribute("dormError", "");//to fix
        request.getSession().setAttribute(BLOCKS, blocks);
        request.getSession().setAttribute(DORMITORIES, dormitories);

        return Pages.EDIT_BLOCK.getPagePath();
    }
}
