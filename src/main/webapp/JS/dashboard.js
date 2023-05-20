/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function(){
    var posts;
    console.log("entra al console log del document ready");
    getPagination();
    getRecentPosts();
});

//$('.numPage').on('click', getPagination());

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
                /*$("#posts").append(
                    $("<div>").addClass("card contentItem").append(
                        $("<div>").addClass("card-header").append(
                            $("<img>").attr("src", data[i].postUserPfp).addClass("img-fluid rounded-circle pfpNewpost")
                            .append(data[i].postUserFirstname+ " " + data[i].postUserpLastname)).append(
                        $("<div>").addClass("card-header").append(
                            $("<h5>").text(data[i].title)
                            .append($("<p>").text(data[i].description))).append(
                        $("<div>").addClass("card-footer").append(
                            $("<p>").append("<i>").addClass("icon ion-md-heart pe-2").text("0"))
                        ))
                    )
                );*/
                $("#posts").append(
                    $("<div>").addClass("card contentItem").append(
                        $("<div>").addClass("card-header").append(
                            $("<img>").attr("src", data[i].postUserPfp).addClass("img-fluid rounded-circle pfpNewpost")).append($("</img>")).append(
                            data[i].postUserFirstname+ " " + data[i].postUserpLastname).append(
                        $("</div>")).append(
                        $("<div>").addClass("card-body").append(
                            $("<h5>").text(data[i].title).append($("</h5>")).append(
                            $("<p>").text(data[i].description)).append($("</p>")).append(
                            $("<img>").attr("src", data[i].media).addClass("img-fluid postImg"))).append(
                        $("</div>"))).append(
                        $("<div>").addClass("card-footer").append(
                            $("<p>").append("<i>").addClass("icon ion-md-heart pe-2").append($("</i>")).text("0")).append($("</p>")).append(
                        $("</div>"))).append(
                    $("</div>")))
                );
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

function getPagination(){
    console.log("Entro a getPagination");
    $.ajax({
          url: "postServlet?action=totalPages"
        , type: "GET"
        , dataType: "text"
        , success: function(data){
            console.log("got into getPagination");
            var totalPages = parseInt(data);
            totalPages = data;
            console.log("total pages: " + totalPages + "in Page: " + i );
            var i = 0;
            do{
                i++;
                console.log("total pages: " + totalPages + "in Page: " + i );
                $("#pages").append('<span class="numPage">' + i + '</span>');
            }while(i < totalPages)
        },
        error: function(xhr, data, error) {
            console.log(xhr.responseText);
            console.log(xhr.statusText);
            console.log(data);
            console.log(error);
            console.log("error en cosultPagination");
        }
    });
}