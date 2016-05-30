/* 
 * Kaio Barbosa
 * ksbarbos
 * 12B
 * pa5
 * 22 May 2016
 * DictionaryTest.c
 * Program to test Dictionary functions
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define tableSize 101

int main(int argc, char* argv[]){
 Dictionary A = newDictionary();
 //char* k;
 //char* v;
  char* X[] = {"one","two","three","four","five","six","seven"};
  char* Y[] = {"a","b","c","d","e","f","g"};
  int i;

  for(i=0; i<7; i++){
    insert(A, X[i], Y[i]);
  }

	printDictionary(stdout, A);
	fprintf(stdout, "%d\n", isEmpty(A));
	delete(A, "six");
	printDictionary(stdout, A);
	makeEmpty(A);
	printDictionary(stdout, A);
	fprintf(stdout, "%d\n", isEmpty(A));
   freeDictionary(&A);

}
