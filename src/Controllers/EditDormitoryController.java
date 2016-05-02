package Controllers;

import Interfaces.IController;
import Model.Dormitory;
import Repositories.BlockRepository;
import Repositories.DormitoryRepository;
import Repositories.RoomRepository;
import Repositories.StudentRepository;
import Utils.Pages;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

public class EditDormitoryController  extends ActionSupport implements IController {

    private ArrayList<Dormitory> dormitories;
    private String dormitoryToDel;
    private String address;
    private String numberD;
    private String maxBlock;

    public ArrayList<Dormitory> getDormitories(){
        return dormitories;
    }

    public void setDormitoryToDel(String dormitoryToDel){
        this.dormitoryToDel = dormitoryToDel;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setNumberD(String numberD){
        this.numberD = numberD;
    }

    public void setMaxBlock(String maxBlock){
        this.maxBlock = maxBlock;
    }

    public  String deleteDormitory(){
        dormitories = DormitoryRepository.readAll();

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

        return Pages.EDIT_DORMITORY.getPageName();
    }

    public String addDormitory(){
        dormitories = DormitoryRepository.readAll();
        if (address != null && numberD != null && maxBlock != null) {
            Dormitory dormitory = new Dormitory();
            dormitory.setAddress(address);
            dormitory.setDormitoryNumber(Integer.parseInt(numberD));
            dormitory.setMaxBlocksCount(Integer.parseInt(maxBlock));
            dormitory.setFreeBlocksCount(Integer.parseInt(maxBlock));

            dormitories.add(dormitory);
            DormitoryRepository.create(dormitory);
        }

        return Pages.EDIT_DORMITORY.getPageName();
    }
}
