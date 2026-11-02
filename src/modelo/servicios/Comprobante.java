package modelo.servicios;

import modelo.clinica.Cita;
import java.time.LocalDate;


/**
 * Recibo de pago de una cita atendida, la clinica calcula el total y lo entrega ya calculado
 */
public class Comprobante {
    private static int totalGenerados = 0;

    private int idComprobante;
    private double total;
    private LocalDate fechaEmision;
    private Cita citaAsociada;

    /**
     * Crea un comprobante con la fecha de hoy y suma uno al contador de comprobantes
     *
     * @param idComprobante identificador del comprobante
     * @param citaAsociada cita que se cobra
     * @param total monto a pagar
     */
    public Comprobante(int idComprobante, Cita citaAsociada, double total) {
        this.idComprobante = idComprobante;
        this.citaAsociada = citaAsociada;
        this.fechaEmision = LocalDate.now();
        this.total = total;
        totalGenerados++;
    }

    /**
     * Obtiene cuantos comprobantes se han creado en total
     *
     * @return cantidad de comprobantes creados
     */
    public static int getTotalGenerados() {
        return totalGenerados;
    }

    /**
     * Arma el texto del comprobante para mostrarlo en pantalla
     *
     * @return detalle del comprobante en varias lineas
     */
    public String mostrarDetalle() {
        return "===== COMPROBANTE DE PAGO ======" +
            "\nID Comprobante: " + idComprobante +
            "\nFecha de Emisión: " + fechaEmision +
            "\nCita Asociada: " + (citaAsociada != null ? citaAsociada.toString() : "N/A") +
            "\nTotal a Pagar: $" + total;
    }

    /**
     * Obtiene el identificador del comprobante
     *
     * @return identificador del comprobante
     */
    public int getIdComprobante() {
        return idComprobante;
    }

    /**
     * Establece el identificador del comprobante
     *
     * @param idComprobante nuevo identificador
     */
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    /**
     * Obtiene el monto a pagar
     *
     * @return total a pagar
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el monto a pagar
     *
     * @param total nuevo total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene la fecha de emision
     *
     * @return fecha de emision
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emision
     *
     * @param fechaEmision nueva fecha de emision
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene la cita que se cobra
     *
     * @return cita asociada
     */
    public Cita getCitaAsociada() {
        return citaAsociada;
    }

    /**
     * Establece la cita que se cobra
     *
     * @param citaAsociada nueva cita asociada
     */
    public void setCitaAsociada(Cita citaAsociada) {
        this.citaAsociada = citaAsociada;
    }

    /**
     * Devuelve el detalle del comprobante en texto
     *
     * @return texto con el detalle del comprobante
     */
    @Override
    public String toString() {
        return mostrarDetalle();
    }
}
