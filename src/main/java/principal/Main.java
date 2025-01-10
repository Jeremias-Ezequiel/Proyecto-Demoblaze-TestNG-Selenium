package principal;

import java.util.List;

import reader.ExcelReader;
import templates.ModeloProcesoPago;

public class Main {
    public static void main(String[] args) {
        String casoNegativo = "NEG"; 

        List<ModeloProcesoPago> listaCompleta = ExcelReader.obtenerExcel(ModeloProcesoPago.class); 
        List<ModeloProcesoPago> listaFiltrada = ExcelReader.filtrarPorTipo(listaCompleta, casoNegativo);
    
        for(ModeloProcesoPago dato : listaFiltrada){
            System.out.println(dato);
        }
    }
}
