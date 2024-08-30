//
//  main.cpp
//  CommandLineArgs
//
//  Created by Naveen Kumar on 8/29/24.
//

#include <iostream>

int main(int argc, const char * argv[]) {

    for(int i=1; i<argc; i++){
        std::cout << argv[i] << std::endl;
    }
    
    return 0;
}
