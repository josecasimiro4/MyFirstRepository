package Utils;


public class OutputManager {

	private static int _image=0x0;
	
	public static void setNibble(int input){
		_image = input;
		NotUsbPort.out(input);
	}
	
	public static void setBits(int mask, int val, int pos){
		_image &= ~mask;
		_image |= val<<pos;
		
		NotUsbPort.out(_image);
	}
	
	public static void setMask(int mask)
	{
		_image |= mask;
		NotUsbPort.out(_image);
	}
	
	public static void clearMask(int mask)
	{
		_image &= ~mask;
		NotUsbPort.out(_image);
	}
	
	public static void toggleMask(int mask){
		_image ^= mask;
		NotUsbPort.out(_image);
	}
	
	
}
