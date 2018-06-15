import Implementaciones.Matriz;
import TDA.MatrizTDA;
import TDA.VectorTDA;

public class Main {

    public static void main(String[] args) {
        try {
            Teseo teseo = new Teseo();
            Importador importador = new Importador();
            InputEscaner escaner = new InputEscaner();
            String inputUsuario = importador.leerArchivo(escaner.pedirRutaDeArchivo());

            Dimension dimension = importador.obtenerDimensiones(inputUsuario);
            VectorTDA<Casillero> casilleros = importador.obtenerCasilleros(inputUsuario,
                    dimension.columnas * dimension.filas);

            MatrizTDA<Casillero> tablero = importador.generarLaberinto(casilleros, dimension);
            Laberinto laberinto = new Laberinto(tablero, dimension);

            MatrizTDA<Casillero> tableroSolucion = new Matriz<>();

            Posicion inicio = escaner.pedirInputDeCasillerosInicio();
            Posicion destino = escaner.pedirInputDeCasillerosDestino();

            teseo.backLab(laberinto, inicio, destino, 0, 0, tableroSolucion);

            if (tableroSolucion.obtenerDimension() == 0) {
                String msj = "No hay un camino valido desde la posicion (%s) hasta (%s)";
                System.out.println(String.format(msj,inicio.toString(), destino.toString()));
            } else {
                System.out.println(Laberinto.representarTablero(tableroSolucion, dimension));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
