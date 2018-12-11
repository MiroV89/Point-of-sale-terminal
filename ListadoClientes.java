import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *Clase Listado Clientes se encarga de crear una lista de clientes, añadir o borrar clientes
 *Tambien importa/exporta dicha lista a un archivo de texto
 */
public class ListadoClientes
{
    public ArrayList<Cliente> listaClientes;
    private static int numeroClientes=0;
    private static final String ARCHIVADOR = "clientes.txt";
    /**
     * Constructor for objects of class ListaClientes
     */
    public ListadoClientes()
    {
        listaClientes = new ArrayList<Cliente>();
        Importar();
    }

    /**
     * Añade un cliente nuevo pidiendo que se rellenen sus caracteristicas
     */
    public void nuevoCliente(String codigoCl, String cif, 
    String nombre, String apellidos, String razonSocial, 
    String domicilio, String fechaAlta)throws Exception
    {
        try{
            int code=Integer.parseInt(codigoCl);
            Cliente cl=new Cliente();
            cl.setCodigo(code);
            cl.setCIF(cif);
            cl.setNombre(nombre);
            cl.setApellidos(apellidos);
            cl.setRazonSocial(razonSocial);
            cl.setDomicilio(domicilio);
            cl.setFechaAlta(fechaAlta);
            listaClientes.add(cl);
            numeroClientes++;
        }
        catch(Exception e){}
        exportar();
    }
    
    /**
     * Añade un cliente nuevo desde un objeto cliente 
     */
    public void anadirCliente(Cliente cliente)
    {
        listaClientes.add(cliente);
        numeroClientes++;
    }
    
    /**
     * Borrar cliente dandole el codigo
     */
    public boolean borrarClCod(String codigoCl)
    {
        boolean borrado=false;
        int code=Integer.parseInt(codigoCl);
        if (getCodigo(code).getCodigo() !=0)
        {
            listaClientes.remove(getCodigo(code));
            borrado=true;
        }
        return borrado;
    }
    
    /**
     * Borra un objeto cliente
     */
    public void borrarCliente(Cliente cliente)
    {
        listaClientes.remove(cliente);
        numeroClientes--;
    }
    
     /**
     * Codigo del producto (numero entero) devuelve cliente
     */
    public Cliente getCodigo(int codigo){
        boolean encontrado=false;
        Cliente clin = new Cliente();
        for(Cliente elemento: listaClientes){
            if (!encontrado && codigo==elemento.getCodigo()){
                clin=elemento;
                encontrado=true;
            } 
        }
        //Si no encontramos el codigo del cliente, le asignamos el codigo 0,
        //que corresponde al cliente generico (no registrado)
        if(encontrado=false)
        {
            clin.setCodigo(0);
        }
        return clin;
    }
    
    /**
     * Guardamos (exportamos) la lista en un archivo de texto
     * 
     */
    public void exportar(){
      try {
            File archivo=new File(ARCHIVADOR);
            if((archivo.exists() && archivo.delete()) || (!archivo.exists())){
                  FileWriter escribir=new FileWriter(archivo,true);
                  Iterator<Cliente> lista = listaClientes.iterator();
                        while(lista.hasNext()){
                            Cliente cl = lista.next();
                            escribir.write(+cl.getCodigo()+"*"+cl.getCIF()+
                            "*"+cl.getNombre()+"*"+cl.getApellidos()+" "+
                            "*"+cl.getRazonSocial()+"*"+cl.getDomicilio()+
                            "*"+cl.getFechaAlta());
                            escribir.write("\r\n");
                        }
                  escribir.close();
            }
      }
       catch(Exception e){ System.out.println("Error al escribir");}
    }
    
    /**
     *  Importamos un listado
     */
    public void Importar(){
        BufferedReader lector = null;
        try {
            File miArchivo= crearArchivo(ARCHIVADOR);
            lector = new BufferedReader(new FileReader(miArchivo));
            String linea;
            linea = lector.readLine();
            
            while(linea != null) {
                Cliente cl;         
                StringTokenizer campos = new StringTokenizer(linea, "*");
                
                while(campos.hasMoreElements()){
                    int code = Integer.parseInt(campos.nextElement().toString());
                    String cif = campos.nextElement().toString();
                    String nom = campos.nextElement().toString();
                    String ape = campos.nextElement().toString();
                    String razS = campos.nextElement().toString();
                    String dom = campos.nextElement().toString();
                    String fech = campos.nextElement().toString();
                                       
                    cl = new Cliente(code,cif,nom,ape,razS,dom,fech);
                    listaClientes.add(cl);
                }
                linea = lector.readLine();
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " +
                               ARCHIVADOR);
        }
        catch(IOException e) {
            System.out.println("No se pudo leer el archivo: " +
                               ARCHIVADOR);
        }
        finally {
            if(lector != null) {
                //Coge cualquier excepcion
                try {
                    lector.close();
                }
                catch(IOException e) {
                    System.out.println("No se pudo cerrar el archivo: " +
                                       ARCHIVADOR);
                }
            }
        }
        
    }
    /**
     * Se crea el archivo de texto para importar/exportar nuestra lista
     */
    public File crearArchivo(String nombreArchivo) throws IOException
    {
        try {
            File archivo = new File(nombreArchivo);
            if(!archivo.isAbsolute()) {
                archivo = new File(getCarpetaProyecto(), nombreArchivo);
            }
            return archivo;
        }
        catch(URISyntaxException e) {
            throw new IOException("No se pudo crear el nombre " +
                                  nombreArchivo);
        }
    }
     /**
      * Se accede a la ruta de la carpeta del proyecto TPV
      */
     private File getCarpetaProyecto() throws URISyntaxException
    {
         String myClassFile = getClass().getName() + ".class";
         URL url = getClass().getResource(myClassFile);
         return new File(url.toURI()).getParentFile();
    }
}
