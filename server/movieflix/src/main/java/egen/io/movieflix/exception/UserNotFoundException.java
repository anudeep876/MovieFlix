package egen.io.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason = "User does not exist")
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6802637946933629333L;

	public UserNotFoundException(String message) {
		super(message);
	}
}