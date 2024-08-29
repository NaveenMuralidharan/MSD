//
//  PoliStructsHelpers.cpp
//  PoliStructs
//
//  Created by Naveen Kumar on 8/27/24.
//

#include "PoliStructsHelpers.hpp"

std::vector<Politician> Javacans(std::vector<Politician> Politicians){
    std::vector<Politician> javacanPoliticians;
    
    for(int i=0; i<Politicians.size(); i++){
        if(Politicians[i].party == "Javacans"){
            javacanPoliticians.push_back(Politicians[i]);
        }
    }
    return javacanPoliticians;
}

std::vector<Politician> federalCplusers(std::vector<Politician> Politicians){
    std::vector<Politician> federalCplusers;
    for(int i=0; i<Politicians.size(); i++){
        if(Politicians[i].party == "Cplusers" && Politicians[i].level == "federal"){
            federalCplusers.push_back(Politicians[i]);
        }
    }
    return federalCplusers;
}
