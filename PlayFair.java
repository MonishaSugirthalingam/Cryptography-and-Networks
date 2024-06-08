import java.util.*;
import java.lang.Math;

class Main{
    
    static char[][] arr=new char[5][5];
    static int[] alpha=new int[26];

    static void details()
    {
        System.out.println("******************************************************");
        System.out.println("  TITLE : Implementation of Playfair Cypher Algorithm");
        System.out.println("  NAME  : MONISHA S");
        System.out.println("  DATE  : 20th Jan 2024");
        System.out.println("******************************************************");
    }
    static int[] rowcol(char a,char b){
        int[] index=new int[4];
        int count=0;
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++){
                if(arr[i][j]==a){
                    index[0]=i;
                    index[1]=j;
                }
                if(arr[i][j]==b){
                    index[2]=i;
                    index[3]=j;
                }
                if(count==2){
                    break;
                }
            }
            if(count==2)
            {
                break;
            }
        }
        return index;
    }
    static void decryption(String ct){
        int[] index=new int[4];
        int r1,c1,r2,c2;
        for(int i=0;i<ct.length();i=i+2){
            index=rowcol(ct.charAt(i),ct.charAt(i+1));
            //same row
            if(index[0]==index[2]){
                r1=index[0];
                c1=index[1]-1;
                if(c1<0){
                    c1=4;
                }
                r2=index[2];
                c2=index[3]-1;
                if(c2<0){
                    c2=4;
                }
                //System.out.println(r1+" "+c1+" "+r2+" "+c2);
                System.out.print(arr[r1][c1]+" "+arr[r2][c2]+" ");
            }
            //same col
            else if(index[1]==index[3])
            {
                r1=index[0]-1;
                if (r1<0){
                    r1=4;
                }
                c1=index[1];
                r2=index[2]-1;
                if(r2<0){
                    r2=4;
                }
                c2=index[3];
                //System.out.println(r1+" "+c1+" "+r2+" "+c2);
                System.out.print(arr[r1][c1]+" "+arr[r2][c2]+" ");

            }
            else{
                System.out.print(arr[index[0]][index[3]]+" "+arr[index[2]][index[1]]+" ");
            }
        }
        
    }
    static void encryption(String pt){
        int[] index=new int[4];
        int r1,c1,r2,c2;
        for(int i=0;i<pt.length();i=i+2){
            index=rowcol(pt.charAt(i),pt.charAt(i+1));
            //same row
            if(index[0]==index[2]){
                r1=index[0];
                c1=(index[1]+1)%5;
                r2=index[2];
                c2=(index[3]+1)%5;
                //System.out.println(r1+" "+c1+" "+r2+" "+c2);
                System.out.print(arr[r1][c1]+" "+arr[r2][c2]+" ");
            }
            //same col
            else if(index[1]==index[3])
            {
                r1=(index[0]+1)%5;
                c1=index[1];
                r2=(index[2]+1)%5;
                c2=index[3];
                //System.out.println(r1+" "+c1+" "+r2+" "+c2);
                System.out.print(arr[r1][c1]+" "+arr[r2][c2]+" ");

            }
            else{
                System.out.print(arr[index[0]][index[3]]+" "+arr[index[2]][index[1]]+" ");
            }
        }
    }
    static String NewPt(String a){
        String n="";
        for(int i=0;i<a.length();i++){
            if ((i+1)==a.length()){
                n=n+a.charAt(i)+'X';
                //System.out.println(n);
            }
            else if(a.charAt(i)!=a.charAt(i+1)){
                n=n+a.charAt(i)+a.charAt(i+1);
                //System.out.println(n);
                i++;
            }
            else if(a.charAt(i)==a.charAt(i+1)){
                n=n+a.charAt(i)+'X';
                //System.out.println(n);
            }
        }
        //System.out.println(n);
        return n;
    }
    static boolean notinkey(String a,char x){
        for(int i=0;i<a.length();i++)
        {
            if(x==a.charAt(i)){
                return false;
            }
        }
        return true;
    }
    static String removeduplicates(String a){
        String key="";
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)!=' ' && notinkey(key,a.charAt(i))){
                key=key+a.charAt(i);
            }
        }
        System.out.println(key);
        return key;
    }
    static void matrix(String a){
        for(int i=0;i<26;i++)
        {
        alpha[i]=0;
        }
        int count=0;
        int i=0;
        int ijfound=0;
        while(count<a.length()){
            for(int j=0;j<5;j++)
            {
                if(count==a.length())
                {
                    break;
                }
                arr[i][j]=a.charAt(count);
                if (arr[i][j]=='I' || arr[i][j]=='J'){
                    ijfound=1;
                }
                alpha[a.charAt(count)-65]=1;
                //System.out.println(a.charAt(count)-65);
                count++;
            }
            i++;
        }
        int row=i-1;
        int col=(a.length()%5);
        if(col==0){
            row=row+1;
        }
        //System.out.println(row+" "+col);
        for(i=0;i<26;i++)
        {
            if(alpha[i]!=1){
                if(col==5){
                    col=0;
                    row=row+1;
                }
                int l=i+'A';
                char x=(char)l;
                //System.out.println(row+" "+col+" "+x);
                if(x=='I' || x=='J'){
                    if(ijfound!=1){
                        arr[row][col]=x;
                        ijfound=1;
                        col++;
                    }
                }
                else{
                    arr[row][col]=x;
                    col++;
                }
                
            }
        }
        for(int r=0;r<5;r++)
        {
            for(int c=0;c<5;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        details();
        Scanner input=new Scanner(System.in);
        System.out.println("What do you want to perform?"+"\n"+"1.Encryption"+"\n"+"2.Decryption");
        System.out.print("Enter your choice : ");
        int choice=input.nextInt();
        String line=input.nextLine();
        
        if (choice==1){
            System.out.print("Enter the Plain Text : ");
            String plainText=input.nextLine();
            System.out.print("Enter the key : ");
            String key=input.nextLine();
            key=removeduplicates(key);
            matrix(key);
            String newpt=NewPt(plainText);
            encryption(newpt);
        }
        else{
            System.out.print("Enter the Cipher Text : ");
            String cipherText=input.nextLine();
            System.out.print("Enter the key : ");
            String key=input.nextLine();
            key=removeduplicates(key);
            matrix(key);
            decryption(cipherText);
            
        }
    }
}