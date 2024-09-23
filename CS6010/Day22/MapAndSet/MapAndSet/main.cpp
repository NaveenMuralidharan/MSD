//
//  main.cpp
//  MapAndSet
//
//  Created by Naveen Kumar on 9/18/24.
//

#include <iostream>
#include <fstream>
#include <set>
#include <map>

int main(int argc, const char * argv[]) {

    if(argc < 2){
        std::cout<<"specify a file"<<std::endl;
        return 1;
    }
    
    std::ifstream file(argv[1]);
    
    std::set<std::string> words;
    std::map<std::string, int> counter;
    
    std::string word;
    while(file>>word){
        words.insert(word);
        
//        counter[word]++;
        if(counter.find(word) == counter.end()){
            counter[word] = 1;
        } else {
            counter[word]++;
        }
    }
    std::cout<<words.size()<<std::endl;
    
    for(const auto& pair: counter){
        std::cout<<pair.first<< ' '<<pair.second<<std::endl;
    }
    
    return 0;
}
