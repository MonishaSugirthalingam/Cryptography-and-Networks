
import java.util.*;
class Main{
    
    static void details()
    {
        System.out.println("******************************************************");
        System.out.println("  TITLE : Implementation of Caeser Cypher Algorithm");
        /* Encyption  plaintext(i)+key=cypher Text(i)
           Decyption  cyphertext(i)-key=plaintext(i)
           */
        System.out.println("  NAME  : MONISHA S");
        System.out.println("  DATE  : 20th Jan 2024");
        System.out.println("******************************************************");
    }
    static void encryption(String a,int b){
        for(int i=0;i<a.length();i++)
        {
            int c=a.charAt(i)+b;
            char l=(char)c;
            System.out.print(l);
        }
    }
    
    static void decryption(String a,int b){
        for(int i=0;i<a.length();i++)
        {
            int c=a.charAt(i)-b;
            char l=(char)c;
            System.out.print(l);
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
            int key=input.nextInt();
            encryption(plainText,key);
        }
        else{
            System.out.print("Enter the Cipher Text : ");
            String cipherText=input.nextLine();
            System.out.print("Enter the key : ");
            int key=input.nextInt();
            decryption(cipherText,key);
        }
    }
}