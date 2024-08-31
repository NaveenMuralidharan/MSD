//
//  CardHelpers.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/27/24.
//

#include "CardHelpers.hpp"

std::vector<Card> createDeck(){
    std::vector<std::string> suites = {"club", "diamond", "heart", "spade"};
    std::vector<Card> cardDeck;
    for(std::string suite : suites){
        for(int i=1; i<14; i++){
            cardDeck.push_back(Card{suite, i});
        }
    }
    return cardDeck;
};

void printDeck(const std::vector<Card> &cardDeck){
    for(Card card : cardDeck){
        if(card.num == 1){
            std::cout << card.suite << "A" << std::endl;
        } else if(card.num == 11){
            std::cout << card.suite << "J"<< std::endl;
        } else if(card.num == 12){
            std::cout << card.suite << "Q"<< std::endl;
        } else if(card.num == 13){
            std::cout << card.suite << "K" << std::endl;
        } else {
            std::cout << card.suite << card.num << std::endl;
        }
    }
}


