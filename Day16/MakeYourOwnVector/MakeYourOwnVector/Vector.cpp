//
//  Vector.cpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#include "Vector.hpp"

myVector makeVector(int initialCapacity){
    
    myVector myVec;
    myVec.capacity = initialCapacity;
    myVec.ptr = new int[myVec.capacity];
    myVec.size = 0;
    
    return myVec;
}

void freeVector(myVector &myVec){
    delete myVec.ptr;
    myVec.ptr = nullptr;
}

void pushBack(myVector &myVec, int value){
    if(myVec.size == myVec.capacity){
        growVector(myVec);
    }
    myVec.ptr[myVec.size] = value;
    myVec.size ++;
}

void popBack(myVector &myVec){
    if(myVec.size == 0){
        myVec.size = 0;
    } else {
        myVec.size -= 1;
    }
}

int get(const myVector &myVec, int index){
    return myVec.ptr[index];
}

void set(myVector &myVec, int index, int newValue){
    if(index < myVec.size){
        myVec.ptr[index] = newValue;
    }
}

void growVector(myVector &myVec){
    int *newPtr = new int[myVec.capacity*2];
    
    for(int i=0; i<myVec.size; i++){
        newPtr[i] = myVec.ptr[i];
    }
    delete [] myVec.ptr;
    myVec.ptr = newPtr;
    myVec.capacity *= 2;
    newPtr = nullptr;
}
