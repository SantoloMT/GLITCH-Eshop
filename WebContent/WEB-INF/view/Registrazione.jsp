<!-- Modal Registrazione che impedisce di effettuare la registrazione se non sono compilati tutti i campi-->
<div class="modal fade" id="myRegistrazione" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<form action="RegistrazioneServlet" class="was-validated">
					<div class="form-group">
						<input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="cognome" placeholder="Cognome" name="cognome" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="email" placeholder="E-mail" name="email" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="citta" placeholder="Città" name="citta" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="provincia" placeholder="Provincia" name="provincia" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="via" placeholder="Via" name="via" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="numero" placeholder="Numero" name="numero" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="CAP" placeholder="CAP" name="CAP" required>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Registrazione</button>
				</form>
			</div>
		</div>
	</div>
</div>
