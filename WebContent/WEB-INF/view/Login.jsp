<!-- Modal Login che impedisce di effettuare il login se non sono compilati tutti i campi -->
<div class="modal fade" id="myLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<form action="LoginServlet" class="was-validated">
					<div class="form-group">
						<input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Accedi</button>
				</form>
			</div>
		</div>
	</div>
</div>
