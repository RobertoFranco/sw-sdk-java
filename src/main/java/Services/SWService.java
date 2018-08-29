package Services;

import java.io.IOException;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.Authentication.AuthOptionsRequest;
import Utils.Requests.Authentication.AuthRequest;
import Utils.Responses.SuccessAuthResponse;

public abstract class SWService {
    private String Token = null;
    private String User = null;
    private String Password = null;

    public String getToken() {
        return Token;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }

    public String getURI() {
        return URI;
    }

    private String URI;

    protected SWService(String user, String password, String URI) {
        User = user;
        Password = password;
        this.URI = URI;
    }

    protected SWService(String token, String URI) {

        Token = token;
        this.URI = URI;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    public void generateToken() throws AuthException, GeneralException, IOException {

        if (User == null || Password == null) {
            throw new AuthException(400, "no existen elementos de autenticación");
        }
        AuthOptionsRequest settings = new AuthOptionsRequest(URI, getUser(), getPassword());
        AuthRequest req = new AuthRequest();
        SuccessAuthResponse res = (SuccessAuthResponse) req.sendRequest(settings);
        if (res.HttpStatusCode == 200) {
            setToken(res.token);
        } else {
            throw new AuthException(res.HttpStatusCode, res.message);
        }
    }
}
