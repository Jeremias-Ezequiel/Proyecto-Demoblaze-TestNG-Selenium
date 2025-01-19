package templates;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import utils.IFiltrarTipo;

@ExcelSheet("Registro de usuario")
public class ModeloRegistroUsuario implements IFiltrarTipo {
    @ExcelCellName("ID")
    private String id;
    @ExcelCellName("USUARIO")
    private String usuario;
    @ExcelCellName("PASSWORD")
    private String password;
    @ExcelCellName("TIPO")
    private String tipo;

    public String getId() {
        return id == null ? "" : id;
    }

    public String getUsuario() {
        return usuario == null ? "" : usuario;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public String getTipo() {
        return tipo == null ? "" : tipo;
    }

    @Override
    public String toString() {
        String multiline = """
                {
                    id : %s,
                    Usuario : %s,
                    Contraseña : %s,
                    Tipo : %s
                }
                """;
        return String.format(multiline,id,usuario,password,tipo);
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
