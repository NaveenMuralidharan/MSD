//
//  FractionTest.cpp
//  FractionClass
//
//  Created by Naveen Kumar on 9/11/24.
//

#include "FractionTest.hpp"

/*
 * Author: Daniel Kopta and <add your names here>
 * July 2017

 * Testing program for your Fraction class.
 * These tests are not very thorough, and you will need to add your own.
*/

// Include the student's Fraction class
#include "Fraction.hpp"

// Standard includes
#include <iostream>
#include <string>
#include <cassert>
// Also include math so we can use the pow and abs functions (see below)
#include <cmath>

using std::cout;
using std::cin;
using std::endl;
using std::string;

bool CompareDoubles( double d1, double d );


/* Helper function for writing tests that compare strings.
 * Compares expected to result, and prints an error if they don't match.
 */
void Test( const string & message, const string & expected, const string & result )
{
  cout << "Test: " << message << endl;
  if(expected != result) {
    cout << "\tFAILED, expected: \"" << expected << "\", got: \"" << result << "\"" << endl;
  }
  else {
    cout << "\tPASSED" << endl;
  }
}

/* Helper function for writing tests that compare doubles (overloaded version of the above)
 * Compares expected to result, and prints an error if they don't match.
 */
void Test( const string & message, double expected, double result )
{
  cout << "Test: " << message << endl;
  if( !CompareDoubles( expected, result ) ) {
    cout << "\tFAILED, expected: " << expected << ", got: " << result << endl;
  }
  else {
    cout << "\tPASSED" << endl;
  }
}


/*
 * Helper function
 * Returns whether or not two doubles are "equivalent",
 * within a certain error bound.
 *
 * As we know, floating point numbers are incapable of
 * precisely representing certain values, so to compare
 * equality, we must tolerate some absolute difference.
 */
bool CompareDoubles( double d1, double d2 )
{
  return std::abs( d1 - d2 ) < 1e-6;
}

/*
 * Basic constructor and toString tests
 */
void TestConstructors()
{
  std::string result = "";
  Fraction f1;
  result = f1.toString();
  Test( "Default constructor", "0/1", result );
  
  Fraction f2(1, 2);
  result = f2.toString();
  Test( "Basic constructor", "1/2", result );

  //TODO: Add your own additional tests here

}


/*
 * Negative fraction tests
 */
void TestNegative()
{
  std::string result = "";
  Fraction f1(-1, 2);
  result = f1.toString();
  Test( "Negative numerator", "-1/2", result );

  Fraction f2(1, -2);
  result = f2.toString();
  Test( "Negative denominator", "-1/2", result );

  Fraction f3(-1, -2);
  result = f3.toString();
  Test( "Negative numerator and denominator", "1/2", result );

  Fraction f4(4, -16);
  result = f4.toString();
  Test( "Negative denominator and reduce", "-1/4", result );


  //TODO: Add your own additional tests here
}


/*
 * Reduced fraction tests
 */
void TestReduced()
{
  std::string result = "";
  Fraction f1(2, 4);
  result = f1.toString();
  Test( "Reduce fraction (2/4)", "1/2", result );

  //TODO: Add your own additional tests here
}


/*
 * Reciprocal tests
 */
void TestReciprocal()
{
  std::string result = "";
  Fraction f1(1, 3);
  f1 = f1.reciprocal();
  result = f1.toString();
  Test( "Reciprocal of simple", "3/1", result );

  Fraction f2(-1, 2);
  f2 = f2.reciprocal();
  result = f2.toString();
  Test( "Reciprocal of negative", "-2/1", result );

  Fraction f3(6, 2);
  f3 = f3.reciprocal();
  result = f3.toString();
  Test( "Reciprocal of non-reduced", "1/3", result );

  //TODO: Add your own additional tests here
}

/*
 * Fraction addition tests
 */
void TestPlus()
{
  std::string result1 = "";
  Fraction f1(4, 6);
  Fraction f2(3, 4);
  
  // Should result in 17/12
  Fraction f3 = f1.plus(f2);
  result1 = f3.toString();
  Test( "Addition of non-reduced", "17/12", result1 );
  //TODO: Add your own additional tests here
    std::string result2 = "";
    Fraction f4(2, 3);
    Fraction f5(-7, 5);
    Fraction f6 = f4.plus(f5);
    result2 = f6.toString();
    Test( "Addition of negative numerator", "-11/15", result2 );
    std::string result3 = "";
    Fraction f7(-2, 3);
    Fraction f8(-7, 5);
    Fraction f9 = f7.plus(f8);
    result3 = f9.toString();
    Test( "Addition of 2 negative numerators", "-31/15", result3 );
    //Test operators + and +=
    Fraction f10 = f1 + f2;
    std::string resultX = f1.toString();
    std::string result4 = f10.toString();
    Test( "+ operator of non-reduced", "17/12", result4 );
    f1 += f2;
    std::string result5 = f1.toString();
    Test( "+= operator of non-reduced", "17/12", result5 );
}

