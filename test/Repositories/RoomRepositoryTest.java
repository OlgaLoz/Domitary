package Repositories;

import Model.Block;
import Model.Room;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RoomRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {
        int expectedBlockId = 16, expectedRoomNumber = 1, expectedMaxPlacesCount = 4, expectedFreePlacesCount = 2;

        Room expectedRoom = new Room();
        expectedRoom.setBlockId(expectedBlockId);
        expectedRoom.setRoomNumber(expectedRoomNumber);
        expectedRoom.setMaxPlacesCount(expectedMaxPlacesCount);
        expectedRoom.setFreePlacesCount(expectedFreePlacesCount);

        int last_inserted_id = RoomRepository.create(expectedRoom);
        Room actualRoom = RoomRepository.read(last_inserted_id);
        RoomRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedBlockId, actualRoom.getBlockId());
        Assert.assertEquals(expectedRoomNumber, actualRoom.getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount, actualRoom.getMaxPlacesCount());
        Assert.assertEquals(expectedFreePlacesCount, actualRoom.getFreePlacesCount());
    }

    @Test
    public void testUpdateReadDelete() throws Exception {
        int expectedBlockId = 16, expectedRoomNumber = 1, expectedMaxPlacesCount = 4, freePlacesCount = 2, expectedFreePlacesCount = 1;

        Room expectedRoom = new Room();
        expectedRoom.setBlockId(expectedBlockId);
        expectedRoom.setRoomNumber(expectedRoomNumber);
        expectedRoom.setMaxPlacesCount(expectedMaxPlacesCount);
        expectedRoom.setFreePlacesCount(freePlacesCount);

        int last_inserted_id = RoomRepository.create(expectedRoom);
        expectedRoom.setRoomId(last_inserted_id);
        expectedRoom.setFreePlacesCount(expectedFreePlacesCount);
        RoomRepository.update(expectedRoom);

        Room actualRoom = RoomRepository.read(last_inserted_id);
        RoomRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedBlockId, actualRoom.getBlockId());
        Assert.assertEquals(expectedRoomNumber, actualRoom.getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount, actualRoom.getMaxPlacesCount());
        Assert.assertEquals(expectedFreePlacesCount, actualRoom.getFreePlacesCount());
    }

    @Test
    public void testCreateReadAllDeleteByBlockId() throws Exception {
        int actualBlockId, expectedRoomNumber1 = 1, expectedMaxPlacesCount1 = 4, freePlacesCount1 = 4;
        int expectedRoomNumber2 = 2, expectedMaxPlacesCount2 = 2, freePlacesCount2 = 2;

        Block block = new Block();
        block.setDormitoryId(18);
        block.setBlockNumber(10);
        BlockRepository.create(block);

        Room expectedRoom1 = new Room();
        expectedRoom1.setBlockId(block.getBlockId());
        expectedRoom1.setRoomNumber(expectedRoomNumber1);
        expectedRoom1.setMaxPlacesCount(expectedMaxPlacesCount1);
        expectedRoom1.setFreePlacesCount(freePlacesCount1);

        Room expectedRoom2 = new Room();
        expectedRoom2.setBlockId(block.getBlockId());
        expectedRoom2.setRoomNumber(expectedRoomNumber2);
        expectedRoom2.setMaxPlacesCount(expectedMaxPlacesCount2);
        expectedRoom2.setFreePlacesCount(freePlacesCount2);

        RoomRepository.create(expectedRoom1);
        RoomRepository.create(expectedRoom2);

        ArrayList<Room> actualRooms = RoomRepository.readAllByBlockId(block.getBlockId());
        RoomRepository.delete(block.getBlockId());
        BlockRepository.delete(block.getBlockId());

        Assert.assertEquals(block.getBlockId(), actualRooms.get(0).getBlockId());
        Assert.assertEquals(expectedRoomNumber1, actualRooms.get(0).getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount1, actualRooms.get(0).getMaxPlacesCount());
        Assert.assertEquals(freePlacesCount1, actualRooms.get(0).getFreePlacesCount());
        Assert.assertEquals(block.getBlockId(), actualRooms.get(1).getBlockId());
        Assert.assertEquals(expectedRoomNumber2, actualRooms.get(1).getRoomNumber());
        Assert.assertEquals(expectedMaxPlacesCount2, actualRooms.get(1).getMaxPlacesCount());
        Assert.assertEquals(freePlacesCount2, actualRooms.get(1).getFreePlacesCount());
    }

    @Test
    public void testCreateAllReadAllDeleteAll() throws Exception {

        Block block = new Block();
        block.setDormitoryId(18);
        block.setBlockNumber(10);
        BlockRepository.create(block);

        Room expectedRoom1 = new Room();
        expectedRoom1.setBlockId(block.getBlockId());
        expectedRoom1.setRoomNumber(1);
        expectedRoom1.setMaxPlacesCount(4);
        expectedRoom1.setFreePlacesCount(4);

        Room expectedRoom2 = new Room();
        expectedRoom2.setBlockId(block.getBlockId());
        expectedRoom2.setRoomNumber(2);
        expectedRoom2.setMaxPlacesCount(2);
        expectedRoom2.setFreePlacesCount(2);

        Room[] rooms = new Room[2];
        rooms[0] = expectedRoom1;
        rooms[1] = expectedRoom2;

        RoomRepository.addAll(rooms);

        ArrayList<Room> actualRooms = RoomRepository.readAllByBlockId(block.getBlockId());
        ArrayList<Integer> idList = new ArrayList<Integer>();
        idList.add(actualRooms.get(0).getRoomId());
        idList.add(actualRooms.get(1).getRoomId());
        RoomRepository.deleteAll(idList);
        BlockRepository.delete(block.getBlockId());

        Assert.assertEquals(block.getBlockId(), actualRooms.get(0).getBlockId());
        Assert.assertEquals(rooms[0].getRoomNumber(), actualRooms.get(0).getRoomNumber());
        Assert.assertEquals(rooms[0].getMaxPlacesCount(), actualRooms.get(0).getMaxPlacesCount());
        Assert.assertEquals(rooms[0].getFreePlacesCount(), actualRooms.get(0).getFreePlacesCount());
        Assert.assertEquals(block.getBlockId(), actualRooms.get(1).getBlockId());
        Assert.assertEquals(rooms[1].getRoomNumber(), actualRooms.get(1).getRoomNumber());
        Assert.assertEquals(rooms[1].getMaxPlacesCount(), actualRooms.get(1).getMaxPlacesCount());
        Assert.assertEquals(rooms[1].getFreePlacesCount(), actualRooms.get(1).getFreePlacesCount());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
