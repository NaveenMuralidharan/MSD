//
//  Fraction.cpp
//  FractionClass
//
//  Created by Naveen Kumar on 9/11/24.
//

#include "Fraction.hpp"

void Fraction::reduce(){

    long divisor = GCD();
    numerator = numerator / divisor;
    denominator = denominator / divisor;

}

long Fraction::GCD(){
    long gcd = std::abs(numerator);
    long remainder = std::abs(denominator);
    while(remainder != 0) {
      long temp = remainder;
      remainder = gcd % remainder;
      gcd = temp;
    }
    return gcd;
}

Fraction::Fraction(){
    numerator = 0;
    denominator = 1;
}

Fraction::Fraction(long n, long d){
    numerator = n;
    denominator = d;
    bool isNegative = false;
    if((numerator < 0 || denominator < 0) && !(numerator <0 && denominator <0) ){
        isNegative = true;
    }
    reduce();
    numerator = std::abs(numerator);
    denominator = std::abs(denominator);
    if(isNegative){
        numerator *= -1;
    }
}

Fraction Fraction::plus(Fraction rhs){
    long newDenominator = denominator * rhs.denominator;
    long newNumerator = (numerator * rhs.denominator) + (rhs.numerator * denominator);
    return Fraction(newNumerator, newDenominator);
}

Fraction Fraction::minus(Fraction rhs){
    long newDenominator = denominator * rhs.denominator;
    long newNumerator = (numerator * rhs.denominator) - (rhs.numerator * denominator);
    return Fraction(newNumerator, newDenominator);
}

Fraction Fraction::times(Fraction rhs){
    long newDenominator = denominator * rhs.denominator;
    long newNumerator = numerator * rhs.numerator;
    return Fraction(newNumerator, newDenominator);
}
Fraction Fraction::dividedBy(Fraction rhs){
    long newDenominator = denominator * rhs.numerator;
    long newNumerator = numerator * rhs.denominator;
    return Fraction(newNumerator, newDenominator);
}

double Fraction::toDouble(){
    double result = numerator / (double)denominator;
    return result;
}

Fraction Fraction::reciprocal(){
    
    return Fraction(denominator, numerator);
}


string Fraction::toString(){
    /* pre: fraction is reduced and - sign is in numerator*/
    string numtr = std::to_string(numerator);
    string denmtr = std::to_string(denominator);
    string result = numtr + '/' + denmtr;
//    if(numerator < 0){
//        result = '-' + numtr + '/' + denmtr;
//    } else {
//        result = numtr + '/' + denmtr;
//    }
    return result;
}

//********************************************
//OPERATOR OVERLOADS
Fraction Fraction::operator +(Fraction rhs)const{
    return Fraction(numerator, denominator).plus(rhs);
}

void Fraction::operator +=(Fraction rhs){
    Fraction temp = Fraction(numerator, denominator).plus(rhs);
    numerator = temp.numerator;
    denominator = temp.denominator;
}

Fraction Fraction::operator -(Fraction rhs)const{
    return Fraction(numerator, denominator).minus(rhs);
    
}

void Fraction::operator -=(Fraction rhs){
    Fraction temp = Fraction(numerator, denominator).minus(rhs);
    numerator = temp.numerator;
    denominator = temp.denominator;
}

Fraction Fraction::operator *(Fraction rhs)const{
    return Fraction(numerator, denominator).times(rhs);
    
}

void Fraction::operator *=(Fraction rhs){
    Fraction temp = Fraction(numerator, denominator).times(rhs);
    numerator = temp.numerator;
    denominator = temp.denominator;
}

Fraction Fraction::operator /(Fraction rhs)const{
    return Fraction(numerator, denominator).dividedBy(rhs);
    
}

void Fraction::operator /=(Fraction rhs){
    Fraction temp = Fraction(numerator, denominator).dividedBy(rhs);
    numerator = temp.numerator;
    denominator = temp.denominator;
}

bool Fraction::operator ==(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() == rhs.toDouble();
}
bool Fraction::operator !=(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() != rhs.toDouble();
}
bool Fraction::operator <(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() < rhs.toDouble();
}
bool Fraction::operator >(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() > rhs.toDouble();
}
bool Fraction::operator <=(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() < rhs.toDouble() || Fraction(numerator, denominator).toDouble() == rhs.toDouble();
}
bool Fraction::operator >=(Fraction rhs){
    return Fraction(numerator, denominator).toDouble() > rhs.toDouble() || Fraction(numerator, denominator).toDouble() == rhs.toDouble();
}
