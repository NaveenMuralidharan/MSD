//
//  main.cpp
//  Cards
//
//  Created by Naveen Kumar on 8/27/24.
//

#include <iostream>
#include "CardHelpers.hpp"

int main(int argc, const char * argv[]) {

    std::vector<Card> cardDeck = createDeck();
    printDeck(cardDeck);
    
    
    return 0;
}
