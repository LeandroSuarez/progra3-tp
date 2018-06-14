import TDA.MatrizTDA;

import java.util.HashSet;
import java.util.Set;

public class Laberinto {

    public void backLab(MatrizTDA<Casillero> laberinto, Posicion inicio, Posicion destino, int costoSolucion,
                        int mejorCosto, MatrizTDA<Casillero> solucion) {

        int costoActual = laberinto.obtenerValor(inicio.x, inicio.y).costo;

        this.bloquearCasillero(inicio, laberinto);
        costoSolucion += costoActual;

        if (this.sonLaMismaPosicion(inicio, destino)) {
            if (costoSolucion > mejorCosto) {
                mejorCosto = costoSolucion;
                this.copiarMatriz(laberinto, solucion);
            }
        } else {
            Set<Posicion> libres = this.buscarLibres(laberinto, inicio);

            if (!libres.isEmpty()) {
                for (Posicion libre : libres) {
                    backLab(laberinto, libre, destino, costoSolucion, mejorCosto, solucion);
                }
            }
        }

        this.desbloquearCasillero(laberinto, inicio);
        costoSolucion -= costoActual;
    }

    private boolean sonLaMismaPosicion(Posicion p1, Posicion p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    private void bloquearCasillero(Posicion posicion, MatrizTDA<Casillero> tablero) {
        Casillero casillero = tablero.obtenerValor(posicion.x, posicion.y);
        casillero.accesible = false;
    }

    private void copiarMatriz(MatrizTDA<Casillero> m1, MatrizTDA<Casillero> m2) {
        m2.inicializarMatriz(m1.obtenerDimension());
    }

    private Set<Posicion> buscarLibres(MatrizTDA<Casillero> tablero, Posicion posicion) {

        Set<Posicion> libres = new HashSet<>();

        Posicion arriba = new Posicion(posicion.x, posicion.y-1);
        Posicion abajo = new Posicion(posicion.x, posicion.y+1);
        Posicion izquierda = new Posicion(posicion.x-1, posicion.y);
        Posicion derecha = new Posicion(posicion.x+1, posicion.y);

        if (this.esViable(tablero, arriba)) libres.add(arriba);
        if (this.esViable(tablero, abajo)) libres.add(abajo);
        if (this.esViable(tablero, izquierda)) libres.add(izquierda);
        if (this.esViable(tablero, derecha)) libres.add(derecha);

        return libres;
    }

    private boolean esViable(MatrizTDA<Casillero> tablero, Posicion posicion) {
        return tablero.obtenerValor(posicion.x, posicion.y).accesible; /* && verificar limites matriz*/
    }

    private void desbloquearCasillero(MatrizTDA<Casillero> tablero, Posicion posicion) {
        tablero.obtenerValor(posicion.x, posicion.y).accesible = true;
    }
}
