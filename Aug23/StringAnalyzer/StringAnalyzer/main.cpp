//
//  main.cpp
//  StringAnalyzer
//
//  Created by Naveen Kumar on 8/24/24.
//

#include <iostream>
#include <string>

bool isTerminator(char c){
    if(c == '.' || c == '?' || c == '!'){
        return true;
    } else {
        return false;
    }
}

bool isPunctuation(char c){
    if(isTerminator(c) || c == ','){
        return true;
    } else {
        return false;
    }
}

bool isVowel(char c){
    c = std::tolower(c, std::locale());

    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y'){
        return true;
    } else {
        return false;
    }
//    char vowels[] = {'a','e','i','o','u','y'};
//    
//    for(int i=0; i<vowels.length(); i++){
//        if(c == vowels[i]){
//            return true;
//        }
//    }
//    return false;
}
bool isConsonant(char c){
    if(c != ' ' && !isTerminator(c) && !isPunctuation(c) && !isVowel(c)){
        return true;
    } else {
        return false;
    }
}

int numWords(std::string text){
    int numWords;
    int spaces=0;
    for(int i=0; i < text.length(); i++){
        if(text[i] == ' '){
            spaces += 1;
        }
    }
    numWords = spaces + 1;
    return numWords;
}

int numSentences(std::string text){
    int numSentences = 0;
    for(int i=0; i < text.length(); i++){
        if(isTerminator(text[i]) || isPunctuation(text[i])){
                    numSentences += 1;
                }
    }
    return numSentences;
}

int numVowels(std::string text){
    int numVowels=0;
    for(int i=0; i < text.length(); i++){
        if(isVowel(text[i])){
            numVowels += 1;
        }
    }
    return numVowels;
}

int numConsonants(std::string text){
    int numConsonants = 0;
    for(int i=0; i<text.length(); i++){
        if(isConsonant(text[i])){
            numConsonants += 1;
        }
    }
    return numConsonants;
}

double averageWordLength(std::string text){
    int wordLength = 0;
    double wordNumber = numWords(text);
    double wordAverage;
    
    for(int i=0; i<text.length(); i++){
        if(isVowel(text[i]) || isConsonant(text[i])){
            wordLength += 1;
        }
    }
    wordAverage = wordLength / wordNumber;
    return wordAverage;
}

double averageVowelsPerWord(std::string text){
    int vowelsLength = 0;
    double wordNumber = numWords(text);
    double avgVowels;
    
    for(int i=0; i<text.length(); i++){
        if(isVowel(text[i])){
            vowelsLength += 1;
        }
    }
    avgVowels = vowelsLength / wordNumber;
    return avgVowels;
}

void analyzeString(std::string input){
 
    std::cout << "Analysis: " << std::endl << "Number of words: " << numWords(input) << std::endl;
    std::cout << "Number of sentences: " << numSentences(input) << std::endl;
    std::cout << "Number of vowels: " << numVowels(input) << std::endl;
    std::cout << "Number of consonants: " << numConsonants(input) << std::endl;
    std::cout << "Reading level (average word length): " << averageWordLength(input) << std::endl;
    std::cout << "Average vowels per word: " <<
    averageVowelsPerWord(input) << std::endl;
    //----------------------
    
 
    
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
