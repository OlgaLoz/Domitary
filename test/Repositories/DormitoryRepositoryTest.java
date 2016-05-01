package Repositories;

import Model.Dormitory;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DormitoryRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        int expectedDormitoryNumber = 5, expectedMaxBlocksCount = 200, expectedFreeBlocksCount = 200;
        String expectedDormitoryAddress = "qwerty";

        Dormitory expectedDormitory = new Dormitory();
        expectedDormitory.setDormitoryNumber(expectedDormitoryNumber);
        expectedDormitory.setAddress(expectedDormitoryAddress);
        expectedDormitory.setMaxBlocksCount(expectedMaxBlocksCount);
        expectedDormitory.setFreeBlocksCount(expectedFreeBlocksCount);

        int last_inserted_id = DormitoryRepository.create(expectedDormitory);
        Dormitory actualDormitory = DormitoryRepository.read(last_inserted_id);

        DormitoryRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedDormitoryNumber, actualDormitory.getDormitoryNumber());
        Assert.assertEquals(expectedDormitoryAddress, actualDormitory.getAddress());
        Assert.assertEquals(expectedMaxBlocksCount, actualDormitory.getMaxBlocksCount());
        Assert.assertEquals(expectedFreeBlocksCount, actualDormitory.getFreeBlocksCount());
    }

    @Test
    public void testUpdateReadDelete() throws Exception {
        int expectedDormitoryNumber = 5, expectedMaxBlocksCount = 200, expectedFreeBlocksCount = 200, updateFreeBlocksCount = 100;
        String expectedDormitoryAddress = "qwerty", updateAddress = "asdfgh";

        Dormitory expectedDormitory = new Dormitory();
        expectedDormitory.setAddress(expectedDormitoryAddress);
        expectedDormitory.setDormitoryNumber(expectedDormitoryNumber);
        expectedDormitory.setMaxBlocksCount(expectedMaxBlocksCount);
        expectedDormitory.setFreeBlocksCount(expectedFreeBlocksCount);

        int last_inserted_id = DormitoryRepository.create(expectedDormitory);
        expectedDormitory.setAddress(updateAddress);
        expectedDormitory.setFreeBlocksCount(updateFreeBlocksCount);
        DormitoryRepository.update(expectedDormitory);

        Dormitory actualDormitory = DormitoryRepository.read(last_inserted_id);
        DormitoryRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedDormitoryNumber, actualDormitory.getDormitoryNumber());
        Assert.assertEquals(expectedMaxBlocksCount, actualDormitory.getMaxBlocksCount());
        Assert.assertEquals(updateFreeBlocksCount, actualDormitory.getFreeBlocksCount());
        Assert.assertEquals(updateAddress, actualDormitory.getAddress());
    }

    @Test
    public void testAddAllReadDelete() throws Exception {

        Dormitory expectedDormitory1 = new Dormitory();
        expectedDormitory1.setDormitoryNumber(8);
        expectedDormitory1.setAddress("qwerty");
        expectedDormitory1.setMaxBlocksCount(20);
        expectedDormitory1.setFreeBlocksCount(20);

        Dormitory expectedDormitory2 = new Dormitory();
        expectedDormitory2.setDormitoryNumber(9);
        expectedDormitory2.setAddress("asdfgh");
        expectedDormitory2.setMaxBlocksCount(10);
        expectedDormitory2.setFreeBlocksCount(10);

        Dormitory[] dormitories = new Dormitory[2];
        dormitories[0] = expectedDormitory1;
        dormitories[1] = expectedDormitory2;

        DormitoryRepository.addAll(dormitories);

        Dormitory actualDormitory1 = DormitoryRepository.read(expectedDormitory1.getDormitoryId());
        Dormitory actualDormitory2 = DormitoryRepository.read(expectedDormitory2.getDormitoryId());
        DormitoryRepository.delete(expectedDormitory1.getDormitoryId());
        DormitoryRepository.delete(expectedDormitory2.getDormitoryId());

        Assert.assertEquals(expectedDormitory1.getDormitoryNumber(), actualDormitory1.getDormitoryNumber());
        Assert.assertEquals(expectedDormitory1.getAddress(), actualDormitory1.getAddress());
        Assert.assertEquals(expectedDormitory1.getFreeBlocksCount(), actualDormitory1.getFreeBlocksCount());
        Assert.assertEquals(expectedDormitory1.getMaxBlocksCount(), actualDormitory1.getMaxBlocksCount());
        Assert.assertEquals(expectedDormitory2.getDormitoryNumber(), actualDormitory2.getDormitoryNumber());
        Assert.assertEquals(expectedDormitory2.getAddress(), actualDormitory2.getAddress());
        Assert.assertEquals(expectedDormitory2.getFreeBlocksCount(), actualDormitory2.getFreeBlocksCount());
        Assert.assertEquals(expectedDormitory2.getMaxBlocksCount(), actualDormitory2.getMaxBlocksCount());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
