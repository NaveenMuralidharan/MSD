//
//  PokerHands.hpp
//  Poker
//
//  Created by Naveen Kumar on 8/28/24.
//

#ifndef PokerHands_hpp
#define PokerHands_hpp
#include "CardHelpers.hpp"

std::vector<Card> drawCards(std::vector<Card> cardDeck);
bool isFlush(std::vector<Card> drawnCards);
bool isStraight(std::vector<Card> drawnCards);
std::vector<Card> sortCard(std::vector<Card> drawnCards);
bool isConsecutive(std::vector<Card> drawnCards);
bool isStraightFlush(std::vector<Card> drawnCards);
bool isRoyalFlush(std::vector<Card> drawnCards);
bool isFullHouse(std::vector<Card> drawnCards);

#endif /* PokerHands_hpp */
