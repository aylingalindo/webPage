 /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function(){
    var posts;
    console.log("entra al console log del document ready");
    alert("Uno nuevooooooo");
    getRecentPosts();
});

function getRecentPosts(){
    console.log("entra a funcion getRecentPost, antes de ajax");
    $("#postsPerfil").empty();
    $.ajax({
          url: "postServlet?action=profile"
        , type: "GET"
        , dataType: "JSON"
        , success: function(data){
            console.log("entra a succes");
            console.log("data", data);
            posts = data;
            alert("nombre:" + data[0].postUserFirstname + " " + data[0].postUserpLastname);
            /*for(var i=0; i<Object.keys(data).length; i++){
                console.log("Post desde USER PROFILE ", data[i]);
                $("#postsPerfil").empty();
            }*/
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

