package modelo.mascotas;
import modelo.personas.*;

public class Ave extends Mascota {
    private double longitudAlas;

    public Ave (double longitudAlas, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.longitudAlas = longitudAlas;
    }

    public double getLongitudAlas(){
        return longitudAlas;
    }

    public void setLongitudAlas(double longitudAlas){
        this.longitudAlas = longitudAlas;
    }

    @Override 
    public void hacerSonido(){
        System.out.println("Canto");
    }
    
}
