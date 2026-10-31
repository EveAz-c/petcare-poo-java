package modelo.mascotas;

import java.util.ArrayList;
import java.util.List;
import modelo.servicios.*;
import modelo.clinica.*;
public class HistorialClinico {
    private List<Facturable> serviciosRealizados;

    public HistorialClinico (){
        this.serviciosRealizados = new ArrayList<>();
    }

    public void agregarServicio(){
        if(servicio != null){
            serviciosRealizados.add(servicio);
        }
    }

    public void mostrarHistorial(){
        System.out.println("---HISTORIAL CLINICO---");
        if(serviciosRealizados.isEmpty()){
            System.out.println("No hay servicios registrados en este historial");
        } else {
            for (int i = 0; i < serviciosRealizados.size(); ++i){
                System.out.println(i + 1) + ". Costo registrado " + serviciosRealizados.get(i).calcularcosto());
            }
        }
    }


    
}
