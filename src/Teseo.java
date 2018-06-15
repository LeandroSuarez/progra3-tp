import Implementaciones.Conjunto;
import TDA.ConjuntoTDA;
import TDA.MatrizTDA;

import java.util.HashSet;
import java.util.Set;

public class Teseo {

    public void backLab(Laberinto laberinto, Posicion inicio, Posicion destino, int costoSolucion,
                        int mejorCosto, MatrizTDA<Casillero> solucion) {

        int costoActual = laberinto.tablero.obtenerValor(inicio.x, inicio.y).costo;

        this.bloquearCasillero(inicio, laberinto);
        costoSolucion += costoActual;

        if (this.sonLaMismaPosicion(inicio, destino)) {
            if (costoSolucion > mejorCosto) {
                mejorCosto = costoSolucion;
                this.copiarMatriz(laberinto.tablero, solucion);
            }
        } else {
            ConjuntoTDA<Posicion> libres = this.buscarLibres(laberinto, inicio);

            while(!libres.conjuntoVacio()) {
                Posicion libre = libres.elegir();

                backLab(laberinto, libre, destino, costoSolucion, mejorCosto, solucion);

                libres.sacar(libre);
            }
        }


        this.desbloquearCasillero(laberinto, inicio);
        costoSolucion -= costoActual;
    }

    private boolean sonLaMismaPosicion(Posicion p1, Posicion p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    private void bloquearCasillero(Posicion posicion, Laberinto laberinto) {
        Casillero casillero = laberinto.tablero.obtenerValor(posicion.x, posicion.y);
        casillero.accesible = false;
    }

    private void copiarMatriz(MatrizTDA<Casillero> m1, MatrizTDA<Casillero> m2) {
        m2.inicializarMatriz(m1.obtenerDimension());
    }

    private ConjuntoTDA<Posicion> buscarLibres(Laberinto laberinto, Posicion posicion) {

        ConjuntoTDA<Posicion> libres = new Conjunto<>();
        libres.inicializarConjunto();

        Posicion arriba = new Posicion(posicion.x-1, posicion.y);
        Posicion abajo = new Posicion(posicion.x+1, posicion.y);
        Posicion izquierda = new Posicion(posicion.x, posicion.y-1);
        Posicion derecha = new Posicion(posicion.x, posicion.y+1);

        if (this.esUnCasilleroValido(laberinto, arriba)) libres.agregar(arriba);
        if (this.esUnCasilleroValido(laberinto, abajo)) libres.agregar(abajo);
        if (this.esUnCasilleroValido(laberinto, izquierda)) libres.agregar(izquierda);
        if (this.esUnCasilleroValido(laberinto, derecha)) libres.agregar(derecha);

        return libres;
    }

    private boolean esUnCasilleroValido(Laberinto laberinto, Posicion posicion) {
        boolean dentroDeLosLimites = this.posicionValida(posicion, laberinto.dimension);
        if (!dentroDeLosLimites) return false;
        return laberinto.tablero.obtenerValor(posicion.x, posicion.y).accesible;
    }

    private boolean posicionValida(Posicion posicion, Dimension dimension) {
        boolean validoEnX = posicion.x < dimension.filas && posicion.x > -1;
        boolean validoEnY = posicion.y < dimension.columnas && posicion.y > -1;
        return validoEnX && validoEnY;
    }

    private void desbloquearCasillero(Laberinto laberinto, Posicion posicion) {
        laberinto.tablero.obtenerValor(posicion.x, posicion.y).accesible = true;
    }
}
