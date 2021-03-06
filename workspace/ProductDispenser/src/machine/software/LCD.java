package machine.software;

import isel.leic.utils.Time;
import Utils.Emitter;
import Utils.MaskManager;
import Utils.OutputManager;

public class LCD {

	private static final boolean PARALLEL = false;
	
	private static final int MIXD = 0x1;
	private static final int ENABLE = 0x2;
	private static final int RS = 0x4;	
	private static final int NIBBLE_DATA_MASK = 0XF0;
	
	private static final int DATA_POS = MaskManager.calcPosFromMask(NIBBLE_DATA_MASK);
	private static final boolean COMMAND_MODE = false;
	private static final boolean CHAR_MODE = true;

	

	public static void init(){
		
		Time.sleep(15);
		writeNibble((byte)0x3,COMMAND_MODE);
		Time.sleep(4);		
		writeNibble((byte)0x3,COMMAND_MODE);
		writeNibble((byte)0x3,COMMAND_MODE);
		
		writeNibble((byte)0x2,COMMAND_MODE);
		
		writeCommand(0x2C);
		writeCommand(0x08);
		writeCommand(0x01);
		writeCommand(0x06);
		
		//display enable
		writeCommand(0X0F);		
	}
	
	public static void writeString(String s){
		for(int i=0;i<s.length();++i){
			writeChar(s.charAt(i));
		}
	}
	
	public static void setCursor(int line, int col){
		int cmd = col & 0xf;
		cmd |= (line == 1)? 0xC0 : 0x80;
		writeCommand(cmd);
		
	}
	
	public static void writeChar(char c){
		writeByte((byte) c,CHAR_MODE);
	}
	
	public static void writeCommand(int cmd){
		writeByte((byte)cmd, COMMAND_MODE);
	}
	
	private static void writeByte(byte bt, boolean rs){
		writeNibble((byte)(bt >>4), rs);
		writeNibble((byte)(bt & 0XF), rs);				
	}
	
	private static void writeNibble(int nibble, boolean rs){
		if(PARALLEL){
	 		if(rs)
	 			OutputManager.setMask(RS); 		
	 	    else 
	 	    	OutputManager.clearMask(RS);
	 		
			OutputManager.setBits(NIBBLE_DATA_MASK, nibble, DATA_POS);
			
			OutputManager.setMask(ENABLE);
			OutputManager.clearMask(ENABLE);
		}else{
			
			nibble<<=2;
			nibble|=rs?2:0;//set rs in 0x2
			nibble|=1;//set LnD=1 in 0x1
			
			Emitter.sendTrama(nibble, 6);			
		}
		
	}
}
