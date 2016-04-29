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

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
