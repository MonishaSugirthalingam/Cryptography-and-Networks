import java.util.*;
public class DiffieHelmanManInMiddle
{
       public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		//Normal Input
                System.out.print("Enter Alpha Value : ");
		int alpha=input.nextInt();
                System.out.print("Enter q Value : ");
		int q=input.nextInt();
                System.out.print("Enter private key of a : ");
		int xa=input.nextInt(); //private key of a
                System.out.print("Enter private key of b : ");
		int xb=input.nextInt(); //private key of b
		
		//Man in the middle input
                System.out.print("Enter private key of Middle Man for a : ");
		int xd1=input.nextInt();
                System.out.print("Enter private key of Middle Man for b : ");
		int xd2=input.nextInt();
		
		//Man in the middle 
		int yd1=(int)(Math.pow(alpha,xd1))%q;
		int yd2=(int)(Math.pow(alpha,xd2))%q;
		System.out.println("Man In Middles Public Key : "+yd1+" "+yd2);
		int ya=(int)(Math.pow(alpha,xa))%q;
		int yb=(int)(Math.pow(alpha,xb))%q;
		System.out.println("A and B's : "+ya+" "+yb);
		
		
	    // Now a and b are change their public key but in the middle man steal the ya and yb and send instead yd1 and yd2
		
		int k1=(int)(Math.pow(yb,xd1))%q;
		int k2=(int)(Math.pow(ya,xd2))%q;

		System.out.println("Middle Man Keys :"+k1+" "+k2);

		int ka=(int)(Math.pow(yd2,xa))%q;
		int kb=(int)(Math.pow(yd1,xb))%q;

		System.out.println("A and Bs keys : "+ka+" "+kb);	
	} 
}
