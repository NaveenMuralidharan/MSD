document.writeln("Hello world");

console.log("Hello world");
//document.writeln writes the text into the DOM object
//whereas, console.log simply writes it to web developer tools console
//we can use console log to check logic of js whereas to show anything to user, we have to manipulate the DOM

let arr = ["Naveen", true, 23, 34.567, {"greet": "hello", "person": "Naveen"}];
console.log(arr);
arr[2] = 456;
arr[4].greet = "howdy";
console.log(arr);
//When modifying array, all changes are made to original array

function f1(a, b){
    return a+b
}

let f2 = function(a,b) { return a+b }

//I prefer the former syntax, apart from the fact that a function is being stored in a
//variable explicitly in the latter syntax, there is no discernible difference.

console.log(f1(2,3));
console.log(f2(2,3));
console.log(f1(3.45,6.78));
console.log(f2(3.45,6.78));
console.log(f1("hello", " world"));
console.log(f2("hello", " world"));
console.log(f1(12, "hello"));
console.log(f2(12, "hello"));
//JS is not a "typed" language the type of parameters does not need to be declred in function
//hence, data of any type can be added, the + operator is overloaded in how JS is designed to accommodate this.

