package week1.day1;

public class Android {
	
	long serialNum = 86654765;
	boolean withNFC = true;
	String brand = "OnePlus";
	int price = 35000;
	float osVersion = 10f;
	
	public static void main(String[] args) {
		
		Android android = new Android();
		
		System.out.println(android.serialNum);
		System.out.println(android.withNFC);
		System.out.println(android.brand);
		System.out.println(android.price);
		System.out.println(android.osVersion);
		
	}
	
	

}
