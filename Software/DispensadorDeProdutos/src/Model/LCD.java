package Model;

import isel.leic.utils.Time;
import Utils.MaskManager;
import Utils.OutputManager;

public class LCD {

	private static final boolean PARALLEL = false;
	
	private static final int MIXD = 0x1;
	private static final int ENABLE = 0x2;
	private static final int RS = 0x4;
	private static final int MICK = 0x8;	
	private static final int NIBBLE_DATA_MASK = 0XF0;
	
	private static final int DATA_POS = MaskManager.calcPosFromMask(NIBBLE_DATA_MASK);
	private static final boolean COMMAND_MODE = false;
	private static final boolean CHAR_MODE = true;

	private static final int NIBBLE_SIZE = 4;

	public static void init(){
		OutputManager.setMask(MIXD);
		
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
	
	private static void writeNibble(byte nibble, boolean rs){
		if(PARALLEL){
	 		if(rs)
	 			OutputManager.setMask(RS); 		
	 	    else 
	 	    	OutputManager.clearMask(RS);
	 		
			OutputManager.setBits(NIBBLE_DATA_MASK, nibble, DATA_POS);
			
			OutputManager.setMask(ENABLE);
			OutputManager.clearMask(ENABLE);
		}else{
			//waitForNotBusy();
			
			OutputManager.clearMask(MICK);
			
			OutputManager.clearMask(MIXD);
				
			sendBitToMIxDSer(true);				
			sendBitToMIxDSer(rs);
			
			for(int i=0;i<NIBBLE_SIZE;++i)
				sendBitToMIxDSer(((nibble>>i)&0x1)==1);
			
			OutputManager.setMask(MIXD);
			
		}
		
	}

	private static void sendBitToMIxDSer(boolean b) {
		OutputManager.setMask(MICK);
		
		if(b)
			OutputManager.setMask(MIXD);
		else
			OutputManager.clearMask(MIXD);
		
		OutputManager.clearMask(MICK);
		
	}

	private static void waitForNotBusy() {
		// TODO Auto-generated method stub
		
	}
	
}
