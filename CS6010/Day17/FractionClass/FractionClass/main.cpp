//
//  main.cpp
//  FractionClass
//
//  Created by Naveen Kumar on 9/11/24.
//

#include <iostream>
#include "Fraction.hpp"
#include "FractionTest.hpp"

int main(int argc, const char * argv[]) {

    std::cout << "tests" << std::endl;
  // Break up the tests into categories for better readability.
  TestConstructors();
  TestNegative();
  TestReduced();
  TestReciprocal();
  TestToDouble();
  TestPlus();
  TestMinus();
  TestTimes();
  TestDividedBy();
  Test("Approximating pi", 3.141592, ComputePi());
  testComparators();

//Do you need to implement the rule of 3? Either implement the 3 functions or explain why they are not needed.
//No heap memory was allocated, no copy constructor or destructor was needed, so did not need to implement rule of 3.
    
    return 0;
}
