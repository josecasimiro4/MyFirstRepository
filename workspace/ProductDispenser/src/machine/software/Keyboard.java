package machine.software;
import isel.leic.utils.Time;
import Utils.InputManager;
import Utils.OutputManager;
import Utils.Resources;


public class Keyboard {
	
	private static final boolean SERIAL  = true;
	private static final int DATA_SIZE = 4;
	public static final char KEY_UP='u';
	public static final char KEY_DOWN='d';
	public static final char KEY_OK='o';
	public static final char NO_KEY=0;
	private static final char [] keyMap =  {'0','1','2','3',
											'4','5','6','7',
											'8','9','A',KEY_UP,
											'C','D',KEY_OK,KEY_DOWN};
	
	public static char getKey(int timeout){
		int elapsed = 0;		
		long lastTime = Time.getTimeInMillis();
		
		do{
			char key = getKey();
			if(key == NO_KEY){
				long curTime = Time.getTimeInMillis();
				elapsed += curTime - lastTime;
				lastTime=curTime;
			}else
			return key;
			
		}while(elapsed>=timeout);
		return NO_KEY;
	}
	
	private static char getKey() {
		
		int key;
		
		if (!SERIAL){
		
			if(InputManager.getBit(Resources.K_VAL) != 0){
			
				key = InputManager.getBits(Resources.keyInput, Resources.keyInput_POS);
			
				OutputManager.setMask(Resources.K_ACK);
			
				while(InputManager.getBit(Resources.K_VAL) != 0);
			
				OutputManager.clearMask(Resources.K_ACK);
				return keyMap[key];
			
			}
			return NO_KEY;
		}
		else{
			int data = 0;
			if (getKXD() !=0) return NO_KEY;
			int tempKxd;
			for (int i=0; i < DATA_SIZE; ++i) {
				data|= getKXD()<<i;
			}
			//STOP //clock 
			//verificação se ta a 1
			
			
			if(getKXD()==0){
				
				//SINCRONISMO
				while (getKXD()== 0);
				return NO_KEY;
			}
			
			return keyMap[data];
		}
	}

	public static int getKXD(){
		OutputManager.clearMask(Resources.KCK);
		Time.sleep(0);
		OutputManager.setMask(Resources.KCK);
		return  InputManager.getBits(Resources.KXD, Resources.KXD_POS);
		
		
	}

	public static void init() {
		OutputManager.clearMask(Resources.K_ACK);
		OutputManager.setMask(Resources.KCK);
		
	}
	
	

}
