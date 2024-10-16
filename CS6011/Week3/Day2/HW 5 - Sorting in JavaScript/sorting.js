console.log("JS connected");

let nums = [23, 4, -3, 7, 0];

function selectionSort(arr){

    for(let i=0; i<arr.length-1; i++){
        let minIndex = findMinLocation(arr, i);
        if(minIndex != i){
            let temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

//given an array, return min index
function findMinLocation(arr, minIndex){
    let startIndex = minIndex;
    for(let j = startIndex+1; j<arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
    return minIndex;
}

selectionSort(nums);
console.log(nums);

const arr2 = [3.45, 6.78, 3.54, 8.789, 2.46];
selectionSort(arr2);
console.log(arr2);

const arr3 = ["hello", "World", "NAVEEN", "KUMAR", "HELLO"];
selectionSort(arr3);
console.log(arr3);
//lowercase characters are treated as higher value than uppercase characters because it is comparing unicode values and doing a lexicoraphical comparison

const arr4 = [3,4.56, "hello", 7, "10"];
selectionSort(arr4);
console.log(arr4);

function selectionSort2(arr, compareFunc){

    for(let i=0; i<arr.length-1; i++){
        let minIndex = findMinLocation2(arr, i, compareFunc);
        if(minIndex != i){
            let temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

function findMinLocation2(arr, minIndex, compareFunc){
    let startIndex = minIndex;
    for(let j = startIndex+1; j<arr.length; j++){
                if(compareFunc(arr[j],arr[minIndex])){
                    minIndex = j;
                }
            }
    return minIndex;
}

function compareFunc(a, b){
    if(a<b){
        return 1;
    } else {
        return 0;
    }
}

let nums2 = [23, 4, -3, 7, 0];
selectionSort2(nums2, compareFunc);
console.log(nums2);

function compareFunc2(a, b){
    if(a>b){
        return 1;
    } else {
        return 0;
    }
}
let nums3 = [23, 4, -3, 7, 0];
selectionSort2(nums3, compareFunc2);
console.log(nums3);
//changing the compare function to return true if a >b, sorts the array in descending order

function comparePeople1(a,b){
    const a1 = {"first" :a.first.toLowerCase(), "last": a.last.toLowerCase()};
    const b1 = {"first" :b.first.toLowerCase(), "last": b.last.toLowerCase()};
    if(a1.last < b1.last){
        return 1;
    } else if(a1.last == b1.last){
        if(a1.first < b1.first){
            return 1;
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}

const arr5 = [  {first: "Ben", last: "Benderson"},
                {first: "Cal", last: "Anderson"},
                {first: "Adam", last: "Anderson"}
              ]

selectionSort2(arr5, comparePeople1);
console.log(arr5);

function comparePeople2(a,b){
    const a1 = {"first" :a.first.toLowerCase(), "last": a.last.toLowerCase()};
    const b1 = {"first" :b.first.toLowerCase(), "last": b.last.toLowerCase()};
    if(a1.first < b1.first){
        return 1;
    } else if(a1.first == b1.first){
        if(a1.last < b1.last){
            return 1;
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}

const arr6 = [  {first: "Ben", last: "Benderson"},
                {first: "Ben", last: "Anderson"},
                {first: "Adam", last: "Anderson"}
              ]

selectionSort2(arr6, comparePeople2);
console.log(arr6);