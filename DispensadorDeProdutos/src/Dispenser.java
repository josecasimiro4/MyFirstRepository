import Utils.InputManager;
import Utils.MaskManager;
import Utils.OutputManager;
import isel.leic.utils.Time;


public class Dispenser {

	private static final int EJECT = 0x10;
	private static final int FINISH = 0x1;
	private static final int DATA = 0XF0;
	private static final int DATA_POS = MaskManager.calcPosFromMask(DATA);
	
	private static final int TIMEOUT = 2000;
	
	public boolean eject(int prod){
		long  time = Time.getTimeInMillis();
		boolean ret = true;
		while(!ready() ){
			return calcTimeOut(time,time=Time.getTimeInMillis());
		}	
		
		OutputManager.setBits(DATA, prod,DATA_POS);
		OutputManager.setMask(EJECT);
		
		time = Time.getTimeInMillis();
		
		while(ready())
			return calcTimeOut(time,time=Time.getTimeInMillis());
		
		OutputManager.clearMask(EJECT);
		
		return ret;
	}
	
	private boolean calcTimeOut(long timeLast, long timeCurr ){		
		return timeLast+TIMEOUT < timeCurr;
	}
	
	private boolean ready(){
		return InputManager.getBit(FINISH) == 0;
		
	}
	public static void main(String[] args){
		
		
	}
	                                       
	                                     
	
}
