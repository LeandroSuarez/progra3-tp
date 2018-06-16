import java.util.Scanner;

public class InputEscaner {

    private Scanner scanner;
    private static final String MENSAJE_INPUT_CASILLEROS = "Por favor ingrese la posición del casillero de %s (separado por coma, ej: 2,1):";
    private static final String MENSAJE_INPUT_ARCHIVO = "Por favor ingrese la ruta completa del archivo a leer (para Windows agregue también la extensión):";
    private static final String INICIO= "INICIO";
    private static final String DESTINO= "DESTINO";

    public String pedirRutaDeArchivo() {
        System.out.println(MENSAJE_INPUT_ARCHIVO);
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Posicion pedirInputDeCasillerosInicio() {
        return pedirInputDeCasilleros(INICIO);
    }

    public Posicion pedirInputDeCasillerosDestino() {
        return pedirInputDeCasilleros(DESTINO);
    }

    private Posicion pedirInputDeCasilleros(String tipoDeCasillero) {
        scanner = new Scanner(System.in);
        System.out.println(String.format(MENSAJE_INPUT_CASILLEROS, tipoDeCasillero));
        String inputPos = scanner.nextLine();
        String[] posiciones = inputPos.split(",");
        if (posiciones.length < 2) throw new RuntimeException("Error al ingresar posiciones de inicio y destino");
        return new Posicion(Integer.parseInt(posiciones[0]), Integer.parseInt(posiciones[1]));
    }

}
