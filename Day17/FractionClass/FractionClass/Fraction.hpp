//
//  Fraction.hpp
//  FractionClass
//
//  Created by Naveen Kumar on 9/11/24.
//

#ifndef Fraction_hpp
#define Fraction_hpp
#include <iostream>
#include <stdio.h>
#include <cmath>

using std::string;

class Fraction{
    
    long numerator;
    long denominator;
    void reduce();
    long GCD();
    
public:
    Fraction();
    Fraction(long n, long d);
    Fraction plus(const Fraction& rhs)const;
    Fraction minus(const Fraction& rhs)const;
    Fraction times(const Fraction& rhs)const;
    Fraction dividedBy(const Fraction& rhs)const;
    double toDouble()const;
    Fraction reciprocal()const;
    string toString()const;
    Fraction operator +(const Fraction& rhs)const;
    void operator +=(const Fraction& rhs);
    Fraction operator -(const Fraction& rhs)const;
    void operator -=(const Fraction& rhs);
    Fraction operator *(const Fraction& rhs)const;
    void operator *=(const Fraction& rhs);
    Fraction operator /(const Fraction& rhs)const;
    void operator /=(const Fraction& rhs);
    bool operator ==(const Fraction& rhs)const;
    bool operator !=(const Fraction& rhs)const;
    bool operator <(const Fraction& rhs)const;
    bool operator >(const Fraction& rhs)const;
    bool operator <=(const Fraction& rhs)const;
    bool operator >=(const Fraction& rhs)const;
    
};





#endif /* Fraction_hpp */
