//
//  CardShuffle.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/28/24.
//

#include "CardShuffle.hpp"

void shuffledDeck(std::vector<Card> &cardDeck){
    
    int numCards = cardDeck.size();
    
    for(int i=0; i<cardDeck.size(); i++){
        Card temp = cardDeck[i];
        int j = rand() % numCards;
        cardDeck[i] = cardDeck[j];
        cardDeck[j] = temp;
    }
    
    
}
