<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Errore"/>
</jsp:include>


<div id="pagina">
 <div class="container-fluid">
 	 <div class="col-sm-12 col-md-12 col-lg-12 item" style="padding: 15%">
		<section>
			<h2><%= exception.getMessage() %></h2>
			<img alt="gameOver" style="max-width: 100%"
				src="https://cdn5.vectorstock.com/i/1000x1000/94/64/game-over-games-screen-glitch-computer-video-vector-22579464.jpg"><br><br>
			<a href="BaseServlet" style="font-size: 1.5 vw"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>&#160;&#160;Torna alla Home</a>
		</section>
	 </div>
   </div>
</div>
	
	
	
<%@include file="Footer.html"%>