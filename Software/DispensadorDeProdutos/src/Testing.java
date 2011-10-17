import Utils.InputManager;
import Utils.OutputManager;


public class Testing {
	
	
	
	public static void main (String[] args){
		
		OutputManager.setNibble(0xAA);
		
		System.out.println();
		System.out.println(Integer.toHexString(InputManager.readNibble()));
		
		
	}
}
