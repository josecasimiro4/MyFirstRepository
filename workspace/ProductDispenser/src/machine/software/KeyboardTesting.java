package machine.software;
import Utils.KeyMapper;
import Utils.OutputManager;




public class KeyboardTesting {
	
	public static void main (String[]args){
		
		char key;	
		OutputManager.init();
		Keyboard.init();
		while ((key=Keyboard.getKey())!= Keyboard.KEY_OK){
			if(key!=Keyboard.NO_KEY)
				System.out.println(key);
		}
		}
	
	

}
