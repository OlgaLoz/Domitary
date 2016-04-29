package Repositories;

import Model.Block;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlockRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        int expectedDormitoryId = 18, expectedBlockNumber = 4;

        Block expectedBlock = new Block();
        expectedBlock.setDormitoryId(expectedDormitoryId);
        expectedBlock.setBlockNumber(expectedBlockNumber);

        int last_inserted_id = BlockRepository.create(expectedBlock);
        Block actualBlock = BlockRepository.read(last_inserted_id);
        BlockRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedDormitoryId, actualBlock.getDormitoryId());
        Assert.assertEquals(expectedBlockNumber, actualBlock.getBlockNumber());
    }

    @Test
    public void testUpdateReadDelete() throws Exception {
        int expectedDormitoryId = 18, expectedBlockNumber = 4, updateValue = 5;

        Block expectedBlock = new Block();
        expectedBlock.setDormitoryId(expectedDormitoryId);
        expectedBlock.setBlockNumber(expectedBlockNumber);

        int last_inserted_id = BlockRepository.create(expectedBlock);
        expectedBlock.setBlockNumber(updateValue);
        BlockRepository.update(expectedBlock);

        Block actualBlock = BlockRepository.read(last_inserted_id);
        BlockRepository.delete(last_inserted_id);

        Assert.assertEquals(expectedDormitoryId, actualBlock.getDormitoryId());
        Assert.assertEquals(updateValue, actualBlock.getBlockNumber());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
