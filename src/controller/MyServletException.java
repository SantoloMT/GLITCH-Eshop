
package controller;

import javax.servlet.ServletException;

//MyServletException permette di lanciare eccezioni riguardanti errori specifici sui dati del sito

public class MyServletException extends ServletException {
	private static final long serialVersionUID = -8976023136478643816L;

	public MyServletException() {
		super();
	}

	public MyServletException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public MyServletException(String message) {
		super(message);
	}

	public MyServletException(Throwable rootCause) {
		super(rootCause);
	}

}
