import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
import java.io.IOException;
import java.util.EmptyStackException;
/**
 * La clase Generacion Listados se encarga de:
 * -Imprimir los tiquets de un cliente en un periodo de tiempo
 * -Imprimir todos los tiquets en un periodo de tiempo
 * -Imprimir un ranking de los productos vendidos en un periodo de tiempo
 */
public class GeneracionListados
{
    private ArrayList<String> listaTiquets;
    private ArrayList<String> listaFinal;
    private ArrayList<Producto> listaProductosTiquet;
    private ArrayList<Producto> listaTemporal;
    private ArrayList<Integer> listaEnteros;
    private static final String TIQUETS = "tiquets.txt";
    private static final double IVA=0.21;
    private double precioCaja=0;
    Inventario inv;
    Tique tique;
    Cliente cl;
    ListadoClientes listado;
    /**
     * Constructor de la clase
     */
    public GeneracionListados()
    {
        listaTiquets = new ArrayList<String>();
        listaFinal = new ArrayList<String>();
        listaTemporal = new ArrayList<Producto>();
        listaEnteros = new ArrayList<Integer>();  
        listaProductosTiquet = new ArrayList<Producto>();        
        tique = new Tique();
        listado = new ListadoClientes();
        inv = new Inventario();
    }
    
    /**
     * Cambia la fecha en caso de haber introducido solo año o año y mes
     */
    private int cambiarFecha(int fecha)
    {
        while (fecha<10000000)
        {
            fecha=fecha*10;
        }
        return fecha;
    }
    
    /**
     * Crea una lista con los tiquets entre las dos fechas indicadas.
     * Rellena la listatiquets
     * Fecha inicio y fecha fin deben tener formato YYYYMMDD.
     */
    private void intervaloTiempo(int fechaInicio, int fechaFin) throws EmptyStackException, Exception
    {
        //Limpiamos la listaTiquets
        listaTiquets.clear();
        fechaInicio = cambiarFecha(fechaInicio);
        fechaFin = cambiarFecha(fechaFin);
        BufferedReader lector = null;
        ArrayList<String> listaTiquetsImportada= new ArrayList<String>();
            try {
                //Importamos todos los tiquets y los añadimos a la listaTiquets
                File miArchivo = new File(TIQUETS);
                lector = new BufferedReader(new FileReader(miArchivo));
                String linea = lector.readLine();
                while (linea != null)
                {
                    listaTiquetsImportada.add(linea);
                    linea = lector.readLine();
                }
                lector.close();
                //De la listaTiquetsImportada añadimos a listaTiquets aquellos que estan en el rango de las fechas
                for(String elemento : listaTiquetsImportada)
                {
                    String subFecha= elemento.substring(0,8);
                    int comparador = Integer.parseInt(subFecha);
                    if (comparador>fechaInicio && comparador<fechaFin)
                    {
                        listaTiquets.add(elemento);
                    }
                }
            }            
            catch(Exception e){}
            if (listaTiquets.size()==0){throw new EmptyStackException();}
    }
    
    /**
     * Devuelve el cliente al que le corresponde el codigo introducido
     */
    private Cliente buscaCliente(String codCliente)
    {
        boolean encontrado=false;
        int codigoIntCliente = Integer.parseInt(codCliente);
        cl = new Cliente();
        for(Cliente cliente : listado.listaClientes)
        {
            if(!encontrado || (cliente.getCodigo() == codigoIntCliente))
            {
                encontrado=true;
                cl = cliente;                
            }
        }
        return cl;
    }

    /**
     * Añade los tiquets del cliente a una lista para despues sacarlos de la lista y devolverlos en una cadena
     */
    public String busquedaPorCliente(String codCliente, int fechaInicio, int fechaFin) throws EmptyStackException,Exception
    {
        buscaCliente(codCliente);
        String nombre=String.format("%-10s %-20s",cl.getNombre(),cl.getApellidos());
        String cadCompras=("Compras de : "+nombre+
                    "\t Num. Cliente: "+ cl.getCodigo()+"\n\n");
        intervaloTiempo(fechaInicio,fechaFin);
        double precioTotal=0;
        String cadenaLarga="";
        listaFinal.clear();
        for (String elemento : listaTiquets)
        {
            if(elemento.endsWith("cl"+codCliente))
            {
                listaFinal.add(elemento);
            }
        }
        for (String elementos: listaFinal)
        {
            //Lista final se compone de los tiquets (fechas) que entran en el periodo señalado
            String elementoFecha=elementos.substring(0,8);
            int fechaInt = Integer.parseInt(elementoFecha);
            String fecha=(imprimeFecha(fechaInt));
            //Se importa el tiquet y se guardan los productos en la listaCompra
            tique.importarTiqueFecha(elementos,false);

            for(Producto p : tique.listaCompra)
            {   //De la lista Compra guardamos los productos en una cadena
                String cadena = String.format("%-10s",p.getNombre());
                double totalProducto = p.getCantidad()*p.getPrecio()+(p.getCantidad()*p.getPrecio()*IVA);
                String precio=String.format("%.2f",totalProducto);
                String cadProducto=(cadena+"    \t Cantidad: "+p.getCantidad()+"\t Fecha: "+fecha+"\t TOTAL: "+precio+" €\n");
                precioTotal=precioTotal+totalProducto;
                cadenaLarga=(cadenaLarga+cadProducto);
            }
        }
        precioCaja=precioCaja+precioTotal;
        String precioT=String.format("%.2f",precioTotal);
        String cadenaTotal=("\t\t\t\t\t\t\t TOTAL: "+precioT+" €\n\n");
        String ultimaCad=cadCompras+cadenaLarga+"\n"+cadenaTotal;
        listaTiquets.clear();
        return ultimaCad;
    }
    
