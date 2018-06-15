import java.util.Scanner;

public class InputEscaner {

    private Scanner scanner;
    private static final String MENSAJE_INPUT_CASILLEROS = "Por favor ingrese la posición del casillero de %s (separado por coma):";
    private static final String MENSAJE_INPUT_ARCHIVO = "Por favor ingrese el archivo a leer:";
    private static final String INICIO= "INICIO";
    private static final String DESTINO= "DESTINO";

    public String pedirRutaDeArchivo() {
        /*System.out.println(MENSAJE_INPUT_ARCHIVO);
        scanner = new Scanner(System.in);
        String ruta = scanner.nextLine();*/
        return System.getProperty("user.dir") + "/" + "laberinto"/*ruta*/ + ".txt";
    }

    public Posicion pedirInputDeCasillerosInicio() {
        //return pedirInputDeCasilleros(INICIO);
        return new Posicion(0,0);
    }

    public Posicion pedirInputDeCasillerosDestino() {
        //return pedirInputDeCasilleros(DESTINO);
        return new Posicion(1,1);
    }

    private Posicion pedirInputDeCasilleros(String tipoDeCasillero) {
        scanner = new Scanner(System.in);
        System.out.println(String.format(MENSAJE_INPUT_CASILLEROS, tipoDeCasillero));
        String inputPos = scanner.nextLine();
        String[] posiciones = inputPos.split(",");
        return new Posicion(Integer.parseInt(posiciones[0]), Integer.parseInt(posiciones[1]));
    }

}
