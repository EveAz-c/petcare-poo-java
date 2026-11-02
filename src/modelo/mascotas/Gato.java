package modelo.mascotas;
import modelo.personas.Dueno;


public class Gato extends Mascota{
    private boolean domestico;
    
    public Gato(boolean esDomestico, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.domestico = esDomestico;
    }

    public boolean isDomestico(){
        return domestico;
    }

    public void setDomestico(boolean domestico){
        this.domestico = domestico;
    }

    @Override 
    public void hacerSonido(){
        System.out.println("Miau");
    }
    
}
