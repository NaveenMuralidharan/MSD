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

template <typename T>

class myVector{
    T* ptr;
    int capacity;
    int size;
    
public:
    myVector(int initialCapacity);
    myVector(const myVector & rhs);
    void operator =(const myVector & rhs);
    T operator [](int index)const;
    T& operator [](int index);
    int getSize();
    int getCapacity();
    ~myVector();
    void pushBack(T value);
    void popBack();
    T get(int index);
    void set(int index, T newValue);
    void growVector();
    bool operator ==(const myVector& rhs)const;
    bool operator !=(const myVector& rhs)const;
    bool operator <(const myVector& rhs)const;
    bool operator <=(const myVector& rhs)const;
    bool operator >(const myVector& rhs)const;
    bool operator >=(const myVector& rhs)const;
    T* begin()const;
    T* begin();
    T* end()const;
    T* end();
};

template <typename T>

myVector<T>::myVector(int initialCapacity){

    capacity = initialCapacity;
    ptr = new T[capacity];
    size = 0;
}
//copy constructor
template <typename T>
myVector<T>::myVector(const myVector<T> & rhs){
    ptr = new T[rhs.capacity];
    for(int i=0; i< rhs.size; i++){
        ptr[i] = rhs.ptr[i];
    }
    size = rhs.size;
    capacity = rhs.capacity;
}
//overload = operator
template <typename T>
void myVector<T>::operator =(const myVector & rhs){
    myVector temp = myVector(rhs);
    std::swap(size, temp.size);
    std::swap(capacity, temp.capacity);
    std::swap(ptr, temp.ptr);
}
// [] to get value at index
template <typename T>
T myVector<T>::operator [](int index)const{
    return ptr[index];
}
//[] to set value at index
template <typename T>
T& myVector<T>::operator [](int index){
    return ptr[index];
}

template <typename T>
int myVector<T>::getSize(){
    return size;
}

template <typename T>
int myVector<T>::getCapacity(){
    return capacity;
}

template <typename T>
myVector<T>::~myVector(){
    delete [] ptr;
}

template <typename T>
void myVector<T>::pushBack(T value){
    if(size == capacity){
        growVector();
    }
    ptr[size] = value;
    size ++;
}

template <typename T>
void myVector<T>::popBack(){
    if(size == 0){
        size = 0;
    } else {
        size -= 1;
    }
}

template <typename T>
T myVector<T>::get(int index){
    return ptr[index];
}

template <typename T>
void myVector<T>::set(int index, T newValue){
    if(index < size){
        ptr[index] = newValue;
    }
}

template <typename T>
void myVector<T>::growVector(){
    T *newPtr = new T[capacity*2];

    for(int i=0; i<size; i++){
        newPtr[i] = ptr[i];
    }
    delete [] ptr;
    ptr = newPtr;
    capacity *= 2;
    newPtr = nullptr;
}
//******************
//PART 3: Comparisons
//==
template <typename T>
bool myVector<T>::operator ==(const myVector& rhs)const {
    if(size != rhs.size){
        return false;
    }
    for(int i=0; i<size; i++){
        if(ptr[i] != rhs.ptr[i]){
            return false;
        }
    }
    return true;
}
//!=
template <typename T>
bool myVector<T>::operator !=(const myVector& rhs)const{
    return !(*this == rhs);
}
// <
template <typename T>
bool myVector<T>::operator <(const myVector& rhs)const{
    for(int i=0; i<size; i++){
        if(ptr[i] < rhs.ptr[i]){
            return true;
        }
    }
    return false;
}
// <=
template <typename T>
bool myVector<T>::operator <=(const myVector& rhs)const{
    return (*this == rhs || *this < rhs);
}
// >
template <typename T>
bool myVector<T>::operator >(const myVector& rhs)const{
    return rhs < *this;
}
// >=
template <typename T>
bool myVector<T>::operator >=(const myVector& rhs)const{
    return (*this == rhs || *this > rhs);
}
//begin() const
template <typename T>
T* myVector<T>::begin()const{
    return ptr;
}
//begin() non const
template <typename T>
T* myVector<T>::begin(){
    return ptr;
}
//end() const
template <typename T>
T* myVector<T>::end()const{
    return begin() + size;
}
//end() non const
template <typename T>
T* myVector<T>::end(){
    return begin() + size;
}
#endif /* Vector_hpp */
