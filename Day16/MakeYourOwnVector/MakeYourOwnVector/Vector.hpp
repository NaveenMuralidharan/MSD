//
//  Vector.hpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#ifndef Vector_hpp
#define Vector_hpp
#include <iostream>
#include <stdio.h>

class myVector{
    int* ptr;
    int capacity;
    int size;
    
public:
    myVector(int initialCapacity);
    myVector(const myVector & rhs);
    void operator =(const myVector & rhs);
    int operator [](int index)const;
    int& operator [](int index);
    int getSize();
    int getCapacity();
    ~myVector();
    void pushBack(int value);
    void popBack();
    int get(int index);
    void set(int index, int newValue);
    void growVector();
    
};

//myVector makeVector(int initialCapacity);
//void freeVector(myVector &myVec);
//void pushBack(myVector &myVec, int value);
//void popBack(myVector &myVec);
//int get(const myVector &myVec, int index);
//void set(myVector &myVec, int index, int newValue);
//void growVector(myVector &myVec);


#endif /* Vector_hpp */
