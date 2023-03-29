<%-- 
    Document   : user-profile
    Created on : Mar 21, 2023, 10:05:39 PM
    Author     : Aylin
--%>

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

    <link rel="stylesheet" href="style.css"/>
    <script defer src="funcionalidad.js"></script>
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
        <form class="d-flex position-relative p-3 d-inline-block" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn searchNavbar position-absolute" type="submit"><i class="icon ion-md-search"></i></button>
        </form>
        <button data-modal-target="#popupAdvancedSearch" type="button" class="btn btn-primary ms-4 advancedSearchBtn">Advanced Search</button>

        <!--elementos para seleccionar-->
        <div class="menu d-flex flex-column justify-content-evenly mb-5 pb-5">

          <!-- Info User-->
          <div class="nav-item pb-3 userInfo">
            <img src="assets/fotoPerfil.jpeg" class="img-fluid rounded-circle pfp mb-2">
            <a href="user-profile.html" class="nav-link mb-2">Aylin Galindo</a>    <!-- dblock para que cada uno este en una linea y p-3 para separalos-->
            <p class="mb-2">Game Dev Student</p>
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
            <div class="row misLinks">
              <div class="col-2 pe-1">
                <i class="icon ion-md-home lead "></i>
              </div>
              <div class="col-8">
                <a href="dashboard.jsp" class="nav-link">Home</a>
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
          <div class="row mx-3">
            <label class="form-label">Date range</label>
          </div>
          <div class="row mx-3 mb-4">
            <div class="col ms-5">
              <input type="date" class="form-control" name="fechaInicio" value=""/>
            </div>
            <div class="col fs-3 ps-5 pe-0">
              <label class="form-label">---</label>
            </div>
            <div class="col me-5">
            <input type="date" class="form-control" name="fechaFin" value=""/>
            </div>
          </div>
          <div class="row mx-3">
            <label class="form-label">Category</label>
          </div>
          <div class="row mx-3 mb-4 newpostContainer">
            <div class="col">
              <i class="icon ion-md-pricetags ms-2 mt-2"></i>
            </div>
            <div class="col-11 categories">
              <button type="button" class="cat btn">#Science</button>
              <button type="button" class="cat btn">#Tech</button>
              <button type="button" class="cat btn">#Art</button>
              <button type="button" class="cat btn">#Design</button>
              <button type="button" class="cat btn">#Business</button>
              <button type="button" class="cat btn">#Psychology</button>
              <button type="button" class="cat btn">#Medicine</button>
              <button type="button" class="cat btn">#Human Arts</button>
            </div>
          </div>
          <div class="row mx-3 my-4">
              <button type="submit" class="btn btn-primary signUpBtn">Search</button>
          </div>
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

              <!-- form de la pagina 1 :D-->
              <div class="carousel-item active">
                <form class="row g-3 needs-validation" novalidate>
                  <div class="col-12">
                    <label for="validationName" class="form-label">First name(s)</label>
                    <input type="text" class="form-control" id="validationName" value="Aylin" required>
                    <div class="invalid-feedback">
                      Please fill with letters. 
                    </div>
                  </div>
                  <div class="col-6">
                    <label for="validationFirstLN" class="form-label">First last name</label>
                    <input type="text" class="form-control" id="validationFirstLN" value="Galindo" required>
                    <div class="invalid-feedback">
                      Please fill with letters. 
                    </div>
                  </div>
                  <div class="col-6">
                    <label for="validationSecondLN" class="form-label">Second last name</label>
                    <div class="input-group has-validation">
                      <input type="text" class="form-control" id="validationSecondLN" value="Acosta" required>
                      <div class="invalid-feedback">
                        Please fill with letters.
                      </div>
                    </div>
                  </div>
                </form>
              </div>

              <!-- form de la pagina 2 :D -->
              <div class="carousel-item">
                  <div class="row g-3 align-content-start justify-content-center flex-fill ms-3">
                    <div class="col-12">
                      <label class="form-label">Choose a profile picture</label>
                    </div>
                    <div class="col-12 pfpRegistro">
                        <img src="assets/defaultpfp.png" class="img-fluid rounded-circle">
                    </div>
                    <div class="col-12 mb-3">
                      <input type="file" class="form-control" aria-label="file example" required>
                      <div class="invalid-feedback">Example invalid form file feedback</div>
                    </div>
                    <div class="col-12">
                      <label class="form-label">Choose a background image</label>
                    </div>
                    <div class="col-12 mb-3">
                      <input type="file" class="form-control" aria-label="file example" required>
                      <div class="invalid-feedback">Example invalid form file feedback</div>
                    </div>
                  </div>
              </div>

              <!-- form de la pagina 3 :DD -->
              <div class="carousel-item">
                <form class="row g-3 needs-validation" novalidate>
                  <div class="col-12">
                    <label for="validationCustom01" class="form-label">Change username</label>
                    <input type="text" class="form-control" id="validationCustom01" value="@aylingalindo" required>
                    <div class="valid-feedback">
                      Looks good!
                    </div>
                  </div>
                  <div class="col-12">
                    <label for="validationCustomUsername" class="form-label">Change password</label>
                    <div class="input-group has-validation">
                      <input type="password" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" value="password" required>
                      <div class="invalid-feedback">
                        Invalid passowrd.
                      </div>
                    </div>
                  </div>
                  <div class="col-12">
                    <label for="validationCustomUsername" class="form-label">Confirm password</label>
                    <div class="input-group has-validation">
                      <input type="password" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" value="" required>
                      <div class="invalid-feedback">
                        Invalid passowrd.
                      </div>
                    </div>
                  </div>
                  <div class="col-12">
                    <button type="submit" class="btn btn-primary signInBtn">Confirm</button>
                  </div>
                </form>
              </div>
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
              <h4>@aylingalindo</h4>
            </div>
            <div class="col ms-2 pt-3">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>
        <!-- form para editar información-->
        <div class="card-body d-flex">
          <form class="row g-3">
              <div class="col-12">
                <label class="form-label">Ocupation</label>
                <input type="text" class="form-control" id="Ocupation" value="Game Developer">
              </div>
              <div class="col-12">
                <label class="form-label">Location</label>
                <input type="text" class="form-control" id="Ocupation" value="Monterrey, MX">
              </div>
              <div class="col-12">
                <label for="validationEmail" class="form-label">Email</label>
                <div class="input-group has-validation">
                  <input type="text" class="form-control" id="validationEmail" value="aylingalindo@email.com" required>
                  <div class="invalid-feedback">
                    Please write a valid email.
                  </div>
                </div>
              </div>
              <div class="col-12">
                <label for="validationDate" class="form-label">Date of birth</label>
                <div class="input-group has-validation">
                  <input type="date" class="form-control" name="fecha" id="validationDate" value="" required>
                  <div class="invalid-feedback">
                  Please select a valid date.
                </div>
                </div>
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary signInBtn">Sign Up</button>
              </div>
          </form>
        </div>
      </div>
      
      <!-- POP UP EDIT POST-->
      <div id="popupEditPost" class="card">

        <!-- titulo y boton que se van a quedar siempre -->
        <div class="card-header">
          <div class="row pt-3 mx-3">
            <div class="col-11 me-auto">
              <img src="assets/fotoPerfil.jpeg" class="img-fluid rounded-circle pfpNewpost">
              Aylin Galindo
            </div>
            <div class="col">
              <button data-close-button type="button" class="closeBtn"><i class="icon ion-md-close"></i></button>
            </div>
          </div>
        </div>

        <!-- contenido que va a cambiar -->
        <div class="card-body">
          <div id="postTitle" class="row mx-3 mb-4">
            <textarea class="form-control form-control-lg" type="text" placeholder="Title"></textarea>
          </div>
          <div class="row mx-3 mb-4">
            <textarea class="form-control" placeholder="What do you want to share?" rows="4"></textarea>
          </div>
          <div class="row mx-3 my-4 newpostContainer">
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-photos"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-play"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-attach"></i></button>
              <button data-close-button type="button" class="closeBtn col"><i class="icon ion-md-pin"></i></button>
          </div>
          <div class="row mx-3 my-4 newpostContainer">
            <div class="col">
              <i class="icon ion-md-pricetags ms-2 mt-2"></i>
            </div>
            <div class="col-11 categories">
              <button type="button" class="cat">#Science</button>
              <button type="button" class="cat">#Tech</button>
              <button type="button" class="cat">#Art</button>
              <button type="button" class="cat">#Design</button>
              <button type="button" class="cat">#Business</button>
              <button type="button" class="cat">#Psychology</button>
              <button type="button" class="cat">#Medicine</button>
              <button type="button" class="cat">#Human Arts</button>
            </div>
          </div>
          <div class="row mx-3 my-4">
              <button type="submit" class="btn btn-primary signUpBtn">Post</button>
          </div>
        </div>

      </div>

      <!-- POP UP CONFIRM DELETE POST -->
      <div id="popupDeletePost" class="card">
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
              <button type="submit" class="btn btn-primary signUpBtn">Delete</button>
            </div>
            <div class="col-6">
              <button data-close-button type="submit" class="btn btn-primary signInBtn">Cancel</button>
            </div>
          </div>
        </div>
      </div>
      
      <!-- PERFIL -->
      <section class="border-bottom">
        <div class="container contentItem headerPerfil">
          <div class="portadaPerfil">
            <img class="img-fluid" src="assets/fotoPortada.jpeg">
          </div>
          <div class="d-flex ms-5">
            <div class="pfpDiv align-self-baseline">
              <img src="assets/fotoPerfil.jpeg" class="img-fluid rounded-circle pfpPerfil">
            </div>
            <div class="flex-column ms-3 me-auto">
              <div class="row">
                <h2 class="m-0">Aylin Galindo</h2>
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
                <p>Game Developer</p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-person pe-2"></i>User:</h6>
              </div>
              <div class="col">
                <p>@aylingalindo</p>
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
                <p>aylingalindo@email.com</p>
              </div>
            </div>

            <div class="row">
              <div class="col-3">
                <h6><i class="icon ion-md-home pe-2"></i>Location:</h6>
              </div>
              <div class="col">
                <p>Monterrey, MX</p>
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
          <h5>Gatitos bonitos</h5>
          <p> I like to hunt </p>
          <img src="assets/gatitos.jpeg" class="img-fluid postImg">
        </div>
        <div class="card-footer">
          <p><i class="icon ion-md-heart pe-2"></i>15</p>
        </div>
      </div>

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
          <h5>Titulo</h5>
          <p> I like to hunt </p>
        </div>
        <div class="card-footer">
          <p><i class="icon ion-md-heart pe-2"></i>15</p>
        </div>
      </div>

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
          <h5>Perritos bonitos</h5>
          <p> I like to hunt </p>
          <img src="assets/cachorros.jpg" class="img-fluid postImg">
        </div>
        <div class="card-footer">
          <p><i class="icon ion-md-heart pe-2"></i>15</p>
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
</body>
</html>
