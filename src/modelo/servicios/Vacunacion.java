package modelo.servicios;


/**
 * Aplicacion de una vacuna, su costo es la tarifa de la vacuna aplicada
 */
public class Vacunacion implements Facturable {
    private double costoVacuna;
    private String nombreVacuna;

    /**
     * Crea una vacunacion
     *
     * @param costoVacuna costo de la vacuna, no puede ser negativo
     * @param nombreVacuna nombre de la vacuna
     * @throws IllegalArgumentException si el costo es negativo
     */
    public Vacunacion(double costoVacuna, String nombreVacuna) {
        setCostoVacuna(costoVacuna);
        this.nombreVacuna = nombreVacuna;
    }

    /**
     * Devuelve el costo de la vacuna
     *
     * @return costo de la vacunacion
     */
    @Override
    public double calcularCosto() {
        return this.costoVacuna;
    }

    /**
     * Obtiene el costo de la vacuna
     *
     * @return costo de la vacuna
     */
    public double getCostoVacuna() {
        return costoVacuna;
    }

    /**
     * Establece el costo de la vacuna
     *
     * @param costoVacuna nuevo costo
     * @throws IllegalArgumentException si el costo es negativo
     */
    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna < 0) {
            throw new IllegalArgumentException("El costo de la vacunacion no puede ser negativo ");
        }
        this.costoVacuna = costoVacuna;
    }

    /**
     * Obtiene el nombre de la vacuna
     *
     * @return nombre de la vacuna
     */
    public String getNombreVacuna() {
        return nombreVacuna;
    }

    /**
     * Establece el nombre de la vacuna
     *
     * @param nombreVacuna nuevo nombre
     */
    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    /**
     * Devuelve el nombre y el costo de la vacuna en texto
     *
     * @return texto con los datos de la vacunacion
     */
    @Override
    public String toString() {
        return "Vacunación [Vacuna: " + nombreVacuna + ", Costo: $" + costoVacuna + "]";
    }
}
