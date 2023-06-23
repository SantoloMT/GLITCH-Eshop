<!-- Modal E-mail che mi permette di poter rispondere ad un utente-->
<div class="modal fade" id="myEmail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
			<form action="RispostaAssistenzaServlet" method="post">
					<h2 class="text-center">Risposta</h2>
					<div class="form-group">
						<label><strong>Mittente: </strong>${richiesta.utenteEmail}</label>
					</div>
					<div class="form-group">
						<textarea class="form-control" name="descrizione" placeholder="Messaggio" rows="14"></textarea>
					</div>
					<div class="form-group">
						<input type="hidden" name="richiestaId" value="${richiesta.id }">
						<button type="submit" class="btn btn-primary btn-block">Invio risposta</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
