console.log("JS connected");
const h1 = document.createElement('h1');
h1.textContent = "Powerpuff Girls Fan Site";

const h2 = document.createElement('h2');
h2.textContent = "Producer: Hanna Barbara";

const h3 = document.createElement('h3');
h3.textContent = "Aired on: Cartoon Network";

const h4 = document.createElement('h4');
h4.textContent = "Era: 1990's";

const h5 = document.createElement('h5');
h5.textContent = "Audience: 5+";

const h6 = document.createElement('h6');
h6.textContent = "Rating: 4.5";

const p = document.createElement('p');
const span = document.createElement('span');
span.textContent = "Powerpuff girls recipe";
p.appendChild(span);

const div1 = document.createElement('div');
const ul = document.createElement('ul');
const arr = ['Sugar', 'Spice', 'Everything nice'];
arr.forEach(e=> {
    const li = document.createElement('li');
    li.textContent = e;
    ul.appendChild(li);
});
div1.appendChild(ul);

const div2 = document.createElement('div');
const em = document.createElement('em');
em.textContent = 'Blossom';
div2.appendChild(document.createTextNode('(voiced by Cathy Cavadini) is one of the main characters in the animated.television series "Powerpuff Girls."') );

const body = document.body;
body.style.backgroundColor = "yellow";
body.style.fontFamily = '"Times New Roman", Times, serif';
span.style.color = "red";

body.appendChild(h1);
body.appendChild(h2);
body.appendChild(h3);
body.appendChild(h4);
body.appendChild(h5);
body.appendChild(h6);
body.appendChild(p);
body.appendChild(div1);
body.appendChild(div2);





