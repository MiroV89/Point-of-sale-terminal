import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Clase Inventario
 * Se encarga de crear una lista de Productos,
 * Guarda y lee la lista desde un archivo de texto.
 * =================================================
 * CONSTRUCTOR
 * Inventario()     | Crea una lista de productos e importa desde el fichero base de datos
 * =================================================
 * ELEMENTOS PUBLICOS
 * ArrayList<Producto> listaProductos   | Crea una lista de Productos
 * void nuevoProducto (Producto)        | Añade un nuevo producto a la lista
 * void borrarProducto (Producto)       | Borra un producto de la lista
 * void numProductos ()                 | Imprime por pantalla el tamaño de la lista
 * Producto getCodigo (int codigo)      | Busca el codigo del producto en la lista y devuelve el producto
 * void listarProductos()               | Imprime la lista de los productos
 * void exportar()                      | Guarda el contenido de la lista en un archivo de texto
 * void importar()                      | Importa una lista desde un archivo de texto
 * void ImprimirListaArchivo            | Imprime por pantalla la lista desde un archivo de texto
 * File leerArchivo(String)             | Crea un archivo de texto para guardar la lista
 * =================================================
 * ELEMENTOS PRIVADOS
 * =================================================
 * File getCarpetaProyecto()            | Devuelve la carpeta en la que se encuentra el proyecto
 * int numeroProductos=0                | Inicializa el numero de productos en la lista
 * int codigo                           | Variable para el codigo del producto
 * static final String ARCHIVADOR       | CONSTANTE para asignar el fichero donde se guarda la lista ("producto.txt")
 * =================================================
 * @Miro 
 * @version 1.0
 */
public class Inventario
{
    public static ArrayList<Producto> listaProductos;
    private int codigo;
    private static final String ARCHIVADOR = "producto.txt";
    private String nombre;
    /**
     *Constructor del Inventario
     *Crea la lista de productos
     *Importa los productos desde un archivo
     */
    public Inventario()
    {
      listaProductos = new ArrayList<Producto>();
      importar(ARCHIVADOR);
    }
    
    
    /**
     * Se añade un producto nuevo dandole sus caracteristicas por teclado
     */
    public void anadirProducto(String nombre , int codigo, String descripcion, double precio, int cantidad)throws Exception
    {
         boolean seRepiteCodigo=false;
         for(Producto elemento:listaProductos)
         {
            if (!seRepiteCodigo && codigo==elemento.getCodigo())
            {
             seRepiteCodigo=true;
             elemento.setCantidad(elemento.getCantidad()+cantidad);
            }
         }
         //Si el codigo del producto no esta ocupado por otro producto, se crea el producto con ese codigo
         if (!seRepiteCodigo)
         {
             Producto p= new Producto();
             p.setNombre(nombre);
             p.setCodigo(codigo);
             p.setDescripcion(descripcion);
             p.setPrecio(precio);
             p.setCantidad(cantidad);
             listaProductos.add(p);
             exportar(ARCHIVADOR);
         }
    }
    
    /**
     * Añandimos un nuevo Producto a la lista de productos pasandole por referencia el producto en si.
     */
    public void nuevoProducto(Producto producto)throws Exception
    {
        listaProductos.add(producto);
        exportar(ARCHIVADOR);
    }
    
    /**
     * Borramos un producto de la lista
     */
    public void borrarProducto(Producto producto)throws Exception
    {
        listaProductos.remove(producto);
        exportar(ARCHIVADOR);
    }
   
     /**
     * Codigo del producto (numero entero)
     */
    public Producto getCodigo(int codigo){
        boolean encontrado=false;
        Producto prod=new Producto();
        for(Producto elemento: listaProductos){
            if (!encontrado && codigo==elemento.getCodigo()){
                prod=elemento;
                encontrado=true;
            } 
        }
        if(encontrado=false){
            System.out.println("Codigo no encontrado");
        }
        return prod;
    }
    
    /**
     * Devuelve una cadena con la lista de los productos
     */
    public String getPrNm(String nombre)
    {
        String listaP="";
        for(Producto p:listaProductos)
        {
            if (nombre.equalsIgnoreCase(p.getNombre()))
            {
                String cadena= String.format("%2d\t"+"%-10s     \t"+"%4d und.  "+"\t   %-3.2f €   "+"\t%-20s\n",
                p.getCodigo(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getDescripcion());
                listaP=(listaP+cadena);
            }
        }
        return listaP;
    }
   
    
    public String listaPS()
    {
        String listaP="";
        for(Producto p: listaProductos){
            String cadena= String.format("%2d\t"+"%-10s     \t"+"%4d und.  "+"\t   %-3.2f €   "+"\t%-20s\n",
            p.getCodigo(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getDescripcion());
            listaP=(listaP+cadena);
        }
        return listaP;
    }
    
    /**
     * Guardamos (exportamos) la lista en un archivo de texto
     */
    public void exportar(String ARCHIVADOR) throws Exception
    {
      try { 
          File nuevo=new File(ARCHIVADOR);
          String ruta=nuevo.getAbsolutePath();
          File archivo=new File(ruta);
                if((archivo.exists() && archivo.delete()) || (!archivo.exists())){
              FileWriter escribir=new FileWriter(archivo,true);
              Iterator<Producto> lista = listaProductos.iterator();
                    while(lista.hasNext()){
                        Producto elemento = lista.next();
                        escribir.write(elemento.getNombre()+"*"+elemento.getCodigo()+
                        "*"+elemento.getDescripcion()+"*"+elemento.getPrecio()+
                        "*"+elemento.getCantidad());
                        escribir.write("\r\n");
                    }
              escribir.close();
            }
            
      }
       catch(Exception e){}
    }
    
    public void importar(String ARCHIVADOR)
    {
        File nuevo=new File(ARCHIVADOR);
        String ruta=nuevo.getAbsolutePath();
        BufferedReader lector = null;
        listaProductos.clear();
        try {
            File miArchivo = leerArchivo(ruta);
            lector = new BufferedReader(new FileReader(miArchivo));
            String linea;
            linea = lector.readLine();
            
            while(linea != null) {
                Producto p;         
                StringTokenizer campos = new StringTokenizer(linea,"*");
                
                while(campos.hasMoreElements()){
                    String nom = campos.nextElement().toString();
                    int code = Integer.parseInt(campos.nextElement().toString());
                    String des = campos.nextElement().toString();
                    double prec = Double.parseDouble(campos.nextElement().toString());
                    int cant = Integer.parseInt(campos.nextElement().toString());
                 
                    p = new Producto(nom,code,des,prec,cant);
                    listaProductos.add(p);
                }
                linea = lector.readLine();
            }
            lector.close();
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
     * Se lee el archivo de texto para importar/exportar nuestra lista
     */
    public File leerArchivo(String nombreArchivo) throws IOException
    {
        try {
            File archivo = new File(nombreArchivo);
            if(!archivo.isAbsolute()) {
                archivo = new File(getCarpetaProyecto(), nombreArchivo);
            }
            return archivo;
        }
        catch(URISyntaxException e) {
            throw new IOException("No se pudo leer el archivo " +
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
    /**
     * Devuelve la ruta completa de donde se encuentra el proyecto
     */
    private String getDirectorio() throws IOException
    {
        File miDir = new File (".");
        String dir="";
        try {
            dir=miDir.getCanonicalPath();
        }
        catch(Exception e) {
           e.printStackTrace();
        }
        return dir;
     }
    }


