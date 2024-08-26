//
//  WordHelpers.cpp
//  StringAnalyzer
//
//  Created by Naveen Kumar on 8/25/24.
//

#include "WordHelpers.hpp"
#include "LetterHelpers.hpp"

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
