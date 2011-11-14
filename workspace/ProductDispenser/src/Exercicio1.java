
import Utils.InputManager;
import Utils.OutputManager;
import isel.leic.utils.*;
public class Exercicio1 {

	private static final int STOP = 0X2;
	private static final int CLOCK = 0X1;
	
	private static final int BLINK_LED = 0X1;
	private static final int LED_T = 0X2;
	
	private static final int ONE_SECOND = 1000;
	
	public static void main (String[] args){
		
		int clockValue = 0;
		
		long time= Time.getTimeInMillis(),currTime;
		
		while(isStopSignaled()){			
			if(clockValue == 0 && (clockValue= getClockValue())==1){
				toggleLedT();
			}
			
			currTime = Time.getTimeInMillis();
			if(currTime-time >= ONE_SECOND){
				toggleBlinkLed();
			}
			
		}
		
	}
	
	private static boolean isStopSignaled(){
		return InputManager.getBit(STOP)==1;
	}
	
	private static int getClockValue(){
		return InputManager.getBit(CLOCK);
	}
	
	private static void toggleLedT(){
		OutputManager.toggleMask(LED_T);
	}
	
	private static void toggleBlinkLed(){
		OutputManager.toggleMask(BLINK_LED);
	}
	
}
