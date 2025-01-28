package modelos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredencialesModel {
    @JsonProperty("username")
    private String username; 
    @JsonProperty("password")
    private String password;
    @JsonProperty("message")
    private String message;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    } 

    
}
