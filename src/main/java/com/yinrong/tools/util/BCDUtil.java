package com.yinrong.tools.util;

import com.yinrong.tools.util.BCDASCIIUtil;

public class BCDUtil {
	
	public static void intToBCD(byte[] b, int value) {
	       for (int i = b.length-1; i >=0; i--) {
	    	   b[i]  = (byte)0;
	           int shift= (b.length-1 - i) * 8 ;           
	           int v1 = ((0xF << shift) & value) >> shift;	           
	           b[i] = (byte)(v1 | b[i]);
	           shift = shift + 4;
	           int v2= ((0xF << shift) & value) >> shift ;	          
	           b[i] = (byte)((v2<< 4) | b[i]);
	       }
		   
	 }
	
	public static int bcdAsciiToInt(byte[] b) {
		int sum = 0;
		for(int i=0; i<b.length; i++ ) {
			if(((int)b[i])>'9'){//AscII -- A-F
				int a = (int)b[i] - 'A' + 10;
				sum = sum * 16 + a;
			}else{//AscII -- 0-9
				int a = (int)b[i] - '0';
				sum = sum * 16 + a;
			}
		}
		return sum;
	}
	
	
	
	public static long bcdToLong(byte[] b) {
		long sum = 0;
		for(int i=0; i<b.length; i++ ) {
			if(((int)b[i])>'9'){//AscII -- A-F
				int a = (int)b[i] - 'A' + 10;
				sum = sum * 16 + a;
			}else{//AscII -- 0-9
				int a = (int)b[i] - '0';
				sum = sum * 16 + a;
			}
		}
		return sum;
	}
	

	
	public static String byteToHexString(byte[] a ) {
		String s="";
		for(int i=0,len=a.length;i<len;i++) {
			s += Integer.toHexString(new Byte(a[i]).intValue());
		}
		return s;
	}
		
	
	public static void main(String[] args) {
		byte[] b = new byte[2];
		intToBCD(b,Integer.parseInt("0200", 16));
		//打印byte
		for(int j= 0; j< b.length;j++){
			for(int i = 7;i>=00;i--){
				int v = 1  &  (b[j]>>i);
				System.out.print(v);
				if(i == 4){
					System.out.print(' ');
				}
			}
			System.out.print(' ');
		}
		String str = BCDASCIIUtil.fromBCDToASCIIString(b, 0, b.length*2, true);
		System.out.print("str: "+str);
//		System.out.println(BCDtoInt("2635"));
//		byte[] t={0x26,0x35};
//		System.out.println(BCDtoInt(ByteToHexString(t)));
		System.out.println();;
		String s = "1A";
		int a = bcdAsciiToInt(s.getBytes());
		System.out.println(a);
		Integer.parseInt(s, 16);
	}

}
