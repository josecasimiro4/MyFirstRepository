package Utils;

import isel.leic.utils.Time;

public class Emitter {


	private static final int MIXD = 0x1;
	private static final int MICK = 0x8;
	private static final int RX_READY = 0x1;
	private static final int TIMEOUT = 5000;
	
	public static void init(){
		OutputManager.setMask(MIXD);
		//OutputManager.setMask(MICK);
		setMICK();
	}
	
	private static boolean isBusy (){
		
		long  time = Time.getTimeInMillis() + TIMEOUT;
		
			while((Time.getTimeInMillis() < time )){
				
				if (InputManager.getBit(RX_READY)!=0) return false;
			}
			return true;
	}
	


	public static boolean sendTrama(int bits, int size){
		/*if(!waitForNotBusy())
				return false;*/
		if(isBusy())return false;
		
		startCondition();
	
		for(int i=0;i<size;++i)
			sendBit(((bits>>i)&0x1)==1);
		
		goIdle();
		
		return true;
		
		
	}
	private static void goIdle() {
		setMICK();
		OutputManager.setMask(MIXD);		
	}
	private static void startCondition() {
		
		
		clearMICK();		
		OutputManager.clearMask(MIXD);
			
	}
	private static void setMICK(){
		
		OutputManager.clearMask(MICK);
		
	}
	private static void clearMICK(){
		
		OutputManager.setMask(MICK);

		
	}
	private static void sendBit(boolean b) {
		
		setMICK();
		
		if(b)
			OutputManager.setMask(MIXD);
		else
			OutputManager.clearMask(MIXD);
		
		 clearMICK();
		
	}
	
	@SuppressWarnings("unused")
	private static boolean waitForNotBusy() {
		return false;
		
	}
	
}
