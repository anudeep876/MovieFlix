package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RatingAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -8103551137473934109L;

	public RatingAlreadyExistException(String message) {
		super(message);
	}
}