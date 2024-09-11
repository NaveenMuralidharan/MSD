//
//  Vector.cpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#include "Vector.hpp"

myVector::myVector(int initialCapacity){
    
    capacity = initialCapacity;
    ptr = new int[capacity];
    size = 0;
}

int myVector::getSize(){
    return size;
}

int myVector::getCapacity(){
    return capacity;
}

void myVector::freeVector(){
    delete ptr;
    ptr = nullptr;
    size = 0;
}

void myVector::pushBack(int value){
    if(size == capacity){
        growVector();
    }
    ptr[size] = value;
    size ++;
}

void myVector::popBack(){
    if(size == 0){
        size = 0;
    } else {
        size -= 1;
    }
}

int myVector::get(int index){
    return ptr[index];
}

void myVector::set(int index, int newValue){
    if(index < size){
        ptr[index] = newValue;
    }
}

void myVector::growVector(){
    int *newPtr = new int[capacity*2];
    
    for(int i=0; i<size; i++){
        newPtr[i] = ptr[i];
    }
    delete [] ptr;
    ptr = newPtr;
    capacity *= 2;
    newPtr = nullptr;
}
