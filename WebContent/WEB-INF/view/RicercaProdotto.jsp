<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="RicercaServlet"/>
</jsp:include>
 
 
 
 <div id="pagina">
 	<div class="container-fluid">
 		<div class="col-sm-12 ">
 			<h1>Risultato ricerca</h1>
 			<hr>
 			<!-- <div>
				<c:if test="${ricerca == null}">
   					<div class="col-md-12 col-xs-12">	
						<div style="padding: 20%">
							<h2>Siamo spiacenti. Non abbiamo prodotti con questo nome.</h2>
							<img alt="gameOver" style="max-width: 100%"
								src="https://cdn5.vectorstock.com/i/1000x1000/94/64/game-over-games-screen-glitch-computer-video-vector-22579464.jpg"><br><br>
							<a href="BaseServlet">Torna alla Home a dare uno sguardo</a><br>
						</div>
					</div>					
   				</c:if>
			</div>  -->
			<div>
 				<c:if test="${ricerca == 'Videogioco'}">
 					<c:forEach items="${itemTrovati}" var="videogioco">
	 					<div class="row">
	            		<div class="col-md-6 col-xs-6">
	               			<a href="${videogioco.immagine}" target="_blank">
	               				<img src="${videogioco.immagine}" alt="${videogioco.nome}" class="imgFluid">
	               			</a>          
	            		</div>
	            		<div class="padding-txt col-md-6 col-xs-6" style="padding-top: 40px">
	                 		<label><strong><c:out value="${videogioco.nome}" /></strong></label><br>
	                 		<label><c:out value="${videogioco.genere}" /></label><br>
	                 		<label><c:out value="${videogioco.prezzo}" />€</label><br>
	                 		<label><strong>Descrizione:</strong> <c:out value="${videogioco.descrizione}" /></label><br>
	                 		<c:choose>
								<c:when test="${utente == null}">
									<form>
										<input type="hidden" name="prodId" value="${videogiochi.id}">
										<input type="submit" class="btnGlitch"
											value="Aggiungi al carrello" disabled="disabled">
									</form>
								</c:when>
								<c:otherwise>
									<form action="GestioneCarrelloServlet">
										<label>Quantità: <input type="number"
											class="form-control-sm d-sm-flex d-md-flex d-lg-flex"
											name="number" placeholder="1" min="1" max="20" value="1" /></label>
										<input type="hidden" name="prodId" value="${videogiochi.id}">
										<input type="hidden" name="operazione" value="inserimento"><br>
										<input type="submit" class="btnGlitch"
											value="Aggiungi al carrello">
									</form><br>
								</c:otherwise>
							</c:choose>   
	             		</div>
	         		</div>
	         		</c:forEach>
   				</c:if>
 				<c:if test="${ricerca == 'Console'}">
 					<div class="row">
            			<div class="col-md-6 col-xs-6">
               				<a href="${itemTrovati.immagine}" target="_blank">
               					<img id="myImg" src="${itemTrovati.immagine}" alt="${itemTrovati.modello}" class="imgFluid">
               				</a>
            			</div>
            			<div class="padding-txt col-md-6 col-xs-6">
                 			<label><strong><c:out value="${itemTrovati.modello}" /></strong></label><br>
                 			<label><c:out value="${itemTrovati.casaProduttrice}" /></label><br>
                 			<label><c:out value="${itemTrovati.prezzo}" />€</label><br>
                 			<label><strong>Descrizione:</strong> <c:out value="${itemTrovati.descrizione}" /></label><br>
							<c:choose>
								<c:when test="${utente == null}">
									<form>
										<input type="hidden" name="prodId" value="${videogiochi.id}">
										<input type="submit" class="btnGlitch"
											value="Aggiungi al carrello" disabled="disabled">
									</form>
								</c:when>
								<c:otherwise>
									<form action="GestioneCarrelloServlet">
										<label>Quantità: <input type="number"
											class="form-control-sm d-sm-flex d-md-flex d-lg-flex"
											name="number" placeholder="1" min="1" max="20" value="1" /></label>
										<input type="hidden" name="prodId" value="${videogiochi.id}">
										<input type="hidden" name="operazione" value="inserimento"><br>
										<input type="submit" class="btnGlitch"
											value="Aggiungi al carrello">
									</form><br>
								</c:otherwise>
							</c:choose>
						</div>
         			</div>
   					</c:if>
		</div>
		</div>
	</div>
</div>
		
		
		
<%@include file="Footer.html"%>