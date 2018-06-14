public class Casillero {
    public int costo;
    public boolean accesible;

    public Casillero(String costo, String accesible) {
        this.costo = Integer.parseInt(costo);
        this.accesible = accesible.equals("A");
    }
}
