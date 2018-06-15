import TDA.MatrizTDA;

public class Laberinto {

    public MatrizTDA<Casillero> tablero;

    public Dimension dimension;

    public Laberinto(MatrizTDA<Casillero> tablero, Dimension dimension) {
        this.tablero = tablero;
        this.dimension = dimension;
    }
}
