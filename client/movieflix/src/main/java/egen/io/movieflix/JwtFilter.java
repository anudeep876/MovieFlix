package egen.io.movieflix;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        if (skipUrls(request.getRequestURL().toString())) {
            chain.doFilter(req, res);
        } else {
//            final String authHeader = request.getHeader("Authorization");
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                throw new ServletException("Missing or invalid Authorization header.");
//            }
//
//            final String token = authHeader.substring(7); // The part after "Bearer "
//
//            try {
//                final Claims claims = Jwts.parser().setSigningKey("secretkey")
//                        .parseClaimsJws(token).getBody();
//                request.setAttribute("claims", claims);
//            } catch (final ExpiredJwtException e) {
//                throw new ServletException("Token Expired");
//            } catch (final SignatureException e) {
//                throw new ServletException("Invalid token.");
//            }

            chain.doFilter(req, res);
        }
    }

    public boolean skipUrls(String url) {
        boolean skip = false;
        List<String> skipUrlsList = new ArrayList();
        skipUrlsList.add("/auth/login");
        skipUrlsList.add("/auth/logout");
        skipUrlsList.add("/users");
        skipUrlsList.add("/movies/getMoviePosters");
        for (String urlSkip : skipUrlsList) {
            if (url.endsWith(urlSkip)) {
                skip = true;
                break;
            }
        }
        return skip;

    }
}
