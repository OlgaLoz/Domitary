package Repositories;

import Model.Block;
import Model.Dormitory;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BlockRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        int expectedDormitoryId = 13, expectedBlockNumber = 4;

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
        int expectedDormitoryId = 13, expectedBlockNumber = 4, updateValue = 5;

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

    @Test
    public void testCreateReadAllDeleteByDormitoryId() throws Exception {
        int expectedBlockNumber = 10;

        Dormitory dormitory = new Dormitory();
        dormitory.setDormitoryNumber(7);
        dormitory.setFreeBlocksCount(10);
        dormitory.setMaxBlocksCount(10);
        dormitory.setAddress("qweqwe");
        DormitoryRepository.create(dormitory);

        ArrayList<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < 3; i++){
            Block block = new Block();
            block.setDormitoryId(dormitory.getDormitoryId());
            block.setBlockNumber(expectedBlockNumber + i);
            blocks.add(block);
            BlockRepository.create(block);
        }

        ArrayList<Block> actualBlocks = BlockRepository.readAllByDormitoryId(dormitory.getDormitoryId());
        BlockRepository.delete(blocks.get(0).getBlockId());
        BlockRepository.delete(blocks.get(1).getBlockId());
        BlockRepository.delete(blocks.get(2).getBlockId());
        DormitoryRepository.delete(dormitory.getDormitoryId());

        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(0).getDormitoryId());
        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(1).getDormitoryId());
        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(2).getDormitoryId());
        Assert.assertEquals(blocks.get(0).getBlockNumber(), actualBlocks.get(0).getBlockNumber());
        Assert.assertEquals(blocks.get(1).getBlockNumber(), actualBlocks.get(1).getBlockNumber());
        Assert.assertEquals(blocks.get(2).getBlockNumber(), actualBlocks.get(2).getBlockNumber());
    }

    @Test
    public void testCreateAllReadAllDeleteAll() throws Exception {

        Dormitory dormitory = new Dormitory();
        dormitory.setDormitoryNumber(7);
        dormitory.setFreeBlocksCount(10);
        dormitory.setMaxBlocksCount(10);
        dormitory.setAddress("qweqwe");
        DormitoryRepository.create(dormitory);

        Block[] blocks = new Block[3];
        for (int i = 0; i < 3; i++){
            Block block = new Block();
            block.setDormitoryId(dormitory.getDormitoryId());
            block.setBlockNumber(10 + i);
            blocks[i] = block;
        }

        BlockRepository.addAll(blocks);

        ArrayList<Block> actualBlocks = BlockRepository.readAllByDormitoryId(dormitory.getDormitoryId());
        ArrayList<Integer> idList = new ArrayList<Integer>();
        idList.add(blocks[0].getBlockId());
        idList.add(blocks[1].getBlockId());
        idList.add(blocks[2].getBlockId());
        BlockRepository.deleteAll(idList);
        DormitoryRepository.delete(dormitory.getDormitoryId());

        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(0).getDormitoryId());
        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(1).getDormitoryId());
        Assert.assertEquals(dormitory.getDormitoryId(), actualBlocks.get(2).getDormitoryId());
        Assert.assertEquals(blocks[0].getBlockNumber(), actualBlocks.get(0).getBlockNumber());
        Assert.assertEquals(blocks[1].getBlockNumber(), actualBlocks.get(1).getBlockNumber());
        Assert.assertEquals(blocks[2].getBlockNumber(), actualBlocks.get(2).getBlockNumber());
    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }
}
