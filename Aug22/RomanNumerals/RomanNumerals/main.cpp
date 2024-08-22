//
//  main.cpp
//  RomanNumerals
//
//  Created by Naveen Kumar on 8/22/24.
//

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {

    //obtain number input from user
    int numInput;
    std::cout << "Enter decimal number \n";
    std::cin >> numInput;
    //check if number is 0 or less
    if(numInput <=0){
        std::cout << "Invalid input \n";
        return 1;
    }
    //initialize a reminder and output variable
    int reminder = numInput;
    std::string output;
    //loop while reminder is not equal to 0
    while(reminder != 0){
        //check each roman numeral token value and subtract
        if(reminder >= 1000){
            output = output + "M";
            reminder = reminder - 1000;
        } else if(reminder >= 900){
            output = output + "CM";
            reminder = reminder - 900;
        } else if(reminder >= 500){
            output = output + "D";
            reminder = reminder - 500;
        } else if(reminder >= 400){
            output = output + "CD";
            reminder = reminder - 400;
        } else if(reminder >= 100){
            output = output + "C";
            reminder = reminder - 100;
        } else if(reminder >= 90){
            output = output + "XC";
            reminder = reminder - 90;
        } else if(reminder >= 50){
            output = output + "L";
            reminder = reminder - 50;
        } else if(reminder >= 40){
            output = output + "XL";
            reminder = reminder - 40;
        } else if(reminder >= 10){
            output = output + "X";
            reminder = reminder - 10;
        } else if(reminder >= 9){
            output = output + "IX";
            reminder = reminder - 9;
        } else if(reminder >= 5){
            output = output + "V";
            reminder = reminder - 5;
        } else if(reminder >=4){
            output = output + "IV";
            reminder = reminder - 4;
        } else if(reminder >= 1){
            output = output + "I";
            reminder = reminder - 1;
        }
        
        
    }
    std::cout << "Roman numeral version: \n" << output << std::endl;
    
    
    return 0;
}
