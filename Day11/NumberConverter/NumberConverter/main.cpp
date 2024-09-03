//
//  main.cpp
//  NumberConverter
//
//  Created by Naveen Kumar and Nathan Johnston on 9/2/24.
//

#include <iostream>
#include <math.h>
#include <vector>
//convert all char to lower case
//check if first char is negative
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

int main(int argc, const char * argv[]) {

//    std::cout << stringToInt("-1", 10) << std::endl;
    
    std::cout << intToString(stringToInt("12000",10), 10) << std::endl;
    
    return 0;
}
