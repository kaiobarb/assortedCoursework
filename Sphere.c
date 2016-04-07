/* Sphere.c
 * Kaio Barbosa
 * ksbarbos
 * This program outputs the volume and surface area of a sphere
 */
#include<stdio.h>
#include<stdio.h>
#include<math.h>

int main(){
   double x, volume, sArea;
   const double fraction = 4.0/3.0, pi = 3.141592654;
   
   printf("Enter the radius of the sphere: ");
   scanf("%lf", &x);
   volume = fraction*pi*pow(x,3);
   sArea = 4.0*pi*pow(x,2);
   printf("The volume is %f and the surface is %f.\n", volume, sArea);
   return 0;
}
