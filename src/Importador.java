import Implementaciones.Matriz;
import Implementaciones.Vector;
import TDA.MatrizTDA;
import TDA.VectorTDA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Importador {

    private BufferedReader reader = null;
    private Pattern dimensionesPattern = Pattern.compile("\\d,\\d");
    private Pattern casillerosPattern = Pattern.compile("\\(\\w,-?(\\d+|\\w)\\)");

    public String leerArchivo(String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);
        String texto;

        try {
            reader = new BufferedReader(new FileReader(archivo));

            while((texto = reader.readLine()) != null) {
                //System.out.println(texto);
                return texto;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        throw new RuntimeException("Error al leer el archivo");
    }

    public Dimension obtenerDimensiones(String texto) {
        Matcher matcher = dimensionesPattern.matcher(texto);

        while(matcher.find()) {
            String dimension = matcher.group();
            String[] filasYColumnas = dimension.split(",");
            if (filasYColumnas.length > 1) {
                return new Dimension(Integer.parseInt(filasYColumnas[0]), Integer.parseInt(filasYColumnas[1]));
            }
        }
        throw new RuntimeException("Input de dimensiones invalido");
    }

    public VectorTDA<Casillero> obtenerCasilleros(String texto, int dimension) {
        VectorTDA<Casillero> casilleros = new Vector<>();
        casilleros.inicializarVector(dimension);

        try {
            Matcher matcher = casillerosPattern.matcher(texto);
            int contador = 0;

            while (matcher.find()) {
                String casillero = matcher.group();
                String[] atributos = casillero.replace("(", "")
                        .replace(")", "").split(",");

                if (atributos.length > 1) {
                    String accesible = atributos[0];
                    String costo = atributos[1];
                    casilleros.agregarElemento(contador, new Casillero(costo, accesible));
                    contador++;
                } else {
                    throw new RuntimeException("Input de casilleros invalido");
                }
            }
        } catch(Exception e) {
            System.out.println("Error al obtener casilleros desde el archivo");
        }
        return casilleros;
    }

    public MatrizTDA<Casillero> generarLaberinto(VectorTDA<Casillero> casilleros, Dimension dimension) {
        try {
            MatrizTDA<Casillero> laberinto = new Matriz<>();
            int maxDim = dimension.filas > dimension.columnas ? dimension.filas : dimension.columnas;
            laberinto.inicializarMatriz(maxDim);
            int contador = 0;
            for (int i = 0; i < dimension.filas; i++) {
                for (int j = 0; j < dimension.columnas; j++) {
                    laberinto.setearValor(i, j, casilleros.recuperarElemento(contador));
                    contador++;
                }
            }
            return laberinto;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el laberinto");
        }
    }
}
