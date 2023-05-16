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
          url: "profileServlet?action=recents"
        , type: "GET"
        , dataType: "JSON"
        , success: function(data){
            console.log("entra a succes");
            console.log("data", data);
            posts = data;
            alert("nombre:" + data[0].postUserFirstname + " " + data[0].postUserpLastname);
            for(var i=0; i<Object.keys(data).length; i++){
                console.log("POST ", data[i]);
                $("#posts").append(
                    $("<div>").addClass("card contentItem").append(
                        $("<form>").addClass("needs-validation row g-3").attr("action","profileServlet").attr("method","post").attr("novalidate").append(
                        $("<div>").addClass("card-header").append(
                            $("<div>").addClass("flex-row d-flex").append(
                                $("<div>").addClass("col me-auto").append(
                                    $("<img>").attr("src", data[i].postUserPfp).addClass("img-fluid rounded-circle pfpNewpost")
                                    .append(data[i].postUserFirstname + " " + data[i].postUserpLastname)).append(
                                $("</div>")).append(
                                $("<div>").addClass("col-2 ps-5 ms-5").append(
                                    $("<button>").attr("data-modal-target", "#popupEditPost").type("submit").addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-create px-0 m-0").append($("</i>"))).append(
                                    $("</button>")).append(
                                    $("<button>").attr("data-modal-target", "#popupDeletePost").type("button").addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-close px-0 m-0").append($("</i>"))).append(
                                    $("</button>"))).append(
                                $("</div>")))).append(
                            $("</div>"))).append(
                        $("</div>"))).append(
                        $("<div>").addClass("card-body").append(
                            $("<input>").attr("value",data[i].title).attr("name", "titleEdit").addClass("h5").attr("disabled").append(
                            $("<input>").attr("hidden").attr("name", "postIdEdit").attr("value", data[i].idPost)).append(
                            $("<input>").attr("value",data[i].description).attr("name", "descriptionEdit").attr("disabled")).append(
                            $("<img>").attr("src", data[i].media).addClass("img-fluid postImg").append(
                            $("<input>").attr("hidden").attr("name", "imgUrlEdit").attr("value", data[i].media)))).append(
                            $("<input>").attr("hidden").attr("name", "catEdit").attr("value", data[i].idCat)).append(
                        $("</div>"))).append(
                        $("<div>").addClass("card-footer").append(
                            $("<p>").append("<i>").addClass("icon ion-md-heart pe-2").append($("</i>")).text("0")).append($("</p>")).append(
                        $("</div>"))).append(
                    $("</form>"))).append(
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

