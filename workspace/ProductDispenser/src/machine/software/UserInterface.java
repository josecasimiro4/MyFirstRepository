package machine.software;

import isel.leic.utils.Time;

public class UserInterface {

	public static int tryGetProductCode(boolean mode){
		char key;
		int cod = 1;
		do{
			key = Keyboard.getKey(5000);
			if(key==Keyboard.NO_KEY)
				return -1;
			
			switch(key){
			case Keyboard.KEY_DOWN:
				cod = Stock.getNext(cod,mode);
				break;
			}
			
		}while(key!=Keyboard.KEY_OK);
	}
	
}
