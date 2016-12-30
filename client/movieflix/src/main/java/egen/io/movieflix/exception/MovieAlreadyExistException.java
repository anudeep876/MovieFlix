package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MovieAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 5407502547383533439L;

	public MovieAlreadyExistException(String message) {
		super(message);
	}
}