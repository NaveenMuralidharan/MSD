//
//  LetterHelpers.cpp
//  StringAnalyzer
//
//  Created by Naveen Kumar on 8/25/24.
//

#include "LetterHelpers.hpp"
#include <iostream>

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
