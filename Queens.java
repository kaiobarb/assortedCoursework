//---------------------------------------------------------
// Kaio Barbosa
// ksbarbos
// pa5
// This program generates solutions to the n-Rooks problem 
// and prints out the solution for the n-Queens problem.
//---------------------------------------------------------

import java.util.Scanner;

class Queens{
	public static void main(String[] args){
		boolean verbose = false, wrongInput = false;
		int n =0, numberOf = 0;
		String option = "-v";
		if(args.length > 0){
			if(args[0].equals(option))
				verbose = true;
			try{
				n = Integer.parseInt(args[args.length-1]);
			}catch(NumberFormatException e){
				error();
				wrongInput = true;
			}
		}else{
			error();
			wrongInput = true;
		}
		if(args.length > 2){
			error();
			wrongInput = true;
		}else if(args.length > 1 && verbose == false){
			error();
			wrongInput = true;
		}

		if(wrongInput == false){
			int[] A = new int[n];
			for(int i = 0; i < n; i++)
				A[i] = i+1;

		for(int i = 0; i < factorial(n); i++){
			if(isSolution(A)){
				numberOf += 1;
				if(verbose){
					printArray(A);
				}
			}
			nextPermutiation(A);
		}
		System.out.println(n +"-Queens has " + numberOf + " soulutions");
		}
	}

public static void nextPermutiation(int[] A){
	for (int a = A.length - 2; a >= 0; --a)              // Find pivot;
    if (A[a] < A[a + 1])								 // If a value is less than its right hand neighbor (pivot found),
      for (int b = A.length - 1;; --b)					 // scan array for a value (A[b]) that is greater than pivot (A[a]);
        if (A[b] > A[a]) {								 // If A[b] is greater,
          swap(A, a, b);								 // swap and reverse array from a to the end of the list; 
          for (++a, b = A.length - 1; a < b; ++a, --b) { // reverse array with a single for loop;
            swap(A, a, b);
          }
          return;
        }
	return;
}

static void swap(int[] Q, int i, int j){
      int temp = Q[i];
      Q[i] = Q[j];
      Q[j] = temp;
   }

public static void reverse(int[] Y, int a){
	int b = Y.length-1;
    while(a<b){
    	swap(Y, a, b);
    	a++;
    	b--;
    }
}

public static int factorial(int n){
    int result = 1;
    for (int i = 1; i <= n; i++) {
    	result = result * i;
	}
	return result;
}

public static void printArray(int[] P){
	System.out.print("(");
    for(int i=0; i<P.length; i++){
    	if(i<P.length-1)
    		System.out.print(P[i]+", ");
    	else 
    		System.out.print(P[i]);
    }
    System.out.println(")");
}

static boolean isSolution(int[] D){
	for(int i = 0; i < D.length; i++)
		for(int j = i+1; j < D.length; j++){
			if(Math.abs(i-j) == Math.abs(D[i]-D[j])) {
				return false;
			} 
		}
	return true;
}

static void error(){
	System.out.println("Usage: Queens [-v] number");
	System.out.println("Option: -v verbose output, print all solutions");
}
}
