//
//  PokerHands.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/28/24.
//

#include "PokerHands.hpp"

std::vector<Card> drawCards(std::vector<Card> cardDeck){
    std::vector<Card> drawnCards;
    for(int i=0; i<5; i++){
        drawnCards.push_back(cardDeck[i]);
    }
    return drawnCards;
}

bool isFlush(std::vector<Card> drawnCards){
    std::string compareSuite = drawnCards[0].suite;
    bool result = true;
    for(Card card : drawnCards){
        if(card.suite != compareSuite){
            result = false;
        }
    }
    return result;
}

std::vector<Card> sortCard(std::vector<Card> drawnCards){
    
    for(int i=0; i<drawnCards.size()-1; i++){
        int lowestCardIndex = i;
        for(int j=i+1; j<drawnCards.size(); j++){
            if(drawnCards[j].num < drawnCards[lowestCardIndex].num){
                lowestCardIndex = j;
            }
        }

        if(lowestCardIndex != i){
            Card temp = drawnCards[i];
            drawnCards[i] = drawnCards[lowestCardIndex];
            drawnCards[lowestCardIndex] = temp;
        }
    }
    return drawnCards;
    
}

bool isConsecutive(std::vector<Card> drawnCards){
    bool result = true;
    for(int i=0; i<drawnCards.size()-1; i++){
        
        if(drawnCards[i+1].num - drawnCards[i].num != 1){
            result = false;
            return result;
        }
    }
    return result;
}


bool isStraight(std::vector<Card> drawnCards){
    //sort hand
    std::vector<Card> sortedCards = sortCard(drawnCards);
    //check if cards inc by 1
    if(isConsecutive(sortedCards)){
        return true;
    } else if(sortedCards[0].num == 1 && sortedCards[1].num == 10){
        std::vector<Card> truncCards;
        for(int i=1; i<sortedCards.size(); i++){
            truncCards.push_back(sortedCards[i]);
        }
        if(isConsecutive(truncCards)){
            return true;
        } else {
            return false;
        }
    }
    return false;
}

bool isStraightFlush(std::vector<Card> drawnCards){
    std::vector<Card> sortedCards = sortCard(drawnCards);
    if(isFlush(sortedCards) && isStraight(sortedCards)){
        return true;
    }
    return false;
}

bool isRoyalFlush(std::vector<Card> drawnCards){
    std::vector<Card> sortedCards = sortCard(drawnCards);
    if(isFlush(sortedCards) && isStraight(sortedCards) && sortedCards[1].num == 10){
        return true;
    }
    return false;
}

bool isFullHouse(std::vector<Card> drawnCards){
    
    std::vector<Card> sortedCards = sortCard(drawnCards);
    
    if((sortedCards[0].num == sortedCards[2].num && sortedCards[3].num == sortedCards[4].num) || (sortedCards[0].num == sortedCards[1].num && sortedCards[2].num == sortedCards[4].num)){
        return true;
    }
    return false;
}
