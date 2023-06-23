<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>


<div id="pagina">
	<div>
		<h1>Gestore catalogo</h1>
	</div><br>
	<div>
		<div class="table-responsive">
    		<h3>Lista Offerte</h3>
				<table class="table table-striped table-hover my-5">
					<thead>
						<tr>
							<th>Codice</th>
							<th>Sconto</th>
							<th>Categoria</th>
							<th>Nome</th>
							<th>Rimozione</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${offerta}" var="offerta">
						<td><c:out value="${offerta.codice}"/></td>
						<td><c:out value="${offerta.sconto}"/></td>
		                <td><c:out value="${offerta.categoria}"/></td>
		                <td><c:out value="${offerta.nome}"/></td>
		                <td>
		                	<form action="GestioneOffertaServlet">
		                		<input type="hidden" name="offertaId" value="${offerta.codice}">
		                  		<input type="hidden" name="operazione" value="rimozione">
		                		<button type="submit">Rimuovi</button>
		                	</form>
		                </td>	
					</c:forEach>
					</tbody>
				</table>
    	</div>

	<div class="container" style="background-color: #ffffff;">
		<h2>Gestione offerte</h2>
	</div>
	<!-- Form di inserimento Videogioco -->
    	<div>
    		<h5>Aggiungi offerta</h5>
				<form action="GestioneOffertaServlet" method="post" id="formGlitch">
                     <label class="caratteriInserisci">Nome: </label><input class="form-control" type="text" name="nome" placeholder="Nome"> <br>
                     <label class="caratteriInserisci">Percentuale sconto: </label>
                   	 	<input class="form-control" type="number" min="1" max="100" name="sconto" placeholder="Percentuale sconto"><br>
                     <label class="caratteriInserisci">Categoria: </label><br>
				     <select name="categoria" class="custom-select">
				     	<option>Categoria</option>
				     	<option value="console">Console</option>
				     	<option value="videogioco">Videogioco</option>
				     </select>
                     <div class="form-group" style="text-align: right; margin-top: 3%">
                     	<input type="hidden" name="operazione" value="inserimento">
						<button class="btn btn-primary" type="submit">Aggiungi offerta</button>
					 </div>  
               </form>
    	</div><br>
	
	
</div>
</div>



<%@include file="Footer.html" %> 