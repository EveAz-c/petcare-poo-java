package modelo.servicios;

/**
 * Contrato para los servicios medicos que se cobran, cada servicio calcula su propio costo
 */
public interface Facturable {

    /**
     * Calcula el costo del servicio
     *
     * @return costo del servicio
     */
    double calcularCosto();
}
