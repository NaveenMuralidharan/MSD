//
//  CardHelpers.hpp
//  Cards
//
//  Created by Naveen Kumar on 8/27/24.
//

#ifndef CardHelpers_hpp
#define CardHelpers_hpp
#include<iostream>
#include<vector>

struct Card{std::string suite; int num;};
std::vector<Card> createDeck();
void printDeck(std::vector<Card> cardDeck);

#endif /* CardHelpers_hpp */
