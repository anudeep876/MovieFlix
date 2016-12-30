package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 5131432623404950911L;

	public MovieNotFoundException(String message) {
		super(message);
	}
}