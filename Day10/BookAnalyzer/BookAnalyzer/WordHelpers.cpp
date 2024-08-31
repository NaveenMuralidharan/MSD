//
//  WordHelpers.cpp
//  BookAnalyzer
//
//  Created by Naveen Kumar on 8/29/24.
//

#include "WordHelpers.hpp"

std::string print(std::vector<std::string> words, int startIndex, int endIndex){
    std::string result = "";
    for(int i=startIndex; i<endIndex; i++){
        result = result + words[i] + " ";
    }
    return result;
}

void printStatistics(const std::vector<std::string> &words, int charCount, int smallestWordIndex, int largestWordIndex){
    /*
     Pre: assumes that Relsease Date is always present.
     If title is unknown, "Title:" does not show up on file, if author is unknown, "Author:" does not show up on file.
     */
    int titleIndex, authorIndex, releaseIndex;
    bool hasTitle = false;
    bool hasAuthor = false;
    //loop words,
    for(int i=0; i<words.size(); i++){
        //  Find "Title:", store titleIndex, set hasTitle to true
        if(words[i] == "Title:"){
            titleIndex = i;
            hasTitle = true;
        }
        //Find "Author:", store authorIndex, set hasAuthor to true
        if(words[i] == "Author:"){
            authorIndex = i;
            hasAuthor = true;
        }
        //Find "Release Date:", store releaseIndex
        if(words[i] == "Release" && words[i+1] == "date:"){
            releaseIndex = i;
            break;
        }
    }
    
    //print title and author
    if(!hasTitle && !hasAuthor){
        std::cout << "Title is unknown!" << "\n" << "Author is unknown!" << std::endl;
        std::cout << "Statistics for unknown by unknown: "<<std::endl;
        
    } else if(hasTitle && !hasAuthor){
        std::string title = print(words, titleIndex+1, releaseIndex);
        std::cout << "Title is: " << title << std::endl;
        std::cout << "Author is unknown!" << std::endl;
        std::cout << "Statistics for " + title + " by unknown: \n";
    } else if(!hasTitle && hasAuthor){
        std::string author = print(words, authorIndex+1, releaseIndex);
        std::cout << "Title is unknown!" << std::endl;
        std::cout << "Author is: " << author << std::endl;
        std::cout << "Statistics for unknown by "+author+": \n";
    } else if(hasTitle && hasAuthor){
        std::string title = print(words, titleIndex+1, authorIndex);
        std::string author = print(words, authorIndex+1, releaseIndex);
        std::cout << "Title is: " << title << std::endl;
        std::cout << "Author is: " << author << std::endl;
        std::cout << "Statistics for "+title+" by "+author+": \n";
    }
    //print statistics
    std::cout << "Number of words: " + std::to_string(words.size())+"\n";
    std::cout << "Number of characters: " + std::to_string(charCount)+"\n";
    std::cout << "The shortest word is "<<"\""<<words[smallestWordIndex]<<"\", and the longest word is "<<"\""<<words[largestWordIndex]<<"\""<<std::endl;
}

void keyWordPrint(std::vector<FoundWord> foundWords, int charCount, std::string searchWord, std::vector<std::string> words){
    
    std::string result = "The word "+searchWord+" appears "+std::to_string(foundWords.size())+" times: \n";
    
    if(foundWords.size() != 0){
        for(int i=0; i<foundWords.size(); i++){
            double percent = foundWords[i].charCount / (double) charCount;
            int roundPercent = std::round(percent);
            std::string phrase = words[foundWords[i].wordIndex-1]+" "+words[foundWords[i].wordIndex]+" "+words[foundWords[i].wordIndex+1];
            result += "at "+std::to_string(roundPercent)+"%: "+ "\"" +phrase+"\""+"\n";
        }
        std::cout << result << std::endl;
    }
}

