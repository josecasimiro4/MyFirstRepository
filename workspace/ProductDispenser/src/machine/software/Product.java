package machine.software;

public class Product {
	
	private int _cod;
	private String _name;
	private int _quantity;
	private double _unitPrice;
	
	public Product (int cod, String name, int quantity,double unitPrice){
		
		_cod=cod;
		_name=name;
		_quantity=quantity;
		_unitPrice = unitPrice;
		
	}
	
	public int getCod(){
		
		return _cod;
	}
	
	public String getName(){
		
		return _name;
	}
	
	public int getQuantity(){
		
		return _quantity;
	}
	
	public double getPrice(){
		
		return _unitPrice;
	}
	
	public void restock(int quant){
		_quantity= _quantity+quant>20 ? 20 : _quantity+quant;
		
	}

	public void decrementQuantity() {
		--_quantity;
		
	}

	public void resetQuantity() {
		_quantity=0;
		
	}
	

}
