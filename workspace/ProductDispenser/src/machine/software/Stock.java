package machine.software;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Stock {
	
	private static Product[] products = new Product[16];
	
	public static boolean importFrom(String filePath) {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File(filePath)));
			String in;
			while((in=br.readLine())!=null){
				String[]data = in.split("-");
				int prodCod = Integer.parseInt(data[0]);
				products[prodCod] = new Product(prodCod,
						                        data[1],
						                        Integer.parseInt(data[2]),
						                        Double.parseDouble(data[3]));
				
			}
		}catch(Exception e){
			return false;
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				
			}
		}
		return true;
	}
	
	public static boolean exportTo(String filePath){
		PrintStream ps = null;
		try{		
			ps = new PrintStream(filePath);
			Product p;
			for(int i=0;i<products.length;++i){
				if((p=products[i])!=null){
					ps.println(String.format("%d-%s-%d-%d",p.getCod(),p.getName(),p.getQuantity(),p.getPrice()));
				}
			}
		}catch(FileNotFoundException e){
			return false;
		}finally{
			ps.close();
		}
		return true;		
	}
	
	public static void restock(int prodCod,int quant){
		
		if(isValidCode(prodCod)){
			products[prodCod].restock(quant);
		}
	}
	
	private static boolean isValidCode(int prodCod){
		return prodCod>=0 && prodCod<=15 && products[prodCod]!=null;
	}
	
	public static Product getProduct(int prodCod){
		if(isValidCode(prodCod)){
			return products[prodCod];
		}
		return null;
	}

	public static int getNext(int cod, boolean withQuantity) {
		for(int i=0;i<16;++i){
			cod = cod == 15 ? 0 : cod+1;
			if(products[cod]!= null){ 
			     if(withQuantity)
			    	 if(products[cod].getQuantity()>0)
			    		 return cod;
			     else
			    	 return cod;
				
			}
		}
		return cod;
	}
	
	public static int getPrevious(int cod, boolean withQuantity) {
		for(int i=0;i<16;++i){
			cod = cod == 0 ? 15 : cod-1;
			if(products[cod]!= null){ 
			     if(withQuantity)
			    	 if(products[cod].getQuantity()>0)
			    		 return cod;
			     else
			    	 return cod;
				
			}
		}
		return cod;
	}

	public static void remove(int prodCod) {
		if(isValidCode(prodCod)){
			products[prodCod].decrementQuantity();
		}
	}

	public static void cancelProduct(int prodCod) {
		if(isValidCode(prodCod)){
			products[prodCod].resetQuantity();
		}
		
	}
}
