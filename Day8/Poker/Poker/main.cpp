//
//  main.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/27/24.
//
/* While analyzing statistics for 100,000 hands,
 Num of Flush: 269
 Num of Straight: 377
 Num of Straight Flush: 3
 Num of Royal Flush: 0
 Num of Full House: 140
*/

#include <iostream>
#include "CardHelpers.hpp"
#include "CardShuffle.hpp"
#include "PokerHands.hpp"
#include "PlayHand.hpp"
#include "Statistics.hpp"
#include <time.h>

int main(int argc, const char * argv[]) {
    srand(std::time(nullptr));
    
    std::vector<Card> cardDeck = createDeck();
    
//    playHand(cardDeck);
    
    statistics(cardDeck, 100000);
    
    
    
    
    return 0;
}
