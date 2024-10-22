console.log("JS connected");

//const canvas = document.querySelector('#myCanvas');
const canvas = document.getElementById('myCanvas');

const context = canvas.getContext('2d');

        // Set background color
function setCanvasBackgroundColor(color) {
            context.fillStyle = color;
            context.fillRect(0, 0, canvas.width, canvas.height);
    }

let isGameOver = false;

//create bees
let bees = [];
for (let i =0; i<10; i++){
    let bee = {'x': 100, 'y': 150, 'speed': Math.floor(Math.random()*10+1)};
    bee.width = 30;
    bee.height = 30;
    bee.img = new Image();
    bee.leftImg = "bees-left.svg";
    bee.rightImg = "bees-right.svg";
    bee.img.src = bee.rightImg;
    bees.push(bee);
}

let cursorLocation = {x:0, y:0};
function handleMouseMove(e){
    cursorLocation.x = e.x;
    cursorLocation.y = e.y;
}

function handleMove(){

    bees.forEach(bee => {

             if(Math.abs(bee.x - cursorLocation.x) < bee.speed &&
                        Math.abs(bee.y - cursorLocation.y) < bee.speed ){
                 bee.x = cursorLocation.x;
                 bee.y = cursorLocation.y;

             } else {
                //is bee x < currentLocation x
                            if(bee.x < cursorLocation.x){
                                bee.img.src = bee.rightImg;
                                bee.x += bee.speed;
                            } else {
                                bee.img.src = bee.leftImg;
                                bee.x -= bee.speed;
                            }
                            //is bee y < currentLocation y
                            if(bee.y < cursorLocation.y){
                                bee.y += bee.speed;
                            } else {
                                bee.y -= bee.speed;
                            }
             }
    });

}

//draw honey
let honey = {x: 100, y:100};
honey.img = new Image();
honey.img.src = "honey2.jpg";
honey.img.onload = function () {
    mainGameLoop(); // Start the game loop after honey is loaded
};


function draw(){
        context.clearRect(0,0,1000,1000);
        setCanvasBackgroundColor('lightblue');
        context.drawImage(honey.img, cursorLocation.x - (50/2), cursorLocation.y -(50/2), 50, 50);
        bees.forEach(bee => {

            context.drawImage(bee.img, bee.x, bee.y, bee.width, bee.height);

        });

}

function checkGameOver(){
    bees.forEach(bee => {
        if(Math.abs(bee.x - cursorLocation.x) < bee.speed &&
            Math.abs(bee.y - cursorLocation.y) < bee.speed ){
            isGameOver = true;

        }
    });
}

function mainGameLoop(){
    handleMove();
    draw();
    checkGameOver();
    if(!isGameOver){
        window.requestAnimationFrame(mainGameLoop);
    }
}

//mainGameLoop();

canvas.onmousemove = handleMouseMove;
