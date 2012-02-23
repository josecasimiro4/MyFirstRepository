package machine.software;

public class Main {
	
	private static final boolean USER_MODE = true;
	private static final boolean MAINTENANCE_MODE = false;
	private static final String STOCK_FILE_PATH = "stockFile.stk";
	private static boolean mode = USER_MODE;
	

	public static void main(String []args){
		Stock.importFrom(STOCK_FILE_PATH);
		while(true){
			UserInterface.showIdleMessage();
			
			if(mode == USER_MODE){
				dispensa();
			}else{
				if(maintenance())
					break;
			}
		}
		Stock.exportTo(STOCK_FILE_PATH);
	}


	private static void dispensa() {
		int prodCod;
		prodCod = UserInterface.tryGetProductCode(mode);
		if(prodCod!=-1){
			Stock.remove(prodCod);
			Dispenser.eject(prodCod);
		}
	}
	
	
	private static boolean maintenance(){
		char funcKey = UserInterface.tryGetFunctionKey();
		if(funcKey == 0) return false;
		if(funcKey == 'D')return true;
		
		int prodCod = UserInterface.tryGetProductCode(mode);
		if(prodCod == -1)
			return false;
		
		switch(funcKey){
			case 'A':
				Stock.cancelProduct(prodCod);
				break;
			case 'C':
				int quantity = UserInterface.tryGetQuantity();
				if(quantity == -1)
					return false;
				Stock.restock(prodCod, quantity);
				break;
		}
		return false;
	}

}
