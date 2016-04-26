package Model;

public class Dormitory {

	private String address;
	private int dormitoryId;
	private int dormitoryNumber;
	private int maxBlocksCount;
	private int freeBlocksCount;

	public Dormitory(){	}

	public String getAddress(){
		return address;
	}

	public int getDormitoryId(){
		return dormitoryId;
	}

	public int getDormitoryNumber(){
		return  dormitoryNumber;
	}

	public int getMaxBlocksCount(){
		return maxBlocksCount;
	}

	public int getFreeBlocksCount(){
		return freeBlocksCount;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setDormitoryId(int dormitoryId){
		this.dormitoryId = dormitoryId;
	}

	public void setDormitoryNumber(int dormitoryNumber){
		this.dormitoryNumber = dormitoryNumber;
	}

	public void setMaxBlocksCount(int maxBlocksCount){
		this.maxBlocksCount = maxBlocksCount;
	}

	public void setFreeBlocksCount(int freeBlocksCount){
		this.freeBlocksCount = freeBlocksCount;
	}
}