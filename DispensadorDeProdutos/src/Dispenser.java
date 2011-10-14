import java.util.Scanner;

import Utils.InputManager;
import Utils.MaskManager;
import Utils.OutputManager;
import isel.leic.utils.Time;


public class Dispenser {

	private static final int EJECT = 0x1;
	private static final int FINISH = 0x1;
	private static final int MASK_DATA = 0XF0;
	private static final int DATA_POS = MaskManager.calcPosFromMask(MASK_DATA);
	
	private static final int TIMEOUT = 9000;
	
	public static boolean eject(int prod){		
		boolean ret=false;
		if(waitWhileReadyIs(false)){		
			OutputManager.setBits(MASK_DATA, prod,DATA_POS);
			OutputManager.setMask(EJECT);
			
			ret = waitWhileReadyIs(true);
			
			OutputManager.clearMask(EJECT);
		}		
		return ret;
	}
	
	private static boolean waitWhileReadyIs(boolean b){
		long  time = Time.getTimeInMillis();
	
		while(ready() == b ){
			if(time+TIMEOUT < Time.getTimeInMillis()){
				System.out.println("Timeout");
				return false;
			}
		}
		System.out.println((b?"":"Not ")+"Ready");
		return true;
		
	}
	
	private static boolean ready(){
		return InputManager.getBit(FINISH) == 0;
		
	}
	public static void main(String[] args){

		Scanner kbd = new Scanner(System.in);
		int c=0;
		while(c!=-1){
			System.out.println("Insira o cod do produto");
			c=kbd.nextInt();
			eject(c);
		}
		
		
		
	}
	                                       
	                                     
	
}
