<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<!-- Tabella che mostra la lista delle richieste di assistenza -->
<div id="pagina" style="padding-bottom: 20%">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">Gestione assistenza</h2>
        </div>
    </div>
    <div class="container text-monospace d-lg-flex justify-content-lg-center visible">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>E-mail gestore</th>
                        <th>Lettura</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${richiesta}" var="r">
                    <tr>
                    	<td>
                    		<c:out value="${r.utenteEmail}"></c:out>
                    	</td>
                         <td>
							<form action="LeggiRisposta" method="post">
								<input type="hidden" name="email" value="${r.id}">
								<input class="btn btn-info" type="submit" value="Leggi">
							</form>
						</td>
                    </tr>
                    
                 </c:forEach>  
                </tbody>
            </table>
        </div>
    </div>


<!-- Form usato dal Gestore assistenza per rispondere ai clienti -->

</div>



<%@include file="Footer.html" %> 