void TestMinus()
{
  std::string result1 = "";
  Fraction f1(4, 6);
  Fraction f2(-7, 5);
  
  // Should result in 17/12
  Fraction f3 = f1.minus(f2);
  result1 = f3.toString();
  Test( "Subtraction of non-reduced and negative numerator", "31/15", result1 );

    std::string result2 = "";
    Fraction f4(-3, 4);
    Fraction f5(-6, -5);
    Fraction f6 = f4.minus(f5);
    result2 = f6.toString();
    Test( "Subtraction of negative numerator and negative numerator and negative denominator", "-39/20", result2 );
    //Test operators - and -=
    Fraction f7 = f1 - f2;
    std::string result3 = f7.toString();
    Test( "- operator of non-reduced and negative numerator", "31/15", result3 );
    f1 -= f2;
    std::string result4 = f1.toString();
    Test( "-= operator of non-reduced and negative numerator", "31/15", result4 );
}

void TestTimes()
{
  std::string result1 = "";
  Fraction f1(4, 6);
  Fraction f2(-7, 5);
  
  // Should result in 17/12
  Fraction f3 = f1.times(f2);
  result1 = f3.toString();
  Test( "Multiplication of non-reduced and negative numerator", "-14/15", result1 );

    std::string result2 = "";
    Fraction f4(-3, 4);
    Fraction f5(-6, -5);
    Fraction f6 = f4.times(f5);
    result2 = f6.toString();
    Test( "Multiplication of negative numerator and negative numerator and negative denominator", "-9/10", result2 );
    
    //Test operators * and *=
    Fraction f7 = f1 * f2;
    std::string result3 = f7.toString();
    Test( "* operator of non-reduced and negative numerator", "-14/15", result3 );
    f1 *= f2;
    std::string result4 = f1.toString();
    Test( "*= operator of non-reduced and negative numerator", "-14/15", result4 );
}

void TestDividedBy()
{
  std::string result1 = "";
  Fraction f1(4, 6);
  Fraction f2(-8, 5);
  
  Fraction f3 = f1.dividedBy(f2);
  result1 = f3.toString();
  Test( "Division of non-reduced and negative numerator", "-5/12", result1 );

    std::string result2 = "";
    Fraction f4(-3, 4);
    Fraction f5(-6, -5);
    Fraction f6 = f4.dividedBy(f5);
    result2 = f6.toString();
    Test( "Division of negative numerator and negative numerator and negative denominator", "-5/8", result2 );
    
    //Test operators * and *=
    Fraction f7 = f1 / f2;
    std::string result3 = f7.toString();
    Test( "/ operator of non-reduced and negative numerator", "-5/12", result3 );
    f1 /= f2;
    std::string result4 = f1.toString();
    Test( "/= operator of non-reduced and negative numerator", "-5/12", result4 );
}


void TestToDouble()
{
  Fraction f1(1, 2);
  double result = f1.toDouble();
  Test( "toDouble (1/2)", 0.5, result );

  Fraction f2(1, 3);
  result = f2.toDouble();
  Test( "toDouble (1/3)", 1.0/3.0, result );
    
    Fraction f3(-1, 2);
    result = f3.toDouble();
    Test( "toDouble (-1/2)", -0.5, result );
}
/*
 * Approximates pi using a summation of fractions.
 */
double ComputePi()
{
  Fraction sum;

  // We will only compute the first 4 terms
  // Note that even with long (64-bit) numbers,
  // the intermediate numbers required for fraction addition
  // become too large to represent if we go above k=4.
  for( long k = 0; k < 4; k++ )
  {
    Fraction multiplier( 1, pow(16, k) );
    Fraction firstTerm(  4, 8*k + 1 );
    Fraction secondTerm( 2, 8*k + 4 );
    Fraction thirdTerm(  1, 8*k + 5 );
    Fraction fourthTerm( 1, 8*k + 6 );

    Fraction temp = firstTerm.minus( secondTerm );
    temp = temp.minus( thirdTerm );
    temp = temp.minus( fourthTerm );
 
    Fraction product = multiplier.times( temp );

    sum = sum.plus( product );
  }
  
  return sum.toDouble();
}

//test comparison operators
void testComparators(){
    //==
    Fraction f1(1,2);
    Fraction f2(-3,-6);
    assert(f1 == f2);
    //!=
    Fraction f3(1,2);
    Fraction f4(-3,6);
    assert(f3 != f4);
    // <
    Fraction f5(1,2);
    Fraction f6(8,9);
    assert(f5 < f6);
    // >
    assert(f6 > f5);
    // <=
    assert(f1 <= f2);
    // >=
    assert(f1 >= f2);
}
