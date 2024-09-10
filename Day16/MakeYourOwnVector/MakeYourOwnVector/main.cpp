//
//  main.cpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar on 9/10/24.
//

#include <iostream>
#include "Vector.hpp"
#include <cassert>

int main(int argc, const char * argv[]) {
    
    //test makeVector
    myVector test1Vec = makeVector(4);
    assert(test1Vec.capacity == 4);
    assert(test1Vec.size == 0);
    
    //test freeVector
    freeVector(test1Vec);
    assert(test1Vec.ptr == nullptr);
    
    //test pushBack
    myVector test2Vec = makeVector(3);
    pushBack(test2Vec, 80);
    assert(test2Vec.ptr[test2Vec.size-1] == 80);
    
    //test growVector
    myVector testGrowVec = makeVector(1);
    pushBack(testGrowVec, 12);
    pushBack(testGrowVec, 20);
    pushBack(testGrowVec, 22);
    pushBack(testGrowVec, 34);
    pushBack(testGrowVec, 45);
    assert(testGrowVec.capacity == 8);
    
    //test popBack
    myVector testPopBack = makeVector(3);
    pushBack(testPopBack, 10);
    pushBack(testPopBack, 12);
    popBack(testPopBack);
    assert(testPopBack.size == 1);
    
    //test get
    assert(get(testGrowVec, 2) == 22);
    
    //test set
    myVector testSetVec = makeVector(4);
    pushBack(testSetVec, 12);
    set(testSetVec, 0, 10);
    assert(testSetVec.ptr[0]==10);
    
    return 0;
}
