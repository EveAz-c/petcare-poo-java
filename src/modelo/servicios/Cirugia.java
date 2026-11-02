package modelo.servicios;


/**
 * Procedimiento quirurgico, su costo depende de las horas de quirofano y de la complejidad
 */
public class Cirugia implements Facturable {
    private String complejidad;
    private double horasQuirofano;

    /**
     * Crea una cirugia
     *
     * @param complejidad nivel de complejidad: BAJA, MEDIA o ALTA
     * @param horasQuirofano horas de quirofano, no pueden ser negativas
     * @throws IllegalArgumentException si las horas son negativas
     */
    public Cirugia(String complejidad, double horasQuirofano) {
        this.complejidad = complejidad;
        setHorasQuirofano(horasQuirofano);
    }

    /**
     * Calcula el costo con las horas de quirofano, una tarifa base por hora y un multiplicador
     * que sube segun la complejidad
     *
     * @return costo de la cirugia
     */
    @Override
    public double calcularCosto() {
        double tarifaBasePorHora = 1200.0;
        double multiplicador = 1.0;

        if (complejidad != null) {
            String comp = complejidad.trim().toUpperCase();
            if (comp.contains("MEDIA")) {
                multiplicador = 1.5;
            } else if (comp.contains("ALTA")) {
                multiplicador = 2.0;
            }
        }

        return this.horasQuirofano * tarifaBasePorHora * multiplicador;
    }

    /**
     * Obtiene la complejidad de la cirugia
     *
     * @return nivel de complejidad
     */
    public String getComplejidad() {
        return complejidad;
    }

    /**
     * Establece la complejidad de la cirugia
     *
     * @param complejidad nuevo nivel de complejidad
     */
    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    /**
     * Obtiene las horas de quirofano
     *
     * @return horas de quirofano
     */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    /**
     * Establece las horas de quirofano
     *
     * @param horasQuirofano nuevas horas de quirofano
     * @throws IllegalArgumentException si las horas son negativas
     */
    public void setHorasQuirofano(double horasQuirofano) {
        if (horasQuirofano < 0) {
            throw new IllegalArgumentException("Las horas de quirófano no pueden ser negativa ");
        }
        this.horasQuirofano = horasQuirofano;
    }

    /**
     * Devuelve la complejidad, las horas y el costo de la cirugia en texto
     *
     * @return texto con los datos de la cirugia
     */
    @Override
    public String toString() {
        return "-Cirugía- \n Complejidad: " + complejidad + ", Horas: " + horasQuirofano
            + "hrs, Costo: $" + calcularCosto();
    }
}
