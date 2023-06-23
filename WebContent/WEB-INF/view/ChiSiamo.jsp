<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>



<div id="pagina">
<div class="row">
	<div class="col-sm-6 col-md-6 col-lg-6 item">
		<img alt="logo" src="GlitchLogo.png" style="max-width: 90%">
	</div>
	<div class="col-sm-6 col-md-6 col-lg-6 item" style="padding: 5%">
	<h3>GLITCH</h3>
	<p>
		Glitch nasce per consentire a tutti gli amanti dei videogiochi di poter avere un proprio negozio di fiducia sempre a disposizione, 
		che non solo garantisca loro un ampio catalogo da “sfogliare”, ma che offra anche la possibilità di poter ricevere il proprio 
		ordine comodamente a casa mediante spedizioni su tutto il territorio nazionale.
	</p>
	</div>
</div>
<div class="col-md-12 col-lg-12 item">
	<h3>Qual è il suo scopo?</h3>
	<p>
		Al giorno d’oggi sono sempre di più gli appassionati di videogiochi e sempre di più i problemi ad essi legati.
		Quante volte ti è capitato di andare nel tuo negozio di fiducia e non trovare il gioco che desideravi? 
		O quante volte non avevi voglia di uscire di casa per recarti in negozio?
		Glitch nasce apposta per te. Sulla sua piattaforma, infatti, troverai tutte le nuove uscite dei tuoi giochi preferiti, 
		tutte le nuove console e tanta disponibilità.
		Ti basterà registrarti sul nostro sito, effettuare il login, e goderti il nostro fornitissimo catalogo.
		Un’ultima cosa, ricorda: <strong>ciò che vuoi, qui lo troverai!</strong>	
	</p>
</div>

<h3>TEAM</h3>
<div class="row people">
    <div class="col-md-6 col-lg-4 item">
    	<img class="rounded-circle" src="FN.jpg" id="foto"/>
        <h3 class="name">Ferdinando Napolitano</h3>
        <p class="title">Studente di informatica</p>
        <div class="social"><a href="https://www.instagram.com/" target="_blank"><i class="fa fa-instagram"></i></a></div>
    </div>
    <div class="col-md-6 col-lg-4 item">
    	<img class="rounded-circle" src="AE.jpg" id="foto"/>
        <h3 class="name">Annunziata Elefante</h3>
        <p class="title"><br />Studente di informatica<br /></p>
        <div class="social"><a href="https://www.instagram.com/" target="_blank"><i class="fa fa-instagram"></i></a></div>
    </div>
    <div class="col-md-6 col-lg-4 item">
    	<img class="rounded-circle" src="SM.jpg" id="foto"/>
        <h3 class="name">Santolo Mutone</h3>
        <p class="title"><br />Studente di informatica<br /></p>
        <div class="social"><a href="https://www.instagram.com/" target="_blank"><i class="fa fa-instagram"></i></a></div>
    </div>
</div>
</div>


<%@include file="Footer.html" %> 