// GCD.java
// Kaio Barbosa
// ksbarbos
// pa3
// This program returns the greatest common denominator of 2 positive integers.

import java.util.Scanner;

class GCD{

  public static void  main(String[] args){

    int a, b, remainder=1, gdc=0;
    Scanner sc = new Scanner(System.in);
    
    System.out.print("Enter a positive integer: ");
    while(true){
      if(sc.hasNextInt()){
      	a = sc.nextInt();
      	if(a>0) break;
      }else sc.next();
      
      System.out.print("Please enter a positive integer: ");
      }
    
    System.out.print("Enter another positive integer: ");
    while(true){
      if(sc.hasNextInt()){
      	b = sc.nextInt();
      	if(b>0) break;
      }else sc.next();
      
      System.out.print("Please enter a positive integer: ");
      }
    
    System.out.print("The GCD of "+a+" and "+b+" is ");

    if(b>a){
      int temp=a;
      a=b;
      b=temp;
    }

    while(remainder > 0){
      	gdc = remainder;
      	remainder = a%b;
      	a = b;
      	b = remainder;
      }
    
    System.out.println(gdc);
    }
}
