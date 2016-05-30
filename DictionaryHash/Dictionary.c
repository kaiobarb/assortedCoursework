/* 
 * Kaio Barbosa
 * ksbarbos
 * 12B
 * pa5
 * 22 May 2016
 * Dictionary.c
 * Dictionary ADT using hashmaps
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
#define tableSize 101

//Hash functions---------------------------------

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
 int sizeInBits = 8*sizeof(unsigned int);
 shift = shift & (sizeInBits - 1);
 if ( shift == 0 )
  return value;
 return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
 unsigned int result = 0xBAE86554;
 while (*input) {
   result ^= *input++;
   result = rotate_left(result, 5);
 }
 return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
 return pre_hash(key)%tableSize;
}

// Private types

//NodeObj
typedef struct NodeObj{
  char* key;
  char* value;
  struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode() constructor for the Node type
Node newNode(char* k, char* v){
  Node N = malloc(sizeof(NodeObj));
  assert(N!=NULL);
  N->key = k;
  //strcpy(N->key, k);
  N->value = v;
  N->next = NULL;
  return(N);
}

// freeNode() destructor fo the Node type
void freeNode(Node* N){
  if(N != NULL && *N != NULL){
    free(*N);
    *N = NULL;
  }
}

// Dictionary--------------------------------------
// Exported reference type
typedef struct DictionaryObj{
  Node table [tableSize];
  int numItems;
}DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
  assert(D != NULL);
  for(int i = 0; i<tableSize; i++) D->table[i] = NULL;
  D->numItems = 0;
  return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if(pD!=NULL && *pD!=NULL){
     if(!isEmpty(*pD)) makeEmpty(*pD);
     free(*pD);
     *pD= NULL;
  }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
  if(D==NULL){
    fprintf(stderr, "ERROR: calling isEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	if(D==NULL){
		fprintf(stderr, "ERROR: calling size() on NULL Dictionary reference\n");
   exit(EXIT_FAILURE);
   }
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   if(D == NULL){
   fprintf(stderr, "error: bad Dictionary reference");
   exit(EXIT_FAILURE);
   }
   int a = hash(k);
   Node N = D->table[a];
   while(N!=NULL){
      if(strcmp(N->key, k) == 0) return N->value;
   	N = N->next;
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if(lookup(D, k) != NULL){
      fprintf(stderr, "error: duplicate keys");
      exit(EXIT_FAILURE);
   }
   Node N = newNode(k, v);
   N->next = D->table[hash(k)];
   D->table[hash(k)] = N;
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
  if(lookup(D, k) == NULL){
      fprintf(stderr, "error: key not found");
      exit(EXIT_FAILURE);
   }
   int a = hash(k);
   Node current = D->table[a]->next;
	Node previous = D->table[a];

	if(strcmp(k, D->table[a]->key) == 0){

		Node temp = D->table[a];
		D->table[a] = D->table[a]->next;
		freeNode(&temp);
		D->table[a] = NULL;
		D->numItems--;
		return;
	}
	while(current!=NULL && previous !=NULL){
		if(strcmp(k, current->key) == 0){
			assert(current != NULL);
			Node temp = current;
			previous->next = current->next;
			freeNode(&temp);
			D->numItems--;
			return;
		}
		previous = current;
		current = current->next;
	}
   D->numItems--;
	return;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   if(D == NULL){
      exit(EXIT_FAILURE);
   }
   if(D->numItems == 0){
      exit(EXIT_FAILURE);
   }
   Node current = NULL;
	Node next = NULL;
   for(int i = 0; i<tableSize; i++){
		current = D->table[i];
		while(current!=NULL){
		   next = current->next;
			freeNode(&current);
			current = next;
		}
		freeNode(&current);
		D->table[i] = NULL;
   }
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	Node N;
	for(int i = 0; i<tableSize; i++){
		for(N=D->table[i]; N!=NULL; N=N->next){
			fprintf(out,"%s %s \n", N->key, N->value);
		}
	}
}
