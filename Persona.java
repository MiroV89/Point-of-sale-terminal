
/**
 * Write a description of class Persona here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Persona
{
    String CIF,razonSocial;
    int codigo;
    public Persona(int codigo,String CIF, String razonSocial)
    {
        this.codigo=codigo;
        this.CIF=CIF;
        this.razonSocial=razonSocial;
    }
        public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int codigo)
    {
        this.codigo=codigo;
    }
    public String getCIF()
    {
        return CIF;
    }
    public void setCIF(String CIF)
    {
        this.CIF=CIF;
    }
    public String getRazonSocial()
    {
        return razonSocial;
    }
    public void setRazonSocial(String razon)
    {
        this.razonSocial=razon;
    }
 
    
    
}
