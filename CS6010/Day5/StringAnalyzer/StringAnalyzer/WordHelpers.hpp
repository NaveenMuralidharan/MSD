//
//  WordHelpers.hpp
//  StringAnalyzer
//
//  Created by Naveen Kumar on 8/25/24.
//

#ifndef WordHelpers_hpp
#define WordHelpers_hpp

#include <stdio.h>
#include <string>

int numWords(std::string text);
int numSentences(std::string text);
int numVowels(std::string text);
int numConsonants(std::string text);
double averageWordLength(std::string text);
double averageVowelsPerWord(std::string text);

#endif /* WordHelpers_hpp */
