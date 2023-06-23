<%@ page session="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
	<title>GLITCH</title>
	<!-- link per i file css -->
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/footer.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/registrazione.css">
	<link rel="stylesheet" href="css/carrello.css">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"> <!-- Icone social -->  
	<!-- link per BOOTSRAP 4 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<!-- JavaScript -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  	<!-- link SweetAlert2 -->
  	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>


<body>
	<div>
		<nav
			class="navbar navbar-light navbar-expand-md navigation-clean-search">
			<div class="container-fluid">
				<img class="image-holder" src="GlitchLogo.png" style="width: 100px" />
				<button data-toggle="collapse" data-target="#navcol-3" class="navbar-toggler">
					<span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse image-holder" id="navcol-3" src="GlitchLogo.png">
				
					<!-- Barra di ricerca -->
					<form class="form-inline mr-auto" target="_self" action="RicercaProdott" onkeyup="loadSuggerimenti(this.cerca.value)">
						<div class="form-group">
							<input type="search" class="form-control search-field" id="search-field" list="listaProdotti" autocomplete="off" name="cerca" placeholder="Cerca"/>
							<datalist id="listaProdotti">
            				</datalist>
						</div>
					</form>
					
					<!-- Opzioni da navbar specifiche per i vari tipi di utente del sito -->
					<c:choose>
						<c:when test="${utente == null}">
							<span class="navbar-text"><a class="login" href="#" target="_blank" data-toggle="modal" data-target="#myLogin">Accedi&#160;&#160;</a></span>
							<a class="btn btn-light action-button" role="button"
								data-toggle="modal" data-target="#myRegistrazione">Registrati</a>
						 </c:when>
						 <c:otherwise>		
							<c:if test="${ utente != null && utente.ruolo == null }">
								<div class="dropdown show d-lg-flex" style="margin: 0px; margin-right: 25px;">
									<a data-toggle="dropdown" aria-expanded="true" class="dropdown-toggle text-left" href="#" target="_blank"><i class="fa fa-user-circle"></i>Ciao, ${utente.nome}</a>
									<div role="menu" class="dropdown-menu show">
										<a role="presentation" class="dropdown-item d-lg-flex" href="RedirectPaginaPersonale">Pagina personale</a>
										<a role="presentation" class="dropdown-item" href="RedirectPaginaOrdini">I miei ordini</a>
										<a role="presentation" class="dropdown-item" href="RedirectRispostaUtente">Le mie notifiche <span class="glyphicon glyphicon-comment"></span><span class="badge badge-notify">3</span></a>
										<a role="presentation" class="dropdown-item d-lg-flex" href="LogoutServlet">Logout</a>
									</div>
								</div>
								<a class="btn btn-light action-button" role="button" href="RedirectCarrello" ><i class="fa fa-shopping-cart"></i>Carrello</a>
							</c:if>
      	
							<c:if test="${ utente.ruolo != null }">
								<div class="dropdown show d-lg-flex" style="margin: 0px; margin-right: 25px;">
									<a data-toggle="dropdown" aria-expanded="true" class="dropdown-toggle text-left" href="#"><i class="fa fa-user-circle"></i>Ciao, ${utente.nome}</a>
									<div role="menu" class="dropdown-menu show">
										<a role="presentation" class="dropdown-item d-lg-flex" href="RedirectPaginaPersonale">Pagina personale</a>
										<a role="presentation" class="dropdown-item" href="RedirectPaginaOrdini">I miei ordini</a>
										<a role="presentation" class="dropdown-item" href="RedirectRispostaUtente">Le mie notifiche </a>
										<c:if test="${ utente.ruolo == 'Account' }">
											<a role="presentation" class="dropdown-item d-lg-flex" href="RedirectGestioneRuoloServlet">Gestore account</a>
										</c:if>
										<c:if test="${ utente.ruolo == 'Catalogo'  }">
												<a role="presentation" class="dropdown-item" href="RedirectGestoreProdotti">Gestione prodotti</a>
												<a role="presentation" class="dropdown-item" href="RedirectGestoreOfferte">Gestione offerte</a>
										</c:if>
										<c:if test="${  utente.ruolo == 'Assistenza' }">
											<a role="presentation" class="dropdown-item" href="RedirectRispostaAssistenza">Gestore assistenza</a>
										</c:if>
										<a role="presentation" class="dropdown-item d-lg-flex" href="LogoutServlet">Logout</a>
									</div>
								</div>
								<a class="btn btn-light action-button" role="button" href="RedirectCarrello" ><i class="fa fa-shopping-cart"></i>Carrello</a>							</c:if>
               			</c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>
	</div>


<!-- Script per la ricerca -->
<script>
function loadSuggerimenti(input){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.status == 200 && this.readyState == 4){
			carica(this);
		}
	}
	xhttp.open("GET", "RicercaServlet?input="+input, true);
	xhttp.send();
}

function carica(xhttp){
		var obj = JSON.parse(xhttp.responseText);
		var suggerimenti;
		for(i=0; i<obj.length;i++){
			suggerimenti += "<option>" + obj[i].name + "</option>";
		}
		document.getElementById("listaProdotti").innerHTML = suggerimenti;
}
</script>



<%@include file="Login.jsp" %> 
<%@include file="Registrazione.jsp" %> 