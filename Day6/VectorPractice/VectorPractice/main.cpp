//
//  main.cpp
//  VectorPractice
//
//  Created by Naveen Kumar on 8/25/24.
//

#include <iostream>
#include "HelperFunctions.hpp"
#include <vector>
#include <string>

int main(int argc, const char * argv[]) {
    
    std::vector<int> ints = {1,2,3,4,5};
    std::cout << "Sum is: " << sum(ints) << std::endl;
    
    std::string text = "hello";
    std::vector<char> output = stringToVec(text);
    for(int i=0; i<output.size(); i++){
        std::cout << output[i] << std::endl;
    }

    std::vector<int> nums = {1,2,3,4,5};
    std::vector<int> reverseNums = reverse(nums);
    for(int i=0; i<reverseNums.size(); i++){
        std::cout << reverseNums[i] << std::endl;
    }
    
    return 0;
}
