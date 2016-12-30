package egen.io.movieflix.authentication;

import egen.io.movieflix.entity.User;
import egen.io.movieflix.model.LoginResponse;
import egen.io.movieflix.model.UserLogin;
import egen.io.movieflix.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
            throws ServletException {
        if (login.getUserName() == null) {
            throw new ServletException("Invalid login");
        }
        User user = userService.authUser(login.getUserName(), login.getPassword());
        String token = Jwts.builder().setSubject(login.getUserName())
                .claim("roles", user.getUserAccountType()).setExpiration(new Date((new Date()).getTime() + (30 * 60 * 1000))).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return new LoginResponse(token, user.getId(), user.getUserName(), user.getUserAccountType());
    }
}
