//
//  main.cpp
//  IfStatementPractise
//
//  Created by Naveen Kumar on 8/21/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    //PART 1
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
    //PART 2
    char weekdayResponse;
    char holidayResponse;
    char childResponse;
    
    bool isWeekday = false;
    bool isHoliday = false;
    bool hasChild = false;
    
    std::cout << "Is this a weekday? Y/N \n";
    std::cin >> weekdayResponse;
    
    std::cout << "Is this a holiday? Y/N \n";
    std::cin >> holidayResponse;
    
    std::cout << "Do you have children? Y/N \n";
    std::cin >> childResponse;
    
    if(weekdayResponse == 'Y'){
        isWeekday = true;
    }
    
    if(holidayResponse == 'Y'){
        isHoliday = true;
    }
    
    if(childResponse == 'Y'){
        hasChild = true;
    }
    //Print whether the user gets to sleep in today or not
    if(hasChild){
        std::cout << "You cannot sleep in, you have children! \n";
    } else {
        if(!isWeekday){
            std::cout << "You can sleep in, it's not a weekday! \n";
        } else {
            if(isHoliday){
                std::cout << "You can sleep in, it's a holiday! \n";
            } else {
                std::cout << "You cannot sleep in, it's a weekday and not a holiday! \n";
            }
        }
    }
    
    return 0;
}
