<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<!-- Tabella che mostra la lista degli utenti del sito coi relativi ruoli -->
<div class="article-list">
	<div class="container">
		<div class="intro">
			<h2 class="text-center">Gestione account</h2>
		</div>
	</div>
	<div
		class="container text-monospace d-lg-flex justify-content-lg-center visible">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr style="background-color: #c4fff8;">
						<th>UTENTE</th>
						<th>RUOLO</th>
						<th>AGGIUNGI RUOLO</th>
						<th>ELIMINA RUOLO</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${utente}" var="utente">
					<tr>
						<td><c:out value="${utente.nome}" /> <c:out value="${utente.cognome}" /></td>
						<td><c:out value="${utente.ruolo}" /></td>
						<td>
							<div class="dropdown show">
								<button class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-expanded="true" type="button"
									style="background-color: rgba(21, 86, 184, 0); color: rgb(0, 0, 0);">
									<i class="fa fa-plus-square-o"></i> Ruolo
								</button>
								<div class="dropdown-menu show" role="menu">
									<a class="dropdown-item" role="presentation" href="#">Gestore catalogo</a>
									<a class="dropdown-item" role="presentation" href="#">Gestore account</a>
									<a class="dropdown-item" role="presentation" href="#">Gestore assistenza</a>
								</div>
							</div>
						</td>
						<td>
							<div class="dropdown show">
								<button class="btn btn-primary dropdown-toggle"
									data-toggle="dropdown" aria-expanded="true" type="button"
									style="background-color: rgba(21, 86, 184, 0); color: rgb(0, 0, 0);">
									<i class="fa fa-trash-o"></i> Ruolo
								</button>
								<div class="dropdown-menu show" role="menu">
									<a class="dropdown-item" role="presentation" href="#">Gestore catalogo</a>
									<a class="dropdown-item" role="presentation" href="#">Gestore account</a>
									<a class="dropdown-item" role="presentation" href="#">Gestore assistenza</a>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
		</div>
	</div>
</div>



<%@include file="Footer.html" %> 