//
//  main.cpp
//  DateFormats
//
//  Created by Naveen Kumar on 8/21/24.
//

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {

    //get date input
    std::string dateInput;
    std::cout << "Enter a date: \n";
    std::cin >> dateInput;
    //find first /
    int firstSlash;
    firstSlash = dateInput.find("/");
    //extract substring and store month
    std::string month;
    month = dateInput.substr(0,firstSlash);
    //extract remaining substring
    std::string substring1;
    substring1 = dateInput.substr(firstSlash+1, dateInput.length()-1);
    //find second /
    int secondSlash;
    secondSlash = substring1.find("/");
    //extract substring and store date
    std::string date;
    date = substring1.substr(0,secondSlash);
    //extract remaining substring and store year
    std::string year;
    year = substring1.substr(secondSlash+1, substring1.length()-1);

    
    // check input date validity
    if(std::stoi(month) > 12 || std::stoi(month) < 1){
        std::cout << "Invalid date";
        return 1;
    } else if(std::stoi(date) > 31 || std::stoi(date) < 1){
        std::cout << "Invalid date";
        return 1;
    } else if(std::stoi(year) > 9999 || std::stoi(year) < 1000){
        std::cout << "Invalid date";
        return 1;
    }
    
    //determine month
    std::string month_long;
    if(std::stoi(month) == 1){
        month_long = "January";
    } else if(std::stoi(month) == 2){
        month_long = "February";
    } else if(std::stoi(month) == 3){
        month_long = "March";
    } else if(std::stoi(month) == 4){
        month_long = "April";
    } else if(std::stoi(month) == 5){
        month_long = "May";
    } else if(std::stoi(month) == 6){
        month_long = "June";
    } else if(std::stoi(month) == 7){
        month_long = "July";
    } else if(std::stoi(month) == 8){
        month_long = "August";
    } else if(std::stoi(month) == 9){
        month_long = "September";
    } else if(std::stoi(month) == 10){
        month_long = "October";
    } else if(std::stoi(month) == 11){
        month_long = "November";
    } else { month_long = "December"; }
    
    //concatenate final result
    std::string result = month_long + " " + date + ", " + year;
    std::cout << result << std::endl;
    
    return 0;
}
