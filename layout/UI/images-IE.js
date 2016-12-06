
// function to display rondom images from json file in the background with container3 position absolute
for(i=0; i<3; i++){
$(document).ready(function () {
        var value={};
$.getJSON('movielist.json', function(data) { //this will read object data from json file
     value=data;
        var img;
        var k = value.length;//get the leangth of object
       for(i=0;i<k; i++){
      var index = Math.floor(Math.random() * k);//randomly generate theh number for index

var immg = value[index];
        
       var x = document.createElement("IMG");//create image element
    x.setAttribute("src", immg.Poster);//get the poster url and assign it to the src attribute
    x.setAttribute("id", "rel1");
    
    x.setAttribute("alt", "not found");
    document.getElementById("container3").appendChild(x);//append everything to container3
            
       }
    });});

}

//Ends here

// using this function to display only one movie box as if now. More can be genrtaed by angular ng-repeat directive.
 var value={};
$.getJSON('movielist.json', function(data) { 
     value=data;

      var htmlText = '';
                  // generating tags and reading details from json file for on file only as if now more can be done by angular
                htmlText += '<p id="box-p"> Title: ' + value[0].Title + '</p>';
                 htmlText += '<p id="box-p"> Year: ' + value[0].Year + '</p>';
                  htmlText += '<p id="box-p"> Rated: ' + value[0].Rated + '</p>';
                   htmlText += '<p id="box-p"> Released: ' + value[0].Released + '</p>';
                    htmlText += '<p id="box-p"> Runtime: ' + value[0].Runtime + '</p>';
                     htmlText += '<p id="box-p"> Genre: ' + value[0].Genre + '</p>';
                      htmlText += '<p id="box-p"> Director: ' + value[0].Director + '</p>';
                      htmlText += '<p id="box-p"> Writer: ' + value[0].Writer + '</p>';
                      htmlText += '<p id="box-p"> Actors: ' + value[0].Actors + '</p>';
                      htmlText += '<p id="box-p"> Plot: ' + value[0].Plot + '</p>';
                      htmlText += '<p id="box-p"> Language: ' + value[0].Language + '</p>';
                      htmlText += '<p id="box-p"> Awards: ' + value[0].Awards + '</p>';
                     document.getElementById("mySidenav").style.backgroundImage = "url('" + value[0].Poster + "')";
                     //as IE dosent support background-blend mode the above line will just show the poster in background of more details
                      htmlText += '<p id="box-p"> Metascore: ' + value[0].Metascore + '</p>';
                      htmlText += '<p id="box-p"> Ratings: ' + value[0].imdbRating + '</p>';
                      htmlText += '<p id="box-p"> Votes: ' + value[0].imdbVotes + '</p>';
                      htmlText += '<p id="box-p"> Type: ' + value[0].Type + '</p>';
                      $('#mySidenav').append(htmlText); //this will append to the html body all the tags for more detail
     
     document.getElementById("box-year").innerHTML = value[0].Year;//parse and assign the value to the box element
     document.getElementById("box-title").innerHTML = value[0].Title;
  document.getElementById("box-img").src = value[0].Poster;
     
     
    

 }); 



//function that is generating only one box as if now but can genrta ell movie boxes by angular directives
function boxview(){
document.getElementById("box").style.display = 'block'; 
}


//function for login/signup page displaying tabs onclick

function opentab(evt, tabselected) {
    var i, x, y;
    x = document.getElementsByClassName("menu");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    y = document.getElementsByClassName("x");
    for (i = 0; i < y.length; i++) {
        y[i].className = y[i].className.replace(" active", "");
    }
    document.getElementById(tabselected).style.display = "block";
    evt.currentTarget.className += " active";
}

//function for main page for more details
function openNav() {
    document.getElementById("mySidenav").style.width = "100%";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";}