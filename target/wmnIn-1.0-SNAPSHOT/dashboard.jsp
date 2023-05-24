<%-- 
    Document   : dashboard
    Created on : Mar 21, 2023, 10:04:41 PM
    Author     : Aylin
--%>

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
    <div id="overlay"></div>
    <!-- NAV BAR-->
    <div id="sidebar" class="bg-primary">

        <!--logo-->
        <h4 class="logo">wm.In</h4>  <!-- no se pone bold??? -->

        <!--search bar-->
        
        <form id="search" class="d-flex position-relative p-3 d-inline-block" role="search">
          <input id="wordSearch" name="search" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn searchNavbar position-absolute" type="submit"><i class="icon ion-md-search"></i></button>
         </form>
        <button data-modal-target="#popupAdvancedSearch" type="button" class="btn btn-primary ms-4 advancedSearchBtn">Advanced Search</button>
       
 <%
     Usuario usuarioLogin = new Usuario();
     usuarioLogin = (Usuario)request.getAttribute("usuario");
     //System.out.println("Usuario dashboard "+ usuarioLogin.getUsername());
     String ocupacion = usuarioLogin.getOccupation();
     //System.out.println("Ocupacion: "+ ocupacion);
     String nombreDisplay = usuarioLogin.getFirstname() + " " + usuarioLogin.getpLastname();
     String profileImg = usuarioLogin.getProfileImg();
     //System.out.println("img dashboard "+ profileImg);
 %>              
        
        <!--elementos para seleccionar-->
        <div class="menu d-flex flex-column justify-content-evenly mb-5 pb-5">

          <!-- Info User-->
          <form action="profileServlet" method="get" class="needs-validation" novalidate>
          <div class="nav-item pb-3 userInfo">
            <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfp mb-2">
            <input href="user-profile.jsp" type="submit" class="nav-link mb-2 ms-5 ps-5 closeBtn" value="<% out.print(nombreDisplay); %>"/>
            <p class="mb-2"> <% out.print(usuarioLogin.getOccupation()); %> </p>
          </div>
          </form>
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
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-home lead "></i>
              </div>
              <div class="col-8">
                <a class="nav-link">Home</a>
              </div>
            </div>
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

      <h2>Dashboard</h2>

      <!-- POP UP NEW POST-->
      <div id="popupNewpost" class="card">

        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row pt-3 mx-3">
            <div class="col-11 me-auto">
              <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfpNewpost">
              <% out.print(nombreDisplay);%> 
            </div>
            <div class="col">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>

        <!-- contenido que va a cambiar -->
        <form class="card-body" action="dashboardServlet" method="post" class="needs-validation" novalidate>
          <div id="postTitle" class="row mx-3 mb-4">
            <input class="form-control form-control-lg" name="titleNewPost" type="text" placeholder="Title"/>
          </div>
          <div class="row mx-3 mb-4">
            <input class="form-control" name="descriptionNewPost" placeholder="What do you want to share?" rows="4"/>
          </div>
          <div class="row mx-3 my-4 newpostContainer">
            <div class="col mt-2">
              <i class="icon ion-md-photos ms-2"></i>
            </div>
            <div class="col-11">
              <input type="url" class="form-control" name="mediaNewPost" id="mediaNewPost" value="">
            </div>
              <!--<button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-play"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-attach"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-pin"></i></button>-->
          </div>
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
          </div>
          <div class="row mx-3 my-4">
              <input type="submit" class="btn btn-primary signUpBtn" value = "Post"/>
          </div>
        </form>

      </div>

      <!-- POP UP ADVANCED SEARCH-->
      <div id="popupAdvancedSearch" class="card">

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

        <div class="card-body">
            <form id="advancedSearch" class="needs-validation" novalidate>
                <div class="row mx-3">
                  <label class="form-label">Date range</label>
                </div>
                <div class="row mx-3 mb-4">
                  <div class="col ms-5">
                    <input type="date" class="form-control" id="initialDate" name="initialDate" value=""/>
                  </div>
                  <div class="col fs-3 ps-5 pe-0">
                    <label class="form-label">---</label>
                  </div>
                  <div class="col me-5">
                    <input type="date" class="form-control" id="finalDate" name="finalDate" value=""/>
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
                    <input name="categ" type="radio" class="cat" id="c1" value="Science"/>
                    <label for="c1">Science</label>
                    <input name="categ" type="radio" class="cat" id="c2" value ="Tech"/>
                    <label for="c2">Tech</label>
                    <input name="categ" type="radio" class="cat" id="c3" value="Art"/>
                    <label for="c3">Art</label>
                    <input name="categ" type="radio" class="cat" id="c4" value="Design"/>
                    <label for="c4">Design</label>
                    <input name="categ" type="radio" class="cat" id="c5" value="Business"/>
                    <label for="c5">Business</label>
                    <input name="categ" type="radio" class="cat" id="c6" value="Psychology"/>
                    <label for="c6">Psychology</label>
                    <input name="categ" type="radio" class="cat" id="c7" value="Medicine"/>
                    <label for="c7">Medicine</label>
                    <input name="categ" type="radio" class="cat" id="c8" value="Human arts"/>
                    <label for="c8">Human Arts</label>
                  </div>
                </div>
                <div class="row mx-3 my-4">
                    <button type="submit" class="btn btn-primary signUpBtn">Search</button>
                </div>
            </form>
        </div>

      </div>

      <!-- NEW POST -->
      <section class="contentItem">
        <div class="container">
          <div class="row">
            <div class="col-md-auto">
              <img src="<% out.print(profileImg); %>" class="img-fluid rounded-circle pfpNewpost">
            </div>
            <div class="col-lg">
              <button data-modal-target="#popupNewpost" class="btn btn-primary btnNewpost">New post</button>
            </div>
          </div>
        </div>
      </section>

      <!-- FEED -->
      <section>

        <!-- sorting bars-->
        <div class="container text-center border-bottom">
          <div id="dashSections" class="row">
            <!--<div class="col sortingItem active">
              <a href="#" class="nav-link subTitle">Comunity</a> 
            </div>
            <div class="col sortingItem">
              <a href="#" class="nav-link subTitle">Discover</a> 
            </div>-->
          </div>
        </div>

        <!-- sort by options-->
        <!--<div class="dropdown d-flex flex-row-reverse">
          <button class="btn btn-secondary dropdown-toggle sortBy" type="button" data-bs-toggle="dropdown" aria-expanded="false">Sort by</button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Latest</a></li>
            <li><a class="dropdown-item" href="#">Popular</a></li>
            <li><a class="dropdown-item" href="#">Category</a></li>
          </ul>
        </div>-->
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" id="categoryDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Select Category
            </button>
            <div class="dropdown-menu" aria-labelledby="categoryDropdown">
              <a class="dropdown-item" href="#"  data-category="all">All categories</a>
              <a class="dropdown-item" href="#"  data-category="science">Science</a>
              <a class="dropdown-item" href="#"  data-category="tech">Tech</a>
              <a class="dropdown-item" href="#"  data-category="art">Art</a>
              <a class="dropdown-item" href="#"  data-category="design">Design</a>
              <a class="dropdown-item" href="#"  data-category="business">Business</a>
              <a class="dropdown-item" href="#"  data-category="psychology">Psychology</a>
              <a class="dropdown-item" href="#"  data-category="medicine">Medicine</a>
              <a class="dropdown-item" href="#"  data-category="human arts">Human Arts</a>
            </div>
        </div>


        <!-- post -->
        <div id="posts">
            <div class="card contentItem">
              <div class="card-header">
                <img src="assets/razor.jpeg" class="img-fluid rounded-circle pfpNewpost">
                Razor
              </div>
              <div class="card-body">
                  <h5>Perritos bonitos</h5>
                <p> I like to hunt </p>
                <img src="assets/cachorros.jpg" class="img-fluid postImg">
              </div>
              <div class="card-footer">
                <p><i class="icon ion-md-heart pe-2"></i>15</p>
              </div>
            </div>
        </div>
        <div id="pagination" class="pagination">
            <button id="prevPage" class="btn btn-primary signUpBtn">Prev</button>
                <div id="pages">
                <!--<span class="numPage">1</span>
                <span class="numPage">2</span>
                <span class="numPage">3</span> -->
                </div>
            <button id="nextPage" class="btn btn-primary signUpBtn">Next</button>
        </div>
      </section>
      
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

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-scrollto/1.4.6/jquery-scrollto.min.js"></script>
    <script defer src="JS/funcionalidad.js"></script>
    <script defer src="JS/dashboard03.js"></script>
</body>
</html>
