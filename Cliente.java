/**
 * Clase Cliente se encarga de asignar los distintos parametros del cliente
 */
public class Cliente extends Persona
{
    private int codigoCliente;
    private String nif, nombre, apellidos, razonSocial, domicilio, fechaAlta;
    /**
     * Constructor
     */
    public Cliente(int codigoCliente, String nif, String nombre, String apellidos, String razonSocial, String domicilio, String fechaAlta)
    {
        super(codigoCliente,nif,razonSocial);
        //this.codigoCliente = codigoCliente;
        //this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        //this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }
    
    /**
     * Creamos un producto generico para pruebas
     */
    public Cliente(){
        this(1,"1342X","Juan","De Aqui","soltero","calle tralala","24-02-2015");
    }
    
    public void setCodigo(int codigoCliente)
    {
        this.codigoCliente = codigoCliente;
    }
    public void setNIF(String nif)
    {
        this.nif = nif;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
    public void setRazonSocial(String razonSoc)
    {
        this.razonSocial = razonSoc;
    }
    public void setFechaAlta(String fechaAlta)
    {
        this.fechaAlta = fechaAlta;
    }
    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }
    public int getCodigo()
    {
        return codigoCliente;
    }
    public String getNIF()
    {
        return nif;
    }
    public String getNombre()
    {
        return nombre;
    }
    public String getApellidos()
    {
        return apellidos;
    }
    public String getRazonSocial()
    {
        return razonSocial;
    }
    public String getFechaAlta()
    {
        return fechaAlta;
    }
    public String getDomicilio()
    {
        return domicilio;
    }
}
