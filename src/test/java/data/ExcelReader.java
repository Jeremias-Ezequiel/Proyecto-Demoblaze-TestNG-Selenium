package data;

import com.poiji.bind.Poiji;

import interfaces.FiltrarTipo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private static final String excelPath = "src/main/java/data/DatosEntrada.xlsx";

    public static <T> List<T> obtenerExcel(Class<T> clase){
        return  Poiji.fromExcel(new File(excelPath), clase);
    }

    public static <T extends FiltrarTipo> List<T> filtrarPorTipo(List<T> listaCompleta, String tipo){
        List<T> listaFiltrada = new ArrayList<>();

        for(T objeto : listaCompleta){
            if(objeto.obtenerTipo().equalsIgnoreCase(tipo)){
                listaFiltrada.add(objeto);
            }
        }
        return listaFiltrada;
    }

    public static <T extends FiltrarTipo> T filtrarPorId(List<T> listaCompleta, String id){
        T datos = null;

        for(T objeto : listaCompleta){
            if(objeto.obtenerId().equalsIgnoreCase(id)){
                datos = objeto;
                break;
            }
        }
        return datos;
    }

}
