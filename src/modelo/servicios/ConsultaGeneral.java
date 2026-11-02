package modelo.servicios;


/**
 * Consulta medica general, su costo es una tarifa que se captura al crearla
 */
public class ConsultaGeneral implements Facturable {
    private double costo;
    private String diagnostico;
    private String observaciones;


    /**
     * Crea una consulta general
     *
     * @param costo costo de la consulta, no puede ser negativo
     * @param diagnostico diagnostico de la consulta
     * @param observaciones observaciones medicas
     * @throws IllegalArgumentException si el costo es negativo
     */
    public ConsultaGeneral(double costo, String diagnostico, String observaciones) {
        setCosto(costo);
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
    }

    /**
     * Devuelve el costo de la consulta
     *
     * @return costo de la consulta
     */
    @Override
    public double calcularCosto() {
        return this.costo;
    }

    /**
     * Obtiene el costo de la consulta
     *
     * @return costo de la consulta
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Establece el costo de la consulta
     *
     * @param costo nuevo costo
     * @throws IllegalArgumentException si el costo es negativo
     */
    public void setCosto(double costo) {
        if (costo < 0) {
            throw new IllegalArgumentException(
                "El costo de la consulta general no puede ser negativo ");
        }
        this.costo = costo;
    }

    /**
     * Obtiene el diagnostico
     *
     * @return diagnostico de la consulta
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnostico
     *
     * @param diagnostico nuevo diagnostico
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene las observaciones medicas
     *
     * @return observaciones de la consulta
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones medicas
     *
     * @param observaciones nuevas observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Devuelve el diagnostico y el costo de la consulta en texto
     *
     * @return texto con los datos de la consulta
     */
    @Override
    public String toString() {
        return "-Consulta General- \n Diagnóstico: " + diagnostico + ", Costo: $" + costo;
    }
}
