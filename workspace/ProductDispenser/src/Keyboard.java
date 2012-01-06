import Utils.InputManager;
import Utils.KeyMapper;
import Utils.OutputManager;
import Utils.Resources;


public class Keyboard {
	

	public static final char KEY_UP='u';
	public static final char KEY_DOWN='d';
	public static final char KEY_OK='o';
	public static final char NO_KEY=0;
	private static final char [] keyMap =  {'0','2','1','3',
											'8','A','9',KEY_UP,
											'4','6','5','7',
											'C','D',KEY_OK,KEY_DOWN};
	
	public static char getKey() {
		
		int key;
		if(InputManager.getBit(Resources.K_VAL) != 0){
			
			key = InputManager.getBits(Resources.keyInput, Resources.keyInput_POS);
			
			OutputManager.setMask(Resources.K_ACK);
			
			while(InputManager.getBit(Resources.K_VAL) != 0);
			OutputManager.clearMask(Resources.K_ACK);
			return keyMap[key];
			
		}
		return NO_KEY;
		
	}

	public static void init() {
		OutputManager.clearMask(Resources.K_ACK);
		
	}
	
	

}
