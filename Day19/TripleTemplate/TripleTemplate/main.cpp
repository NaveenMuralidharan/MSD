//
//  main.cpp
//  TripleTemplate
//
//  Created by Naveen Kumar on 9/12/24.
//

#include <iostream>
#include "Triple.h"
#include <vector>

int main(int argc, const char * argv[]) {

    Triple<int> ints = {1,2,3};
    std::cout << ints.a << std::endl;
    
    Triple<std::string> strs = {"hello", "hi", "bonjour"};
    std::cout << strs.a << std::endl;

    Triple<double> doubles = {1.1,2.2,3.3};
    std::cout << doubles.a << std::endl;
    
    std::cout << ints.sum() << std::endl;
    std::cout << strs.sum() << std::endl;
    
    Triple<std::vector<int>> vectorInts = {{1,2,3}, {3,4,5}, {6,7,8}};
    std::cout << vectorInts.a[0] << std::endl;
    
//    vectorInts.sum();
//    This does not work since the + operator is not defined for vector class
    
    return 0;
}
