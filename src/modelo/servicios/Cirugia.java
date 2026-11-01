package modelo.servicios;


public class Cirugia implements Facturable {
    private String complejidad;
    private double horasQuirofano;

    public Cirugia(String complejidad, double horasQuirofano) {
        this.complejidad = complejidad;
        setHorasQuirofano(horasQuirofano);
    }

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

    public String getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    public void setHorasQuirofano(double horasQuirofano) {
        if (horasQuirofano < 0) {
            throw new IllegalArgumentException("Las horas de quirófano no pueden ser negativa ");
        }
        this.horasQuirofano = horasQuirofano;
    }

    @Override
    public String toString() {
        return "-Cirugía- \n Complejidad: " + complejidad + ", Horas: " + horasQuirofano + "hrs, Costo: $" + calcularCosto();
    }
}