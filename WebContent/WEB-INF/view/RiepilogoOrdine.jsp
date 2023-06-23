<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina">
	<div>
		<h2>Storico ordini</h2>
	</div>
	<br>
	<div>
		<div class="table-responsive">
			<table class="table table-striped table-hover my-5">
				<thead>
					<tr>
						<th>Codice</th>
						<th>Num Prodotti</th>
						<th>Data acquisti</th>
						<th>Totale</th>
						<th>Dettagli</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Consoles}" var="console">
						<td><c:out value="${console.id}" /></td>
						<td><c:out value="${console.modello}" /></td>
						<td><c:out value="${console.prezzo}" /></td>

						<c:forEach items="${Videogiochi}" var="videogiochi">
							<tr class="text-center">
								<td><c:out value="${videogiochi.id}" /></td>
								<td><c:out value="${videogiochi.nome}" /></td>
								<td><c:out value="${videogiochi.prezzo}" /></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>



<%@include file="Footer.html"%>