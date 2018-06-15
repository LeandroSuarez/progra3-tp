public class Casillero {
    public int costo;
    public boolean accesible;

    public Casillero(String costo, String accesible) {
        this.costo = costo.equals("X") ? 0 : Integer.parseInt(costo);
        this.accesible = accesible.equals("A");
    }

    @Override
    public String toString() {
        return "["+ accesible + "," + costo + ']';
    }
}
