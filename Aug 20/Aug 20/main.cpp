//
//  main.cpp
//  Aug 20
//
//  Created by Naveen Kumar on 8/20/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...
    int num1, num2, result;
    std::cout << "Please enter the first number \n";
    std::cin >> num1;
    std::cout << "Please enter the second number \n";
    std::cin >> num2;
    
    result = num1 * num2;
    std::cout << "Number 1: " << num1 << "Number 2: " << num2 << "Result: " << result << "\n";
    return 0;
}