import java.util.ArrayList;

/**
 * Clase encargada de crear y gestionar los productos
 * **************************************************
 * **************************************************
 * ELEMENTOS PUBLICOS
 * ==================================================
 * CONSTRUCTOR
 * Producto()           | Crea un producto generico
 * Producto(nombre,codigo,descripcion,precio,cantidad)|
 * (String,int,String,double,int)
 * ==================================================
 * METODOS
 * getNombre(String)        | Devuelve el nombre del Producto
 * setNombre(String)        | Cambia el nombre del Producto
 * getCodigo(int)           | Devuelve el codigo del Producto
 * getDescripcion(String)   | Devuelve la descripcion
 * getPrecio(double)        | Devuelve el precio
 * getCantidad(int)         | Devuelve la cantidad
 * setCantidad(int)         | Cambia el valor de la cantidad
 * ==================================================
 * @author Miro 
 * @version 0
 */
public class Producto
{
    private String nombre, descripcion;
    private double precio;
    private int codigo, cantidad;
    /**
     * Creamos un producto generico para pruebas
     */
    public Producto(){
        this("codigo no existente",0,"producto no existente",0.0,0);
    }
    
    /**
     * Constructor de la clase Producto
     */
    public Producto(String nombre , int codigo, String descripcion, double precio, int cantidad)
    {
         this.nombre = nombre;
         this.codigo = codigo;
         this.descripcion = descripcion;
         this.precio = precio;
         this.cantidad = cantidad;
    }
    
    /**
     * Cambia el nombre del Producto
     */
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    /**
     * Retorna el nombre del Producto
     */
    public String getNombre(){
         return nombre;
    }
    
    /**
     * Retorna el codigo del producto (numero entero)
     */
    public int getCodigo(){
        return codigo; 
    }
    
    /**
     * Cambia el codigo del Producto
     */
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    
    /**
     * Retorna la descripcion del producto
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Cambia la descripcion del Producto
     */
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
    /**
     * Retorna el precio del producto
     */
    public double getPrecio(){
        return precio;
    }
    
    /**
     * Cambia el nombre del Producto
     */
    public void setPrecio(Double precio){
        this.precio=precio;
    }
    
    /**
     * Retorna la cantidad disponible del producto
     */
    public int getCantidad(){
        return cantidad;
    }
    
    /**
     * Cambia el valor de la cantidad el Producto
     */
    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
}
