import machine.software.LCD;
import Utils.Emitter;


public class Testing {
	
	
	
	public static void main (String[] args){
		
		System.out.println("Initiating..");
		Emitter.init();
		LCD.init();
		System.out.println("Initiated");
		
		LCD.writeString("Its alive!");
	
		
	}
}
