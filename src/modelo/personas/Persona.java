package modelo.personas;

/**
 * Clase base con los datos de contacto que comparten duenos, veterinarios y recepcionistas
 */
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;

    /**
     * Crea una persona con sus datos de contacto
     *
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @throws IllegalArgumentException si el nombre o el apellido estan vacios
     */
    public Persona(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Obtiene el nombre de la persona
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona
     *
     * @param nombre nuevo nombre
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene el apellido de la persona
     *
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de la persona
     *
     * @param apellido nuevo apellido
     * @throws IllegalArgumentException si el apellido esta vacio
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        this.apellido = apellido.trim();
    }

    /**
     * Obtiene el telefono de contacto
     *
     * @return telefono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el telefono de contacto
     *
     * @param telefono nuevo telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electronico
     *
     * @return correo electronico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electronico
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve el tipo de persona, su nombre completo y su telefono en texto
     *
     * @return texto con los datos de la persona
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + nombre + " " + apellido
            + ", tel: " + telefono + "]";
    }
}