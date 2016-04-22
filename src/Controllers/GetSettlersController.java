package Controllers;

import Interfaces.IController;
import Model.Room;
import Repositories.RoomRepository;
import Utils.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by maksi on 22-Apr-16.
 */
public class GetSettlersController implements IController {

    private static final String ROOMS_ATTRIBUTE = "rooms";

    @Override
    public String run(HttpServletRequest request) {
        RoomRepository repository = new RoomRepository();
        ArrayList<Room> rooms = repository.readAll();
        request.setAttribute(ROOMS_ATTRIBUTE, rooms);
        return Pages.HOME_SETTLERS.getPagePath();
    }
}
