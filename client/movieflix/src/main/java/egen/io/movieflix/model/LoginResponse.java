package egen.io.movieflix.model;

public class LoginResponse {

    private String token;
    private String userName;
    private String userRole;
    private int userId;

    public LoginResponse(final String token, final int userId, final String userName, final String userRole) {
        this.token = token;
        this.userName = userName;
        this.userRole = userRole;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
