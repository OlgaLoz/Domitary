package Controllers;

import Interfaces.IController;
import Model.Block;
import Model.Dormitory;
import Model.Room;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

public class EditBlockController  extends ActionSupport implements IController {

    private ArrayList<Dormitory> dormitories;
    private ArrayList<Block> blocks;

    private static String dormitoryId;
    private String dormError;
    private String blockError;
    private String blockToDel;

    private String numberB;
    private String numberD;

    public ArrayList<Dormitory> getDormitories(){
        return dormitories;
    }

    public ArrayList<Block> getBlocks(){
        return blocks;
    }

    public void setDormitoryId(String dormitoryId){
        this.dormitoryId = dormitoryId;
    }

    public String getDormError(){
        return dormError;
    }

    public String getBlockError(){
        return blockError;
    }

    public void setBlockToDel(String blockToDel){
        this.blockToDel = blockToDel;
    }

    public void setNumberB(String numberB){
        this.numberB = numberB;
    }

    public void setNumberD(String numberD){
        this.numberD = numberD;
    }

    public String getDormitoryBlocks(){
        dormitories = DormitoryRepository.readAll();
        blocks = null;
        dormError = "";

        if (dormitoryId != null) {
            blocks = BlockRepository.readAllByDormitoryId(Integer.parseInt(dormitoryId));
        }

        return Pages.EDIT_BLOCK.getPageName();
    }

    public String addBlock(){
        dormitories = DormitoryRepository.readAll();

        String newBlockDormitoryNumber = numberD;

        boolean isDormitoryExist = false;
        boolean isBlockCountCorrect = false;
        Integer dormitoryIDint = 0;
        Integer freeBlocksCount = 0;

        for(int i = 0; i< dormitories.size(); i++){
            if (dormitories.get(i).getDormitoryNumber() == Integer.parseInt(newBlockDormitoryNumber)){
                isDormitoryExist = true;
                dormitoryIDint = dormitories.get(i).getDormitoryId();
                dormitoryId = dormitoryIDint.toString();

                freeBlocksCount = dormitories.get(i).getFreeBlocksCount();
                if (freeBlocksCount != 0) {
                    isBlockCountCorrect = true;
                    dormitories.get(i).setFreeBlocksCount(--freeBlocksCount);
                }
                break;
            }
        }

       blocks = BlockRepository.readAllByDormitoryId(dormitoryIDint);

        if (!isDormitoryExist){
            dormError =  "Такого общежития нет.";
            return Pages.EDIT_BLOCK.getPageName();
        }

        if (!isBlockCountCorrect){
            blockError =  "Мест для добавления нет.";
            return Pages.EDIT_BLOCK.getPageName();
        }

        dormError = "";
        blockError = "";

        Integer newBlockNumber = Integer.parseInt(numberB);

        Block block = new Block();
        block.setBlockNumber(newBlockNumber);
        block.setDormitoryId(dormitoryIDint);
        BlockRepository.create(block);

        Room roomA = new Room();
        roomA.setBlockNumber(newBlockNumber);
        roomA.setFreePlacesCount(2);
        roomA.setMaxPlacesCount(2);
        roomA.setRoomNumber(1);
        roomA.setBlockId(block.getBlockId());
        RoomRepository.create(roomA);
        block.getRooms().add(roomA);

        Room roomB = new Room();
        roomB.setBlockNumber(newBlockNumber);
        roomB.setFreePlacesCount(3);
        roomB.setMaxPlacesCount(3);
        roomB.setRoomNumber(2);
        roomB.setBlockId(block.getBlockId());
        RoomRepository.create(roomB);
        block.getRooms().add(roomB);

        blocks.add(block);

        DormitoryRepository.update(dormitoryIDint, freeBlocksCount);


        return Pages.EDIT_BLOCK.getPageName();
    }

    public String deleteBlock(){
        blocks = BlockRepository.readAllByDormitoryId(Integer.parseInt(dormitoryId));

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
        dormitories = DormitoryRepository.readAll();

        return Pages.EDIT_BLOCK.getPageName();
    }
}
