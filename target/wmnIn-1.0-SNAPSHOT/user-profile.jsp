<%-- 
    Document   : user-profile
    Created on : Mar 21, 2023, 10:05:39 PM
    Author     : Aylin
--%>

<%@page import="modelos.entidades.Publicacion"%>
<%@page import="modelos.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    
    <!-- link de los icons q vaya a usar y font-->
    <link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital@1&family=Roboto:wght@100&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="CSS/style.css"/>
    
    <title>wm.In</title>
</head>
<body>
  <div class="d-flex"> 
    
    <%
        Usuario usuarioLogin = new Usuario();
        usuarioLogin = (Usuario)request.getAttribute("usuario");
        System.out.println("Usuario dashboard "+ usuarioLogin.getUsername());
        String ocupacion = usuarioLogin.getOccupation();
        System.out.println("Ocupacion: "+ ocupacion);
        String nombreDisplay = usuarioLogin.getFirstname() + " " + usuarioLogin.getpLastname();
        String profileImg = usuarioLogin.getProfileImg();
        System.out.println("img dashboard "+ profileImg);
    %> 
    
    <div id="overlay"></div>
    
    <!-- NAV BAR-->
    <div id="sidebar" class="bg-primary">

        <!--logo-->
        <h4 class="logo">wm.In</h4>  <!-- no se pone bold??? -->

        <!--search bar-->
        <form id="searchProfile" class="d-flex position-relative p-3 d-inline-block" role="search">
          <input name="search" id="wordS" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn searchNavbar position-absolute" type="submit"><i class="icon ion-md-search"></i></button>
        </form>
        <button data-modal-target="#popupAdvancedSearch" type="button" class="btn btn-primary ms-4 advancedSearchBtn">Advanced Search</button>

        <!--elementos para seleccionar-->
        <div class="menu d-flex flex-column justify-content-evenly mb-5 pb-5">

          <!-- Info User-->
          <div class="nav-item pb-3 userInfo">
                <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfp mb-2">
            <a class="nav-link mb-2">
                 <% out.print(nombreDisplay); %>
            </a>    <!-- dblock para que cada uno este en una linea y p-3 para separalos-->
            <p class="mb-2">
                <% out.print(usuarioLogin.getOccupation()); %>
            </p>
          </div>

          <!-- Links -->
          <div class="container px-5 pb-3">
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-notifications lead align-self-center"></i>
              </div>
              <div class="col-8">
                <a href="#" class="nav-link">Notifications</a> 
              </div>
            </div>
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-mail lead"></i>
              </div>
              <div class="col-8">
                <a href="#" class="nav-link">Messages</a>
              </div>
            </div>
            <form action="dashboardServlet?action=dashboard" method="get" class="needs-validation" novalidate>
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-home lead "></i>
              </div>
              <div class="col-8">
                  <input href="dashboard.jsp" type="submit" class="nav-link closeBtn" value="Home"/>
              </div>
            </div>
            </form>
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-exit"></i>
              </div>
              <div class="col-8">
                <a href="index.jsp" class="nav-link">Log out</a>
              </div>
            </div>
          </div>

        </div>
    </div>

    <!-- CONTENT -->
    <div id="content">
        
      <!-- POP UP ADVANCED SEARCH-->
      <div id="popupAdvancedSearch" class="card">

        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row">
            <div class="col-10 ms-5 me-auto mt-3">
              <h4>Advanced Search</h4>
            </div>
            <div class="col pt-3">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>

        <!-- contenido que va a cambiar -->
        <div class="card-body">
            <form id="advancedSearchProfile" class="needs-validation" novalidate>
          <div class="row mx-3">
            <label class="form-label">Date range</label>
          </div>
          <div class="row mx-3 mb-4">
            <div class="col ms-5">
              <input id="initialDateProf" type="date" class="form-control" name="fechaInicio" value=""/>
            </div>
            <div class="col fs-3 ps-5 pe-0">
              <label class="form-label">---</label>
            </div>
            <div class="col me-5">
            <input id="finalDateProf" type="date" class="form-control" name="fechaFin" value=""/>
            </div>
          </div>
          <div class="row mx-3">
            <label class="form-label">Category</label>
          </div>
          <div class="row mx-3 mb-4 newpostContainer">
            <div class="col mt-2">
              <i class="icon ion-md-pricetags ms-2 mt-2"></i>
            </div>
            <div class="col-11 categories">
              <input name="cat" type="radio" class="cat" id="c1" value="Science"/>
              <label for="c1">Science</label>
              <input name="cat" type="radio" class="cat" id="c2" value ="Tech"/>
              <label for="c2">Tech</label>
              <input name="cat" type="radio" class="cat" id="c3" value="Art"/>
              <label for="c3">Art</label>
              <input name="cat" type="radio" class="cat" id="c4" value="Design"/>
              <label for="c4">Design</label>
              <input name="cat" type="radio" class="cat" id="c5" value="Business"/>
              <label for="c5">Business</label>
              <input name="cat" type="radio" class="cat" id="c6" value="Psychology"/>
              <label for="c6">Psychology</label>
              <input name="cat" type="radio" class="cat" id="c7" value="Medicine"/>
              <label for="c7">Medicine</label>
              <input name="cat" type="radio" class="cat" id="c8" value="Human arts"/>
              <label for="c8">Human Arts</label>
            </div>
          </div>
          <div class="row mx-3 my-4">
              <button type="submit" class="btn btn-primary signUpBtn">Search</button>
          </div>
          </form>
        </div>

      </div>

      <!-- POP UP EDITAR INFO PERFIL -->
      <div id="popupEditInfo" class="card">

        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row">
            <div class="col-10 ms-3 me-auto mt-3">
              <h4>Edit Info</h4>
            </div>
            <div class="col pt-3">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>
        <!-- contenido que va a cambiar -->
        <div class="card-body d-flex">
          <div id="carouselExample" class="carousel slide flex-fill">
            <div class="carousel-inner mx-3 mb-5">
                
             <form action="profileServlet" method="post" id="formEditInfo" class="needs-validation" novalidate>
              <!-- form de la pagina 1 :D-->
              <div class="carousel-item active">
                  <div class="col-12">
                    <label for="validationName" class="form-label">First name(s)</label>
                    <input type="text" class="form-control" id="validationName" name="validationName" value="<% out.print(usuarioLogin.getFirstname()); %>" required>
                    <div class="invalid-feedback">
                      Please fill with letters. 
                    </div>
                  </div>
                  <div class="col-6">
                    <label for="validationFirstLN" class="form-label">First last name</label>
                    <input type="text" class="form-control" id="validationFirstLN" name="validationFirstLN" value="<% out.print(usuarioLogin.getpLastname()); %>" required>
                    <div class="invalid-feedback">
                      Please fill with letters. 
                    </div>
                  </div>
                  <div class="col-6">
                    <label for="validationSecondLN" class="form-label">Second last name</label>
                    <div class="input-group has-validation">
                      <input type="text" class="form-control" id="validationSecondLN" name="validationSecondLN" value="<% out.print(usuarioLogin.getmLastname()); %>" required>
                      <div class="invalid-feedback">
                        Please fill with letters.
                      </div>
                    </div>
                  </div>
              </div>

              <!-- form de la pagina 2 :D -->
              <div class="carousel-item">
                  <div class="row g-3 align-content-start justify-content-center flex-fill ms-3">
                    <div class="col-12">
                      <label class="form-label">Choose a profile picture</label>
                    </div>
                    <div class="col-12 pfpRegistro">
                        <img src="<% out.print(usuarioLogin.getProfileImg()); %>" id="pfpUserProfileEdit" class="img-fluid rounded-circle">
                    </div>
                    <div class="col-12 mb-3">
                      <input type="url" class="form-control" name="profileImgEdit" id="profileImgEdit" value="<% out.print(usuarioLogin.getProfileImg()); %>" onchange="showImageEdit()" required>
                      <div class="invalid-feedback">Example invalid form file feedback</div>
                    </div>
                    <div class="col-12">
                      <label class="form-label">Choose a background image</label>
                    </div>
                    <div class="col-12 mb-3">
                      <% 
                        String portada = "";
                        if (usuarioLogin.getCoverImg() != "null" | usuarioLogin.getCoverImg() != ""){
                            portada = usuarioLogin.getCoverImg();
                        } 
                      %>
                      <input type="url" class="form-control" name="backgroundImgEdit" id="profileImgEdit" value="<% out.print(portada); %>">
                      <div class="invalid-feedback">Example invalid form file feedback</div>
                    </div>
                  </div>
              </div>

              <!-- form de la pagina 3 :DD -->
              <div class="carousel-item">
                  <div class="col-12">
                    <label for="validationUsername" class="form-label">Change username</label>
                    <input type="text" class="form-control" id="validationUsername" name="validationUsername" value="<% out.print(usuarioLogin.getUsername()); %>" required>
                    <div class="valid-feedback">
                      Looks good!
                    </div>
                  </div>
                  <div class="col-12">
                    <label for="validationPassword" class="form-label">Change password</label>
                    <div class="input-group has-validation">
                      <input type="password" class="form-control" id="validationPassword" name="validationPassword" aria-describedby="inputGroupPrepend" value="<% out.print(usuarioLogin.getPassword()); %>" required>
                      <div class="invalid-feedback">
                        Invalid passowrd.
                      </div>
                    </div>
                  </div>
                  <div class="col-12">
                    <label for="validationConfirmPass" class="form-label">Confirm password</label>
                    <div class="input-group has-validation">
                      <input type="password" class="form-control" id="validationConfirmPass" name="validationConfirmPass" aria-describedby="inputGroupPrepend" value="" required>
                      <div class="invalid-feedback">
                        Invalid passowrd.
                      </div>
                    </div>
                  </div>
                  <div class="col-12">
                      <input hidden name="hiddenOpc" value="1"/>
                    <input type="submit" class="btn btn-primary signInBtn" value="Confirm"/>
                  </div>
              </div>
             </form>
            </div>
            <div class="carousel-indicators mb-0">
              <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
          </div>
        </div>
      </div>

      <!-- POP UP EDITAR ABOUT -->
      <div id="popupEditAbout" class="card">
        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row">
            <div class="col-10 ms-3 mt-3">
              <h4><% out.print("@"+usuarioLogin.getUsername()); %></h4>
            </div>
            <div class="col ms-2 pt-3">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>
            
        <!-- form para editar información-->
        <div class="card-body d-flex">
          <form action="profileServlet" method="post" class="needs-validation row g-3" novalidate>
              <div class="col-12">
                <label class="form-label">Ocupation</label>
                <input type="text" class="form-control" id="OcupationAbout" name="OcupationAbout" value="<% out.print(usuarioLogin.getOccupation()); %>" required>
              </div>
              <div class="col-12">
                  <div class="col-3">
                    <label class="form-label">City</label>
                    <input type="text" class="form-control" id="cityAbout" name="cityAbout" value="<% out.print(usuarioLogin.getCity()); %>">
                  </div>
                  <div class="col-3">
                    <label class="form-label">State</label>
                    <input type="text" class="form-control" id="stateAbout" name="stateAbout" value="<% out.print(usuarioLogin.getState()); %>">
                  </div>
                  <div class="col-3">
                    <label class="form-label">Country</label>
                    <input type="text" class="form-control" id="countryAbout" name="countryAbout" value="<% out.print(usuarioLogin.getState()); %>">
                  </div>
              </div>
              <div class="col-12">
                <label for="emailAbout" class="form-label">Email</label>
                <div class="input-group has-validation">
                  <input type="text" class="form-control" id="emailAbout" name="emailAbout" value="<% out.print(usuarioLogin.getEmail()); %>" required>
                  <div class="invalid-feedback">
                    Please write a valid email.
                  </div>
                </div>
              </div>
              <div class="col-12">
                <label for="fechaAbout" class="form-label">Date of birth</label>
                <div class="input-group has-validation">
                  <input type="date" class="form-control" name="fechaAbout" id="validationDate" value="<% out.print(usuarioLogin.getBirthdate()); %>" required>
                  <div class="invalid-feedback">
                  Please select a valid date.
                </div>
                </div>
              </div>
              <div class="col-12">
                  <input hidden name="hiddenOpc" value="2"/>
                <button type="submit" class="btn btn-primary signInBtn">Save About Info</button>
              </div>
          </form>
        </div>
      </div>
      
      <% 
            Publicacion publi  = usuarioLogin.getCurrent_post();
            System.out.println(" ID DEL POST: " + publi.getId_post());
            if (publi.getId_post() != 0) {   
            int modal = (int)request.getAttribute("modal");
                if(modal == 1){
      %>
      <!-- POP UP EDIT POST-->
      <div id="overlay2" class="active"></div>
      <div id="popupEditPost" class="card active">

        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row pt-3 mx-3">
            <div class="col-11 me-auto">
              <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfpNewpost">
              <% out.print(nombreDisplay); %>
            </div>
            <div class="col">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>

        <!-- contenido que va a cambiar -->
        <div class="card-body">
        <form action="postServlet" method="post" id="formEdit" class="needs-validation" novalidate>
          <div id="postTitle" class="row mx-3 mb-4">
            <input class="form-control form-control-lg" name="titleEdit" type="text" value="<% out.print(publi.getTitle()); %>">
          </div>
          <div class="row mx-3 mb-4">
            <input class="form-control" name="descEdit" value="<% out.print(publi.getDescription()); %>" rows="4">
          </div>
          <div class="row mx-3 my-4 newpostContainer">
              <div class="col mt-2">
              <i class="icon ion-md-photos ms-2"></i>
            </div>
            <div class="col-11">
              <input type="url" class="form-control" name="mediaEdit" id="mediaEdit" value="<% out.print(publi.getMedia()); %>">
              <input hidden="true" name="opcPost" id="mediaEdit" value="1">
            </div>
              <!--<button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-photos"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-play"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-attach"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-pin"></i></button>-->
          </div>
          <!--
          <div class="row mx-3 my-4 newpostContainer">
            <div class="col mt-2">
              <i class="icon ion-md-pricetags ms-2 mt-2"></i>
            </div>
            
            <div class="col-11 categories">
              <input name="cat" type="radio" class="cat" id="c1" value="1"/>
              <label for="c1">Science</label>
              <input name="cat" type="radio" class="cat" id="c2" value ="2"/>
              <label for="c2">Tech</label>
              <input name="cat" type="radio" class="cat" id="c3" value="3"/>
              <label for="c3">Art</label>
              <input name="cat" type="radio" class="cat" id="c4" value="4"/>
              <label for="c4">Design</label>
              <input name="cat" type="radio" class="cat" id="c5" value="5"/>
              <label for="c5">Business</label>
              <input name="cat" type="radio" class="cat" id="c6" value="6"/>
              <label for="c6">Psychology</label>
              <input name="cat" type="radio" class="cat" id="c7" value="7"/>
              <label for="c7">Medicine</label>
              <input name="cat" type="radio" class="cat" id="c8" value="8"/>
              <label for="c8">Human Arts</label>
            </div>
          </div>-->
          <div class="row mx-3 my-4">
              <button type="submit" class="btn btn-primary signUpBtn">Post</button>
          </div>
        </form>
        </div>

      </div>
      
      <%
                }
                if(modal == 2){
      %>
      
      <!-- POP UP CONFIRM DELETE POST -->
      <div id="popupDeletePost" class="card active">
        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <h4>Confirm delete</h4>
        </div>
        <!-- form para editar información-->
        <div class="card-body d-flex flex-column">
          <div class="row">
            <h6 class="m-5">Are you sure you want to delete this post?</h6>
          </div>
          <div class="row">
            <div class="col-6">
              <form action="postServlet" method="post" id="formDelete" class="needs-validation" novalidate>
              <button type="submit" class="btn btn-primary signUpBtn">Delete</button>
              <input hidden="true" name="opcPost" value="2">
              </form>
            </div>
            <div class="col-6">
              <button data-close-button type="submit" class="btn btn-primary signInBtn">Cancel</button>
            </div>
          </div>
        </div>
      </div>
      
      <%
                }
            }
      %>
      <!-- PERFIL -->
      <section class="border-bottom">
        <div class="container contentItem headerPerfil">
          <div class="portadaPerfil">
            <img class="img-fluid" src="<% out.print(usuarioLogin.getCoverImg()); %>">
          </div>
          <div class="d-flex ms-5">
            <div class="pfpDiv align-self-baseline">
              <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfpPerfil">
            </div>
            <div class="flex-column ms-3 me-auto">
              <div class="row">
                <h2 class="m-0"><% out.print(nombreDisplay); %></h2>
              </div>
              <div class="row">
                <h6>128 community friends</h6>
              </div>
            </div>
            <button data-modal-target="#popupEditInfo" type="button" class="btn btn-primary signUpBtn m-4">
              <i class="icon ion-md-create"></i>
              <i class="icon ion-md-add invisible"></i>
            </button>
          </div>
        </div>
      </section>

      <!-- DATOS -->
      <section>

        <!-- ABOUT EL USUARIO-->
        <div class="card contentItem">
          <div class="card-header">
            <div class="row">
              <div class="col-11">
                <h4>About</h4>
              </div>
              <div class="col ps-5 pe-0">
                <button data-modal-target="#popupEditAbout"  type="button" class="closeBtn px-0"><i class="icon ion-md-create px-0"></i></button>
              </div>
            </div>
          </div>
          <div class="card-body">

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-briefcase pe-2"></i>Occupation:</h6>
              </div>
              <div class="col">
                <p><% out.print(usuarioLogin.getOccupation()); %></p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-person pe-2"></i>User:</h6>
              </div>
              <div class="col">
                <p><% out.print("@"+usuarioLogin.getUsername()); %></p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-calendar pe-2"></i>Age:</h6>
              </div>
              <div class="col">
                <p>21 years</p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-at pe-2"></i>Email:</h6>
              </div>
              <div class="col">
                <p><% out.print(usuarioLogin.getEmail()); %></p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-home pe-2"></i>Location:</h6>
              </div>
              <div class="col">
                <p><% out.print(usuarioLogin.getCity() +", "+ usuarioLogin.getState() +", " + usuarioLogin.getCountry()); %></p>
              </div>
            </div>

          </div>
        </div>

        <!-- MY COMMUNITY / mis amigos -->
        <div class="card contentItem">
          <div class="card-header communitySorting">
            <div class="row">
              <h4>Community</h4> 
            </div>

            <!-- div de friend requests -->
            <div class="row">
              <div class="col sortingItem active">
                <a href="#" class="nav-link">My Community</a> 
              </div>
              <div class="col sortingItem">
                <a href="#" class="nav-link">Friend requests</a> 
              </div>
            </div>
          </div>

          <!-- friends users-->
          <div class="card-body">

            <div class="row m-3">
              <div class="col d-flex">
                <img src="assets/razor.jpeg" class="img-fluid pfpFriend">
                <h6 class="ms-3">Razor</h6>
              </div>
              <div class="col d-flex">
                <img src="assets/razor.jpeg" class="img-fluid pfpFriend">
                <h6 class="ms-3">Razor</h6>
              </div>
            </div>

            <div class="row m-3">
              <div class="col d-flex">
                <img src="assets/razor.jpeg" class="img-fluid pfpFriend">
                <h6 class="ms-3">Razor</h6>
              </div>
              <div class="col d-flex">
                <img src="assets/razor.jpeg" class="img-fluid pfpFriend">
                <h6 class="ms-3">Razor</h6>
              </div>
            </div>

          </div>
        </div>

      </section>
      
      <!-- post -->
      <div id="postsPerfil">
        <div class="card contentItem">
          <div class="card-header">
            <div class="flex-row d-flex">
              <div class="col me-auto">
                <img src="assets/fotoPerfil.jpeg" class="img-fluid rounded-circle pfpNewpost">
                Aylin Galindo
              </div>
              <div class="col-2 ps-5 ms-5">
                <button data-modal-target="#popupEditPost"  type="button" class="closeBtn ps-5"><i class="icon ion-md-create px-0 m-0"></i></button>
                <button data-modal-target="#popupDeletePost" type="button" class="closeBtn"><i class="icon ion-md-close px-0 m-0"></i></button>
              </div>
            </div>
          </div>
          <div class="card-body">
            <h5>Hello world</h5>
            <p> I like to hunt </p>
          </div>
          <div class="card-footer">
            <p><i class="icon ion-md-heart pe-2"></i>15</p>
          </div>
        </div>
      </div>

      
      <footer>
        <div class="row text-center">
          <div class="col-4">
            <p>Copyright © 2023 wm.In</p>
          </div>
        </div>
      </footer>

    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-scrollto/1.4.6/jquery-scrollto.min.js"></script>
  <script defer src="JS/funcionalidad.js"></script>
  <script defer src="JS/profile02.js"></script>
</body>
</html>
