package Controllers;

import Interfaces.IController;
import Model.Dormitory;
import Repositories.DormitoryRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AddDormitoryController implements IController {

    private static final String ADDRESS = "address";
    private static final String DORMITORY_NUMBER = "numberD";
    private static final String MAX_BLOCK_COUNT = "maxBlock";
    private static final String DORMITORIES = "dormitories";

    @Override
    public String run(HttpServletRequest request) {
        ArrayList<Dormitory> dormitories = DormitoryRepository.readAll();
        Dormitory dormitory = new Dormitory();
        dormitory.setAddress(request.getParameter(ADDRESS));
        dormitory.setDormitoryNumber(Integer.parseInt(request.getParameter(DORMITORY_NUMBER)));
        dormitory.setMaxBlocksCount(Integer.parseInt(request.getParameter(MAX_BLOCK_COUNT)));
        dormitory.setFreeBlocksCount(Integer.parseInt(request.getParameter(MAX_BLOCK_COUNT)));

        dormitories.add(dormitory);
        DormitoryRepository.create(dormitory);

        request.getSession().setAttribute(DORMITORIES, dormitories);

        return Pages.EDIT_DORMITORY.getPagePath();
    }
}
