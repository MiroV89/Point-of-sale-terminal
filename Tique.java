import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Iterator;
/**
 * Clase Tique
 * =====================================================
 * CONSTRUCTOR
 * Tique()      | Crea una lista de la compra que almacena Productos
 *              | Crea un inventario
 *              | Crea una lista y le da el valor de la lista de Productos de la clase Inventario
 * =====================================================
 * ELEMENTOS PUBLICOS
 * =====================================================
 * ArrayList<Tique> listaTique              | Crea una lista de Tiquets
 * ArrayList<Producto> listaCompra          | Crea una lista de la compra vacia para llenarla de Productos
 * ArrayList<Integer> listaCodigos          | Crea una lista de Codigos
 * static ArrayList<Producto> listaP        | Crea una lista de Productos a usar en esta clase
 * static final double IVA                  | CONSTANTE del IVA
 * void buscarProducto(String cadena)       | Compara si el dato introducido es el codigo o el nombre de un Producto a buscar
 * void imprimeTique()                      | Imprime el tique por pantalla
 * void exportar()                          | Guarda el tique con su correspondiente nombre (Fecha+"cl"+numeroCliente)
 * void mostrarTiquet                       | Imprime el tiquet por pantalla desde un archivo
 * =====================================================
 * ELEMENTOS PRIVADOS
 * =====================================================
 * void getNumCliente()                             | Pide el numero del cliente
 * String cabeceraTique()                           | Devuelve un String con la cabecera del tique
 * String cuerpoTique()                             | Devuelve un String con el cuerpo del tique
 * String finalTique()
 * void leerNombre(String nombre)
 * void leerCodigo(String codigoString)
 * static boolean esNumero(String cadena)
 * void Compra()
 * String getDirectorio()
 * String fechaTiquet()
 * File crearArchivo(String nombreArchivo)
 * File getCarpetaProyecto()
 * void actualizarStock(Producto p, int cantidad)
 * 
 * 
 * @Miro 
 * @version 0
 */
public class Tique
{
    Inventario inv;
    private static final String ARCHIVADOR = "producto.txt";
    private static final String TIQUETS = "tiquets.txt";
    private String dir,cadenaLarga;
    private int codigo, numCliente;
    private int cantidad;
    public ArrayList<String> listaTique;
    public ArrayList<Producto> listaCompra;
    private ArrayList<Integer> listaCodigos;
    private Scanner in= new Scanner(System.in);
    public static ArrayList<Producto> listaP;
    static final double IVA=0.21;
    private String asteriscos=("**********************************************************************************\n");
    /**
     * Constructor for objects of class Tique
     */
    public Tique()
    {
      listaCompra = new ArrayList<Producto>();
      Inventario inv = new Inventario();
      listaP=inv.listaProductos;
      listaTique = new ArrayList<String>();
      importaTique();
    }
    
    /**
     * Pide el numero de cliente
     */
    private void getNumCliente(int numCl)
    {
        numCliente=numCl;
    }
    
    /**
     * Imprime el cuerpo del tiquet
     */
    private String cuerpoTique(ArrayList<Producto> lista){
        double total=0;
        cadenaLarga="";
        Date fechaActual=new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("H-mm-ss dd/MM/yyyy");
        String fechaT = formato.format(fechaActual);
        String lineaFecha=String.format("******************************"+fechaT+"*****************************\n");
        String lineaParametros=("Cod\tNombre\t\tCantidad\tPrecio Unidad\t IVA\tTotal\n");
        for (Producto p: lista){
            String cadenaCorta;
            double precioConIva=(p.getPrecio()+p.getPrecio()*IVA)*p.getCantidad();
            double precioUnidad=p.getPrecio()+p.getPrecio()*IVA;
            double ivaAplicado=p.getPrecio()*p.getCantidad()*IVA;

            cadenaCorta= String.format("%2d\t"+"%-10s    "+"\t%2d und.  "+"\t\t   %-3.2f €   "+"\t %-3.2f €"+
            "\t%-3.2f"+" €\n",
            p.getCodigo(),p.getNombre(),p.getCantidad(),precioUnidad,
            ivaAplicado,precioConIva);
            total=total+precioConIva;
            cadenaLarga=(cadenaLarga+cadenaCorta);
        
        }
        String cadenaTotal=String.format("**********************************************     Total:\t\t%-5.2f€   \n",total);
        cadenaLarga=(asteriscos+lineaFecha+asteriscos+lineaParametros+cadenaLarga+"\n\n"+asteriscos+cadenaTotal+asteriscos);
        return cadenaLarga;
    }
  
