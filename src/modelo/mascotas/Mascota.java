package modelo.mascotas;
import modelo.personas.Dueno;

public abstract class Mascota {
    protected String nombre;
    protected int edad;
    protected double peso;
    protected Dueno dueno;
    protected HistorialClinico historialClinico;

    public Mascota(String nombre, int edad, double peso, Dueno dueno){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.dueno = dueno;
        this.historialClinico = new HistorialClinico();

    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        if(edad <= 0){
            throw new IllegalArgumentException("La edad debe ser mayor a 0");
        }
        this.edad = edad;
    }

    public double getPeso(){
        return peso;
    }

    public void setPeso(double peso){
        if(peso <= 0){
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
        this.peso = peso;
    }

    public abstract void hacerSonido();

}
