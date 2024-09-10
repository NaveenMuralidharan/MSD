//
//  main.cpp
//  Pointers
//
//  Created by Naveen Kumar on 9/9/24.
//

#include <iostream>

double* copyArr(double* arr, int size){
    double* arrayFirstIndex = new double[size];
    
    for(int i=0; i<size; i++){
        arrayFirstIndex[i] = arr[i];
    }
    
    return arrayFirstIndex;
}


int main(int argc, const char * argv[]) {

    double arr[5] = {1,2,3,4,5};
    double* arrIndex = &arr[0];
    double* testArr = copyArr(arrIndex, 5);
    for(int i=0; i<5; i++){
        std::cout << testArr[i] << std::endl;
    }
    delete[] testArr;
    testArr = nullptr;

    return 0;
}
