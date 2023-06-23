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
    		<h3>Lista Console</h3>
				<table class="table table-striped table-hover my-5">
					<thead>
						<tr>
							<th>Codice</th>
							<th>Modello</th>
							<th>Produttore</th>
							<th>Prezzo</th>
							<th>Rimozione</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${Consoles}" var="console">
							<tr class="text-center">
								<td><c:out value="${console.id}"/></td>
								<td><c:out value="${console.modello}"/></td>
								<td><c:out value="${console.casaProduttrice}"/></td>
		                  		<td><c:out value="${console.prezzo}"/></td>
		                  		<td>
		                  			<form action="GestioneProdottoServlet" method="post">
										<input type="hidden" name="prodId" value="${console.id}">
										<input type="hidden" name="operazione" value="rimozione">
										<button type="submit">Rimuovi</button>
									</form>
		                  		</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
				
    		<h3>Lista Videogiochi</h3>
				<table class="table table-striped table-hover my-5">
					<thead>
						<tr>
							<th>Codice</th>
							<th>Nome</th>
							<th>Genere</th>
							<th>Prezzo</th>
							<th>Rimozione</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${Videogiochi}" var="videogiochi">
							<tr class="text-center">
								<td><c:out value="${videogiochi.id}"/></td>
								<td><c:out value="${videogiochi.nome}"/></td>
								<td><c:out value="${videogiochi.genere}"/></td>
		                  		<td><c:out value="${videogiochi.prezzo}"/></td>
		                  		<td>
		                  		<form action="GestioneProdottoServlet" method="post">
									<input type="hidden" name="prodId" value="${videogiochi.id}">
									<input type="hidden" name="operazione" value="rimozione">
									<button type="submit">Rimuovi</button>
								</form>
		                  			
		                  		</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>
    	</div>
	
	</div>
	
	<div class="container-fluid">
		<h3>Nuovi prodotti</h3>
	</div>
	
	<!-- Form di inserimento Console -->
    	<div>
    		<h5>Aggiungi console</h5>
				<form action="GestioneProdottoServlet" method="post" id="formGlitch">
                     <label class="caratteriInserisci">Modello: </label><input class="form-control" type="text" name="modello" placeholder="Modello"> <br>
                     <label class="caratteriInserisci">Casa produttrice: </label><input class="form-control" type="text" name="casaProduttrice" placeholder="Produttore"> <br>
                     <label class="caratteriInserisci">URL Immagine: </label><input class="form-control" type="text" name="immagine" placeholder="URL Immagine"> <br>
                     <label class="caratteriInserisci">Prezzo: </label><input class="form-control" type="text" name="prezzo" placeholder="Prezzo"> <br> 
                     <label class="caratteriInserisci">Descrizione: </label><textarea class="form-control" name="descrizione" placeholder="Descrizione" rows="14"></textarea><br>
                     <div class="form-group" style="text-align: right;">
                     	<input type="hidden" name="operazione" value="inserimento">
						<button class="btn btn-primary" type="submit">Aggiungi console</button>
					 </div> 
               </form>
    	</div><br>
    	
    <!-- Form di inserimento Videogioco -->
    	<div>
    		<h5>Aggiungi videogioco</h5>
				<form action="GestioneProdottoServlet" method="post" id="formGlitch">
                     <label class="caratteriInserisci">Nome: </label><input class="form-control" type="text" name="nome" placeholder="Nome"> <br>
                     <label class="caratteriInserisci">Genere: </label><input class="form-control" type="text" name="genere" placeholder="Genere"> <br>
                     <label class="caratteriInserisci">URL Immagine: </label><input class="form-control" type="text" name="immagine" placeholder="URL Immagine"> <br>
                     <label class="caratteriInserisci">Prezzo: </label><input class="form-control" type="text" name="prezzo" placeholder="Prezzo"> <br> 
                     <label class="caratteriInserisci">Descrizione: </label><textarea class="form-control" name="descrizione" placeholder="Descrizione" rows="14"></textarea><br>
                     <div class="form-group" style="text-align: right;">
                     <input type="hidden" name="operazione" value="inserimento">
						<button class="btn btn-primary" type="submit">Aggiungi videogioco</button>
					 </div>  
               </form>
    	</div><br>
</div>



<%@include file="Footer.html" %> 