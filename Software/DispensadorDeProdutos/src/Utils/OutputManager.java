package Utils;


public class OutputManager {

	private static int _image=0x0;
	
	public static void setBits(int mask, int val, int pos){
		
		int oldImg = _image;
		_image &= ~mask;
		_image |= val<<pos;
		
		if(oldImg!=_image)
			NotUsbPort.out(_image);
	}
	
	public static void setMask(int mask)
	{
		int oldImg = _image;
		_image |= mask;
		
		if(oldImg!=_image)
			NotUsbPort.out(_image);
	}
	
	public static void clearMask(int mask)
	{
		int oldImg = _image;
		_image &= ~mask;
		
		if(oldImg!=_image)
			NotUsbPort.out(_image);
	}
	
	public static void toggleMask(int mask){
		int oldImg = _image;
		_image ^= mask;
		
		if(oldImg!=_image)
			NotUsbPort.out(_image);
	}
	
	
}
