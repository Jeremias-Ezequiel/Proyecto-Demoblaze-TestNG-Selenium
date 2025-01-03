package data;

import java.util.Map;

import modelos.ModeloCredenciales;

public class DataGiven {
    public static Map<String,ModeloCredenciales> obtenerMapCredenciales(){
        return JsonReader.obtenerMapCredenciales().getMapCredenciales();
    }

    public static ModeloCredenciales getCredencialValida(){
        return obtenerMapCredenciales().get("valido");
    }

    public static ModeloCredenciales getCredencialInvalida(){
        return obtenerMapCredenciales().get("invalido");
    }

    public static ModeloCredenciales getCredencialInexistente(){
        return obtenerMapCredenciales().get("inexistente");
    }
}
