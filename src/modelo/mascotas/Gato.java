package modelo.mascotas;
import modelo.personas.*;

public class Gato extends Mascota{
    private boolean esDomestico;
    
    public Gato(boolean esDomestico, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.esDomestico = esDomestico;
    }

    public void isEsDomestico(boolean esDomestico){
        if(esDomestico = false){
            System.out.println("Salvaje");
        }
        System.out.println("Domestico");
    }

    @Override 
    public void hacerSonido(){
        System.out.println("Miau");
    }
    
}
