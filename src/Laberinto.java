import TDA.MatrizTDA;

public class Laberinto {

    public MatrizTDA<Casillero> tablero;

    public Dimension dimension;

    public Laberinto(MatrizTDA<Casillero> tablero, Dimension dimension) {
        this.tablero = tablero;
        this.dimension = dimension;
    }

    public static String representarTablero(MatrizTDA<Casillero> tablero, Dimension dimension) {
        String tableroString = "";
        for (int i = 0; i < dimension.filas; i++) {
            for (int j = 0; j < dimension.columnas; j++) {
                Casillero casillero = tablero.obtenerValor(i, j);
                tableroString = tableroString.concat(casillero.toString());
            }
            tableroString = tableroString.concat("\n");
        }
        return tableroString;
    }
}
