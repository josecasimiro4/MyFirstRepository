package Utils;

public class Resources {
	
	//Input Ports
	
	public static final int keyInput = 0XF0 ;
	public static final int keyInput_POS = MaskManager.calcPosFromMask(keyInput) ;
	public static final int RX_READY = 0x01;
	public static final int K_VAL= 0X02 ;
	public static final int KXD = 0x04;
	public static final int KXD_POS = MaskManager.calcPosFromMask(KXD);
	
	
	//Output Ports
	/**pc\ TRASNMITTOR **/
	
	public static final int MIXD = 0x01;
	public static final int MICK = 0x8;
	public static final int K_ACK =0X02;
	public static final int KCK = 0x04;
	
	
	
	
	

}
