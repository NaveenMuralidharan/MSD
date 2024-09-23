//
//  main.cpp
//  ForLoopPractise
//
//  Created by Naveen Kumar on 8/21/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {

    //Print the numbers from 1 to 10. Do this once using a for loop
    for(int i=1; i<11; i++){
        std::cout << i << "\n";
    }
    //Print the numbers from 1 to 10. Do this once using a while loop
    int j = 1;
    while(j < 11){
        std::cout << j << "\n";
        j++;
    }
    // It is better to use for loop as the number of iterations is already known and it's shorter syntax.
    //***************************
    //Prompt the user to enter the two numbers. Then print all the numbers from the first to the second.
    int lowerNumber=0;
    int higherNumber=0;
    int firstNumber;
    int secondNumber;
    
    std::cout << "Enter the first number: \n";
    std::cin >> firstNumber;
    
    std::cout << "Enter the second number: \n";
    std::cin >> secondNumber;
    
    if(firstNumber > secondNumber){
        higherNumber = firstNumber;
        lowerNumber = secondNumber;
    } else {
        higherNumber = secondNumber;
        lowerNumber = firstNumber;

    }
    while(lowerNumber <= higherNumber){
        std::cout << lowerNumber << "\n";
        lowerNumber++;
    }
    //***********************************
    //Print all the odd numbers between 1 and 20. Using loop and if statement
    for(int i=1; i<21; i++){
        if(i % 2 != 0){
            std::cout << i << "\n";
        }
    }
    //Print all the odd numbers between 1 and 20. Using ONLY loop and not if statement
    for(int i=1; i<21; i+=2){
        std::cout << i << "\n";
    }
    //************************************
    //enter positive numbers to add up. Keep reading and adding numbers until the user enters a number that is less than 0
    int num;
    int sum = 0;
    
    std::cout << "Enter number to add: \n";
    std::cin >> num;
    
    while(num > 0){
        sum = sum + num;
        std::cout << "Enter number to add: \n";
        std::cin >> num;
    }
    std::cout << sum << "\n";
    //***************************************
    
    for(int i=1; i<6; i++){
        std::cout << i << "x*: ";
        for(int j=1; j<6; j++){
            std::cout << i*j << " ";
        }
        std::cout << "\n";
    }
    
    
    
}
