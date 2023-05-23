 /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var posts;

$(document).ready(function(){
    getRecentPosts();
});

function getRecentPosts(){
    console.log("entra a funcion getRecentPost, antes de ajax");  
    $("postsPerfil").empty()
    $.ajax({
          url: "postServlet?action=profile"
        , type: "GET"
        , dataType: "JSON"
        , success: function(data){
            console.log("entra a succes");
            console.log("data", data);
            posts = data;
            for(var i=0; i<Object.keys(data).length; i++){
               /* $("#postsPerfil").append(
                    $("<div>").addClass("card contentItem").append(
                    $("<form>").addClass("needs-validation row g-3").attr("action","profileServlet").attr("method","post").attr("novalidate", "true").append(
                        $("<div>").addClass("card-header").append(
                        $("<div>").addClass("flex-row d-flex").append(
                                $("<div>").addClass("col me-auto").append(
                                    $("<img>").attr("src", data[i].postUserPfp).addClass("img-fluid rounded-circle pfpNewpost").append($("</img>"))
                                    .append(data[i].postUserFirstname + " " + data[i].postUserpLastname)).append(
                                $("</div>"))).append(
                                $("<div>").addClass("col-2 ps-5 ms-5").append(
                                    $("<button>").attr("data-modal-target", "#popupEditPost").attr("type", "submit").addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-create px-0 m-0").append($("</i>"))).append(
                                    $("</button>"))).append(
                                    $("<button>").attr("data-modal-target", "#popupDeletePost").addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-close px-0 m-0").append($("</i>"))).append(
                                    $("</button>"))).append(
                                $("</div>")).append(
                            $("</div>"))).append(
                        $("</div>")))).append(
                        $("<div>").addClass("card-body").append(
                            $("<input>").attr("value",data[i].title).attr("name", "titleEdit").addClass("h5").attr("disabled", "true")).append(
                            $("<input>").attr("hidden", "true").attr("name", "postIdEdit").attr("value", data[i].idPost)).append(
                            $("<input>").attr("value",data[i].description).attr("name", "descriptionEdit").attr("disabled", "true")).append(
                            $("<img>").attr("src", data[i].media).addClass("img-fluid postImg")).append(
                            $("<input>").attr("hidden", "true").attr("name", "imgUrlEdit").attr("value", data[i].media)).append(
                            $("<input>").attr("hidden", "true").attr("name", "catEdit").attr("value", data[i].idCat)).append(
                            $("<input>").attr("hidden", "true").attr("name", "hiddenOpc").attr("value", 2)).append(
                        $("</div>"))).append(
                        $("<div>").addClass("card-footer").append(
                            $("<p>").append("<i>").addClass("icon ion-md-heart pe-2").append($("</i>")).text("0")).append($("</p>")).append(
                        $("</div>")))).append(
                    $("</div>"))
<<<<<<< Updated upstream
                );
=======
                );*/
                
                 $("#postsPerfil").append(
                    $("<div>").addClass("card contentItem").append(
                        $("<div>").addClass("card-header").append(
                        $("<div>").addClass("flex-row d-flex").append(
                                $("<div>").addClass("col me-auto").append(
                                    $("<img>").attr("src", data[i].postUserPfp).addClass("img-fluid rounded-circle pfpNewpost")).append($("</img>"))
                                    .append(data[i].postUserFirstname + " " + data[i].postUserpLastname).append(
                                $("</div>"))).append(
                                $("<div>").addClass("col-2 ps-5 ms-5").append(
                                    $("<form>").addClass("needs-validation row g-3").attr("action","postServlet").attr("method","get").attr("novalidate", "true").append(
                                    $("<button>").attr("data-modal-target", "#popupEditPost").attr('onclick', 'popupEditPost()').attr("type", "submit").addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-create px-0 m-0").append($("</i>"))).append(
                                    $("</button>")).append(
                                    $("<input>").attr("hidden", "true").attr("name", "postIdEdit").attr("value", data[i].idPost)).append(
                                    $("<input>").attr("hidden", "true").attr("name", "action").attr("value", "mod")).append(
                                    $("</form>"))).append(
                                    $("<button>").attr("data-modal-target", "#popupDeletePost").attr("type", "submit").attr('onclick', 'openDeleteModal()').addClass("closeBtn ps-5").append(
                                        $("<i>").addClass("icon ion-md-close px-0 m-0").append($("</i>"))).append(
                                    $("</button>"))).append(
                                $("</div>"))).append(
                            $("</div>"))).append(
                        $("</div>")))).append(
                        $("<div>").addClass("card-body").append(
                            $("<h5>").text(data[i].title).append($("</h5>")).append(
                            $("<p>").text(data[i].description)).append($("</p>")).append(
                            $("<img>").attr("src", data[i].media).addClass("img-fluid postImg"))).append(
                        $("</div>"))).append(
                        $("<div>").addClass("card-footer").append(
                            $("<p>").append("<i>").addClass("icon ion-md-heart pe-2").append($("</i>")).text("0")).append($("</p>")).append(
                        $("</div>"))).append(
                    $("</div>"))
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

