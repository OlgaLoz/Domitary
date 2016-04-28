package Model;

import java.util.ArrayList;

public class Block {

	private int blockId;
	private int blockNumber;
	private int dormitoryId;
	private ArrayList<Room> rooms = new ArrayList<Room>();

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

	public ArrayList<Room> getRooms(){ return rooms; }

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public void setBlockNumber(int blockNumber){
		this.blockNumber = blockNumber;
	}

	public void setDormitoryId(int dormitoryId){
		this.dormitoryId = dormitoryId;
	}

	public void setRooms(ArrayList<Room> rooms){
		this.rooms = rooms;
	}
}