//
//  main.cpp
//  RoadTripCalculator
//
//  Created by Naveen Kumar on 8/20/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
//declare variables
    int driving_distance;
    double miles_per_gallon;
    double gas_cost;
    double num_gallons;
    double total_cost;
//ask user to enter values
    std::cout << "Enter the driving distance in miles: \n";
    std::cin >> driving_distance;
    std::cout << "Enter the vehicle's miles per gallon efficiency: \n";
    std::cin >> miles_per_gallon;
    std::cout << "ENter the cost of gas in dollars per gallon: \n";
    std::cin >> gas_cost;
    //calculate num of gallons used
    num_gallons = driving_distance / miles_per_gallon;
    total_cost = num_gallons * gas_cost;
    //print out total gas cost
    std::cout << "The total gas cost is: " << total_cost << "\n";
    
    return 0;
}
