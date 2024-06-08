import java.util.*;

class Main {
    //parity bit drop Table (56 bits)
    static int pbdropt[]={57, 49, 41, 33, 25, 17, 9,
                        1, 58, 50, 42, 34, 26, 18,
                        10, 2, 59, 51, 43, 35, 27,
                        19, 11, 3, 60, 52, 44, 36,
                        63, 55, 47, 39, 31, 23, 15,
                        7, 62, 54, 46, 38, 30, 22,
                        14, 6, 61, 53, 45, 37, 29,
                        21, 13, 5, 28, 20, 12, 4};
                          
    static String hexTobin(String s){
        
        String t="";
        
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)=='0'){
                t=t+'0'+'0'+'0'+'0';
            }
            else if(s.charAt(i)=='1'){
                t=t+'0'+'0'+'0'+'1';
            }
            else if(s.charAt(i)=='2'){
                t=t+'0'+'0'+'1'+'0';
            }
            else if(s.charAt(i)=='3'){
                t=t+'0'+'0'+'1'+'1';
            }
            else if(s.charAt(i)=='4'){
                t=t+'0'+'1'+'0'+'0';
            }
            else if(s.charAt(i)=='5'){
                t=t+'0'+'1'+'0'+'1';
            }
            else if(s.charAt(i)=='6'){
                t=t+'0'+'1'+'1'+'0';
            }
            else if(s.charAt(i)=='7'){
                t=t+'0'+'1'+'1'+'1';
            }
            else if(s.charAt(i)=='8'){
                t=t+'1'+'0'+'0'+'0';
            }
            else if(s.charAt(i)=='9'){
                t=t+'1'+'0'+'0'+'1';
            }
            else if(s.charAt(i)=='A'){
                t=t+'1'+'0'+'1'+'0';
            }
            else if(s.charAt(i)=='B'){
                t=t+'1'+'0'+'1'+'1';
            }
            else if(s.charAt(i)=='C'){
                t=t+'1'+'1'+'0'+'0';
            }
            else if(s.charAt(i)=='D'){
                t=t+'1'+'1'+'0'+'1';
            }
            else if(s.charAt(i)=='E'){
                t=t+'1'+'1'+'1'+'0';
            }
            else{
                t=t+'1'+'1'+'1'+'1';
            }
        }
        return t;
    }
    
    static String key64to56(String key){
        
        String k="";
        for(int i=0;i<56;i++){
            k=k+key.charAt(pbdropt[i]-1);
        }
        
        System.out.println("Sub Key             "+k); //sub key
        return k;
    }
    //Left Shift By one 
    static String leftshiftby1(String a,String b){
        char x=a.charAt(0);
        String temp="";
        for(int i=1;i<28;i++){
            temp=temp+a.charAt(i);
        }
        temp=temp+x;
        a=temp;
        
        x=b.charAt(0);
        temp="";
        for(int i=1;i<28;i++){
            temp=temp+b.charAt(i);
        }
        temp=temp+x;
        b=temp;
        
        String key=a+b;
        
        return key;
    }
    //Left Shift By Two
    static String leftshiftby2(String a,String b){
        
        char x1=a.charAt(0);
        char x2=a.charAt(1);
        String temp="";
        for(int i=2;i<28;i++){
            temp=temp+a.charAt(i);
        }
        temp=temp+x1+x2;
        a=temp;
        
        x1=b.charAt(0);
        x2=b.charAt(1);
        temp="";
        for(int i=2;i<28;i++){
            temp=temp+b.charAt(i);
        }
        temp=temp+x1+x2;
        b=temp;
        
        String key=a+b;
        return key;
    }
    static String fun1(String k){
        String lk="";
        for(int i=0;i<28;i++){
            lk=lk+k.charAt(i);
        }
        return lk;
    }
    static String fun2(String k){
        String rk="";
        for(int i=28;i<56;i++){
            rk=rk+k.charAt(i);
        }
        return rk;
    }
    //56 bits keys are divided into 2 parts and  left shift by 1 or 2 
    static String keyCompressionPermutation(String key){
        int[] keycom={14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32};
        //key compression (56 to 48)
        String newkey="";
        for(int x=0;x<48;x++){
            newkey=newkey+key.charAt(keycom[x]-1);
        }
        //System.out.println("Round Key " + newkey);
        return newkey;
    }
    static String ptInitialPermutation(String pt){
        int[] iniperm = {58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7};
        
        String temp="";
        for(int i=0;i<64;i++){
            temp=temp+pt.charAt(iniperm[i]-1);
        }
                
        return temp;
    }
    //pt is divided into two half
    static String[] ptDivision(String pt){
        String[] arr=new String[2];
        String x="";
        for(int i=0;i<32;i++){
            x=x+pt.charAt(i);
        }
        String y="";
        for(int i=32;i<64;i++){
            y=y+pt.charAt(i);
        }
        arr[0]=x;
        arr[1]=y;
        return arr;
    }
    //32 bit rpt is will expanded to 48 bits
    static String PtExpansionPermutation(String rpt){
        int[] exp = {32, 1, 2, 3, 4, 5, 4, 5,
		6, 7, 8, 9, 8, 9, 10, 11,
		12, 13, 12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21, 20, 21,
		22, 23, 24, 25, 24, 25, 26, 27,
		28, 29, 28, 29, 30, 31, 32, 1};
		String newrpt="";
		for(int i=0;i<48;i++){
		    newrpt=newrpt+rpt.charAt(exp[i]-1);
		}
        return newrpt;
    }
    //XOR operation of round key and rpt
    static String xorOperation(String key,String rPt){
        
        String r="";
        for(int i=0;i<48;i++){
            if((key.charAt(i)=='0' && rPt.charAt(i)=='0') || (key.charAt(i)=='1' && rPt.charAt(i)=='1')){
                r=r+'0';
            }
            else{
                r=r+'1';
            }
        }
        return r;
    }
    static String fun(int r,int c,int x){
        int[][] s1={{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
		{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
		{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
		{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
		
		int[][] s2={{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
         {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
         {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
         {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
         
		int[][] s3={{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
         {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
         {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
         {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
         
		int[][] s4={{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
         {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
         {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
         {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
         
		int[][] s5={{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
         {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
         {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
         {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
         
		int[][] s6={{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
         {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
         {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
         {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
         
		int[][] s7={{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
         {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
         {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
         {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
         
		int[][] s8={{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
         {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
         {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
         {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
        
        
		
		if(x==1){
		    String t=Integer.toBinaryString(s1[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==2){
		    String t;
		    t=Integer.toBinaryString(s2[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==3){
		    String t;
		    t=Integer.toBinaryString(s3[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==4){
		    String t="";
		    t=Integer.toBinaryString(s4[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==5){
		    String t="";
		    t=Integer.toBinaryString(s5[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==6){
		    String t="";
		    t=Integer.toBinaryString(s6[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else if(x==7){
		    String t="";
		    t=Integer.toBinaryString(s7[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		else{
		    String t="";
		    t=Integer.toBinaryString(s8[r][c]);
		    if(t.length()<4){
		        while(t.length()<4){
		            t='0'+t;
		        }
		    }
		    return t;
		}
		
    }
    static String substitution(String x){
        String s="";
        int count=1;
		for(int i=0;i<48;i=i+6){
		    String row="";
		    String col="";
		    String t="";
		    for(int j=i;j<i+6;j++){
		        t=t+x.charAt(j);
		        if(j!=i && j!=i+6-1){
		            col=col+x.charAt(j);
		        }
		        else{
		            row=row+x.charAt(j);
		        }
		    }
		    int r=Integer.parseInt(row, 2);
		    int c=Integer.parseInt(col, 2);
		    
		    String z=fun(r,c,count);
		    s=s+z;
		    //System.out.println(r+" "+c+" "+count+" "+row+" "+col+" "+t+" "+z);
		    count++;
		}
        return s;
    }
    //straight permutation after substitution
    static String spermutation(String s){
        String r="";
        int[] sper={16,  7, 20, 21,
                    29, 12, 28, 17,
                    1, 15, 23, 26,
                    5, 18, 31, 10,
                    2,  8, 24, 14,
                    32, 27,  3,  9,
                    19, 13, 30,  6,
                    22, 11,  4, 25};
        for(int i=0;i<32;i++){
            r=r+s.charAt(sper[i]-1);
        }
        return r;
    }
    //xor operation of 32 bits
    static String xor(String a,String b){
        String r="";
        for(int i=0;i<32;i++){
            if((a.charAt(i)=='0' && b.charAt(i)=='0') || (a.charAt(i)=='1' && b.charAt(i)=='1')){
                r=r+'0';
            }
            else{
                r=r+'1';
            }
        }
        return r;
    }
    static String swapPt(String a,String b){
        return b+a;
    }
    //final permutation
    static String finalpermu(String pt){
        String r="";
        int[] finalper={40, 8, 48, 16, 56, 24, 64, 32,
              39, 7, 47, 15, 55, 23, 63, 31,
              38, 6, 46, 14, 54, 22, 62, 30,
              37, 5, 45, 13, 53, 21, 61, 29,
              36, 4, 44, 12, 52, 20, 60, 28,
              35, 3, 43, 11, 51, 19, 59, 27,
              34, 2, 42, 10, 50, 18, 58, 26,
              33, 1, 41, 9, 49, 17, 57, 25};
        for(int i=0;i<64;i++){
            r=r+pt.charAt(finalper[i]-1);
        }
        return r;
    }
    static void details()
    {
        System.out.println("******************************************************");
        System.out.println("  TITLE : Implementation of DES (Data Encryption Structure)");
        System.out.println("  NAME  : MONISHA S");
        System.out.println("  DATE  : 24th Jan 2024");
        System.out.println("******************************************************");
    }
    static String[] roundkeys=new String[17];
    static String encryption(String plainTexthex,String keyhex){
            System.out.println("\n\nEncryption\n\n");
            String[] LPTRPT=new String[2];
            String plainText=hexTobin(plainTexthex);
            String key=hexTobin(keyhex);
            String key56=key64to56(key);//ByParityBitDropTable
            String lk="";
            String rk="";
            for (int x=0;x<28;x++){
            lk=lk+key56.charAt(x);
            }
            for(int x=28;x<56;x++){
            rk=rk+key56.charAt(x);
            }
            String pt=ptInitialPermutation(plainText); //Initial Permutation of Plain Test 
            System.out.println("Initial permutation "+pt);
            System.out.println();
            LPTRPT=ptDivision(pt); //devide pt into 32bits of LPT and 32bits of RPT
            for(int i=1;i<=16;i++){
            //System.out.println("--------------------------------------------------");
            System.out.println("Round "+i);
            String k;
            if (i==1 || i==2 || i==9 || i==16){
            k=leftshiftby1(lk,rk);
            }
            else{
            k=leftshiftby2(lk,rk);
            }
            lk=fun1(k);
            rk=fun2(k);
            String key48=keyCompressionPermutation(k); //56 bits to 48 bits
            roundkeys[i]=key48;
            long p=Long.parseLong(key48, 2);
            System.out.println("     Key : "+key48+" "+Long.toString(p,16));
            String rPt=PtExpansionPermutation(LPTRPT[1]); //rpt ==> 32bits to 48bits
                   //System.out.println("RPT "+ rPt);
            String XOR=xorOperation(key48,rPt);//xor operation of key and rpt (48 bits)
                   //System.out.println("XOR "+XOR);
            String s=substitution(XOR); // 48bits to 32 bits (6 bit input and 4 bit output)
                  // System.out.println("sub "+s+" "+s.length());
            String spermu=spermutation(s);//permutation of 32bit substituted rpt
                   //System.out.println("Permutated sub "+spermu);
            String left=xor(spermu,LPTRPT[0]);//XOR operation betweeen subsituted and permutated rpt and left part of initial permutated plain text
                   //System.out.println("Left Part "+left);
            LPTRPT[0]=left;
            if (i<=15) {
            String swap=swapPt(left,LPTRPT[1]);//swap of lpt and s 
            LPTRPT[0]=LPTRPT[1];
            LPTRPT[1]=left;
            System.out.println("     Left PT  : "+LPTRPT[0]);
            System.out.println("     Right PT : "+LPTRPT[1]);
            System.out.println("--------------------------------------------------");
            }
            }
            String compine=LPTRPT[0]+LPTRPT[1];
            //System.out.println(LPTRPT[0]+" "+LPTRPT[1]);
            String cipherText=finalpermu(compine);
            //long p=Long.parseLong(cipherText, 2);
            System.out.println("Cipher Text : "+cipherText);
            return cipherText;
    }
    static void decryption(String plainText,String keyhex){
        System.out.println("\n\nDecryption\n\n");
            String[] LPTRPT=new String[2];
            String key=hexTobin(keyhex);
            String key56=key64to56(key);//ByParityBitDropTable
            String pt=ptInitialPermutation(plainText); //Initial Permutation of Plain Test 
            System.out.println("Initial permutation "+pt);
            System.out.println();
            LPTRPT=ptDivision(pt); //devide pt into 32bits of LPT and 32bits of RPT
            for(int i=1;i<=16;i++){
            //System.out.println("--------------------------------------------------");
            System.out.println("Round "+i);
            long p=Long.parseLong(roundkeys[17-i], 2);
            System.out.println("     Key : "+roundkeys[17-i]+" "+Long.toString(p,16));
            String rPt=PtExpansionPermutation(LPTRPT[1]); //rpt ==> 32bits to 48bits
                   //System.out.println("RPT "+ rPt);
            String XOR=xorOperation(roundkeys[17-i],rPt);//xor operation of key and rpt (48 bits)
                   //System.out.println("XOR "+XOR);
            String s=substitution(XOR); // 48bits to 32 bits (6 bit input and 4 bit output)
                  // System.out.println("sub "+s+" "+s.length());
            String spermu=spermutation(s);//permutation of 32bit substituted rpt
                   //System.out.println("Permutated sub "+spermu);
            String left=xor(spermu,LPTRPT[0]);//XOR operation betweeen subsituted and permutated rpt and left part of initial permutated plain text
                   //System.out.println("Left Part "+left);
            LPTRPT[0]=left;
            if (i<=15) {
            String swap=swapPt(left,LPTRPT[1]);//swap of lpt and s 
            LPTRPT[0]=LPTRPT[1];
            LPTRPT[1]=left;
            System.out.println("     Left PT  : "+LPTRPT[0]);
            System.out.println("     Right PT : "+LPTRPT[1]);
            System.out.println("--------------------------------------------------");
            }
            }
            String compine=LPTRPT[0]+LPTRPT[1];
            //System.out.println(LPTRPT[0]+" "+LPTRPT[1]);
            String cipherText=finalpermu(compine);
            //long p=Long.parseLong(cipherText, 2);
            System.out.println("Cipher Text : "+cipherText);
    }
    public static void main(String args[]){
        
        details();
        Scanner input=new Scanner(System.in);
            //System.out.print("Enter the Plain Text : ");
            //String plainText=input.nextLine();
            //System.out.print("Enter the key : ");
            //String key=input.nextLine();
            String plainTexthex="123456ABCD132536";
            String keyhex="AABB09182736CCDD";
            //Hex to Binary Conversion
            String ct=encryption(plainTexthex,keyhex);
            decryption(ct,keyhex);
    }
}