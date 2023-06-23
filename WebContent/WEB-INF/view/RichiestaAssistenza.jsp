<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<div id="pagina">
	<div class="container">
		<div class="intro">
			<h2 class="text-center">Richiedi assistenza</h2>
			<p class="text-center">
				Hai avuto un problema con una consegna? 
				Il tuo ordine non è arrivato o non era ciò che avevi richiesto?
				Siamo qui per rispondere a tutte le tue domande.
			</p>
		</div>
	</div>
	<div id="formGlitch" class="contact-clean">
		<form method="post" action="RichiestaAssistenzaServlet">
			<h2 class="text-center">Contattaci</h2>
			<div class="form-group">
				<label><strong>Mittente: </strong><c:out value="${utente.nome}"></c:out></label>
			</div>
			<div class="form-group">
				<select name="destinatario">
					<c:forEach items="${destinatario}" var="destinatario">
						<option ><c:out value="${destinatario}"></c:out></option>
					</c:forEach> 
				</select>
			</div>
			<div class="form-group">
				<textarea class="form-control" name="descrizione" placeholder="Messaggio" rows="14"></textarea>
			</div>
			<div class="form-group" style="text-align: right;">
				<c:choose>
					<c:when test="${utente == null }">
						<button class="btn btn-primary" type="submit" disabled="disabled">Invia</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary" type="submit">Invia</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div><br>
</div>



<%@include file="Footer.html" %>