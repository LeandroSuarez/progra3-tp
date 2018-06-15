public class Casillero {
    public int costo;
    public boolean accesible;
    public boolean visitado;

    public Casillero(String costo, String accesible) {
        this.costo = costo.equals("X") ? 0 : Integer.parseInt(costo);
        this.accesible = accesible.equals("A");
        this.visitado = false;
    }

    public Casillero(int costo, boolean accesible, boolean visitado) {
        this.costo = costo;
        this.accesible = accesible;
        this.visitado = visitado;
    }

    @Override
    public String toString() {
        return "["+ (visitado ? "V" : accesible ? "A" : "B") + "," + costo + ']';
    }
}
