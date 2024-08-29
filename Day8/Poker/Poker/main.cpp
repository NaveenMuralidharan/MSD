//
//  main.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/27/24.
//
/* While analyzing statistics for 100,000 hands,
 Num of Flush: 269 = 0.269%
 Num of Straight: 377 = 0.377%
 Num of Straight Flush: 3 = 0.003%
 Num of Royal Flush: 0 = 0%
 Num of Full House: 140 = 0.14%
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
    
    playHand(cardDeck);
    
    statistics(cardDeck, 10000);
    
    
    
    
    return 0;
}
