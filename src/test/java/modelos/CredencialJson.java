package modelos;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CredencialJson {
    @JsonProperty("credenciales")
    private Map<String, ModeloCredenciales> mapCredenciales;

    public Map<String, ModeloCredenciales> getMapCredenciales() {
        return mapCredenciales;
    } 

}
