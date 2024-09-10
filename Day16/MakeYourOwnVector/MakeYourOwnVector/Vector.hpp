//
//  Vector.hpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#ifndef Vector_hpp
#define Vector_hpp

#include <stdio.h>

struct myVector{
    int* ptr;
    int capacity;
    int size;
};

myVector makeVector(int initialCapacity);
void freeVector(myVector &myVec);
void pushBack(myVector &myVec, int value);
void popBack(myVector &myVec);
int get(const myVector &myVec, int index);
void set(myVector &myVec, int index, int newValue);
void growVector(myVector &myVec);


#endif /* Vector_hpp */
