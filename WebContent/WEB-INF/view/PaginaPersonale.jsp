<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div class="article-list" id="pagina">
	<div class="container">
		<div class="intro">
			<h2 class="text-center">Pagina personale</h2>
			<p>In questa sezione potrai cambiare i campi relativi a: 
				<i>email</i>, <i>città</i>, <i>provincia</i>, <i>via</i>, <i>numero</i>, <i>CAP</i> e <i>password</i>.<br>
				Potrai, inoltre, decidere di eliminare il tuo profilo da Glitch.<br>
				N.B. I campi relativi all'indirizzo vanno modificati tutti insieme
			</p>
		</div>
		<hr>
	</div>
	<div class="container">
		<h3>Modifica profilo</h3>
	</div>
	<div class="container">
		<div class="intro"></div>
	</div>
	<form method="post" id="formGlitch">
		<div class="form-group d-lg-flex justify-content-lg-start">
			<span><c:out value="${utente.nome}" /></span>
		</div>
		<div class="form-group d-lg-flex justify-content-lg-start">
			<span class="d-lg-flex justify-content-lg-start"><c:out value="${utente.cognome}" /></span>
		</div>
		<div class="form-group">
			<input class="form-control" type="email" name="email" placeholder="${utente.email}" />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="citta" placeholder="${utente.citta}" />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="provincia" placeholder="${utente.provincia}" />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="via" placeholder="${utente.via}" />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="numero" placeholder="${utente.numero}" />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="cap" placeholder="${utente.cap}" />
		</div>
		<div class="form-group">
			<span class="d-lg-flex justify-content-lg-start"><c:out value="${utente.username}" /></span>
		</div>
		<div class="form-group">
			<input class="form-control" type="password" name="password" placeholder="${utente.password}" />
		</div>
		<div class="container text-right">
			<input type="hidden" name="operazione" value="modifica">
			<button class="btnGlitch">Conferma modifiche</button>
		</div>
	</form>

	
	<!-- Div e form per la rimozione del profilo -->
	<div class="container">
		<hr />
	</div>
	<div class="container">
		<h3 style="padding-top: 5%;">Rimuovi profilo</h3>
	</div>
	<div class="container" style="padding: 3%;">
		<form action="GestioneProfiloServlet">
			<input type="hidden" name="operazione" value="rimozione">
			<button class="btn btn-primary" type="submit">Elimina profilo</button>
		</form>
	</div>
</div>



<%@include file="Footer.html" %>