    public String nombreProd(String nombre)
    {
        ArrayList<Producto> listaNom = new ArrayList<Producto>();
        for(Producto elemento: listaP)
            {
               if(nombre.equalsIgnoreCase(elemento.getNombre())){
                    listaNom.add(elemento);
               }
            }
        String cadena="";
        for(Producto prod:listaNom)
        {
            String nom=String.format("%-10s\t",prod.getNombre());
            String desc=String.format("%-10s\t",prod.getDescripcion());
            cadena=cadena+prod.getCodigo()+"\t"+nom+desc+prod.getPrecio()+"€\n";
        }
        return cadena;
    }
    
     /**
     * Comprueba si el dato introducido es una cadena de caracteres o un numero.
     * Busca el producto con ese nombre/codigo y pide la cantidad.
     * Actualiza el stock y añade el producto a la lista de la compra.
     */
    public boolean buscarProducto(String cadena, String cantidad) throws IOException
    {
        boolean encontrado=false;
        Producto p=new Producto();
        int cant=Integer.parseInt(cantidad);
        if(!esNumero(cadena)){ //Si el dato introducido no es numerico, debe ser el nombre
            for(Producto elemento: listaP)
            {
                //Comprobamos si existe el nombre en la lista de productos
                if(!encontrado && cadena.equalsIgnoreCase(elemento.getNombre())){
                    p.setNombre(elemento.getNombre());
                    p.setCodigo(elemento.getCodigo());
                    p.setDescripcion(elemento.getDescripcion());
                    p.setPrecio(elemento.getPrecio());
                    p.setCantidad(elemento.getCantidad());
                    actualizarStock(elemento,cant);
                    p.setCantidad(cant);
                    listaCompra.add(p);
                    encontrado=true;
                }
            }
        }
        else{ //Si el dato introducido es numerico es el codigo
            int codigo = Integer.parseInt(cadena);
            for(Producto elemento: listaP){
                //Comprobamos si existe el codigo en la lista de productos
                if (!encontrado && codigo==elemento.getCodigo()){
                    p.setNombre(elemento.getNombre());
                    p.setCodigo(elemento.getCodigo());
                    p.setDescripcion(elemento.getDescripcion());
                    p.setPrecio(elemento.getPrecio());
                    p.setCantidad(elemento.getCantidad());
                    actualizarStock(elemento,cant);
                    p.setCantidad(cant);
                    listaCompra.add(p);
                    encontrado=true;
                } 
            }
        }
        return encontrado;
    }
    /**
     * Comprueba si una cadena esta compuesta por numeros o letras
     */
    public boolean esNumero(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    /**
     * Imprime la cabecera del tiquet
     */
    private String cabeceraTique(){
        String lineaTique=("-------------------------------------------------TIQUE--------------------------------------------\n");
        String cadenaLarga=(asteriscos+lineaTique+asteriscos+asteriscos);
        return cadenaLarga;
    }
    
    /**
     * Guarda el final del tiquet en una cadena
     */
    private String finalTique(){
        String cadenaIva=("*********************************IVA aplicado "+IVA+"% ***************************\n");
        String despedida=("**************************Gracias por su visita, vuelva pronto******************\n"); 
        String cadenaLarga=(asteriscos+cadenaIva+asteriscos+despedida+asteriscos);
        return cadenaLarga;
    }
    
    /**
     * Devuelve tiquet en un String
     */
    public String imprimeTS(int numCl, ArrayList<Producto> lista) throws IOException
    {
        exportar(numCl);
        String cadena=cabeceraTique()+cuerpoTique(lista)+finalTique();
        lista.clear();
        return cadena;
    }
    
    /**
     * Devuelve la fecha actual para guardarla en el nombre del tique
     */
    private String fechaTiquet(){
        Date fechaActual=new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHmmss");
        String fechaT = formato.format(fechaActual);
        return fechaT;
    }
    
    /**
     * Guarda el cuerpo del tiquet en la carpeta Tiquets
     */
    public void exportar(int numCl) throws IOException 
    {  
        getNumCliente(numCl);
        String ruta = (getDirectorio()+"\\Tiquets\\"+fechaTiquet()+"cl"+numCliente+".txt");
        nuevoTique(fechaTiquet()+"cl"+numCliente);
        exportaTique();
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        for (Producto p: listaCompra)
        {
            bw.write(p.getNombre()+"*"+p.getCodigo()+"*"+p.getDescripcion()+"*"+p.getPrecio()+"*"+p.getCantidad());
            bw.newLine();
        }
        bw.close();
    }
    
    /**
     * Añade un tiquet a la listaTique pasado por referencia
     */
    private void nuevoTique(String nombreTique)
    {
        listaTique.add(nombreTique);
    }
    
    private void exportaTique()throws IOException
    {
      try {
          File archivo=new File(TIQUETS);
          if (archivo.delete()) {
              FileWriter escribir=new FileWriter(archivo,true);
              Iterator<String> listaT = listaTique.iterator();
                    while(listaT.hasNext()){
                        escribir.write(listaT.next());
                        escribir.write("\r\n");
                    }
              escribir.close();
          }
        }
       catch(Exception e){}
    }
    
    /**
     * Importa todos los tiquets desde una lista de tiquets 
     */
    private void importaTique()
    {
        BufferedReader lector = null;
        listaCompra.clear();
        try {
            File miArchivo= crearArchivo(TIQUETS);
            lector = new BufferedReader(new FileReader(miArchivo));
            String linea;
            linea = lector.readLine();
            
            while(linea != null) {
                listaTique.add(linea);
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
    
    
    public void muestraTiquetPantalla(String fecha)
    {
        System.out.println(mostrarTiquet(fecha));
    }
    /**
     * Imprime por pantalla un tiquet leido desde un archivo dandole como parametro la fecha del tiquet
     */
    public String mostrarTiquet(String fecha){
        String cadena="";
        BufferedReader lector = null;
        try {
            File miArchivo= crearArchivo(getDirectorio()+"\\Tiquets\\"+fecha+".txt");
            lector = new BufferedReader(new FileReader(miArchivo));
            String linea = lector.readLine();
            while(linea != null) {
                Producto p= new Producto();
                StringTokenizer campos = new StringTokenizer(linea,"*");
                while (campos.hasMoreElements())
                {
                    boolean encontrado = false;
                    int codigoP=0;
                    String nombreP = campos.nextElement().toString();
                    codigoP = new Integer(campos.nextElement().toString().trim());
                    String descP = campos.nextElement().toString();
                    double precioP = new Double(campos.nextElement().toString().trim());
                    int cantidadP = Integer.parseInt(campos.nextElement().toString());
                    p = new Producto(nombreP,codigoP,descP,precioP,cantidadP);
                    listaCompra.add(p);
                }
                linea = lector.readLine();
            }
            }
            catch(IOException e) {
                /*System.out.println("No se pudo leer el archivo: " +
                                   fecha);*/
        }
        cadena = cabeceraTique()+cuerpoTique(listaCompra)+finalTique();
        listaCompra.clear();
        return cadena;
    }
    
    /**
     * Devuelve la ruta completa de donde se encuentra el proyecto
     */
    private String getDirectorio() throws IOException
    {
        File miDir = new File (".");
        try {
            dir=miDir.getCanonicalPath();
        }
        catch(Exception e) {
           e.printStackTrace();
        }
        return dir;
     }
    /**
     * Se crea el archivo de texto para importar/exportar nuestra lista
     */
    private File crearArchivo(String nombreArchivo) throws IOException
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
    
    /**
     * Comprueba si hay suficientes existencias para vender y si las hay actualiza el stock.
     */
    private void actualizarStock(Producto p, int cantidad) throws IOException
    {
        int stock = p.getCantidad();
        try{
            if (stock>cantidad){
                stock=stock-cantidad;
                p.setCantidad(stock);
                exportarUno(ARCHIVADOR);
            }
        }
        catch(Exception e){}
    }
   
    /**
     * Guardamos (exportamos) la lista en un archivo de texto con los nuevos datos de cantidad
     */
    public void exportarUno(String ARCHIVADOR)throws IOException
    {
        try {
          File nuevo=new File(ARCHIVADOR);
          String ruta=nuevo.getAbsolutePath();
          File archivo=new File(ruta);
          if (archivo.delete()){
                //System.out.println("El inventario se ha actualizado satisfactoriamente.");
                FileWriter escribir=new FileWriter(archivo,true);
                Iterator<Producto> lista = listaP.iterator();
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
    
    public void importarTiqueFecha(String fecha, boolean importarDeFuera) throws Exception
    {
        listaCompra.clear();
        BufferedReader lector = null;
        try {
            File miArchivo;
            if (!importarDeFuera)
            {
                 miArchivo= crearArchivo(getDirectorio()+"\\Tiquets\\"+fecha+".txt");
            }
            else
            {
                 miArchivo=new File(fecha);
            }
            lector = new BufferedReader(new FileReader(miArchivo));
            String linea;
            linea = lector.readLine();
            
            while(linea != null) {
                Producto p= new Producto();
                StringTokenizer campos = new StringTokenizer(linea,"*");
                while (campos.hasMoreElements())
                {
                    boolean encontrado = false;
                    int codigoP=0;
                    
                    String nombreP = campos.nextElement().toString();
                    codigoP = new Integer(campos.nextElement().toString().trim());
                    String descP = campos.nextElement().toString();
                    double precioP = new Double(campos.nextElement().toString().trim());
                    int cantidadP = Integer.parseInt(campos.nextElement().toString());
                    p = new Producto(nombreP,codigoP,descP,precioP,cantidadP);
                    listaCompra.add(p);
                }
                linea = lector.readLine();
            }
        }
        catch(IOException e) {}
    }
}
    

    

