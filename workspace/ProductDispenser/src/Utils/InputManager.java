package Utils;
public class InputManager {

	public static int readNibble(){		
		return NotUsbPort.in() & 0xFF;
	}
	
	public static int getBit(int mask){
		
		return readNibble()&mask; 
	}

	public static int getBits(int keyInput, int keyInputPos) {
		return getBit(keyInput)>>keyInputPos;
		
	}
	
}
