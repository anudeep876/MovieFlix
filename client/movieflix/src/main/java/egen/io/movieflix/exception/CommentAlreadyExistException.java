package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CommentAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 4164626020179341967L;

	public CommentAlreadyExistException(String message) {
		super(message);
	}
}
