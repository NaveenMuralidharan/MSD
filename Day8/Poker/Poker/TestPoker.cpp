//
//  TestPoker.cpp
//  Poker
//
//  Created by Naveen Kumar on 8/29/24.
//

#include "TestPoker.hpp"

std::vector<Card> flushTest1 = {{"club", 5}, {"club", 7}, {"club", 9}, {"club", 11}, {"club", 13}};
std::vector<Card> flushTest2 = {{"club", 5}, {"club", 7}, {"diamond", 9}, {"club", 11}, {"club", 13}};

void FlushTest1(){
    playHand(flushTest1);
}

void FlushTest2(){
    playHand(flushTest2);
}

std::vector<Card> straightTest1 = {{"club", 1}, {"spade", 3}, {"diamond", 2}, {"club", 5}, {"club", 4}};
std::vector<Card> straightTest2 = {{"club", 1}, {"spade", 13}, {"diamond", 11}, {"club", 12}, {"club", 10}};
std::vector<Card> straightTest3 = {{"club", 3}, {"spade", 13}, {"diamond", 5}, {"club", 12}, {"club", 10}};

void StraightTest1(){
    playHand(straightTest1);
}

void StraightTest2(){
    playHand(straightTest2);
}

void StraightTest3(){
    playHand(straightTest3);
}

std::vector<Card> straightFlushTest1 = {{"club", 1}, {"club", 3}, {"club", 2}, {"club", 5}, {"club", 4}};
std::vector<Card> straightFlushTest2 = {{"club", 1}, {"spade", 6}, {"club", 2}, {"club", 5}, {"club", 4}};


void StraightFlushTest1(){
    playHand(straightFlushTest1);
}

void StraightFlushTest2(){
    playHand(straightFlushTest2);
}

std::vector<Card> royalFlushTest1 = {{"club", 1}, {"club", 13}, {"club", 11}, {"club", 12}, {"club", 10}};
std::vector<Card> royalFlushTest2 = {{"club", 1}, {"club", 5}, {"club", 11}, {"club", 12}, {"club", 10}};


void RoyalFlushTest1(){
    playHand(royalFlushTest1);
}

void RoyalFlushTest2(){
    playHand(royalFlushTest2);
}

std::vector<Card> fullHouseTest1 = {{"club", 1}, {"spade", 3}, {"diamond", 1}, {"heart", 3}, {"club", 3}};
std::vector<Card> fullHouseTest2 = {{"club", 1}, {"spade", 4}, {"diamond", 1}, {"heart", 3}, {"club", 3}};


void FullHouseTest1(){
    playHand(fullHouseTest1);
}

void FullHouseTest2(){
    playHand(fullHouseTest2);
}
