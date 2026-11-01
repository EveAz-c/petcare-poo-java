package modelo.servicios;


public class ConsultaGeneral implements Facturable {
    private double costo;
    private String diagnostico;
    private String observaciones;


    public ConsultaGeneral(double costo, String diagnostico, String observaciones) {
        setCosto(costo);
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
    }

    @Override
    public double calcularCosto() {
        return this.costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        if (costo < 0) {
            throw new IllegalArgumentException("El costo de la consulta general no puede ser negativo ");
        }
        this.costo = costo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "-Consulta General- \n Diagnóstico: " + diagnostico + ", Costo: $" + costo;
    }
}