/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var pageClick = false;
var posts;
var totalPages;
var isSearching = false;

$(document).ready(function(){    
    console.log("entra al console log del document ready");
    if(!isSearching){
        //alert("from de doc ready");
        dashUI();
    }
    getPagination();
    if(!pageClick){
            console.log("FROM THE DOC READY");
            getRecentPosts(1);
    }
    
});

$('#pages').on('click', 'span', function(){
    console.log("CLICK ON NUMPAGE");
    pageClick = true;
    var currentPage = $(this).text();
    getRecentPosts(currentPage);
});

$('#search').on('click', 'span', function(){
    event.preventDefault();
    alert("click en search");
    //searchUI();
});

$('#home').on('click', 'a', function(){
    isSearching = false;
    dashUI();
});

function getRecentPosts(currentPage){
    console.log("entra a funcion getRecentPost, antes de ajax");
    $("#posts").empty();
    $.ajax({
          url: "postServlet?action=recents"
        , type: "GET"
        , data: {
            page: currentPage
          }
        , dataType: "JSON"
        , success: function(data){
            console.log("entra a succes");
            console.log("data", data);
            posts = data;
            for(var i=0; i<Object.keys(data).length; i++){
                console.log("POST ", data[i]);
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
            //console.log("got into getPagination");
            var totalPages = parseInt(data);
            totalPages = data;
            //console.log("total pages: " + totalPages + "in Page: " + i );
            var i = 0;
            do{
                i++;
                //console.log("total pages: " + totalPages + "in Page: " + i );
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

function dashUI(){
    console.log("Dentro de dashUI");
    isSearching = false;
    //alert("dashUI, is searching is " + isSearching);
    $("#dashSections").empty();
    $("#dashSections").append(
            '<div class="col sortingItem active">' + 
                '<a href="#" class="nav-link subTitle">Comunity</a> ' + 
                '</div>' + 
                '<div class="col sortingItem">' +
              '<a href="#" class="nav-link subTitle">Discover</a>' +
            '</div>');
}

function searchUI(){
    console.log("Dentro de searchUI");
    isSearching = true;
    alert("searchUI, is searching is " + isSearching);
    $("#dashSections").empty();
    $("#dashSections").append(
            '<div class="col sortingItem active ">' + 
                '<a href="#" class="nav-link subTitle">Search post</a> ' + 
                '</div>' + 
                '<div class="col sortingItem">' +
              '<a href="#" id="home" class="nav-link subTitle">Back to home</a>' +
            '</div>');
}
