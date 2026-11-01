package modelo.servicios;


public class Vacunacion implements Facturable {
    private double costoVacuna;
    private String nombreVacuna;

    public Vacunacion(double costoVacuna, String nombreVacuna) {
        setCostoVacuna(costoVacuna);
        this.nombreVacuna = nombreVacuna;
    }

    @Override
    public double calcularCosto() {
        return this.costoVacuna;
    }

    public double getCostoVacuna() {
        return costoVacuna;
    }

    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna < 0) {
            throw new IllegalArgumentException("El costo de la vacunacion no puede ser negativo ");
        }
        this.costoVacuna = costoVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    @Override
    public String toString() {
        return "Vacunación [Vacuna: " + nombreVacuna + ", Costo: $" + costoVacuna + "]";
    }
}