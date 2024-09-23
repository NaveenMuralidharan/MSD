//
//  main.cpp
//  SFML_lab
//
//  Created by Naveen Kumar on 9/16/24.
//

#include <iostream>
#include <SFML/Graphics.hpp>
#include <SFML/Window/Keyboard.hpp>


int main(int argc, const char * argv[]) {

    sf::RenderWindow window(sf::VideoMode(800,600), "My first SFML game");
    sf::RectangleShape rect;
    rect.setPosition(100, 200);
    rect.setSize(sf::Vector2<float>(200,100));
    rect.setFillColor(sf::Color::Red);
    
    sf::CircleShape shape2(75.f);
    shape2.setPosition(sf::Vector2f(400.f,500.f));
    
    
    while(window.isOpen()){
        
        
        sf::Event event;
        while(window.pollEvent(event)){
            if(event.type == sf::Event::Closed)
                window.close();
        }
        if(sf::Keyboard::isKeyPressed(sf::Keyboard::D)||sf::Keyboard::isKeyPressed(sf::Keyboard::Right))
        {
            //character.move(1, 0);
                shape2.move(1,0);
        }
        
        window.clear();
        //only code happening between clear and display will be shown
        window.draw(rect);
        window.draw(shape2);
        
        window.display();
        /*processEvent()
         update()
         Render()*/
        //in main there should only be Game game and game.run(), put everything else in helper files
        
    }
    
    return 0;
}
