/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var pageClick = false;
var posts;
var postSearch;
var totalPages;
var isSearching = false;

$(document).ready(function(){    
    console.log("entra al console log del document ready"); 
    var profileSearch = getURLParameter("search");
    var profileCategory = getURLParameter("category");
    var profileInitialDate = getURLParameter("initiDate");
    var profileFinalDate = getURLParameter("finalDate");


    if(profileSearch !== "" && profileSearch !== null && ((profileCategory !== "" && profileCategory !== null) ||
            (profileInitialDate !== "" && profileInitialDate !== null) || (profileFinalDate !== "" && profileFinalDate !== null))){

        advancedSearchUI(profileSearch, profileCategory, profileInitialDate, profileFinalDate);
        return;
    }
    if (profileSearch !== "" && profileSearch !== null) {
      alert("entramos al diferente de search nada + " + profileSearch);
      $("#posts").empty();
      $("#pages").empty();
      searchUI(profileSearch);
      return;
    }
    
    getPagination();
    if(!pageClick){
            getRecentPosts(1);
    }
    dashUI();
    
    $('#search').submit(function(event){
        event.preventDefault();
        alert("vamos bien");
        var wordSearched = $('#wordSearch').val();
        searchUI(wordSearched);
    });
    
    $('#advancedSearch').submit(function(event){
        event.preventDefault();
        var wordSearched = $('#wordSearch').val();
        var category = $('input[name="categ"]:checked').val();
        var initDate = $('#initialDate').val();
        var finDate = $('#finalDate').val();        
        alert("advanced search check " + wordSearched + " " + category + " " + initDate + " " + finDate);

        advancedSearchUI(wordSearched, category, initDate, finDate);
    });
    
    
    
    $('.dropdown-item').click(function(){
        var category = $(this).data('category');
        alert("category from dropdown is " + category); 
        filterCategory(category);        
    });
});

$('#pages').on('click', 'span', function(){
    console.log("CLICK ON NUMPAGE");
    pageClick = true;
    var currentPage = $(this).text();
    getRecentPosts(currentPage);
});

function getURLParameter(parametro) {
    var url = new URL(window.location.href);
    return url.searchParams.get(parametro);
  }

/*$('#search').on('click', 'span', function(){
    event.preventDefault();
    alert("click en search");
    //searchUI();
});*/

function backToHome(){
    isSearching = false;
    getPagination();
    if(!pageClick){
            console.log("FROM THE home button");
            getRecentPosts(1);
    }
    dashUI();
    
}

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
    $("#posts").empty();
    $("#pages").empty();
    //getRecentPosts(1);
    //getPagination();
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

function searchUI(wordSearched){
    console.log("Dentro de searchUI");
    $("#posts").empty();
    $("#pages").empty();
    isSearching = true;
    alert("searchUI, is searching is " + isSearching);
    $("#dashSections").empty();
    $("#dashSections").append(
            '<div class="col sortingItem active ">' + 
                '<a href="#" class="nav-link subTitle">Search post</a> ' + 
                '</div>' + 
                '<div id="home" class="col sortingItem">' +
              '<a href="javascript:backToHome()" class="nav-link subTitle">Back to home</a>' +
            '</div>');
    
    $.ajax({
         url: "dashboardServlet?action=search"
        ,type: "GET"
        ,data: { search: wordSearched}
        ,success: function(data){
            var postResult = JSON.parse(data);
            
            for(var key in postResult){
                if(postResult.hasOwnProperty(key)){
                    var post = postResult[key];
                    var title = post.title;
                    var description = post.description;
                    var username = "" + post.postUserFirstname + " " + post.postUserpLastname;
                    var profileImg = post.postUserPfp;
                   // console.log("Recieved TITLE correctly: " + title);
                   // console.log("Recieved DESCRIPTION correctly: " + description);
                   // console.log("Recieved USERNAME correctly: " + username);
                   console.log("Recieved PROFIMG correctly: " + profileImg);
                   $("#posts").append(
                        $("<div>").addClass("card contentItem")
                          .append($("<div>").addClass("card-header")
                            .append($("<img>").attr("src", profileImg).addClass("img-fluid rounded-circle pfpNewpost")
                              .text(username)
                            )
                          )
                          .append($("<div>").addClass("card-body")
                            .append($("<h5>").text(title))
                            .append($("<p>").text(description))
                          )
                          .append($("<div>").addClass("card-footer").append("<p>").append("<i>").addClass("icon ion-md-heart pe-2").text("0"))
                          
                      );
                    
                        
                }
            }
            /*for(var i=0; i<Object.keys(response).length; i++){
                console.log("POST DE BUSQUEDA " );  
                $("#posts").append(
                        $("<h5>").text(response[i]. title).append("<h5>")
                );
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
    
 function advancedSearchUI(wordSearched, category, initialdate, finaldate){
    console.log("Dentro de advancedSearchUI");
    $("#posts").empty();
    $("#pages").empty();
    isSearching = true;
    alert("advancedSearchUI, is searching is " + isSearching);
    $("#dashSections").empty();
    $("#dashSections").append(
            '<div class="col sortingItem active ">' + 
                '<a href="#" class="nav-link subTitle">Search post</a> ' + 
                '</div>' + 
                '<div id="home" class="col sortingItem">' +
              '<a href="javascript:backToHome()" class="nav-link subTitle">Back to home</a>' +
            '</div>');
    
    $.ajax({
         url: "dashboardServlet?action=advanced"
        ,type: "GET"
        ,data: { search: wordSearched
                ,cat: category
                ,initialDate: initialdate
                ,finalDate: finaldate}
        ,success: function(data){
            var postResult = JSON.parse(data);
            
            for(var key in postResult){
                if(postResult.hasOwnProperty(key)){
                    var post = postResult[key];
                    var title = post.title;
                    var description = post.description;
                    var username = "" + post.postUserFirstname + " " + post.postUserpLastname;
                    var profileImg = post.postUserPfp;
                   // console.log("Recieved TITLE correctly: " + title);
                   // console.log("Recieved DESCRIPTION correctly: " + description);
                   // console.log("Recieved USERNAME correctly: " + username);
                   console.log("Recieved PROFIMG correctly: " + profileImg);
                   $("#posts").append(
                        $("<div>").addClass("card contentItem")
                          .append($("<div>").addClass("card-header")
                            .append($("<img>").attr("src", profileImg).addClass("img-fluid rounded-circle pfpNewpost")
                              .text(username)
                            )
                          )
                          .append($("<div>").addClass("card-body")
                            .append($("<h5>").text(title))
                            .append($("<p>").text(description))
                          )
                          .append($("<div>").addClass("card-footer").append("<p>").append("<i>").addClass("icon ion-md-heart pe-2").text("0"))
                          
                      );
                    
                        
                }
            }
            /*for(var i=0; i<Object.keys(response).length; i++){
                console.log("POST DE BUSQUEDA " );  
                $("#posts").append(
                        $("<h5>").text(response[i]. title).append("<h5>")
                );
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

function filterCategory(category){
    $("#posts").empty();
    $.ajax({
          url: "postServlet?action=category"
        , type: "GET"
        , data: {
            cat: category
          }
        , dataType: "JSON"
        , success: function(data){
            if(category === "all"){
                getRecentPosts(1);
                return;
            }
            console.log("entra a succes - filter category");
            console.log("filter category - data", data);
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