package Repositories;

import Model.Room;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by maksi on 18-Apr-16.
 */
public class RoomRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        int expectedBlockId = 1, expectedRoomNumber = 1, expectedMaxPlacesCount = 4, expectedFreePlacesCount = 2;

        Room expectedRoom = new Room();
        expectedRoom.setBlockId(expectedBlockId);
        expectedRoom.setRoomNumber(expectedRoomNumber);
        expectedRoom.setMaxPlacesCount(expectedMaxPlacesCount);
        expectedRoom.setFreePlacesCount(expectedFreePlacesCount);

        RoomRepository rr = new RoomRepository();
        int last_inserted_id = rr.create(expectedRoom);
        Room actualRoom = rr.read(last_inserted_id);

        rr.delete(last_inserted_id);

        Assert.assertEquals(expectedBlockId, actualRoom.getBlockId());
        Assert.assertEquals(expectedRoomNumber, actualRoom.getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount, actualRoom.getMaxPlacesCount());
        Assert.assertEquals(expectedFreePlacesCount, actualRoom.getFreePlacesCount());
    }

    @Test
    public void testUpdateReadDelete() throws Exception {
        int expectedBlockId = 1, expectedRoomNumber = 1, expectedMaxPlacesCount = 4, freePlacesCount = 2, expectedFreePlacesCount = 1;

        Room expectedRoom = new Room();
        expectedRoom.setBlockId(expectedBlockId);
        expectedRoom.setRoomNumber(expectedRoomNumber);
        expectedRoom.setMaxPlacesCount(expectedMaxPlacesCount);
        expectedRoom.setFreePlacesCount(freePlacesCount);

        RoomRepository rr = new RoomRepository();
        int last_inserted_id = rr.create(expectedRoom);
        expectedRoom.setRoomId(last_inserted_id);
        expectedRoom.setFreePlacesCount(expectedFreePlacesCount);
        rr.update(expectedRoom);

        Room actualRoom = rr.read(last_inserted_id);
        rr.delete(last_inserted_id);

        Assert.assertEquals(expectedBlockId, actualRoom.getBlockId());
        Assert.assertEquals(expectedRoomNumber, actualRoom.getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount, actualRoom.getMaxPlacesCount());
        Assert.assertEquals(expectedFreePlacesCount, actualRoom.getFreePlacesCount());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
