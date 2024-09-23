//
//  main.cpp
//  GiveChange
//
//  Created by Naveen Kumar on 8/20/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    //declare variables
    int price, amount_paid, change, quarters, dimes, nickels, pennies, difference;
    //obtain input from user
    std::cout << "Enter item price in cents: \n";
    std::cin >> price;
    std::cout << "Enter amount paid in cents: \n";
    std::cin >> amount_paid;
    //check if price is greater than amount paid
    if(price > amount_paid){
        std::cout << "Insufficient funds! \n";
        return 1;
    }
    //check if price or amount_paid is negative
    if(price < 0 || amount_paid < 0){
        std::cout << "Error: number of cents cannot be negative \n";
        return 1;
    }
    //find the change
    change = amount_paid - price;
    //calculate denominations

    quarters = change / 25;
    if(quarters > 2){
        quarters = 2;
    }
    difference = change - (quarters * 25);
    dimes = difference / 10;
    if(dimes > 2){
        dimes = 2;
    }
    difference = difference - (dimes * 10);
    nickels = difference / 5;
    if(nickels > 2){
        nickels = 2;
    }
    difference = difference - (nickels * 5);
    pennies = difference / 1;
    if(pennies > 2){
        pennies = 2;
    }
    difference = difference - (pennies * 1);
    if(difference > 0){
        std::cout << "Unable to return change, out of coins! \n";
    } else {
        
        std::cout << "Change = " << change << "\n";
        std::cout << "Quarters: " << quarters << "\n";
        std::cout << "Dimes: " << dimes << "\n";
        std::cout << "Nickels: " << nickels << "\n";
        std::cout << "Pennies: " << pennies << "\n";
    }
    return 0;
}
