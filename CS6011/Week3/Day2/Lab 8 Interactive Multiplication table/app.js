console.log("JS connected");

const style = document.createElement('style');
style.textContent = `
    .highlight {
        background-color: lightblue;
        font-weight: bold;
    }
    .selected {
        background-color: orange;
        font-weight: bold;
    }
`
document.head.appendChild(style);

const table = document.createElement('table');
const nums = [1,2,3,4,5,6,7,8,9,10];
let anySelected = false;
let selected;
//create a row to hold column headers
const headRow = document.createElement('tr');
const firstHeader = document.createElement('th');
firstHeader.textContent = '#';
headRow.appendChild(firstHeader);

//create column headers
nums.forEach(num => {
    const header = document.createElement("th");
    header.textContent = num;
    header.style.border = 'solid 1px black';
    header.addEventListener('mouseenter', e => { header.classList.add('highlight'); });
    header.addEventListener('mouseleave', e => { header.classList.remove('highlight'); });

    headRow.appendChild(header);
});
table.appendChild(headRow);

//loop i through nums
for(let i=0; i<nums.length; i++){
    //create a row for each num and place first td as num itself
    const row = document.createElement('tr');
    const firstEntry = document.createElement('th');
    firstEntry.textContent = nums[i];
    firstEntry.style.border = 'solid 1px black';
    firstEntry.addEventListener('mouseenter', e => { firstEntry.classList.add('highlight'); });
    firstEntry.addEventListener('mouseleave', e => { firstEntry.classList.remove('highlight'); });

    row.appendChild(firstEntry);
    //nest loop j through nums
    for(let j=0; j<nums.length; j++){
        //create new td with content i*j
        const entry = document.createElement('td');
        entry.selected = false;
        entry.textContent = nums[i]*nums[j];
        entry.style.border = 'solid 1px blue';
        entry.style.padding = '10px';
        entry.addEventListener('mouseenter', e => { if(!entry.selected){ entry.classList.add('highlight'); } });
        entry.addEventListener('mouseleave', e => { if(!entry.selected) { entry.classList.remove('highlight'); } });
        entry.addEventListener('click', e => {
            //if any cell is selected,
            if(anySelected){
                //unselect previous cell
//                selected.style.backgroundColor = "white";
                selected.classList.remove('selected');
                selected.classList.remove('highlight');
                selected.selected = false;
            }
            //select this cell
//            entry.style.backgroundColor = "orange";
            entry.classList.add('selected');
            entry.selected = true;
            selected = entry;
            anySelected = true;
        });

        row.appendChild(entry);
    }
    //add row to table
    table.appendChild(row);
    //add style to table
    table.style.border = 'solid 1px black';
}

let isColored = false;
const button = document.createElement('button');
button.textContent = "Change Colors";
button.style.border = "solid 1px red";
button.addEventListener("click", e => { toggleColor(); });

let r = 0;
let g = 0;
let b = 255;
let myInterval;

function toggleColor(){
    if(!isColored){
        isColored = true;
        myInterval = window.setInterval(changeColor, 50);
    } else {
        isColored = false;
        clearInterval(myInterval);
        document.body.style.backgroundColor = "white";
    }

}

function changeColor(){
    if(b < 0 || r >255){
        b = 255;
        r = 0;
    }
    b -= 5;
    r += 5;
    console.log(r);
    let rgb = "rgb("+r+","+g+","+b+")";
    document.body.style.backgroundColor = rgb;

}

function toggleClick(entry){
    console.log(entry.selected);
//    let color = entry.style.backgroundColor;
    if(entry.selected){
        entry.style.backgroundColor = 'white';
    } else {
        entry.style.backgroundColor = 'lightblue';
    }

}


document.body.appendChild(table);
document.body.appendChild(document.createElement('br'));
document.body.appendChild(button);