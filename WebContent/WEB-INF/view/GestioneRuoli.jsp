<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina">
	<div>
		<h1>Gestore account</h1>
	</div><br>
	<div>
		<div class="table-responsive">
    		<h3>Lista Console</h3>
				<table class="table table-striped table-hover my-5">
					<thead>
						<tr>
							<th>Utente</th>
							<th>Ruolo</th>
							<th>Rimozione</th>
							<th>Inserimento</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${Utente}" var="utente">
							<tr class="text-center">
								<td><c:out value="${utente.nome}"/> <c:out value="${utente.cognome}"/></td>
								<td><c:out value="${utente.ruolo}"/></td>
		                  		<td>
		                  			<form action="GestioneRuoloServlet" method="post">
										<input type="hidden" name="userUtente" value="${utente.username}">
										<input type="hidden" name="operazione" value="rimozione">
										<button type="submit">Rimuovi</button>
									</form>
		                  		</td>
		                  		<td>
		                  			<form action="GestioneRuoloServlet" method="post">
										<input type="hidden" name="userUtente" value="${utente.username}">
										<input type="hidden" name="operazione" value="aggiunta">
										<select name="ruolo">
											<option value="Account">Account</option>
											<option value="Catalogo">Catalogo</option>
											<option value="Assistenza">Assistenza</option>
										</select>
										<button type="submit">Aggiungi</button>
									</form>
		                  		</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
</div>



<%@include file="Footer.html" %> 