// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Changes color of cover title using random method 
 

    var mouseOverColor = document.getElementById("changeColor").addEventListener("mouseover", isColor);
    var mouseOutColor = document.getElementById("changeColor").addEventListener("mouseout", isColor);
**/

function isColor() {
    const color =
        ['#3369e8', '#eeb211', '#009925', '#d50f25']; 
    const randColor = color[Math.floor(Math.random() * color.length)]; 
    document.getElementById("changeColor").style.color = randColor; 
} 

/**
 * Adds a random greeting to the page.
 */
function addRandomFact() {
    const aboutMe =
        ['I have lived in NJ my entire life', 
        'My favorite dessert is Cheesecake', 
        'I am a Virgo', 
        'I had a pet guinea pig named Lightening'];

    // Pick a random greeting.
    const moreText = aboutMe[Math.floor(Math.random() * aboutMe.length)];

    // Add it to the page.
    const moreContainer = document.getElementById('more-container');
    moreContainer.innerText = moreText;
}

/**
 * Adds a comment from the server 
 */
function getComment() {
  fetch('/comment').then(response => response.json()).then((comment) => {
    // Build the list of comment entries.
    const historyEl = document.getElementById('history');
    comment.history.forEach((line) => {
      historyEl.appendChild(createListElement(line));
    });
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
