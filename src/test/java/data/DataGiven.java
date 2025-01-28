package data;

import java.util.Map;

import modelos.CredencialesModel;

public class DataGiven {
    public static Map<String,CredencialesModel> obtenerMapCredenciales(){
        return JsonReader.obtenerMapCredenciales().getMapCredenciales();
    }

    public static CredencialesModel getCredencialValida(){
        return obtenerMapCredenciales().get("valido");
    }

    public static CredencialesModel getCredencialInvalida(){
        return obtenerMapCredenciales().get("invalido");
    }

    public static CredencialesModel getCredencialInexistente(){
        return obtenerMapCredenciales().get("inexistente");
    }
}
