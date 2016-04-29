package Controllers;

import Interfaces.IController;
import Model.Block;
import Model.Dormitory;
import Model.Room;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Repositories.RoomRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AddBlockController implements IController {
    private static final String DORMITORY_ERROR = "dormError";
    private static final String BLOCK_ERROR = "blockError";
    private static final String BLOCK_NUMBER = "numberB";
    private static final String DORMITORY_NUMBER = "numberD";
    private static final String BLOCKS = "blocks";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Dormitory> dormitories = DormitoryRepository.readAll();

        String newBlockNumber = request.getParameter(BLOCK_NUMBER);
        String newBlockDormitoryNumber = request.getParameter(DORMITORY_NUMBER);

        boolean isDormitoryExist = false;
        boolean isBlockCountCorrect = false;
        Integer dormitoryID = 0;

        for(int i = 0; i< dormitories.size(); i++){
            if (dormitories.get(i).getDormitoryNumber() == Integer.parseInt(newBlockDormitoryNumber)){
                isDormitoryExist = true;
                dormitoryID = dormitories.get(i).getDormitoryId();
                if (dormitories.get(i).getFreeBlocksCount() != 0)
                    isBlockCountCorrect = true;
                break;
            }
        }

        ArrayList<Block> blocks = BlockRepository.readAllByDormitoryId(dormitoryID);

        if (!isDormitoryExist){
            request.getSession().setAttribute(DORMITORY_ERROR, "Такого общежития нет.");
            return Pages.EDIT_BLOCK.getPagePath();
        }

        if (!isBlockCountCorrect){
            request.getSession().setAttribute(BLOCK_ERROR, "Мест для добавления нет.");
            return Pages.EDIT_BLOCK.getPagePath();
        }

        request.getSession().setAttribute(DORMITORY_ERROR, "");
        request.getSession().setAttribute(BLOCK_ERROR, "");

        Block block = new Block();
        block.setBlockNumber(Integer.parseInt(request.getParameter(BLOCK_NUMBER)));
        block.setDormitoryId(dormitoryID);
        BlockRepository.create(block);

        Room roomA = new Room();
        roomA.setBlockNumber(Integer.parseInt(request.getParameter(BLOCK_NUMBER)));
        roomA.setFreePlacesCount(2);
        roomA.setMaxPlacesCount(2);
        roomA.setRoomNumber(1);
        roomA.setBlockId(block.getBlockId());
        RoomRepository.create(roomA);
        block.getRooms().add(roomA);

        Room roomB = new Room();
        roomB.setBlockNumber(Integer.parseInt(request.getParameter(BLOCK_NUMBER)));
        roomB.setFreePlacesCount(3);
        roomB.setMaxPlacesCount(3);
        roomB.setRoomNumber(2);
        roomB.setBlockId(block.getBlockId());
        RoomRepository.create(roomB);
        block.getRooms().add(roomB);

        blocks.add(block);

        request.getSession().setAttribute(BLOCKS, blocks);

        return Pages.EDIT_BLOCK.getPagePath();
    }
}
