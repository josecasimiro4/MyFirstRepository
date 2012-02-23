package machine.software;
import java.util.Scanner;

import Utils.Emitter;
import Utils.InputManager;
import Utils.MaskManager;
import Utils.OutputManager;
import isel.leic.utils.Time;


public class Dispenser {

	public static boolean eject(int prod){		
		return Emitter.sendTrama(prod<<1, 5);
	}
}
	                                       
	                                     
	

