//
//  WordHelpers.hpp
//  BookAnalyzer
//
//  Created by Naveen Kumar on 8/29/24.
//

#ifndef WordHelpers_hpp
#define WordHelpers_hpp
#include<vector>
#include<iostream>
#include<cmath>

struct FoundWord {int charCount; int wordIndex;};
void printStatistics(const std::vector<std::string> &words, int charCount, int smallestWordIndex, int largestWordIndex);
void keyWordPrint(std::vector<FoundWord> foundWords, int charCount, std::string searchWord, std::vector<std::string> words);

#endif /* WordHelpers_hpp */
