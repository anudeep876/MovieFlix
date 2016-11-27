package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason="User already exists")
public class UserAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 3861116823655864474L;

	public UserAlreadyExistException(String message) {
		super(message);
	}
}