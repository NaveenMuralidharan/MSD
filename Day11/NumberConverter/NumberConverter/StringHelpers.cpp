//
//  StringHelpers.cpp
//  NumberConverter
//
//  Created by Naveen Kumar on 9/3/24.
//

#include "StringHelpers.hpp"

std::vector<int> intVector(std::string num){
    std::vector<int> intVector;
    int index;
    if(num[0] == '-'){
        index = 1;
        intVector.push_back(-1);
    } else {
        index = 0;
        intVector.push_back(1);
    }
    
    //loop through string
    for(int i=index; i<num.size(); i++){
        num[i] = tolower(num[i]);
        if(num[i] >= 'a'){
            intVector.push_back(num[i] - 'a' + 10);
        } else {
            intVector.push_back(num[i] - '0');
        }
    }
    return intVector;
    
}

int stringToInt(std::string num, int base){
    int placeValue;
    int number = 0;
    
    std::vector<int> ints = intVector(num);
    for(int i=1; i<ints.size(); i++){
        placeValue = pow(base, i-1);
        number += ints[ints.size()-i] * placeValue;
    }
    
    return number * ints[0];
}
