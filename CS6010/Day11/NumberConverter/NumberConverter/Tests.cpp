//
//  Tests.cpp
//  NumberConverter
//
//  Created by Naveen Kumar on 9/3/24.
//

#include "Tests.hpp"

void testStringToInt(){
    assert(stringToInt("111111", 2) == 63);
    assert(stringToInt("10001", 2) == 17);
    assert(stringToInt("200", 10) == 200);
    assert(stringToInt("-23", 10) == -23);
    assert(stringToInt("FF", 16) == 255);
    assert(stringToInt("22", 16) == 34);
}

void testIntToString(){
    assert(intToString(17, 2) == "10001");
    assert(intToString(100, 2) == "1100100");
    assert(intToString(234, 10) == "234");
    assert(intToString(-35, 10) == "-35");
    assert(intToString(192, 16) == "C0");
    assert(intToString(48879, 16) == "BEEF");

}
