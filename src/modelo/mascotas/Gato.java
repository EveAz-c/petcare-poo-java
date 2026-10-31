package modelo.mascotas;

public class Gato extends Mascota{
    private boolean esDomestico;
    
    public Gato(boolean esDomestico, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.esDomestico = esDomestico;
    }

    public boolean isEsDomestico(){
        

    }
    
}
