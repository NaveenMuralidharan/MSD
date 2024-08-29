//
//  Statistics.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/28/24.
//

#include "Statistics.hpp"

void statistics(std::vector<Card> cardDeck, int repNum){
    
    int flush, straight, straightFlush, royalFlush, fullHouse;
    flush = straight = straightFlush = royalFlush = fullHouse = 0;
    
    for(int i=1; i<= repNum; i++){
        
        std::string result = playHand(cardDeck);
        if(result == "Flush"){
            flush += 1;
        } else if(result == "Straight"){
            straight += 1;
        } else if(result == "Straight flush"){
            straightFlush +=1;
        } else if(result == "Royal flush"){
            royalFlush += 1;
        } else if(result == "Full house"){
            fullHouse += 1;
        }
    }
    
    std::cout << "Num of Flush: " << flush << std::endl;
    std::cout << "Num of Straight: " << straight << std::endl;
    std::cout << "Num of Straight Flush: " << straightFlush << std::endl;
    std::cout << "Num of Royal Flush: " << royalFlush << std::endl;
    std::cout << "Num of Full House: " << fullHouse << std::endl;
    
}
