import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 * Clase empleados
 */
public class Empleados
{
    public ArrayList<Vendedor> listaVendedores;
    private static final String ARCHIVADOR = "vendedores.txt";
    /**
     * Constructor for objects of class Empleados
     */
    public Empleados()
    {
        listaVendedores = new ArrayList<Vendedor>();
        importar();
    }
    
    /**
     * Eliminar vendedor
     */
    public void borrarVendedor(String codigo)
    {
        for(Vendedor v:listaVendedores)
        {
            if (codigo.equals(v.getCodigo()))
            {
                listaVendedores.remove(v);
            }
        }
    }
    
    /**
     * Contratar vendedor
     */
    public void nuevoVendedor(int codigo,String CIF, String razonSocial)
    {
        Vendedor v = new Vendedor(codigo,CIF,razonSocial);
        listaVendedores.add(v);
    }
    
    /**
     * Guardamos (exportamos) la lista en un archivo de texto
     */
    public void exportar()
    {
      try { 
          File nuevo=new File(ARCHIVADOR);
          String ruta=nuevo.getAbsolutePath();
          File archivo=new File(ruta);
          if((archivo.exists() && archivo.delete()) || (!archivo.exists())){
              FileWriter escribir=new FileWriter(archivo,true);
              Iterator<Vendedor> lista = listaVendedores.iterator();
                    while(lista.hasNext()){
                        Vendedor v = lista.next();
                        escribir.write(v.getCodigo()+"*"+v.getCIF()+"*"+v.getRazonSocial());
                        escribir.write("\r\n");
                    }
              escribir.close();
          }
            
      }
       catch(Exception e){}
    }
    
    /**
     * Importamos lista de vendedores
     */
    public void importar()
    {
        BufferedReader lector = null;
        listaVendedores.clear();
        try {
            File nuevo=new File(ARCHIVADOR);
            lector = new BufferedReader(new FileReader(nuevo));
            String linea;
            linea = lector.readLine();
            
            while(linea != null) {
                Vendedor v;         
                StringTokenizer campos = new StringTokenizer(linea,"*");
                
                while(campos.hasMoreElements()){
                    int codigo = Integer.parseInt(campos.nextElement().toString());
                    String cif = campos.nextElement().toString();
                    String razSoc = campos.nextElement().toString();
                 
                    v = new Vendedor(codigo,cif,razSoc);
                    listaVendedores.add(v);
                }
                linea = lector.readLine();
            }
            lector.close();
        }
        catch(Exception e){System.out.println("No se pudo importar");}
    }
}
