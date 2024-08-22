//
//  main.cpp
//  Palindromes
//
//  Created by Naveen Kumar on 8/21/24.
//

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {
  //declare variables
    std::string word;
    std::string reversed;
  //obtain input from user
    std::cout << "Enter a word: \n";
    std::cin >> word;
  //loop through word in reverse order and add to reversed
    for(int i = word.length()-1; i >= 0; i--){
        reversed = reversed + word[i];
    }
    std::cout << reversed << std::endl;
  //check if palindrome
    if(word == reversed){
        std::cout << word << " IS a palindrome \n";
    } else {
        std::cout << word << " is NOT a palindrome \n";
    }
    
    return 0;
}
//***************************************

