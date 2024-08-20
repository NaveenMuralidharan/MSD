//
//  main.cpp
//  Average
//
//  Created by Naveen Kumar and Nathan Johnston on 8/20/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {
//declare variables
    int score1, score2, score3, score4, score5;
    double average;
    //ask user for scores
    std::cout << "Enter 5 assignment scores: " << "\n";
    std::cin >> score1 >> score2 >> score3 >> score4 >> score5;
    //calculate average
    average = (score1+score2+score3+score4+score5) / 5;
    std::cout << "Average: " << average << "\n";
    
    return 0;
}
