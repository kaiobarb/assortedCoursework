// Roots.java
// Kaio Barbosa
// ksbarbos
// pa4
// This function finds all real roots of a polynomial.

import java.util.Scanner;
import java.io.File;

class Roots{

	public static void main(String[] args) throws Exception{

		double resolution=Math.pow(10,-2);
		double tolerance=Math.pow(10,-7);
		double threshold=Math.pow(10,-3);
		double root;
		Scanner sc = new Scanner(System.in);
		int degree;
		double[] coeff, endP;

		System.out.print("Enter the degree: ");
		while(true){
			if(sc.hasNextInt()){
			degree = sc.nextInt();
			break;
		}else sc.next();
		}

		System.out.print("Enter "+(degree+1)+" coefficients: ");

		coeff = new double[degree+1];
		while(true){
			if(sc.hasNextDouble()){
				for(int i=0;i<coeff.length;i++){
					coeff[i] = sc.nextDouble();
				}
				break;
			}else sc.next();
		}

		System.out.print("Enter the left and right endpoints: ");

		endP = new double[2];
		while(true){
			if(sc.hasNextDouble()){
				for(int i=0; i<endP.length; i++){
					endP[i] = sc.nextDouble();
				}
			} break;
		}
		System.out.println();


		double L = endP[0], R = endP[1], a = endP[0], b = (endP[0]+resolution);
		double[] deriv = diff(coeff);

		while(a < R){
			if(poly(coeff, a)*poly(coeff, b) < 0){
				root = findRoot(coeff, a, b, tolerance);
				System.out.printf("Root found at %.5f%n", root);

			} else if (poly(deriv, a)*poly(deriv, b) < 0){
					root = findRoot(deriv, a, b, tolerance);
					if(poly(coeff, root) < threshold && root > threshold || root < -threshold){
					System.out.printf("Root found at %.5f%n", root);
				}
			} else {
				System.out.println("No roots were found in the specified range.");
				break;
			}
			a=b;
			b+=resolution;

		}

	}

	static double poly(double[] C, double x){

		double sum=0;

		for(int i=0; i < C.length; i++){
			sum += Math.pow(x, i)*C[i];
		}

		return sum;

	}

	static double[] diff(double[] C){
		int n = C.length;
		double[] D = new double[n-1];

		for(int i=0; i < n-1; i++){
			D[i] = C[i+1]*(i+1);		
		}
		return D;
	}

	static double findRoot(double[] C, double a, double b, double tolerance){

		double mid = 0, width;

		while (b-a>tolerance){
			mid = (a+b)/2;
			if( poly(C, a)*poly(C, mid) < 0 ) b = mid;
			else a = mid;
			width = b-a;
		}
		return mid;
	}
}