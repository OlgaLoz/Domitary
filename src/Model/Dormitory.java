package Model;

public class Dormitory {

	private String adress;
	private int dormitoryId;
	private int dormitoryNumber;

	public Dormitory(){	}

	public String getAdress(){
		return adress;
	}

	public int getDormitoryId(){
		return dormitoryId;
	}

	public int getDormitoryNumber(){
		return  dormitoryNumber;
	}

	public void setAdress(String adress){
		this.adress = adress;
	}

	public void setDormitoryId(int dormitoryId){
		this.dormitoryId = dormitoryId;
	}

	public void setDormitoryNumber(int dormitoryNumber){
		this.dormitoryNumber = dormitoryNumber;
	}
}