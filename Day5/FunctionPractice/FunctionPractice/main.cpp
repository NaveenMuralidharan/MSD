//
//  main.cpp
//  FunctionPractice
//
//  Created by Naveen Kumar on 8/22/24.
//

#include <iostream>
#include <cmath>
#include <string>

//PART 2
//a) hypotenuse function
void hypotenuse(double side1, double side2){
    double result = (side1 * side1) + (side2 * side2);
    result = std::sqrt(result);
    std::cout << "The hypotenuse is: " << result <<std::endl;
}
//***************************
//b)xyvelocity function. Due to separation of concerns, it is better to get input of parameter values from main function and call function xyVelocity with those parameters.
void xyVelocity(double speed, double angle){
    
    double xVelocity = speed * std::cos(angle);
    double yVelocity = speed * std::sin(angle);
    std::cout << "X velocity is " << xVelocity << "and y velocity is " << yVelocity << std::endl;
}
//****************************
//d)
std::string boolToString(bool value){
    if(value){
        return "true";
    } else {
        return "false";
    }
}
//******************************
//c)
std::string isCapitalized(std::string text){
    if(text[0] < 'A' || text[0] > 'Z'){
        return boolToString(false);
    } else {
        return boolToString(true);
    }
}
//*******************************
//d)
std::string isPrime(int num){
    int divisors = 0;
    for(int i=2; i<=num; i++){
        if(num % i == 0){
            divisors += 1;
        }
    }
    if(divisors > 1){
        return "Number is NOT prime";
    } else {
        return "Number IS prime";
    }
}



int main(int argc, const char * argv[]) {
    
    //PART 1
    //a) hypotenuse
    double side1;
    double side2;
    std::cout << "Enter length of first right angle side: " << std::endl;
    std::cin >> side1;
    std::cout << "Enter length of second right angle side: " << std::endl;
    std::cin >> side2;
    double hypotenuseLength = (side1 * side1) + (side2 * side2);
    hypotenuseLength = std::sqrt(hypotenuseLength);
    std::cout << "The hypotenuse is: " << hypotenuseLength <<std::endl;
    
    //b xyvelocity
    double speed;
    double angle;
    std::cout << "Enter the speed: " << std::endl;
    std::cin >> speed;
    std::cout << "Enter the angle in radians "<< std::endl;
    std::cin >> angle;
    double xVelocity = speed * std::cos(angle);
    double yVelocity = speed * std::sin(angle);
    std::cout << "X velocity is " << xVelocity << "and y velocity is " << yVelocity << std::endl;
    
    
    //c) localtime. Functions being called are time(), asctime() and localtime()
    std::time_t result = std::time(nullptr);
    std::cout << std::asctime(std::localtime(&result))
              << result << " seconds since the Epoch\n";
    
    
    
    
    return 0;
}
