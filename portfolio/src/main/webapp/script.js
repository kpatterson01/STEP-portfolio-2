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

/** Creates a map and adds it to the page. */
function createMap() {
    const map = new google.maps.Map(
        document.getElementById('map'),
        {center: {lat: 40.222896, lng: -74.637021}, zoom: 16});
    var InfoObj = [];    
    /*const trexMarker = new google.maps.Marker({
        position: {lat: 37.421903, lng: -122.084674},
        map: map,
        title: 'Stan the T-Rex'
    });

    const robMarker = new google.maps.Marker({
        position: {lat: 40.238945, lng: -74.618499},
        map: map,
        title: 'Robbinsville HS'
    });*/
    
    var icons = {
        android: {
            icon: 'images/android.png',
            scaledSize: new google.maps.Size(14, 15), 
            origin: new google.maps.Point(0,0), 
            anchor: new google.maps.Point(0, 0) 
        }
    };

    var favoritePlaces = [
        { 
        placeName: '<div id="content">'+
                    '<div id="siteNotice">'+
                    '</div>'+
                    '<h1 id="firstHeading" class="firstHeading">Bagels n\' Cream</h1>'+
                    '<div id="bodyContent">'+
                    '<p><b>Bagels n\' Cream</b>, is one of my favorite places because they have the ' +
                    '<b>BEST</b> bagels in New Jersey. My favorite bagel to buy here is the Blueberry Crunch Bagel '+
                    'with cream cheese. It is a must buy! If you are ever in New Jersey, I definitely recommend '+
                    'coming here and trying their bagels. See link below to see their menu! '+
                    '<p><a href="https://bagelsncreamnj.com/"</a> '+
                    'Visit Bagels n\' Cream.</p>'+
                    '</div>'+
                    '</div>', 
        position: new google.maps.LatLng(40.222896, -74.637021),
        type: 'android'
        },
        {
        placeName: '<div id="content">'+
                    '<div id="siteNotice">'+
                    '</div>'+
                    '<h1 id="firstHeading" class="firstHeading">Disney World</h1>'+
                    '<div id="bodyContent">'+
                    '<p><b>Disney World</b>, has been a resort I have always fallen in love with. ' +
                    'Established in 1965, Disney World has been known for its fun rides, the huge Cinderella Castle and '+
                    'the place where Dreams are made of. Visiting Disney World as a little kid as always been on of '+
                    'my favorites memories. Disney World is definitely one of my favorites places in the world. '+
                    '</div>'+
                    '</div>',   
        position: new google.maps.LatLng(28.385516, -81.563863),
        type: 'android'
        }, 
        {
        placeName: '<div id="content">'+
                    '<div id="siteNotice">'+
                    '</div>'+
                    '<h1 id="firstHeading" class="firstHeading">Times Square</h1>'+
                    '<div id="bodyContent">'+
                    '<p><b>Disney World</b>, has been a resort I have always fallen in love with. ' +
                    'Established in 1965, Disney World has been known for its fun rides, the huge Cinderella Castle and '+
                    'the place where Dreams are made of. Visiting Disney World as a little kid as always been on of '+
                    'my favorites memories. Disney World is definitely one of my favorites places in the world. '+
                    '</div>'+
                    '</div>',  
        position: new google.maps.LatLng(40.758153, -73.985575),
        type: 'android'
        }, 
        {
        placeName: '<div id="content">'+
                    '<div id="siteNotice">'+
                    '</div>'+
                    '<h1 id="firstHeading" class="firstHeading">Golden Gate Bridge</h1>'+
                    '<div id="bodyContent">'+
                    '<p><b>Disney World</b>, has been a resort I have always fallen in love with. ' +
                    'Established in 1965, Disney World has been known for its fun rides, the huge Cinderella Castle and '+
                    'the place where Dreams are made of. Visiting Disney World as a little kid as always been on of '+
                    'my favorites memories. Disney World is definitely one of my favorites places in the world. '+
                    '</div>'+
                    '</div>',  
        position: new google.maps.LatLng(37.820191, -122.478309),
        type: 'android'
        }, 
        {
        placeName: '<div id="content">'+
                    '<div id="siteNotice">'+
                    '</div>'+
                    '<h1 id="firstHeading" class="firstHeading">Hampton University, My home by the Sea</h1>'+
                    '<div id="bodyContent">'+
                    '<p><b>Disney World</b>, has been a resort I have always fallen in love with. ' +
                    'Established in 1965, Disney World has been known for its fun rides, the huge Cinderella Castle and '+
                    'the place where Dreams are made of. Visiting Disney World as a little kid as always been on of '+
                    'my favorites memories. Disney World is definitely one of my favorites places in the world. '+
                    '</div>'+
                    '</div>',  
        position: new google.maps.LatLng(37.022741, -76.334453),
        type: 'android'
        } 
    ];
    var counter = 0; 
     // Create markers.
    for (var i = 0; i < favoritePlaces.length; i++) {
        var contentString = favoritePlaces[i].placeName; 

        const marker = new google.maps.Marker({
            position: favoritePlaces[i].position,
            icon: icons[favoritePlaces[i].type].icon,
            map: map
        });

        const infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        marker.addListener('click', function() {
            counter++; 
            closeInfoWindow(); 
            infowindow.open(map, marker);
            InfoObj[0] = infowindow; 
            console.log("User has clicked " + counter); 
        });

    };
    //Refreshes info window to display for the current marker 
    function closeInfoWindow() {
        if (InfoObj.length > 0) {
            InfoObj[0].set("marker", null); 
            InfoObj[0].close(); 
            InfoObj[0].length = 0;
        }
    }
}

/**
 * Adds the object comment from the server 
 */
function getComment() {
    var value = document.getElementById("quantity").value; 
    fetch('/comment?num='+value).then(response => response.json()).then((comment) => {
        const parent = document.getElementById('comment-container');
        while(parent.firstChild) {
            parent.removeChild(parent.firstChild)
        }
        document.getElementById('comment-container').appendChild(createListElement(comment));  
        console.log(comment); 
    });
}


/**
 * Creates an <div> element containing data from CommentData object 
 * Create a node that is linked to the CommentData objects 
 */
function createListElement(comment) {
    const node = document.createElement("li");
    for (let i = 0; i < comment.length; i++) {
        const tempNode = document.createElement("ul"); 
        tempNode.innerText = comment[i].firstName + " " + comment[i].lastName 
                            + " ~ Relationship: " + comment[i].relation + " " 
                            + " ~ Known Kayla for " + comment[i].years + "\nComment: " 
                            +  comment[i].comment; 
        node.appendChild(tempNode);
    }
    return node; 
}
