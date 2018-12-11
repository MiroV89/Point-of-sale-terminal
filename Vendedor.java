/**
 * Crear una lista de vendedores con el CIF y la razon social
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vendedor extends Persona
{
    private String CIF,razonSocial;
    private int codigo;
    /**
     * Constructor for objects of class Venta
     */
    public Vendedor(int codigo,String CIF, String razonSocial)
    {
        super(codigo,CIF,razonSocial);
    }
    }

