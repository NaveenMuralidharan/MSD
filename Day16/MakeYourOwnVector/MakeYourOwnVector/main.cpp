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
    
    //test copy constructor
    myVector copy(4);
    copy.pushBack(1);
    copy.pushBack(2);
    
    myVector testCopy(copy);
    assert(copy.getSize() == testCopy.getSize());
    assert(copy.get(0) == testCopy.get(0));
    testCopy.pushBack(3);
    assert(copy.get(2) != testCopy.get(2));
    
    // test = operator overload
    myVector copy1(4);
    copy1.pushBack(1);
    copy1.pushBack(2);
    
    myVector copy2(6);
    copy2.pushBack(3);
    copy2.pushBack(4);
    copy1 = copy2;
    assert(copy1.get(0)==copy2.get(0));
    assert(copy1.getSize()==copy2.getSize());
    //test [] get value
    assert(copy2[0] == 3);
    //test [] set value
    copy2[0] = 10;
    assert(copy2[0] == 10);

    
    return 0;
}
