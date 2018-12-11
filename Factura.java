import java.util.Iterator;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
/**
 * Clase Factura genera facturas a partir de las compras de los clientes
 */
public class Factura
{
    private ArrayList<String> listaTique;
    private ArrayList<Tique> tiquetsCliente;
    private ArrayList<String> facturas;
    private Empleados empl;
    private Tique tique;
    private Cliente cl;
    private ListadoClientes listaClientes;
    private static int numeroFactura=0;
    private String lineaIguales=("==============================================================================\n");
    /**
     * Constructor 
     */
    public Factura()
    {
        listaTique = new ArrayList<String>();
        tique = new Tique();
        tiquetsCliente = new ArrayList<Tique>();
        cl = new Cliente();
        listaClientes = new ListadoClientes();
        facturas = new ArrayList<String>();
        empl=new Empleados();
    } 
    
    public void listarTiquetsCliente (String codigoCliente, String anno)
    {
        for (String fecha : tique.listaTique)
        {
            if (fecha.startsWith(anno))
            {
                if (fecha.endsWith("cl"+codigoCliente)) 
                {
                    facturas.add(fecha);
                }
            }
        }
    }
    
    public String crearFactura(String codigoCliente, String anno, String codigoVendedor)throws Exception
    {
        Vendedor v= new Vendedor(0,"","");
        for(Vendedor vendedor:empl.listaVendedores)
        {
            if (codigoVendedor.equals(vendedor.getCodigo()))
            {
                v=vendedor;
            }
        }
        numeroFactura++;
        double totalFactura=0;
        int code = Integer.parseInt(codigoCliente);
        cl=listaClientes.getCodigo(code);
        String cad1=("==========================="+"Numero Factura: "+numeroFactura+"====================================\n");
        Date fechaActual=new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("H-mm-ss    dd/MM/yyyy");
        String fechaT = formato.format(fechaActual);;
        String cad2=("========================== "+fechaT+" =================================\n");
        String cad3=("============="+"CIF Vendedor:  "+v.getCIF()+"      Razon Social:  "+v.getRazonSocial()+"============\n");
        String cad4=("Datos del cliente:\n\nCodigo cliente: "+codigoCliente+"\n");        
        String cad5=("CIF: "+cl.getCIF()+"\nNombre: "+cl.getNombre()+"\nApellidos: "+cl.getApellidos()+"\nRazon Social: "+cl.getRazonSocial()+"\nDomicilio: "+cl.getDomicilio()+"\nFecha alta: "+cl.getFechaAlta()+"\n\n");
        String cadenaDatos=(cad1+lineaIguales+cad2+lineaIguales+cad3+lineaIguales+cad4+cad5);
        listarTiquetsCliente(codigoCliente,anno);
        String cadena;
        String cadenaLarga="";
        boolean importarDeFuera=false;
        for (String fecha: facturas){
            try{
                tique.importarTiqueFecha(fecha,importarDeFuera);
            }
            catch(Exception e){}
            for(Producto p :tique.listaCompra)
            {
                double totalProducto = p.getCantidad()*p.getPrecio();
                cadena = String.format("%-10s",p.getNombre());
                String cadenaCorta=(cadena+"      \t Tique: "+fecha+"\t Cantidad: "+p.getCantidad()+"\t TOTAL: "+totalProducto+" €\n");
                cadenaLarga = (cadenaLarga+cadenaCorta);
                totalFactura=totalFactura+totalProducto;
            }
        }
        String total=String.format("%-5.2f",totalFactura);
        String cadenaTotal=("\n\t\t\t\t\t\t\tTOTAL FACTURA: "+total+" €");
        String ultimaCadena=(cadenaDatos+cadenaLarga+cadenaTotal);
        return ultimaCadena;
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
     
     /**
     * Guarda el cuerpo del tiquet en la carpeta Tiquets
     */
    public void exportar(String codigoCliente, String ruta, boolean desdeFuera, String codigoVendedor) throws IOException 
    {  
        if (!desdeFuera)
        {
            ruta = (getDirectorio()+"\\Facturas\\"+numeroFactura+"cl"+codigoCliente+".txt");
        }
        try{
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));
            String anno="";
            bw.write(crearFactura(codigoCliente,anno,codigoVendedor));
            bw.close();
        }
        catch(Exception e){}
    }
    
    public String importar(String ruta, String codigoCliente, boolean desdeFuera) throws IOException
    {
        BufferedReader lector = null;
        if(!desdeFuera){
            ruta = (getDirectorio()+"\\Facturas\\"+numeroFactura+"cl"+codigoCliente+".txt");
        }
        File miArchivo=leerArchivo(ruta);
        lector = new BufferedReader(new FileReader(miArchivo));
        String linea="";
        String cadena="";
        linea = lector.readLine();
        while(linea != null) {
            cadena = cadena+linea+"\n";
            linea = lector.readLine();
        }
        return cadena;
    }

    /**
     * Se crea el archivo de texto para importar/exportar nuestra lista
     */
    private File leerArchivo(String nombreArchivo) throws IOException
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

   
    

