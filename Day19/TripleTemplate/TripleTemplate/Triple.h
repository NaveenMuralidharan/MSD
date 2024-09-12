//
//  Triple.h
//  TripleTemplate
//
//  Created by Naveen Kumar on 9/12/24.
//

#ifndef Triple_h
#define Triple_h

template <typename T>

struct Triple {
    T a;
    T b;
    T c;
    T sum();
};

template <typename T>
T Triple<T>::sum(){
    return a+b+c;
}

#endif /* Triple_h */
