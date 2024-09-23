//
//  main.cpp
//  StringAnalyzer
//
//  Created by Naveen Kumar on 8/24/24.
//

#include <iostream>
#include <string>
#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"

void analyzeString(std::string input){
 
    std::cout << "Analysis: " << std::endl << "Number of words: " << numWords(input) << std::endl;
    std::cout << "Number of sentences: " << numSentences(input) << std::endl;
    std::cout << "Number of vowels: " << numVowels(input) << std::endl;
    std::cout << "Number of consonants: " << numConsonants(input) << std::endl;
    std::cout << "Reading level (average word length): " << averageWordLength(input) << std::endl;
    std::cout << "Average vowels per word: " <<
    averageVowelsPerWord(input) << std::endl;
    
}

int main(int argc, const char * argv[]) {
    
    std::string input;
    
    std::cout << "Enter a string containing one or more scentences: " << std::endl;
    std::getline( std::cin, input );
    
    while (input != "done"){
        analyzeString(input);
        std::cout << "Enter a string containing one or more scentences: " << std::endl;
        std::getline( std::cin, input );
        
    }
    std::cout << "Goodbye." << std::endl;
    
    return 0;
}
