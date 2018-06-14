import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Importador {

    private BufferedReader reader = null;

    public File abrirArchivo(String rutaDestino) {
        return new File(rutaDestino);
    }

    public String leerArchivo(File archivo) throws IOException {
        String texto;

        try {
            reader = new BufferedReader(new FileReader(archivo));

            while((texto = reader.readLine()) != null) {
                System.out.println(texto);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return texto;
    }
    public List<Casillero> obtenerCasilleros(String texto) {
        Pattern pattern1 = Pattern.compile("/\\d,\\d/");
        Matcher matcher1 = pattern1.matcher(texto);
        matcher1.
        String dimensiones = texto.("\\d,\\d");
        String[] casilleros = texto.split("\\(\\w,-?(\\d+|\\w)\\)");
    }
}
