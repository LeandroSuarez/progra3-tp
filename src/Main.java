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

            MatrizTDA<Casillero> laberinto = importador.generarLaberinto(casilleros, dimension);

            Posicion inicio = escaner.pedirInputDeCasillerosInicio();
            Posicion destino = escaner.pedirInputDeCasillerosDestino();

            teseo.backLab(laberinto, inicio, destino, 0, 0, new Matriz<>());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
