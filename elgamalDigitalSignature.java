import java.util.*;
/* AIM : IMPLEMENTAION OF ==> Regarding the Elgamal Digital Signature scheme, let q=73 and α = 11. Assume that the public 
key is (q, α, 39) and the secret key is (q, α, 47). Use the secret key as the signing key to sign the 
message m =25. The randomly chosen k is 23. You don’t need to do hashing before signing.

Step1 ==> Read the inpus from user such as k,q,alpha,message,secret and public key
Step2 ==> call findS1 function inorder to find the value of S1 ( S1=a^k mod q)
Step3 ==> call findS2 function inorder to find the value of S2 (S2)
Step4 ==> Inside the findS2 function call findInverseMod function inorder to find p1=(k^-1 mod q-1) value
          and find the value of p2=(m-xa*s1 mod q-1) and finally find s2 value by p1*p2 mod q-1
          And print the value of S1 and S2
Step5 ==> Call function for verifing the signature (findV1 and findV2)
Step6 ==> If the value of both function is equal the return verified otherwise return Not Verified and also print the values of v1 and v2
*/
public class Main
{
    static int findS1(int a,int k,int q){
        int r=1;
        for(int i=1;i<=k;i++){
            r=(r*a)%q;
        }
        return r;
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
    static int findS2(int msg,int k,int q,int xa,int s1){
        int p1=findInverseMod(k,q-1);
        
        int t=msg-(xa*s1);
        int p2;
        if(t<0){
            p2=(q-1)-(Math.abs(t)%(q-1));
        }
        else{
            p2=t%(q-1);
        }
        //System.out.println(p1+" "+p2);
        int s2=(p1*p2)%(q-1);
        //System.out.println(s2);
        return s2;
    }
    static int findV1(int a,int m,int q){
        int v1=findS1(a,m,q);//multi purpose ==> here usues for find v1
        System.out.println("V1 : "+v1);
        return v1;
    }
    static int findV2(int ya,int s1,int s2,int q){
        int p1=findS1(ya,s1,q);//ya^s1 mod q
        int p2=findS1(s1,s2,q);//s1^s2 mod q
        System.out.println("V2 : "+(p1*p2)%q);
        return ((p1*p2)%q);
    }
	public static void main(String[] args) {
	    Scanner input=new Scanner(System.in);
	    System.out.print("Enter the Random K value : ");
	    int k=input.nextInt();
	    System.out.print("Enter the q value : ");
	    int q=input.nextInt();
	    System.out.print("Enter the a(alpha) value : ");
	    int a=input.nextInt();
	    System.out.print("Enter the message(m) : ");
	    int m=input.nextInt();
	    System.out.print("Enter the secret key(xA) : ");
	    int xa=input.nextInt();
	    System.out.print("Enter the public key(yA) : ");
	    int ya=input.nextInt();
	    //Singing
		int s1=findS1(a,k,q);
		int s2=findS2(m,k,q,xa,s1);
		System.out.println("(s1,s2) ("+s1+","+s2+")");
		//Verification
		if(findV1(a,m,q)==findV2(ya,s1,s2,q)){
		    System.out.println("Verified");
		}
		else{
		    System.out.println("Not Verified");
		}
	}
}
