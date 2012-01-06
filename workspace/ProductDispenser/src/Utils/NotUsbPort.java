package Utils;
import isel.leic.usbio.UsbPort;

public class NotUsbPort {

	public static int in()
	{
		UsbPort.in();
		return ~UsbPort.in();
	}
	
	public static void out(int argumento)
	{
		UsbPort.out(~argumento);
	}
	
	
	
	
}
