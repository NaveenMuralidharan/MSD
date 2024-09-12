//
//  FractionTest.hpp
//  FractionClass
//
//  Created by Naveen Kumar on 9/11/24.
//

#ifndef FractionTest_hpp
#define FractionTest_hpp
#include <iostream>
#include <stdio.h>
using std::cout;
using std::cin;
using std::endl;
using std::string;


void Test( const string & message, const string & expected, const string & result );
void Test( const string & message, double expected, double result );

bool CompareDoubles( double d1, double d2 );
void TestConstructors();
void TestNegative();
void TestReduced();
void TestReciprocal();
void TestPlus();
void TestMinus();
void TestTimes();
void TestDividedBy();
void TestToDouble();
double ComputePi();
void testComparators();

#endif /* FractionTest_hpp */
