import TDA.ConjuntoTDA;
import TDA.MatrizTDA;

public class BuscadorDeCaminos {

    public void backLab(Laberinto laberinto, Posicion inicio, Posicion destino, int costoSolucion,
                        int mejorCosto, MatrizTDA<Casillero> solucion) {

        int costoActual = laberinto.tablero.obtenerValor(inicio.x, inicio.y).costo;

        UtilidadDeTablero.bloquearCasillero(inicio, laberinto);
        costoSolucion += costoActual;

        if (UtilidadDeTablero.sonLaMismaPosicion(inicio, destino)) {

            if (costoSolucion > mejorCosto) {
                mejorCosto = costoSolucion;
                UtilidadDeTablero.copiarTablero(laberinto.tablero, solucion);
            }

        } else {

            ConjuntoTDA<Posicion> libres = UtilidadDeTablero.buscarLibres(laberinto, inicio);

            while(!libres.conjuntoVacio()) {
                Posicion libre = libres.elegir();

                backLab(laberinto, libre, destino, costoSolucion, mejorCosto, solucion);

                libres.sacar(libre);
            }
        }

        UtilidadDeTablero.desbloquearCasillero(laberinto, inicio);
        costoSolucion -= costoActual;
    }
}
