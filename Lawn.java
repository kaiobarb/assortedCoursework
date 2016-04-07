/* Lawn.java
 * Kaio Barbosa
 * ksbarbos
 * lab3
 * This program calculates the time it takes to mow a lawn around a house of dimensions specified by the user. */
 import java.util.Scanner;
 
 class Lawn{
   
   public static void main( String[] args) {

     double lotLength, lotWidth, lotArea, houseLength, houseWidth,
            houseArea, mowingRate, mowingTime, lawnArea;
     int h, m, s;
     Scanner sc = new Scanner(System.in);

     lotLength = sc.nextDouble();
     lotWidth = sc.nextDouble();
     lotArea = lotLength * lotWidth;
     
     houseLength = sc.nextDouble();
     houseWidth = sc.nextDouble();
     houseArea = houseLength * houseWidth;
     lawnArea = lotArea-houseArea;
     System.out.print( "The lawn area is: " + lawnArea+"\n" );
     
     mowingRate = sc.nextDouble();
     mowingTime = lawnArea / mowingRate;
   
     s = (int) Math.round(mowingTime);
     m = s / 60;
     s = s % 60;
     h = m / 60;
     m = m % 60;

     System.out.println( "The mowing time is " +  h + " hour" + (h==1? " " : "s ") + m + " minute" + (m==1? "" : "s") + " and " + s + " second" + (s==1? "." : "s." ));

   }
 }
