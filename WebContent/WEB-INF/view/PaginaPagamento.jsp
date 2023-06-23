<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina" style="padding: 5%">
	<div class="container">
		<h1>Pagamento</h1>
	</div>
	<div class="container">
		<h3 class="text-left">Riepilogo informazioni</h3>
	</div>
	<hr />
	<div class="text-left" >
		<strong class="d-lg-flex justify-content-lg-start">1. Indirizzo di spedizione </strong><br>
		<label>${utente.citta} (<c:out value="${utente.provincia}"></c:out>)<br> 
		<c:out value="${utente.via}"></c:out>, <c:out value="${utente.numero}"></c:out> <br> 
		<c:out value="${utente.cap}"></c:out> </label>
	</div>
	<hr />
	<div class="container d-lg-flex justify-content-lg-start">
		<strong>2. Inserimento carta di credito</strong>
	</div>
	<c:if test="${carta != null}">
		<div class="d-lg-flex justify-content-lg-start custom-control custom-radio" style="margin: 30px;">
			<input type="radio" id="healthyes" class="custom-control-input" name="numeroCarta"/>
			<label class="custom-control-label" for="healthyes"> <c:out value="${carta.numeroCarta}"></c:out> </label>
		</div>
	</c:if>
	
	<!-- Div per l'inesrimento della carta di credito -->
	<c:if test="${carta == null}">
	<div class="row .payment-dialog-row" style="margin: 10px;">
		<div class="col-12 col-md-6 col-lg-6">
			<div class="card credit-card-box">
				<div class="card-header">
					<h3> <span class="panel-title-text">Dettagli pagamento</span> </h3>
				</div>
				<div class="card-body">
					<form id="payment-form" action="InserimentoCartaServlet">
						<div class="form-row">
							<div class="col-12">
								<div class="form-group">
									<label for="cardNumber">Numero identificativo</label>
									<div class="input-group">
										<input class="form-control" type="tel" id="cardNumber" name="numero" required placeholder="Numero identificativo valido" />
										<div class="input-group-append">
											<span class="input-group-text"><i class="fa fa-credit-card"></i></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="col-7">
								<div class="form-group">
									<label for="cardExpiry">Scadenza</label>
									<input class="form-control" type="tel" id="cardExpiry" name="scadenzaMese" required placeholder="MM " />
									<input class="form-control" type="tel" id="cardExpiry" name="scadenzaAnno" required placeholder="YY" />
								</div>
							</div>
							<div class="col-5 pull-right">
								<div class="form-group">
									<label for="cardCVC">CVV</label>
									<input class="form-control" type="tel" id="cardCVC" name="cvv" required placeholder="CVV" />
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="col-12">
								<div class="form-group">
									<label for="couponCode">Intestatario</label>
									<input class="form-control" type="text" id="couponCode" name="nome" placeholder="Nome" />
									<input class="form-control" type="text" id="couponCode" name="cognome" placeholder="Cognome" />
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="col-12">
								<button class="btn btn-success btn-block btn-lg" type="submit">Aggiungi carta</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	<hr />
	
	<div class="container text-right">
		<!-- <form action="AcquistoServlet">
		<input type="hidden" name="operazione" value="annullamento">
			<button class="btnGlitch" type="button">Annulla ordine</button>
		</form>  -->
		<form action="AcquistoServlet">
		<input type="hidden" name="operazione" value="conferma">
			<button class="btnGlitch" type="submit">Conferma ordine</button>
		</form>
	</div>
</div>



<%@include file="Footer.html" %>