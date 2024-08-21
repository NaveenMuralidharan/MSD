//
//  main.cpp
//  IfStatementPractise
//
//  Created by Naveen Kumar on 8/21/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    
    //declare age variable and accept input
    int age;
    std::cout << "Please enter your age: \n";
    std::cin >> age;
    
    //Print whether the user is old enough to vote.
    if(age >= 18){
        std::cout << "You are old enough to vote! \n";
    } else {
        std::cout << "You are not old enough to vote! \n";
    }
    
    //Print whether the user is old enough to run for senate.
    if(age >= 30){
        std::cout << "You are old enough to run for senate! \n";
    } else {
        std::cout << "You are not old enough to run for senate! \n";
    }
    //Print which generation the user is a part of:
    if(age > 80){
        std::cout << "You are part of the greatest generation! \n";
    } else if(age > 60 && age < 80){
        std::cout << "You are part of the baby boomers generation! \n";
    } else if(age > 40 && age < 60){
        std::cout << "You are part of generation X! \n";
    } else if(age > 20 && age < 40){
        std::cout << "You are part of millenial generation \n";
    } else {
        std::cout << "You are part of iKid generation! \n";
    }
    
    
    return 0;
}
