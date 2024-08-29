//
//  PlayHand.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/28/24.
//

#include "PlayHand.hpp"

std::string playHand(std::vector<Card> cardDeck){
    
    shuffledDeck(cardDeck);
    std::vector<Card> drawnCards = drawCards(cardDeck);
    sortCard(drawnCards);
    printDeck(drawnCards);
    
    if(isFullHouse(drawnCards)){
        std::cout<< "Full House" << std::endl;
        return "Full house";
    } else if(isRoyalFlush(drawnCards)){
        std::cout<< "Royal Flush" << std::endl;
        return "Royal flush";
    } else if(isStraightFlush(drawnCards)){
        std::cout<< "Straight Flush" << std::endl;
        return "Straight flush";
    } else if(isStraight(drawnCards)){
        std::cout<< "Straight" << std::endl;
        return "Straight";
    } else if(isFlush(drawnCards)){
        std::cout<< "Flush" << std::endl;
        return "Flush";
    } else {
        std::cout<< "High card" << std::endl;
        return "High card";
    }
    
}
