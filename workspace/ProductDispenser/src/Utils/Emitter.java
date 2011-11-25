package Utils;

public class Emitter {


	private static final int MIXD = 0x1;
	private static final int MICK = 0x8;	
	
	public static void init(){
		OutputManager.setMask(MIXD);
		OutputManager.setMask(MICK);
	}
	public static boolean sendTrama(int bits, int size){
		/*if(!waitForNotBusy())
				return false;*/
		
		startCondition();
	
		for(int i=0;i<size;++i)
			sendBit(((bits>>i)&0x1)==1);
		
		goIdle();
		
		return true;
		
		
	}
	private static void goIdle() {
		OutputManager.setMask(MIXD);		
	}
	private static void startCondition() {
		OutputManager.clearMask(MICK);		
		OutputManager.clearMask(MIXD);
			
	}
	private static void sendBit(boolean b) {
		OutputManager.setMask(MICK);
		
		if(b)
			OutputManager.setMask(MIXD);
		else
			OutputManager.clearMask(MIXD);
		
		OutputManager.clearMask(MICK);
		
	}
	
	@SuppressWarnings("unused")
	private static boolean waitForNotBusy() {
		return false;
		
	}
	
}
