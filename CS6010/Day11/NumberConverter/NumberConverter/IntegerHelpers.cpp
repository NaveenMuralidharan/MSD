//
//  IntegerHelpers.cpp
//  NumberConverter
//
//  Created by Naveen Kumar on 9/3/24.
//

#include "IntegerHelpers.hpp"

std::string intToString(int num, int base){
    std::string numAsString;
    std::vector<char> validChars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    int remainder = num;
    
    if(num < 0){
        remainder *= -1;
    }
    
    while(remainder !=0){
        numAsString.insert(0,1,validChars[remainder % base]);
        remainder /= base;
    }
    if(num < 0){
        numAsString.insert(0,1,'-');
    }
    return numAsString;
    
}
