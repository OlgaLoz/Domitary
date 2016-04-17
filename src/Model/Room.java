package Model;

public class Room {

	private int blockId;
	private int roomId;
	private int roomNumber;
	private int maxPlacesCount;
	private int freePlacesCount;

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

	public void setBlockId(int blockId){ this.blockId = blockId; }

	public void setRoomId(int roomId){ this.roomId = roomId; }

	public void setRoomNumber(int roomNumber){ this.roomNumber = roomNumber; }

	public void setMaxPlacesCount(int maxPlacesCount){ this.maxPlacesCount = maxPlacesCount; }

	public void setFreePlacesCount(int freePlacesCount){ this.freePlacesCount = freePlacesCount; }
}