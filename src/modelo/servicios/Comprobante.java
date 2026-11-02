package modelo.servicios;

import modelo.clinica.Cita;
import java.time.LocalDate;


public class Comprobante {
    private int idComprobante;
    private double total;
    private LocalDate fechaEmision;
    private Cita citaAsociada;

    public Comprobante(int idComprobante, Cita citaAsociada, double total) {
        this.idComprobante = idComprobante;
        this.citaAsociada = citaAsociada;
        this.fechaEmision = LocalDate.now();
        this.total = total;
    }

    public String mostrarDetalle() {
        return "===== COMPROBANTE DE PAGO ======" +
            "\nID Comprobante: " + idComprobante +
            "\nFecha de Emisión: " + fechaEmision +
            "\nCita Asociada: " + (citaAsociada != null ? citaAsociada.toString() : "N/A") +
            "\nTotal a Pagar: $" + total;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Cita getCitaAsociada() {
        return citaAsociada;
    }

    public void setCitaAsociada(Cita citaAsociada) {
        this.citaAsociada = citaAsociada;
    }

    @Override
    public String toString() {
        return mostrarDetalle();
    }
}