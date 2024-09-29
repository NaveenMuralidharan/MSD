//
//  main.cpp
//  NumberRepresentations
//
//  Created by Naveen Kumar on 9/4/24.
//

#include <iostream>
#include <cstdint>
#include <cassert>
#include <iomanip>
#include <cmath>
#include <iostream>
#include <fstream>


bool approxEquals(double a, double b, double tolerance){
    double difference = std::abs(a-b);
    if(difference < tolerance){
        return true;
    } else {
        return false;
    }
}


int main(int argc, const char * argv[]) {
    
    //Print out the sizes of the built in numeric types.
    std::cout << "Size of int "<<sizeof(int) << std::endl;
    std::cout << "Size of short "<<sizeof(short) << std::endl;
    std::cout << "Size of long "<<sizeof(long) << std::endl;
    std::cout << "Size of float "<<sizeof(float) << std::endl;
    std::cout << "Size of double "<<sizeof(double) << std::endl;

    std::cout << "Size of int8_t "<<sizeof(int8_t) << std::endl;
    std::cout << "Size of int16_t "<<sizeof(int16_t) << std::endl;
    std::cout << "Size of int32_t "<<sizeof(int32_t) << std::endl;
    std::cout << "Size of int64_t "<<sizeof(int64_t) << std::endl;
    std::cout << "Size of uint8_t "<<sizeof(uint8_t) << std::endl;
    std::cout << "Size of uint16_t "<<sizeof(uint16_t) << std::endl;
    std::cout << "Size of uint32_t "<<sizeof(uint32_t) << std::endl;
    std::cout << "Size of uint64_t "<<sizeof(uint64_t) << std::endl;
    
    int8_t int8Max = 0x7f;
    int8_t int8Min = 0x80;
    uint8_t uint8Max = 0xff;
    uint8_t uint8Min = 0x00;
    std::cout << "Signed int8Max is "<< +int8Max << std::endl;
    std::cout << "Signed int8Min is "<< +int8Min << std::endl;
    std::cout << "Unsigned uint8Max is "<< +uint8Max << std::endl;
    std::cout << "Unsigned uint8Min is "<< +uint8Min << std::endl;
    int16_t int16Max = 0x7fff;
    int16_t int16Min = 0x8000;
    uint16_t uint16Max = 0xffff;
    uint16_t uint16Min = 0x0000;
    std::cout << "Signed int16Max is "<< +int16Max << std::endl;
    std::cout << "Signed int16Min is "<< +int16Min << std::endl;
    std::cout << "Unsigned uint16Max is "<< +uint16Max << std::endl;
    std::cout << "Unsigned uint16Min is "<< +uint16Min << std::endl;
    int64_t int64Max = 0x7fffffffffffffff;
    int64_t int64Min = 0x8000000000000000;
    uint64_t uint64Max = 0xffffffffffffffff;
    uint64_t uint64Min = 0x0000000000000000;
    std::cout << "Signed int64Max is "<< +int64Max << std::endl;
    std::cout << "Signed int64Min is "<< +int64Min << std::endl;
    std::cout << "Unsigned uint64Max is "<< +uint64Max << std::endl;
    std::cout << "Unsigned uint64Min is "<< +uint64Min << std::endl;
    
    //**************************
    //PART 2:
    double sum = .1+.2;
    std::cout << std::setprecision(18) << sum<< std::endl;
    
    std::cout<<approxEquals(sum, 0.3, 0.0000000001)<<std::endl;
    //****************************
    //PART 3:
    
    std::ifstream ins("/Users/naveenkumar/MSD/CS6010/Day12/NumberRepresentations/NumberRepresentations/UTF-8-demo.txt");
    
    if(ins.fail()){
        std::cout << "file not found" <<std::endl;
    } else {
        char c;
        int asciiCount = 0;
        int unicodeCount = 0;
        while(ins >> c){
            std::cout << c << std::endl;
            if(c <= 127 && c>0){
                asciiCount += 1;
            } else {
                unicodeCount += 1;
            }
        }
        std::cout << "Unicode count is: " << unicodeCount << std::endl;
        std::cout << "ASCII count is: " << asciiCount << std::endl;
    }
    
// Chars are signed integers with max value of 127, UTF-8 chars that have value over 127 are not able to be held properly and displayed by char data type.
    
    return 0;
}
