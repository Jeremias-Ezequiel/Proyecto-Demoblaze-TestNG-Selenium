package templates;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import utils.IFiltrarTipo;

@ExcelSheet("Proceso de pago")
public class ModeloProcesoPago implements IFiltrarTipo {
    @ExcelCellName("ID")
    private String id;
    @ExcelCellName("NOMBRE")
    private String nombre;
    @ExcelCellName("PAIS")
    private String pais;
    @ExcelCellName("CIUDAD")
    private String ciudad;
    @ExcelCellName("TARJETA")
    private String tarjeta;
    @ExcelCellName("MES")
    private String mes;
    @ExcelCellName("YEAR")
    private String year;
    @ExcelCellName("TIPO")
    private String tipo;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre == null ? "" : nombre;
    }

    public String getPais() {
        return pais == null ? "" : pais;
    }

    public String getCiudad() {
        return ciudad == null ? "" : ciudad;
    }

    public String getTarjeta() {
        return tarjeta == null ? "" : tarjeta;
    }

    public String getMes() {
        return mes == null ? "" : mes;
    }

    public String getYear() {
        return year == null ? "" : year;
    }

    public String getTipo() {
        return tipo == null ? "" : tipo;
    }

    @Override
    public String toString() {
        String multiline = """
                {
                    id : %s,
                    nombre : %s,
                    pais : %s,
                    ciudad : %s,
                    tarjeta : %s,
                    mes : %s,
                    año : %s,
                    tipoDeCaso : %s
                }
                """;
        return  String.format(multiline,id,nombre,pais,ciudad,tarjeta,mes,year,tipo);
    }


    @Override
    public String obtenerTipo() {
        return tipo;
    }

    @Override
    public String obtenerId() {
        return id;
    }
}
