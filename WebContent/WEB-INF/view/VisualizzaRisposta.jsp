<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina">
<!-- From per visualizzare la richiesta e poter rispondere -->
	<div class="contact-clean" id="formGlitch ">
		<h3 class="text-center">Visualizzazione e-mail del gestore</h3><br>
    	<form style="background-color: #c8fcf8; padding: 5%;">
        	<div class="form-group text-left"><label><strong>Mittente:</strong> ${ richiesta.utenteEmail }</label></div>
        	<div class="form-group text-left"><label><strong>Messaggio:</strong></label></div>
        	<div class="form-group text-left"><p>${ richiesta.descrizione }</p></div>
        	<br>
    	</form>
	</div>
</div>



<%@include file="Footer.html" %> 