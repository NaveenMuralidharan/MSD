//
//  HelperFunctions.cpp
//  VectorPractice
//
//  Created by Naveen Kumar on 8/25/24.
//

#include "HelperFunctions.hpp"
#include <vector>
#include <string>
#include <iostream>

int sum(std::vector<int> ints){
    int result = 0;
    for(int i=0; i<ints.size(); i++){
        result += ints[i];
    }
    return result;
    
}

std::vector<char> stringToVec(std::string text){
    std::vector<char> result;
    for(int i=0; i<text.length(); i++){
        result.push_back(text[i]);
    }
    return result;
}

std::vector<int> reverse(std::vector<int> ints){
    std::vector<int> result;
    for(long i=ints.size()-1; i>=0; i--){
        result.push_back(ints[i]);
    }
    return result;
}
