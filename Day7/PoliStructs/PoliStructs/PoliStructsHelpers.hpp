//
//  PoliStructsHelpers.hpp
//  PoliStructs
//
//  Created by Naveen Kumar on 8/27/24.
//

#ifndef PoliStructsHelpers_hpp
#define PoliStructsHelpers_hpp

#include<iostream>
#include<vector>

struct Politician {std::string name; std::string party; std::string level;};

std::vector<Politician> Javacans(std::vector<Politician> Politicians);
std::vector<Politician> federalCplusers(std::vector<Politician> Politicians);

#endif /* PoliStructsHelpers_hpp */
