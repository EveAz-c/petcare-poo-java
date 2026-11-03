package modelo.mascotas;
import modelo.personas.Dueno;


public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

<<<<<<< Updated upstream
    public Perro(String raza, String nivelEnergia, String nombre, int edad, double peso, Dueno dueno) {
        super(nombre, edad, peso, dueno); 
=======
    /**
     * Crea un perro
     *
     * @param raza raza del perro
     * @param nivelEnergia nivel de energia del perro
     * @param nombre nombre del perro
     * @param edad edad en anos
     * @param peso peso en kilogramos
     * @param dueno dueno del perro
     * @throws IllegalArgumentException si el nombre esta vacio o la edad o el peso no son validos
     */
    public Perro(String raza, String nivelEnergia, String nombre, int edad, double peso,
                Dueno dueno) {
        super(nombre, edad, peso, dueno);
>>>>>>> Stashed changes
        this.raza = raza;
        this.nivelEnergia = nivelEnergia;
    }

    public String getRaza(){
        return raza;
    }

    public void setRaza(){
        this.raza = raza;
    }

    public String getNivelEnergia(){
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
