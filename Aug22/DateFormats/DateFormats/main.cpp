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
    std::string monthsArr[]= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    month_long = monthsArr[std::stoi(month)-1];
    

    //concatenate final result
    std::string result = month_long + " " + date + ", " + year;
    std::cout << result << std::endl;
    
    return 0;
}
