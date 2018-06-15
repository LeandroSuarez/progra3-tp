import Implementaciones.Conjunto;
import TDA.ConjuntoTDA;
import TDA.MatrizTDA;

public class UtilidadDeTablero {

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

    public static void copiarTablero(MatrizTDA<Casillero> origen, MatrizTDA<Casillero> destino) {
        int dim = origen.obtenerDimension();
        destino.inicializarMatriz(dim);

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Casillero casillero = origen.obtenerValor(i, j);
                if (casillero == null) continue;
                Casillero nuevoCasillero = new Casillero(casillero.costo, casillero.accesible, casillero.visitado);
                destino.setearValor(i, j, nuevoCasillero);
            }
        }
    }

    public static ConjuntoTDA<Posicion> buscarLibres(Laberinto laberinto, Posicion posicion) {

        ConjuntoTDA<Posicion> libres = new Conjunto<>();
        libres.inicializarConjunto();

        Posicion arriba = new Posicion(posicion.x-1, posicion.y);
        Posicion abajo = new Posicion(posicion.x+1, posicion.y);
        Posicion izquierda = new Posicion(posicion.x, posicion.y-1);
        Posicion derecha = new Posicion(posicion.x, posicion.y+1);

        if (esUnCasilleroValido(laberinto, arriba)) libres.agregar(arriba);
        if (esUnCasilleroValido(laberinto, abajo)) libres.agregar(abajo);
        if (esUnCasilleroValido(laberinto, izquierda)) libres.agregar(izquierda);
        if (esUnCasilleroValido(laberinto, derecha)) libres.agregar(derecha);

        return libres;
    }

    private static boolean esUnCasilleroValido(Laberinto laberinto, Posicion posicion) {
        boolean dentroDeLosLimites = posicionValida(posicion, laberinto.dimension);
        if (!dentroDeLosLimites) return false;
        Casillero casillero = laberinto.tablero.obtenerValor(posicion.x, posicion.y);
        return casillero.accesible && !casillero.visitado;
    }

    private static boolean posicionValida(Posicion posicion, Dimension dimension) {
        boolean validoEnX = posicion.x < dimension.filas && posicion.x > -1;
        boolean validoEnY = posicion.y < dimension.columnas && posicion.y > -1;
        return validoEnX && validoEnY;
    }

    public static boolean sonLaMismaPosicion(Posicion p1, Posicion p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    public static void bloquearCasillero(Posicion posicion, Laberinto laberinto) {
        Casillero casillero = laberinto.tablero.obtenerValor(posicion.x, posicion.y);
        casillero.visitado = true;
        casillero.accesible = false;
    }

    public static void desbloquearCasillero(Laberinto laberinto, Posicion posicion) {
        Casillero casillero = laberinto.tablero.obtenerValor(posicion.x, posicion.y);
        casillero.visitado = false;
        casillero.accesible = true;
    }
}
