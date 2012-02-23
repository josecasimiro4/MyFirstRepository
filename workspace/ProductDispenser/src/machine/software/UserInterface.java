package machine.software;


public class UserInterface {
	private static final int TIMEOUT = 5000;
	
	public static int tryGetProductCode(boolean mode){
		char key;
		int cod = 0;
		do{
			if((key = Keyboard.getKey(TIMEOUT))==Keyboard.NO_KEY)
				return -1;
			
			if(key != 'A' && key != 'C' && key != 'D'){
				if(key == Keyboard.KEY_OK)
					return cod;
				
				switch(key){
					case Keyboard.KEY_DOWN:
						cod = Stock.getNext(cod,mode);
						break;
					case Keyboard.KEY_UP:
						cod = Stock.getPrevious(cod,mode);
						break;
					default:
						cod = calcCode(key, cod);
						break;
				}
			}			
			Product p = Stock.getProduct(cod);
			displayProduct(p);
		}while(true);
	}

	private static int calcCode(char key, int cod) {
		cod*=10;
		cod+=key-'0';
		cod= cod%100>15 ? cod%10 : cod%100;
		return cod;
	}

	private static void displayProduct(Product p) {
		setName(p.getName());
		setID(p.getCod());
	}

	private static void setID(int cod) {
		LCD.setCursor(1,3);
		LCD.writeString(cod+"");
		if(cod<10)
			LCD.writeChar(' ');
	}

	private static void setName(String name) {
		LCD.setCursor(0,0);
		LCD.writeString(name);
	}
	
	public static void showIdleMessage() {
		setName("Escolha Produto");
		LCD.setCursor(1,3);
		LCD.writeString("??            ");
	}

	public static char tryGetFunctionKey() {
		char key;
		do{
			key = Keyboard.getKey(TIMEOUT);
			if(key==Keyboard.NO_KEY)
				return 0;
			
			if(key == 'A' || key == 'C' || key == 'D')
				return key;
			
		}while(true);
	
	}

	public static int tryGetQuantity() {
		char key;
		int quant = 0;
		setupQuantityDisplay();
		do{
			key = Keyboard.getKey(TIMEOUT);
			if(key==Keyboard.NO_KEY)
				return -1;
			
			if(key == 'A' || key == 'C' || key == 'D' || key == Keyboard.KEY_UP || key == Keyboard.KEY_DOWN)
				continue;
			if(key == Keyboard.KEY_OK)
				break;
			
			quant*=10;
			quant+=key-'0';
			displayQuantity(key-'0');
			
		}while(true);
		
		return quant;
	}

	private static void displayQuantity(int quant) {
		LCD.setCursor(1, 9);
		LCD.writeString(quant+"");
	}

	private static void setupQuantityDisplay() {
		LCD.setCursor(1, 0);
		LCD.writeString("Quantity:");

	}
	
}
