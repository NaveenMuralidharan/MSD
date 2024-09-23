//
//  main.cpp
//  PoliStructs
//
//  Created by Naveen Kumar on 8/27/24.
//

#include <iostream>
#include"PoliStructsHelpers.hpp"
#include <cassert>

int main(int argc, const char * argv[]) {
    
    std::vector<Politician> Politicians;
    
    Politician politician1{"Adam","Cplusers","state"};
    Politician politician2{"Ben","Javacans","federal"};
    Politician politician3{"Stacy","Cplusers","federal"};
    Politician politician4{"Jordan","Javacans","state"};
    
    Politicians.push_back(politician1);
    Politicians.push_back(politician2);
    Politicians.push_back(politician3);
    Politicians.push_back(politician4);
    
    //Javacans() test
    
    std::vector<Politician> JavacanPoliticians = Javacans(Politicians);
    for(Politician politician : JavacanPoliticians){
        assert(politician.party == "Javacans");
    };
    
    //federalCplusers() test
    
    std::vector<Politician> FederalCpluserPoliticians = federalCplusers(Politicians);
    for(Politician politician : FederalCpluserPoliticians){
        assert(politician.party == "Cplusers" || politician.level == "federal");
    }
    
    return 0;
}
