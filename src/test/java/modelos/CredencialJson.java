package modelos;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredencialJson {
    @JsonProperty("credenciales")
    private Map<String, CredencialesModel> mapCredenciales;

    public Map<String, CredencialesModel> getMapCredenciales() {
        return mapCredenciales;
    } 

}
