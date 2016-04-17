package Model;

public class Block {

	private int blockId;
	private int blockNumber;
	private int dormitoryId;

	public Block(){ }

	public int getBlockId(){
		return blockId;
	}

	public int getBlockNumber(){
		return blockNumber;
	}

	public int getDormitoryId(){
		return dormitoryId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public void setBlockNumber(int blockNumber){
		this.blockNumber = blockNumber;
	}

	public void setDormitoryId(int dormitoryId){
		this.dormitoryId = dormitoryId;
	}
}