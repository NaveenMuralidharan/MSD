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
    //find the change
    change = amount_paid - price;
    //calculate denominations
    quarters = change / 25;
    difference = change % 25;
    dimes = difference / 10;
    difference = difference % 10;
    nickels = difference / 5;
    pennies = difference % 5;

    std::cout << "Change = " << change << "\n";
    std::cout << "Quarters: " << quarters << "\n";
    std::cout << "Dimes: " << dimes << "\n";
    std::cout << "Nickels: " << nickels << "\n";
    std::cout << "Pennies: " << pennies << "\n";
    return 0;
}
