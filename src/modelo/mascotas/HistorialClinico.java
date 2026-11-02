package modelo.mascotas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.servicios.Facturable;


public class HistorialClinico {
    private List<Facturable> serviciosRealizados;

    public HistorialClinico() {
        this.serviciosRealizados = new ArrayList<>();
    }

    public HistorialClinico(List<Facturable> serviciosRealizados) {
        this.serviciosRealizados = new ArrayList<>();
        if (serviciosRealizados != null) {
            for (Facturable servicio : serviciosRealizados) {
                agregarServicio(servicio);
            }
        }
    }

    public void agregarServicio(Facturable servicio) {
        if (servicio != null) {
            serviciosRealizados.add(servicio);
        }
    }

    public void mostrarHistorial() {
        mostrarHistorial(serviciosRealizados.size());
    }

    public void mostrarHistorial(int ultimos) {
        System.out.println("---HISTORIAL CLINICO---");
        if (serviciosRealizados.isEmpty()) {
            System.out.println("No hay servicios registrados en este historial");
        } else {
            int inicio = Math.max(0, serviciosRealizados.size() - Math.max(0, ultimos));
            for (int i = inicio; i < serviciosRealizados.size(); ++i) {
                System.out.println((i + 1) + ". Costo registrado: " + serviciosRealizados.get(i).calcularCosto());
            }
        }
    }

    public double calcularCostoTotal() {
        double total = 0;
        for (Facturable servicio : serviciosRealizados) {
            total += servicio.calcularCosto();
        }
        return total;
    }

    public List<Facturable> getServiciosRealizados() {
        return Collections.unmodifiableList(serviciosRealizados);
    }

}

