//
//  main.cpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#include <iostream>
#include "Vector.hpp"
#include <cassert>

int main(int argc, const char * argv[]) {
    
    //test constructor
    myVector test1Vec(4);
    test1Vec.pushBack(10);
    test1Vec.pushBack(12);
    assert(test1Vec.getCapacity() == 4);
    assert(test1Vec.getSize() == 2);
//
    
    //test pushBack
    myVector test2Vec(3);
    test2Vec.pushBack(80);
    assert(test2Vec.get(test2Vec.getSize()-1) == 80);
    
    //test growVector
    myVector testGrowVec(1);
    testGrowVec.pushBack(12);
    testGrowVec.pushBack(20);
    testGrowVec.pushBack(22);
    testGrowVec.pushBack(34);
    testGrowVec.pushBack(45);
    assert(testGrowVec.getCapacity() == 8);
    
    //test popBack
    myVector testPopBack(3);
    testPopBack.pushBack(10);
    testPopBack.pushBack(12);
    testPopBack.popBack();
    assert(testPopBack.getSize() == 1);
    
    //test get
    assert(testGrowVec.get(2) == 22);

    //test set
    myVector testSetVec(4);
    testSetVec.pushBack(12);
    testSetVec.set(0, 10);
    assert(testSetVec.get(0)==10);
    
    return 0;
}
