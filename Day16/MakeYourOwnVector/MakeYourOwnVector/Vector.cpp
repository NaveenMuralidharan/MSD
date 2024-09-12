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
//copy constructor
myVector::myVector(const myVector & rhs){
    ptr = new int[rhs.capacity];
    for(int i=0; i< rhs.size; i++){
        ptr[i] = rhs.ptr[i];
    }
    size = rhs.size;
    capacity = rhs.capacity;
}
// overload = operator
void myVector::operator =(const myVector & rhs){
    myVector temp = myVector(rhs);
    std::swap(size, temp.size);
    std::swap(capacity, temp.capacity);
    std::swap(ptr, temp.ptr);
}
// [] to get value at index
int myVector::operator [](int index)const{
    return ptr[index];
}
//[] to set value at index
int& myVector::operator [](int index){
    return ptr[index];
}

int myVector::getSize(){
    return size;
}

int myVector::getCapacity(){
    return capacity;
}

myVector::~myVector(){
    delete [] ptr;
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
