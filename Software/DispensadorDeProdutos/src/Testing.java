import Model.LCD;


public class Testing {
	
	
	
	public static void main (String[] args){
		
		System.out.println("Initiating..");
		LCD.init();
		System.out.println("Initiated");
		
		LCD.writeString("Its alive!");
	
		
	}
}
