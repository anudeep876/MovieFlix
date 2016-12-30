package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class RatingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4500250788558549465L;

	public RatingNotFoundException(String message) {
		super(message);
	}
}