//
//  main.cpp
//  BookAnalyzer
//
//  Created by Naveen Kumar on 8/29/24.
//

#include <iostream>
#include <fstream>
#include <vector>
#include "WordHelpers.hpp"

int main(int argc, const char * argv[]) {

    std::string fileName = argv[1];
    std::string searchWord = argv[2];
    
    std::ifstream fin("./"+fileName);
    
    if(fin.fail()){
        std::cout << "No such file exists" << std::endl;
        return 1;
    }
    
    std::string word;
    std::vector<std::string> words;
    std::vector<FoundWord> foundWords;
    int charCount=0;
    int smallestWordIndex = 0;
    int largestWordIndex = 0;
    
    while(fin >> word){
        
        charCount += word.size();
        words.push_back(word);
//        std::cout << word <<std::endl;
        int wordIndex = words.size()-1;
        //search word
        if(word == searchWord){
//            std::cout << word <<std::endl;
            FoundWord foundWord = {charCount, wordIndex};
            foundWords.push_back(foundWord);
        }
        //smallest word
        if(word.size() < words[smallestWordIndex].size()){
            smallestWordIndex = wordIndex;
        }
        //largest word
        if(word.size() > words[largestWordIndex].size()){
            largestWordIndex = wordIndex;
        }
        
    }

    printStatistics(words, charCount, smallestWordIndex, largestWordIndex);

    keyWordPrint(foundWords, charCount, searchWord, words);
    
    
    
    return 0;
}
