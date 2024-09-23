//
//  main.cpp
//  MakeYourOwnVector
//
//  Created by Naveen Kumar and Ben Lemon on 9/10/24.
//

#include <iostream>
#include "Vector.hpp"
#include <cassert>
#include <numeric>

int main(int argc, const char * argv[]) {
    
    //test constructor
    myVector<int> test1Vec(4);
    test1Vec.pushBack(10);
    test1Vec.pushBack(12);
    assert(test1Vec.getCapacity() == 4);
    assert(test1Vec.getSize() == 2);
    
    myVector<std::string> test1strVec(4);
    test1strVec.pushBack("hello");
    test1strVec.pushBack("hi");
    assert(test1strVec.getCapacity() == 4);
    assert(test1strVec.getSize() == 2);
    
    //test pushBack
    myVector<int> test2Vec(3);
    test2Vec.pushBack(80);
    assert(test2Vec.get(test2Vec.getSize()-1) == 80);
    
    myVector<std::string> test2strVec(3);
    test2strVec.pushBack("hello");
    assert(test2strVec.get(test2strVec.getSize()-1) == "hello");
    
    //test growVector
    myVector<int> testGrowVec(1);
    testGrowVec.pushBack(12);
    testGrowVec.pushBack(20);
    testGrowVec.pushBack(22);
    testGrowVec.pushBack(34);
    testGrowVec.pushBack(45);
    assert(testGrowVec.getCapacity() == 8);
    
    myVector<std::string> testGrowStrVec(1);
    testGrowStrVec.pushBack("hi");
    testGrowStrVec.pushBack("how");
    testGrowStrVec.pushBack("are");
    testGrowStrVec.pushBack("you");
    testGrowStrVec.pushBack("doing");
    assert(testGrowStrVec.getCapacity() == 8);
    
    //test popBack
    myVector<int> testPopBack(3);
    testPopBack.pushBack(10);
    testPopBack.pushBack(12);
    testPopBack.popBack();
    assert(testPopBack.getSize() == 1);
    
    myVector<std::string> testPopBackStr(3);
    testPopBackStr.pushBack("hi");
    testPopBackStr.pushBack("hello");
    testPopBackStr.popBack();
    assert(testPopBackStr.getSize() == 1);
    
    //test get
    assert(testGrowVec.get(2) == 22);
    assert(testGrowStrVec.get(2) == "are");
    
    //test set
    myVector<int> testSetVec(4);
    testSetVec.pushBack(12);
    testSetVec.set(0, 10);
    assert(testSetVec.get(0)==10);
    
    myVector<std::string> testSetStrVec(4);
    testSetStrVec.pushBack("hello");
    testSetStrVec.set(0, "hi");
    assert(testSetStrVec.get(0)=="hi");
    
    //test copy constructor
    myVector<int> copy(4);
    copy.pushBack(1);
    copy.pushBack(2);
    
    myVector<int> testCopy(copy);
    assert(copy.getSize() == testCopy.getSize());
    assert(copy.get(0) == testCopy.get(0));
    testCopy.pushBack(3);
    assert(copy.get(2) != testCopy.get(2));
    
    myVector<std::string> strCopy(4);
    strCopy.pushBack("hello");
    strCopy.pushBack("hi");
    
    myVector<std::string> testStrCopy(strCopy);
    assert(strCopy.getSize() == testStrCopy.getSize());
    assert(strCopy.get(0) == testStrCopy.get(0));
    testStrCopy.pushBack("bonjour");
    assert(strCopy.get(2) != testStrCopy.get(2));
    
    // test = operator overload
    myVector<int> copy1(4);
    copy1.pushBack(1);
    copy1.pushBack(2);
    
    myVector<int> copy2(6);
    copy2.pushBack(3);
    copy2.pushBack(4);
    copy1 = copy2;
    assert(copy1.get(0)==copy2.get(0));
    assert(copy1.getSize()==copy2.getSize());
    
    myVector<std::string> strCopy1(4);
    strCopy1.pushBack("hello");
    strCopy1.pushBack("hi");
    
    myVector<std::string> strCopy2(6);
    strCopy2.pushBack("bonjour");
    strCopy2.pushBack("namaste");
    strCopy1 = strCopy2;
    assert(strCopy1.get(0)==strCopy2.get(0));
    assert(strCopy1.getSize()==strCopy2.getSize());
    
    //test [] get value
    assert(copy2[0] == 3);
    assert(strCopy2[0] == "bonjour");
    
    //test [] set value
    copy2[0] = 10;
    assert(copy2[0] == 10);
    
    strCopy2[0] = "world";
    assert(strCopy2[0] == "world");
    
    //test == operator overload
    myVector<int> testDblEq1(4);
    testDblEq1.pushBack(1);
    testDblEq1.pushBack(2);
    testDblEq1.pushBack(3);
    
    myVector<int> testDblEq2(6);
    testDblEq2.pushBack(1);
    testDblEq2.pushBack(2);
    testDblEq2.pushBack(3);
    
    assert(testDblEq1 == testDblEq2);
    
    //test != operator overload
    myVector<int> testNotEq1(4);
    testNotEq1.pushBack(1);
    testNotEq1.pushBack(2);
    testNotEq1.pushBack(3);
    testNotEq1.pushBack(5);
    assert(testDblEq1 != testNotEq1);
    
    //test < operator
    myVector<int> testLess1(4);
    testLess1.pushBack(9);
    testLess1.pushBack(9);
    testLess1.pushBack(4);
    testLess1.pushBack(15);
    
    myVector<int> testLess2(4);
    testLess2.pushBack(9);
    testLess2.pushBack(9);
    testLess2.pushBack(10);
    testLess2.pushBack(12);
    
    assert(testLess1 < testLess2);
    assert(!(testDblEq1 < testDblEq2));
    
    //test <= operator
    assert(testLess1 <= testLess2);
    assert(testDblEq1 <= testDblEq2);
    
    //test > operator
    assert(testLess2 > testLess1);
    assert(!(testDblEq1 > testDblEq2));
    
    //test >= operator
    assert(testLess2 >= testLess1);
    assert(testDblEq1 >= testDblEq2);
    
    //test STL methods
    //for each
    myVector<int> testStl(4);
    testStl.pushBack(1);
    testStl.pushBack(0);
    testStl.pushBack(10);
    testStl.pushBack(3);
    for(int x : testStl){
        std::cout << x << std::endl;
    }
    //sort
    std::sort(testStl.begin(), testStl.end());
    for(int x : testStl){
        std::cout << x << std::endl;
    }
    //min-element
    std::cout<< "Min element is: "<<*std::min_element(testStl.begin(), testStl.end())<<std::endl;
    //sum
    int sum = std::accumulate(testStl.begin(), testStl.end(), 0);
    std::cout << "sum is " << sum << std::endl;
    //count_if
    int count = std::count_if(testStl.begin(), testStl.end(), [](int x){ return (x%2 == 0);});
    std::cout << "Even num count is " << count << std::endl;
    
    return 0;
}
