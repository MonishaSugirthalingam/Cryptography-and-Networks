import java.util.*;

class Main{
    static int[][] keymat=new int[3][3];
    static int[][] ptmat=new int[3][1];
    static int[][] ct=new int[3][1];
    static void details()
    {
        System.out.println("******************************************************");
        System.out.println("  TITLE : Implementation of Hill Cypher Algorithm");
        System.out.println("  NAME  : MONISHA S");
        System.out.println("  DATE  : 20th Jan 2024");
        System.out.println("******************************************************");
    }
    static void encryption(){
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<1;j++)
            {
                ct[i][j]=0;
                for(int z=0;z<3;z++){
                    ct[i][j]=ct[i][j]+(keymat[i][z]*ptmat[z][j]);
                }
                ct[i][j]=ct[i][j]%26;
            }
        }
        System.out.print("Cipher Text : ");
        for(int i=0;i<3;i++){
            for(int j=0;j<1;j++)
            {
                char l=(char)(ct[i][j]+65);
                System.out.print(l);
            }
        }
    }
    static void pmatrix(String a){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<1;j++){
                ptmat[i][j]=a.charAt(count)%65;
                count++;
            }
        }
        /*for(int i=0;i<3;i++){
            for(int j=0;j<1;j++){
                System.out.print(ptmat[i][j]+" ");
            }
            System.out.println();
        }*/
    }
    static void kmatrix(String a){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                keymat[i][j]=a.charAt(count)%65;
                count++;
            }
        }
        /*for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(keymat[i][j]+" ");
            }
            System.out.println();
        }*/
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
            pmatrix(plainText);
            kmatrix(key);
            encryption();
        }
        else{
            System.out.print("Enter the Cipher Text : ");
            String cipherText=input.nextLine();
            System.out.print("Enter the key : ");
            String key=input.nextLine();
            //matrix(key);
            //decryption(cipherText);
            
        }
    }
}