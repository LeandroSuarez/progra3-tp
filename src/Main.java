import Implementaciones.Matriz;
import TDA.MatrizTDA;
import TDA.VectorTDA;

public class Main {

    public static void main(String[] args) {
        try {
            BuscadorDeCaminos buscadorDeCaminos = new BuscadorDeCaminos();
            Importador importador = new Importador();
            InputEscaner escaner = new InputEscaner();

            String inputUsuario = importador.leerArchivo(escaner.pedirRutaDeArchivo());

            Dimension dimension = importador.obtenerDimensiones(inputUsuario);
            VectorTDA<Casillero> casilleros = importador.obtenerCasilleros(inputUsuario,
                    dimension.columnas * dimension.filas);

            MatrizTDA<Casillero> tablero = importador.generarLaberinto(casilleros, dimension);
            System.out.println("El tablero de entrada es el siguiente:");
            System.out.println(UtilidadDeTablero.representarTablero(tablero, dimension));

            Laberinto laberinto = new Laberinto(tablero, dimension);

            MatrizTDA<Casillero> tableroSolucion = new Matriz<>();

            Posicion inicio = escaner.pedirInputDeCasillerosInicio();
            Posicion destino = escaner.pedirInputDeCasillerosDestino();

            System.out.println("\n\nBuscando solución a través de BackTracking...");
            buscadorDeCaminos.backLab(laberinto, inicio, destino, 0, 0, tableroSolucion);

            if (tableroSolucion.obtenerDimension() == 0) {
                String msj = "No hay un camino valido desde la posicion (%s) hasta (%s)";
                System.out.println(String.format(msj,inicio.toString(), destino.toString()));
            } else {
                System.out.println("\n\nEl tablero de SOLUCIÓN es el siguiente:");
                System.out.println(UtilidadDeTablero.representarTablero(tableroSolucion, dimension));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
