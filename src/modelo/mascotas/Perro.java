package modelo.mascotas;
import modelo.personas.*;


public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    public Perro(String raza, String nivelEnergia, String nombre, int edad, double peso, Dueno dueno) {
        super(nombre, edad, peso, dueno); 
        this.raza = raza;
        this.nivelEnergia = nivelEnergia;
    }

    public String raza(){
        return raza;
    }

    public void setRaza(){
        this.raza = raza;
    }

    public String nivelEnergia(){
        return nivelEnergia;
    }

    public void setNivelEnergia( String nivelEnergia){
        this.nivelEnergia = nivelEnergia;
    }

    @Override 
    public void hacerSonido(){
        System.out.println("Ladrido");
    }
    
}
