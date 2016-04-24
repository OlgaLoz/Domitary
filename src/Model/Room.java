package Model;

public class Room {

	private int blockId;
	private int roomId;
	private int roomNumber;
	private int maxPlacesCount;
	private int freePlacesCount;
	private int blockNumber;
	private int dormitoryNumber;
	private int dormitoryId;

	public Room(){  }

	public int getBlockId(){ return blockId; }

	public int getRoomId(){
		return roomId;
	}

	public int getRoomNumber(){
		return roomNumber;
	}

	public int getMaxPlacesCount(){ return maxPlacesCount; }

	public int getFreePlacesCount(){
		return freePlacesCount;
	}

	public int getBlockNumber(){
		return blockNumber;
	}

	public int getDormitoryNumber(){
		return dormitoryNumber;
	}

	public int getDormitoryId(){ return dormitoryId; }

	public void setBlockId(int blockId){ this.blockId = blockId; }

	public void setRoomId(int roomId){ this.roomId = roomId; }

	public void setRoomNumber(int roomNumber){ this.roomNumber = roomNumber; }

	public void setMaxPlacesCount(int maxPlacesCount){ this.maxPlacesCount = maxPlacesCount; }

	public void setFreePlacesCount(int freePlacesCount){ this.freePlacesCount = freePlacesCount; }

	public void setBlockNumber(int blockNumber){ this.blockNumber = blockNumber; }

	public void setDormitoryNumber(int dormitoryNumber){ this.dormitoryNumber = dormitoryNumber; }

	public void setDormitoryId(int dormitoryId){ this.dormitoryId = dormitoryId; }
}