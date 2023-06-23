<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<div class="home">
<!-- Carousel pubblicitario -->
<div class="carousel slide" data-ride="carousel" id="carousel-2">
    <div class="carousel-inner" role="listbox">
        <video autoplay muted loop id="myVideo">
        	<source src="giochi.mp4" type="video/mp4">  
        </video>
    </div>
</div>
<div class="features-boxed">
	<!-- Button per filtrare i prodotti del catalogo -->
	<div class="container text-monospace d-lg-flex justify-content-lg-center visible">
		<div role="group" class="btn-group" id="myBtnContainer">
			<button class="btn btn-primary active" type="button" onclick="filterSelection('all')">Catalogo</button>
			<button class="btn btn-primary" type="button" onclick="filterSelection('console')">Console</button>
			<button class="btn btn-primary" type="button" onclick="filterSelection('videogioco')">Videogiochi</button>
		</div>
	</div>
	<div class="container">
		<!-- Due cicli per visualizzare sia i videogiochi sia le console -->
		 <c:forEach items="${videogiochi}" var="videogiochi">
		 <div class="filterDiv videogioco">
		 <div class="row">
			<div class="col-sm-6 col-md-6 col-lg-6 item">
				<img class="imgFluid" src="${videogiochi.immagine}" />
			</div>
			<div class="col-sm-6 col-md-6 col-lg-6 item">
				<div class="box">
					<h3 class="name"><c:out value="${videogiochi.nome}" /></h3>
					<p class="description"><c:out value="${videogiochi.genere}" /></p>
					<p class="description"><c:out value="${videogiochi.piattaforma}" /></p>
					<p class="description"><c:out value="${videogiochi.prezzo}" />€</p>
					<c:choose>
                 		<c:when test="${utente == null}">
							<form>
								<input type="hidden" name="prodId" value="${videogiochi.id}">
								<input type="submit" class="btnGlitch" value="Aggiungi al carrello" disabled="disabled">  
                 			</form>				
						</c:when>
						<c:otherwise>           
                 			<form action="GestioneCarrelloServlet">
                 				<label>Quantità: <input type="number" class="form-control-sm d-sm-flex d-md-flex d-lg-flex" name="number" placeholder="1" min="1" max="20" value="1" /></label>
								<input type="hidden" name="prodId" value="${videogiochi.id}">
								<input type="hidden" name="operazione" value="inserimento"><br>
								<input type="submit" class="btnGlitch" value="Aggiungi al carrello">  
                 			</form>	
                 		</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		</div>
		</c:forEach>
		<c:forEach items="${console}" var="console">
		<div class="filterDiv console">
		<div class="row">
			<div class="col-sm-6 col-md-6 col-lg-6 item">
					<img class="imgFluid" src="${console.immagine}" />
			</div>
			<div class="col-sm-6 col-md-6 col-lg-6 item">
				<div class="box">
					<h3 class="name"><c:out value="${console.modello}" /></h3>
					<p class="description"><c:out value="${console.casaProduttrice}" /></p>
					<p class="description"><c:out value="${console.prezzo}" />€</p>
					<c:choose>
                 		<c:when test="${utente == null}">
							<form>
								<input type="hidden" name="prodId" value="${console.id}">
								<input type="submit" class="btnGlitch" value="Aggiungi al carrello" disabled="disabled">  
                 			</form>				
						</c:when>
						<c:otherwise>           
                 			<form action="GestioneCarrelloServlet">
                 				<label>Quantità: <input type="number" class="form-control-sm d-sm-flex d-md-flex d-lg-flex" name="number" placeholder="1" min="1" max="20" value="1" /></label><br>
								<input type="hidden" name="prodId" value="${console.id}">
								<input type="hidden" name="operazione" value="inserimento"><br>
								<input type="submit" class="btnGlitch" value="Aggiungi al carrello">  
                 			</form>	
                 		</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		</div>
		</c:forEach>
		</div>
	</div>
	<!-- Paginazione -->
	<div class="container text-monospace d-lg-flex justify-content-lg-center visible">
		<ul class="pagination">
			<li class="page-item">
			<form action="PaginazioneServlet">
				<input type="hidden" name="page" value="prec">
				<button class="page-link"  aria-label="Previous"><span aria-hidden="true">«</span></button>
				</form></li>
			<li class="page-item">
			<form action="PaginazioneServlet">
				<input type="hidden" name="page" value="one">
				<button class="page-link" >1</button>
				</form></li>
			<li class="page-item">
			<form action="PaginazioneServlet">
				<input type="hidden" name="page" value="two">
				<button class="page-link" >2</button>
				</form></li>
			<li class="page-item">
			<form action="PaginazioneServlet">
				<input type="hidden"  name="page" value="three">
				<button class="page-link">3</button>
				</form></li>
			<li class="page-item">
			<form action="PaginazioneServlet">
				<input type="hidden" name="page" value="next">
				<button class="page-link"  aria-label="Next"><span aria-hidden="true">»</span></button>
				</form></li>
			</ul>
	</div>
</div>



<script>
filterSelection("all")
function filterSelection(c) {
  var x, i;
  x = document.getElementsByClassName("filterDiv");
  if (c == "all") 
	  c = "";
  for (i = 0; i < x.length; i++) {
    RemoveClass(x[i], "show");
    if (x[i].className.indexOf(c) > -1) AddClass(x[i], "show");
  }
}

function AddClass(element, name) {
  var i, arr1, arr2;
  arr1 = element.className.split(" ");
  arr2 = name.split(" ");
  for (i = 0; i < arr2.length; i++) {
    if (arr1.indexOf(arr2[i]) == -1) {element.className += " " + arr2[i];}
  }
}

function RemoveClass(element, name) {
  var i, arr1, arr2;
  arr1 = element.className.split(" ");
  arr2 = name.split(" ");
  for (i = 0; i < arr2.length; i++) {
    while (arr1.indexOf(arr2[i]) > -1) {
      arr1.splice(arr1.indexOf(arr2[i]), 1);     
    }
  }
  element.className = arr1.join(" ");
}

var btnContainer = document.getElementById("myBtnContainer");
var btns = btnContainer.getElementsByClassName("btn btn-primary");
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function(){
    var current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });
}
</script>



<%@include file="Footer.html" %> 