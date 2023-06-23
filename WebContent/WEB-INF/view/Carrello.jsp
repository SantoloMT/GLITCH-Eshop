<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina" class="features-boxed" style="margin-top: 0px;">
	<div class="container">
		<h1 style="font-family: 'Arbutus Slab', serif; margin-top: 0px;">Carrello</h1>
	</div>
	<div class="container">
		<c:forEach items="${carrello.prodotti}" var="pc">
		<div class="row justify-content-center features">
			<div class="col-sm-6 col-md-6 col-lg-6 item">
				<a href="${pc.prodotto.immagine}" data-lightbox="photos">
					<img class="imgFluid" src="${pc.prodotto.immagine}" />
				</a>
				<a href="${pc.prodotto.immagine}" data-lightbox="photos"></a>
				</div>
				
				<div class="col-sm-6 col-md-6 col-lg-6 item" id="infoCarrello">
				<c:if test="${pc.prodotto == 'Videogioco'}">
					<label class="text-monospace border rounded-0 border-white d-sm-flex d-md-flex d-lg-flex justify-content-lg-center align-items-lg-start">
					${pq.prodotto.nome}</label>
            	</c:if>
            	<c:if test="${pc.prodotto == 'Console'}">
               		<label class="text-monospace border rounded-0 border-white d-sm-flex d-md-flex d-lg-flex justify-content-lg-center align-items-lg-start">
					${pq.prodotto.modello}</label>
				</c:if>
				<label class="text-monospace text-center border rounded-0 border-white d-sm-flex d-md-flex d-lg-flex justify-content-lg-center align-items-lg-start">
					Prezzo: ${pc.prezzoTot} &euro;</label> 
				<form action="GestioneCarrelloServlet" method="post">	
					<label class="text-monospace border rounded-0 border-white d-sm-flex d-md-flex d-lg-flex justify-content-lg-center align-items-lg-start">
					Quantità: <input type="hidden" name="prodId" value="${pc.prodotto.id}">
						<input type="hidden" name="operazione" value="modifica">
						<!-- <input class="form-control-sm d-sm-flex d-md-flex d-lg-flex" type="number" placeholder="1" min="1" max="50" value="1" />  -->
						<input type="number" name="quantita" value="${pc.quantita}" min="0" max="50" class="form-control-sm d-sm-flex d-md-flex d-lg-flex"> 
						<input type="submit" value="Aggiorna" class="btn-info">
					</label>
				</form>
				<form action="GestioneCarrelloServlet" method="post">
					<input type="hidden" name="prodId" value="${pc.prodotto.id}">
					<input type="hidden" name="operazione" value="rimozione">
					<input type="hidden" name="setNum" value="0">
					<input type="submit" value="Rimuovi" class="btn-success">
				</form>
			</div>
		</div>
		<hr />
		</c:forEach>
	</div>
	<!-- Carrello nel caso non vi siano inseriti prodotti -->
	<div class="container">
 		<c:if test="${empty carrello.prodotti}">
 			<div style="padding: 20%">
				<section>
				<h2>Nessun articolo nel carrello</h2>
				<a href="BaseServlet" style="font-size: 1.5vw">Torna alla Home per continuare il tuo Shopping</a>
			</section>
			</div>
 		</c:if>
 	<!-- Una volta visualizzato i prodotti, segue il div con la spesa totale e il bottone di acquisto -->
 		<c:if test="${not empty carrello.prodotti}">
 			<div>
				<h4 style="text-align: right;">Totale: <c:out value="${carrello.prezzoTotProdotti}"></c:out> &euro;</h4><br>	
				<form action="RedirectRiepilogoOrdineServlet" method="post">
					<input type="submit" value="Completa acquisto" class="btnGlitch">
				</form><br>
			</div>
 		</c:if>
 	</div>
</div>




<%@include file="Footer.html" %> 