    /**
     * Separa los dias,meses,años del nombre del tiquet y los devuelve en una cadena con formato DD-MM-YYYY
     */
    private String imprimeFecha(int fecha)
    {
        String cadenaF = Integer.toString(fecha);
        String cadAnno=cadenaF.substring(0,4);
        String cadMes=cadenaF.substring(4,6);
        String cadDia=cadenaF.substring(6,8);
        String cadenaFinal=(cadDia+"-"+cadMes+"-"+cadAnno);
        return cadenaFinal;
    }
    
    /**
     * Devuelve un String con la lista de los tiquets generados a todos los clientes
     */
    public String busquedaTodosClientes(int fechaInicio, int fechaFin)throws Exception
    {
        String cabecera=("Lista de Compras desde "+imprimeFecha(cambiarFecha(fechaInicio))+
                                            "\thasta "+imprimeFecha(cambiarFecha(fechaFin))+"\n");
        String cadena="";
        precioCaja=0.0;
        for (Cliente cliente : listado.listaClientes)
        {
            String codigo;
            codigo=Integer.toString(cliente.getCodigo());
            try{
                cadena=cadena+busquedaPorCliente(codigo, fechaInicio, fechaFin);
            }
            catch(Exception e){}
        }
        String precioTot=String.format("%.2f",precioCaja);
        String todasVentas=("\t\t\t Total vendido: "+precioTot+" €");
        String aDevolver=cabecera+cadena+todasVentas;
        return aDevolver;
    }
    
    /**
     * Devuelve un String con el ranking de todos los productos vendidos de mayor a menor cantidad
     */
    public String imprimirRanking(int fechaInicio, int fechaFin) throws Exception
    {
        String cad1=("Ranking de Compras desde "+imprimeFecha(cambiarFecha(fechaInicio))+
                                          "\thasta "+imprimeFecha(cambiarFecha(fechaFin))+"\n");
        clasificarVendidos(fechaInicio,fechaFin);
        String cadFinal=cad1+ordenarLista();
        return cadFinal;
    }
    
    /**
     * Ordena la lista de las cantidades de los productos vendidos, compra la cantidad con los productos y los devuelve ordenados
     * de mayor a menor en una cadena String
     */
    private String ordenarLista()
    {
        String cadena="";
        //Rellenar una lista con las cantidades vendidas
        ArrayList<Integer> listaEnterosConRepetidos= new ArrayList<Integer>();
        for (Producto p: listaTemporal)
        {
              int i=p.getCantidad();
              listaEnterosConRepetidos.add(i);
        }
        for (Integer cant:listaEnterosConRepetidos)
        {
            if (!listaEnteros.contains(cant))
            {
                listaEnteros.add(cant);
            }
        }
        Collections.sort(listaEnteros, Collections.reverseOrder());
        for (Integer i:listaEnteros)
        {
            if (i!=0){
              for (Producto p:listaTemporal)
              {
                  if (p.getCantidad()==i )
                  {
                      String cadenaNom=String.format("%-10s",p.getNombre());
                      cadena=cadena+("Producto: "+cadenaNom+" \t"+p.getDescripcion()+"        \t Cantidad vendida: "+p.getCantidad()+"\n");
                  }
              }
            }
        }
        return cadena;
    }
    
    /**
     * Rellena una lista de productos con todos los productos vendidos entre las fechas
     * y les asigna la cantidad total vendida en ese periodo.
     */
    private void clasificarVendidos(int fechaInicio, int fechaFin)throws EmptyStackException,Exception
    {   
        listaTemporal.clear();
        listaProductosTiquet.clear();
        intervaloTiempo(fechaInicio, fechaFin);
        for (String elementos:listaTiquets)
        {
            tique.importarTiqueFecha(elementos,false);
            for (Producto p:tique.listaCompra)
            {
                listaProductosTiquet.add(p);
            }
        }
        for(Producto pr:inv.listaProductos)
        {
            int cantidadTotal=0;
            for (Producto p:listaProductosTiquet)
            {
                if (pr.getCodigo()==p.getCodigo())
                {
                    cantidadTotal=cantidadTotal+(p.getCantidad());
                }
            }
            pr.setCantidad(cantidadTotal);
            listaTemporal.add(pr);
        }
        listaTiquets.clear();
    }
}
