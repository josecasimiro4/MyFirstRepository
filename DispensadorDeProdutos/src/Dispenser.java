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
		boolean ret=false;
		if(waitWhileReadyIs(false)){		
			OutputManager.setBits(DATA, prod,DATA_POS);
			OutputManager.setMask(EJECT);
			
			ret = waitWhileReadyIs(true);
			
			OutputManager.clearMask(EJECT);
		}		
		return ret;
	}
	
	private boolean waitWhileReadyIs(boolean b){
		long  time = Time.getTimeInMillis();
		while(ready() == b ){
			if(time+TIMEOUT < (time=Time.getTimeInMillis())){
				return false;
			}
		}
		return true;
		
	}
	
	private boolean ready(){
		return InputManager.getBit(FINISH) == 0;
		
	}
	public static void main(String[] args){
		
		
	}
	                                       
	                                     
	
}
