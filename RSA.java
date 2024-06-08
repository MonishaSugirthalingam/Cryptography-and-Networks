// Java Program to Implement the RSA Algorithm
import java.math.*;
import java.util.*;

class RSA {
	public static void main(String args[])
	{
		int p, q, n, z, d = 0, e, i;
		String str = "monisha";
                System.out.println("Input String : "+str);
		int[] msg=new int[str.length()];
                System.out.print("ASCII Value of Given String is : ");
		for(int j=0;j<str.length();j++){
		    msg[j]=str.charAt(j)-97;
		    System.out.print(msg[j]+" ");
		}
		System.out.println();
		double c;

		// 1st prime number p
		p = 3;

		// 2nd prime number q
		q = 11;
		n = p * q;
		z = (p - 1) * (q - 1);
		System.out.println("the value of z = " + z);

		for (e = 2; e < z; e++) {
			if (gcd(e, z) == 1) {
				break;
			}
		}
		System.out.println("the value of e = " + e);
		for (i = 0; i <= 9; i++) {
			int x = 1 + (i * z);
			if (x % e == 0) {
				d = x / e;
				break;
			}
		}
		System.out.println("the value of d = " + d);
		int[] cipherText=new int[msg.length];
		System.out.print("Encrypted message is : " );
		for(int j=0;j<msg.length;j++){
		    c = (Math.pow(msg[j], e)) % n;
		    cipherText[j]=(int)c;
		    System.out.print((int)c+" ");
		}
	    System.out.println();
        int msgback[]=new int[msg.length];
	    System.out.print("Decrypted message is : " );
		for(int j=0;j<msg.length;j++){
		    c = (Math.pow(cipherText[j], d)) % n;
		    msgback[j]=(int)c;
		    System.out.print((int)c+" ");
		}
	    System.out.println();
	}

	static int gcd(int e, int z)
	{
		if (e == 0)
			return z;
		else
			return gcd(z % e, e);
	}
}
