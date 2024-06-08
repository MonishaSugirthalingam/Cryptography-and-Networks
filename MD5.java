import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String s="SCOPE";
		String hexval="";
		for(int i=0;i<s.length();i++){
		    int x=s.charAt(i);
	        String y=Integer.toHexString(x);
	        if(y.length()!=2){
	            y='0'+y;
	        }
	        hexval+=y;
		}
		//PADDING
		int len=hexval.length()*4;
		int block;
		System.out.println(hexval+" "+len);
		if(len<512){
		    int l=len+64;
		    int pad=512-l;
		    System.out.println(pad);
		    block=1;
		}
		else{
		    int m=len+64;
		    int i=m/512;
		    while(m%512!=0){
		        int val=(i+1)*512;
		        int pad=val-m;
		        m=m+pad;
		        System.out.println(i+" "+pad+" "+m);
		        i++;
		     }
		    block=i;
		}
	   //split to blocks
	   String[] m=new String[16];
	   String temp="";
	   int count=0;
	   int mulofeight=8;
	   for(int j=0;j<hexval.length();j=j+2){
	       if(j==mulofeight){
	           System.out.println(temp);
	           m[count]=temp;
	           temp="";
	           count++;
	           mulofeight=count*8;
	       }
	       temp=temp+hexval.charAt(j)+hexval.charAt(j+1);
	       //System.out.println(temp);
	       m[count]=temp;
	   }
	   //System.out.println(count);
	   String t=m[count];
	   int l=t.length();
	   while(l<8){
	      t=t+'0'; 
	      l++;
	   }
	   m[count]=t;
	   //System.out.println(m[count]);
	   for(int i=count+1;i<=13;i++){
	       t="00000000";
	       m[i]=t;
	   }
	   t=Integer.toHexString(len);
	   if(t.length()<8){
	       l=t.length();
	       while(l<8){
	           t='0'+t;
	           l++;
	       }
	       m[15]=t;
	       m[14]="00000000";
	   }
	   else{
	       l=t.length();
	       while(l<16){
	           t='0'+t;
	           l++;
	       }
	       m[14]=t.substring(0,8);
	       m[15]=t.substring(8,16);
	   }
	   //16 blocks of m 
	   for(int i=0;i<16;i++){
	       System.out.println(m[i]);
	   }
	   //process p 
	   String p="FEDCBA98";
	}
}
