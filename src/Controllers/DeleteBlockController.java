package Controllers;

import Interfaces.IController;
import Model.Block;
import Model.Dormitory;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DeleteBlockController implements IController {

    private static final String BLOCKS = "blocks";
    private static final String DORMITORIES = "dormitories";
    private static final String BLOCK_TO_DELETE = "blockToDel";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Block> blocks = (ArrayList<Block>)request.getSession().getAttribute(BLOCKS);

        String blockToDel = request.getParameter(BLOCK_TO_DELETE);
        if (blockToDel != null) {
            Integer blockToDelId = Integer.parseInt(blockToDel);
            StudentRepository.removeAllFromBlock(blockToDelId);
            RoomRepository.deleteByBlockId(blockToDelId);
            BlockRepository.delete(blockToDelId);
            for( int j = 0; j < blocks.size(); j++) {
                if (blocks.get(j).getBlockId() == blockToDelId){
                    Integer dormitoryID = blocks.get(j).getDormitoryId();
                    Dormitory dormitory = DormitoryRepository.read(dormitoryID);
                    dormitory.setFreeBlocksCount(dormitory.getFreeBlocksCount()+1);
                    DormitoryRepository.update(dormitoryID, dormitory.getFreeBlocksCount());
                    blocks.remove(j);
                    break;
                }
            }
        }
        request.getSession().setAttribute(DORMITORIES, DormitoryRepository.readAll());
        request.getSession().setAttribute(BLOCKS, blocks);

        return Pages.EDIT_BLOCK.getPagePath();
    }
}
