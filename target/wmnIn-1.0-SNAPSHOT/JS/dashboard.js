/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function(){
    var posts;
    console.log("entra al console log del document ready");
    getRecentPosts();
});

function getRecentPosts(){
    console.log("entra a funcion getRecentPost, antes de ajax");    
    $.ajax({
          url: "postServlet?action=recents"
        , type: "GET"
        , dataType: "JSON"
        , success: function(data){
            console.log("entra a succes");
            console.log("data", data);
            posts = data;
            
            for(var i=0; i<Object.keys(data).length; i++){
                console.log("POST ", data[i]);
               $("#posts")
                .append( $("<div>").addClass("card contentItem")
                    .append($("<div>").addClass("card-header")
                        .append($("<img>").attr("src", data[i].media).addClass("img-fluid rounded-circle pfpNewpost")
                        .text(data[i].postUserdata)))
                    .append($("<div>").addClass("card-body")
                        .append($("<h5>")).text(data[i].title)
                        .append($("<p>")).text(data[i].description)
                        .append($("<img>")).attr("src", data[i].media).addClass("img-fluid postImg"))
                    .append($("<div>").addClass("card-footer")
                        .append($("<p>")
                            .append($("<i>")).addClass("icon ion-md-heart pe-2")).text("100"))); 
            }
        },
        error: function(xhr, data, error) {
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(data);
            console.log(error);
            console.log("error");
        }
    });
}