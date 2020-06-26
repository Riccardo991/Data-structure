// Linked-List.cpp : Questo file contiene la funzione 'main', in cui inizia e termina l'esecuzione del programma.
 
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <iostream>
using namespace std;

// this is a implementation of a Linkedlist  and its basic methods 

struct Node {
    int val;
    struct Node *next;    
};

struct Node* head = NULL;
struct Node* walk= NULL;

// add a new element e at front of the list 
void addFirst (int e) {
    struct Node* elem  = (struct Node*) malloc(sizeof(struct Node));
    elem->val = e;
    elem->next = head;          
    head = elem;
}

// add a new element e at end of the list 
void addLast(int e) {
    struct Node* elem  = (struct Node*) malloc(sizeof(struct Node));
    elem->val = e;
    elem->next = NULL;

    for (walk = head; walk->next != NULL; walk = walk->next) {}
    walk->next = elem;
}

// verify  if the list is empty 
bool isEmpty() {
    return head == NULL;
}

//  remove  the first element of the list 
void removeFirst() {
    head = head->next;
}

// remove  the last element of the list 
void removeLast() {
    struct Node* prep = (struct Node*) malloc(sizeof(struct Node));
    prep = NULL;
    for (walk = head; walk->next != NULL; walk = walk->next) {
        prep = walk;
    }
    prep->next= NULL;
}

// get the size of the list 
int sizeList() {
    int k = 0;
    for(walk = head; walk != NULL; walk = walk->next){
        k++;
    }
    return k;
}

// get the  element of the list that it is  in  position n 
struct Node* getElem(int n) {    
    int l = sizeList();
    if ( n <= l) {
        int y = 0;
        for (walk = head; walk != NULL; walk = walk->next) {
            y++;
            if (y == n) {                
                return walk;
            }
        }                
    }
    return NULL;
}

// print the list  in input 
void printList(struct Node *mylist) {
    walk = mylist; 
    while (walk != NULL) {
        int t = walk->val;
        cout << t << ", ";
        walk = walk->next;
    }
    cout << "\n ";
}

//  search the  input element  in the list. if it is in the list return its  position, else return -1 
int searchElem(int e) {
    int z = -1;
    int y = 0;
    for (walk = head; walk != NULL; walk = walk->next) {
        int x = walk->val;
        if (x == e) { z = y; }
        y++;
    }
    return z;
}

// remove the input element from the list
bool removeElem(int e) {
    struct Node* prep = NULL;
    walk = head;
    
    if (head == NULL) {
        return false;
    }
    if (head->val == e) {
        head = head->next;
        return true;
    }
     
    while( walk != NULL){
        if (walk->val == e) {
            prep->next = walk->next;
            return true;
        }
        prep = walk;
        walk = walk->next;
    }
    return false;
}

// insert the input element e in the list   in  input  position.  
//if the input position is bigger  than size  of list return false
bool insertElem(int e, int n) {
    int l = sizeList();
    if (n < l) {
        struct Node* prep = NULL;
        walk = head;
        for (int i = 0; i < n; i++) {
            prep = walk;
            walk = walk->next;            
        }
         struct Node* elem = (struct Node*) malloc(sizeof(struct Node));
         elem->val = e;
         prep->next = elem;
         elem->next = walk;
        return true;
    }
    else {
        return false;
    }
}

// create a random list. the size of list is equals   to input number   
void makeRandomList(int n) {
    while (n > 0) {
        int e = rand() % 20;
        addFirst(e);
        n--;
    }
}

// get the reverse  list 
struct Node* reverse() {
    struct Node* revhead = (struct Node*) malloc(sizeof(struct Node));
    revhead = NULL;
    for (walk = head; walk != NULL; walk = walk->next) {
         struct Node *elem = (struct Node*) malloc(sizeof(struct Node));
         elem->val = walk->val;
         elem->next = revhead;
         revhead = elem;
    }
    return revhead;
}

int main()
{
    cout << " original list \n "; 
    makeRandomList(10);
    printList(head );
    struct Node* rev = reverse();
    cout << "  the reverse list is \n";
    printList(rev);
 
}
 