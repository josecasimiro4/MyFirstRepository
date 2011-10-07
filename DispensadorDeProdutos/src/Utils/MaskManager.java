package Utils;

public class MaskManager {

	public static int calcPosFromMask(int mask) {
		int cnt=0;
		
		while((mask & 1)==0){
			++cnt;
			mask>>=1;
		}
		
		return cnt;
	}

	
}
