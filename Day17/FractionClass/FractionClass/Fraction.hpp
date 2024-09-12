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
    Fraction plus(Fraction rhs);
    Fraction minus(Fraction rhs);
    Fraction times(Fraction rhs);
    Fraction dividedBy(Fraction rhs);
    double toDouble();
    Fraction reciprocal();
    string toString();
    Fraction operator +(Fraction rhs)const;
    void operator +=(Fraction rhs);
    Fraction operator -(Fraction rhs)const;
    void operator -=(Fraction rhs);
    Fraction operator *(Fraction rhs)const;
    void operator *=(Fraction rhs);
    Fraction operator /(Fraction rhs)const;
    void operator /=(Fraction rhs);
    bool operator ==(Fraction rhs);
    bool operator !=(Fraction rhs);
    bool operator <(Fraction rhs);
    bool operator >(Fraction rhs);
    bool operator <=(Fraction rhs);
    bool operator >=(Fraction rhs);
    
};





#endif /* Fraction_hpp */
