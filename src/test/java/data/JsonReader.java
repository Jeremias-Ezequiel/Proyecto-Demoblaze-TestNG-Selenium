package data;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import modelos.CredencialJson;
import utilities.Logs;
import java.io.File;

public class JsonReader {
    private static final String jsonPath = "src/test/resources/data/credenciales.json"; 

    public static CredencialJson obtenerMapCredenciales(){
        ObjectMapper objectMapper = new ObjectMapper(); 

        try{
            return objectMapper.readValue(new File(jsonPath), CredencialJson.class); 
        }catch(IOException e){
            Logs.error("Error al leer el json: %s",e.getLocalizedMessage());
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
