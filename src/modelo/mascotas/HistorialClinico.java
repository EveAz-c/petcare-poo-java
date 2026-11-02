package modelo.mascotas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.servicios.Facturable;


/**
 * Registro de los servicios medicos de una mascota, forma parte de la mascota por composicion
 */
public class HistorialClinico {
    private List<Facturable> serviciosRealizados;

    /**
     * Crea un historial vacio
     */
    public HistorialClinico() {
        this.serviciosRealizados = new ArrayList<>();
    }

    /**
     * Crea un historial con una copia de los servicios recibidos
     *
     * @param serviciosRealizados servicios con los que inicia el historial
     */
    public HistorialClinico(List<Facturable> serviciosRealizados) {
        this.serviciosRealizados = new ArrayList<>();
        if (serviciosRealizados != null) {
            for (Facturable servicio : serviciosRealizados) {
                agregarServicio(servicio);
            }
        }
    }

    /**
     * Agrega un servicio al final del historial
     *
     * @param servicio servicio que se agrega
     */
    public void agregarServicio(Facturable servicio) {
        if (servicio != null) {
            serviciosRealizados.add(servicio);
        }
    }

    /**
     * Muestra en pantalla todos los servicios del historial
     */
    public void mostrarHistorial() {
        mostrarHistorial(serviciosRealizados.size());
    }

    /**
     * Muestra en pantalla los ultimos servicios del historial
     *
     * @param ultimos cantidad de servicios mas recientes que se muestran
     */
    public void mostrarHistorial(int ultimos) {
        System.out.println("---HISTORIAL CLINICO---");
        if (serviciosRealizados.isEmpty()) {
            System.out.println("No hay servicios registrados en este historial");
        } else {
            int inicio = Math.max(0, serviciosRealizados.size() - Math.max(0, ultimos));
            for (int i = inicio; i < serviciosRealizados.size(); ++i) {
                System.out.println((i + 1) + ". Costo registrado: "
                    + serviciosRealizados.get(i).calcularCosto());
            }
        }
    }

    /**
     * Suma el costo de todos los servicios del historial, cada servicio calcula su propio costo
     *
     * @return costo total de los servicios
     */
    public double calcularCostoTotal() {
        double total = 0;
        for (Facturable servicio : serviciosRealizados) {
            total += servicio.calcularCosto();
        }
        return total;
    }

    /**
     * Obtiene los servicios realizados, la lista devuelta es de solo lectura
     *
     * @return lista de solo lectura con los servicios realizados
     */
    public List<Facturable> getServiciosRealizados() {
        return Collections.unmodifiableList(serviciosRealizados);
    }

}

