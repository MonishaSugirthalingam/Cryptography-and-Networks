import java.util.*;
public class Main
{
    static int pow(int a,int b){
        int p=1;
        while(b>0){
		    p*=a;
		    p=p%353;
		    b--;
		}
	//	System.out.println(p);
        return p;
    }
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		//Normal Input
		int alpha=input.nextInt();
		int q=input.nextInt();
		int xa=input.nextInt(); //private key of a
		int xb=input.nextInt(); //private key of b
		
		int ya=pow(alpha,xa)%q;
		int yb=pow(alpha,ya)%q;
		
		System.out.println(ya+" "+yb);
		
		int ka=pow(yb,xa)%q;
		int kb=pow(ya,xb)%q;
		
		System.out.println(ka+" "+kb);
		
		
	}
}
