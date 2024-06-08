/* AIM : While Bob wants to sign a message, he has the following details:
p = 59, q = 29, h = 11, H (M) = 26, k = 10
Bob has to find out the ‘g’ – one of the global public elements from ‘h’. Then he has to choose 
a random integer ‘x’ = 7 which is his private key. Similarly, Bob has to generate the public key 
‘y’. What will be Bob’s Signature (r, s)? Implement with any programming language.

Step 1 : call the function findG() for finding the value of g (h^(p-1)/q) mod p-1
         for that find (p-1)/q and store it in t then call findMod() for find (h^t mod p-1)
         return g value
Step2  : call function findMod() for finding the value of public key(ya) 
Step3  : call the function findMod() inorder to find r and take mod of this function return value and store it in r
Step4  : call the function findS() inorder to find s value
Step5  : Finally print the signatures (r,s)

*/
import java.util.*;
public class Main
{
    static int findMod(int h,int t,int p)
    {
        int r=1;
        for(int i=1;i<=t;i++){
            r=(r*h)%p;   // h^t mod p
        }
        return r;
    }
    static int findG(int h,int p,int q){
        int t=(p-1)/q;
        return findMod(h,t,p); 
    }
    static int findInverseMod(int k,int q){
        ArrayList<Integer> qua=new ArrayList<>();
    
        int r1,r2;
        //align r1,r2
        if(k>=q){
            r1=k;
            r2=q;
        }
        else{
            r1=q;
            r2=k;
        }
        while(r2!=0 && r1!=1){
            qua.add(r1/r2);
            int rem=r1%r2;
            r1=r2;
            r2=rem;
        }
        int s1=0,s2=1,i=0,s=0;
        while(Math.abs(s)!=q){
            s=s1-(qua.get(i)*s2);
            s1=s2;
            s2=s;
            i++;
            //System.out.println(s+" "+s1+" "+s2);
        }
        if(s1<0){
            return(q-(Math.abs(s1)%q));
        }
        else{
            return(s1%q);
        }
    }
    static int findS(int k,int q,int hm,int xa,int r){
        int p1=findInverseMod(k,q);
        int p2=(hm+(xa*r))%q;
        return (p1*p2)%q;
        
    }
    static int findV(int r,int s,int hm,int p,int q,int g,int ya){
        int w=findInverseMod(s,q);
        int u1=(hm*w)%q;
        int u2=(r*w)%q;
        int x=(findMod(g,u1,p)*findMod(ya,u2,p))%p;
        return x%q;
    }
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
	    System.out.println("***********************************");
	    System.out.println("      AIM      :   Implementation of Digital Signature Standard");
	    System.out.println("      NAME     :   MONISHA S");
	    System.out.println("      REG.NO   :   21BCT0414");
	    System.out.println("      DATE     :   11th April 2024");
	    System.out.println("***********************************");
	    System.out.print("Enter the Random K value : ");
	    int k=input.nextInt();
	    System.out.print("Enter the h value : ");
	    int h=input.nextInt();
	    System.out.print("Enter the H(M) value : ");
	    int hm=input.nextInt();
	    System.out.print("Enter the value of p : ");
	    int p=input.nextInt();
	    System.out.print("Enter the value of q : ");
	    int q=input.nextInt();
	    System.out.print("Enter the secret key(xA) : ");
	    int xa=input.nextInt();
	    
	    //int k=15,hm=41,p=283,q=47,xa=24,g=60;
	    
	    int g=findG(h,p,q);
	    int ya=findMod(g,xa,p);//to find public key
	    //to find digital signature
	    int r=(findMod(g,k,p))%q; // find r
	    int s=findS(k,q,hm,xa,r);
	    System.out.println("Digital Signature (r,s) "+"("+r+","+s+")");
	    //verification
	    int v=findV(r,s,hm,p,q,g,ya);
	    System.out.println("v = "+v+" "+"r= "+r);
	    if(v==r){
	        System.out.println("Verified");
	    }
	    else{
	        System.out.println("Not Verified");
	    }
	}
}
