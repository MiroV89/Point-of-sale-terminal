import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.EmptyStackException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Interfaz grafica
 * 
 * Miroslav Krasimirov Vladimirov
 * version 1.0
 */
public class Interfaz extends JFrame implements ActionListener
{
    private JFrame ventana;
    private TextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
    private JButton btnInv,btnCmp,btnFct,btnCln,btnLst;
    /**
     * Constructor de la claseInterfaz
     */
    public Interfaz()
    {
        construirVentana();
    }
    
    /**
     * Boton salir cierra la aplicacion 
     */
    public void salir()
    {
        System.exit(0);
    }
    
    public void actionPerformed(ActionEvent event) 
    { 
        System.out.println("Item: " + event.getActionCommand());
    }
    
    /**
     * Metodo construir ventana principal
     */
    public void construirVentana()
    {
        ventana = new JFrame("TPV");
        ventana.setSize(500,500);
        //ventana.setUndecorated(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        
        //Etiqueta nombre y su campo de texto  
        JLabel autor= new JLabel("Autor: Miroslav Krasimirov Vladimirov");
        autor.setBounds(20,400,300,20);
        ventana.add(autor).setVisible(true);
        JLabel version= new JLabel("Versión: 1.0");
        version.setBounds(20,420,300,20);
        ventana.add(version).setVisible(true);
       
        
        //Boton Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(400,400,80,50);
        panel.add(btnSalir);
        btnSalir.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            { 
                salir();
            }
            });
 
        //Boton inventario
        btnInv = new JButton("Inventario");
        btnInv.setBounds(200, 40, 100, 50);
        panel.add(btnInv);
        btnInv.addActionListener(new ActionListener()
        {
            Inventario inv = new Inventario();
            public void actionPerformed(ActionEvent e)
            {
               ventana.setVisible(false);
               botonInventario();
            }
        });
        
        
        //Boton Compra
        btnCmp = new JButton ("Compra");
        btnCmp.setBounds(200, 110, 100, 50);
        panel.add(btnCmp);
        btnCmp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ventana.setVisible(false);
                botonCompra();
            }
        });
        
        //Boton Factura
        btnFct = new JButton ("Factura");
        btnFct.setBounds(200, 180, 100, 50);
        panel.add(btnFct);
        btnFct.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ventana.setVisible(false);
                botonFactura();
            }
        });
        
        //Boton Clientes
        btnCln = new JButton ("Clientes");
        btnCln.setBounds(200, 250, 100, 50);
        panel.add(btnCln);
        btnCln.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ventana.setVisible(false);
                botonClientes();
            }
        });
        
        //Boton Listas
        btnLst = new JButton ("Listas");
        btnLst.setBounds(200, 320, 100, 50);
        panel.add(btnLst);
        btnLst.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ventana.setVisible(false);
                botonListas();
            }
        });
        
        
        
        ventana.setVisible(true);
    }
    
    /**
     * Botones secundarios Salir (cierra aplicacion) y Volver (vuelve a la pantalla principal)
     */
    private void botonesSecundarios(JFrame ventana, Container panel)
    {
        //Boton Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(400,400,80,50);
        panel.add(btnSalir);
        btnSalir.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            { 
                salir();
            }
            });
            
        //Boton Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(300,400,80,50);
        panel.add(btnVolver);
        btnVolver.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            { 
                ventana.setVisible(false);
                construirVentana();
            }
            });
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * 
     * Aqui empieza la construccion del boton inventario
     * 
     * 
     */
    
    private void botonInventario()
    {
        ventana = new JFrame("Inventario");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        botonesSecundarios(ventana,panel);
        Inventario inv=new Inventario();
        
        //Boton AñadirProducto
        JButton btnAP = new JButton("Añadir Producto");
        btnAP.setBounds(150, 40, 200, 50);
        panel.add(btnAP);
        btnAP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                botonAnadirProducto(inv);
            }
        });
        //Boton BorrarProducto
        JButton btnBP = new JButton ("Borrar Producto");
        btnBP.setBounds(150, 110, 200, 50);
        panel.add(btnBP);
        btnBP.addActionListener(new ActionListener()
        {
           
            public void actionPerformed(ActionEvent e)
            {
                botonBorrarProducto(inv);
            }
        });
        //Boton AñadirCantidad
        JButton btnAC = new JButton ("Actualizar cantidad");
        btnAC.setBounds(150, 180, 200, 50);
        panel.add(btnAC);
        btnAC.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                botonActualizar(inv);
            }
        });
        //Boton ListarProductos
        JButton btnLP = new JButton ("Lista productos");
        btnLP.setBounds(150, 260, 200, 50);
        panel.add(btnLP);
        btnLP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                botonListar(inv);
            }
        });
        //Boton buscarPorCodigo
        JButton btnBuP = new JButton ("Buscar producto");
        btnBuP.setBounds(150, 330, 200, 50);
        panel.add(btnBuP);
        btnBuP.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonBuscar(inv); 
            }
        });
        
        //Boton Importar
        JButton btnIm = new JButton ("Importar");
        btnIm.setBounds(100, 400, 100, 50);
        panel.add(btnIm);
        btnIm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonImportarInv(inv); 
            }
        });
        
        //Boton Exportar
        JButton btnEx = new JButton ("Exportar");
        btnEx.setBounds(0, 400, 100, 50);
        panel.add(btnEx);
        btnEx.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonExportarInv(inv); 
            }
        });
        ventana.setVisible(true);
    }   
    
    private void botonAnadirProducto(Inventario inv)
    {        
            ventana = new JFrame("Añadir Producto");
            ventana.setSize(400,400);
            Container panel = ventana.getContentPane();
            panel.setLayout(null);           
        
            //Boton Añadir
            JButton btnAnadir=new JButton("Añadir");
            btnAnadir.setBounds(100,260,100,30);
            ventana.add(btnAnadir);
            btnAnadir.setVisible(true);
            btnAnadir.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                     if(tf1.getText().isEmpty()==true || tf2.getText().isEmpty()==true || 
                        tf3.getText().isEmpty()==true|| tf4.getText().isEmpty()==true || 
                        tf5.getText().isEmpty()==true)
                            {
                                error("Faltan Datos");
                            }
                            else
                            {
                        Producto p=new Producto();
                        boolean seRepiteCodigo = false;
                        int codigo=Integer.parseInt(tf1.getText());           
                        for(Producto elemento:inv.listaProductos)
                        {
                            if (!seRepiteCodigo && codigo==elemento.getCodigo())
                            {
                             seRepiteCodigo=true;
                            }
                        }
                        
                        /*try{
                             inv.anadirProducto(tf2.getText(),
                              Integer.parseInt(tf1.getText()),
                                              (tf3.getText()),
                            Double.parseDouble(tf4.getText()),
                              Integer.parseInt(tf5.getText()));
                        }
                        catch (Exception er){error("No se pudo añadir el producto");}*/
                        
                        
                        if(!seRepiteCodigo)
                        {
                            try{
                                inv.anadirProducto(tf2.getText(),
                                         Integer.parseInt(tf1.getText()),
                                         (tf3.getText()),
                                         Double.parseDouble(tf4.getText()),
                                         Integer.parseInt(tf5.getText()));
                                mensaje(" ","Añadido correctamente");
                                ventana.setVisible(false);
                            }
                            catch(Exception er){error("No se pudo añadir el producto");}
                        }
                        else{
                            error("Codigo ya existente");
                        }
                        
                    }
               }
            });
                
            //Etiqueta codigo y su campo de texto
            JLabel codigo= new JLabel("Codigo:");
            codigo.setBounds(20,30,50,20);
            ventana.add(codigo).setVisible(true);
            tf1=new TextField();
            tf1.setBounds(130,30,100,20);
            panel.add(tf1);
            tf1.setVisible(true);
                  
                
            //Etiqueta nombre y su campo de texto  
            JLabel nombre= new JLabel("Nombre:");
            nombre.setBounds(20,60,50,20);
            ventana.add(nombre).setVisible(true);
            tf2=new TextField();
            tf2.setBounds(130,60,100,20);
            panel.add(tf2);
            tf2.setVisible(true);
                  
            //Etiqueta descripcion y su campo de texto
            JLabel descripcion= new JLabel("Descripción:");
            descripcion.setBounds(20,90,100,20);
            ventana.add(descripcion).setVisible(true);
            tf3=new TextField();
            tf3.setBounds(130,90,100,20);
            panel.add(tf3);
            tf3.setVisible(true);
                  
            //Etiqueta precio y su campo de texto
            JLabel precio= new JLabel("Precio:");
            precio.setBounds(20,120,50,20);
            ventana.add(precio).setVisible(true);
            tf4=new TextField();
            tf4.setBounds(130,120,100,20);
            panel.add(tf4);
            tf4.setVisible(true);
                  
            //Etiqueta cantidad y su campo de texto
            JLabel cantidad= new JLabel("Cantidad:");
            cantidad.setBounds(20,150,80,20);
            ventana.add(cantidad).setVisible(true);
            tf5=new TextField();
            tf5.setBounds(130,150,100,20);
            panel.add(tf5);
            tf5.setVisible(true);
               
            ventana.setVisible(true);
    }
    
    private void botonBorrarProducto(Inventario inv)
    {
        ventana = new JFrame("Añadir Producto");
        ventana.setSize(400,400);
        Container panel = ventana.getContentPane();
        panel.setLayout(null); 
                
        //Boton Borrar
        JButton btnB=new JButton("Borrar");
        btnB.setBounds(100,260,100,30);
        ventana.add(btnB);
        btnB.setVisible(true);
        btnB.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                int codigo = Integer.parseInt(tf1.getText());
                boolean codigoExiste1 = false;
                for(Producto elemento:inv.listaProductos)
                {  
                   if (!codigoExiste1 && codigo==elemento.getCodigo())
                   {
                        codigoExiste1=true;
                   }
                }
                
                try{
                    inv.borrarProducto(inv.getCodigo(codigo));
                } catch(Exception er){error("No se pudo borrar el producto");}
                boolean codigoExiste2=false;
                for(Producto elemento:inv.listaProductos)
                {  
                   if (!codigoExiste2 && codigo==elemento.getCodigo())
                   {
                        codigoExiste2=true;
                   }
                }
                ventana.setVisible(false);
                if(codigoExiste1 && !codigoExiste2)
                {
                    mensaje(" ","Producto borrado");
                }
                else if(!codigoExiste1)
                {error("No existe el producto");}
           }
        });
                
        //Etiqueta codigo y su campo de texto
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,30,50,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,30,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
        ventana.setVisible(true);
    }
    
    private void botonActualizar(Inventario inv)
    {
        ventana = new JFrame("Actualizar cantidad");
        ventana.setSize(400,400);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        
        //Etiqueta codigo y su campo de texto
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,30,50,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,30,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
                  
        //Etiqueta cantidad y su campo de texto
        JLabel cantidad= new JLabel("Cantidad:");
        cantidad.setBounds(20,60,50,20);
        ventana.add(cantidad).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,60,100,20);
        panel.add(tf2);
        tf2.setVisible(true);
        
        //Boton Actualizar
        JButton btnAc=new JButton("Actualizar");
        btnAc.setBounds(100,260,100,30);
        ventana.add(btnAc);
        btnAc.setVisible(true);
        btnAc.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                boolean codigoExiste=false;
                int cant = Integer.parseInt(tf2.getText());
                int codigoP=Integer.parseInt(tf1.getText());
                   for(Producto elemento:inv.listaProductos)
                {  
                   if (!codigoExiste && codigoP==elemento.getCodigo())
                   {
                        codigoExiste=true;
                        elemento.setCantidad(elemento.getCantidad()+cant);
                        try{
                            inv.borrarProducto(inv.getCodigo(elemento.getCodigo()));
                            inv.nuevoProducto(elemento);
                        }catch(Exception er){error("No se pudo actualizar el producto");}
                   }
                }
                ventana.setVisible(false);
                if(codigoExiste)
                {
                    mensaje(" ","Cantidad Actualizada");
                }
                else if(!codigoExiste)
                {error("No existe el producto");}
               }
            });
    }
    
    public void botonListar(Inventario inv)
    {
        ventana = new JFrame("Lista Productos");
        ventana.setSize(600,610);
        Container panel = ventana.getContentPane();
        panel.setLayout(null); 
        
        //Area de texto
        TextArea areaT = new TextArea();
        areaT.setVisible(true);
        areaT.setBounds(20,20,550,550);
        panel.add(areaT);
        areaT.setText(inv.listaPS());
        ventana.setVisible(true);
    }
    
    public void botonBuscar(Inventario inv)
    {
        ventana = new JFrame("Lista Productos");
        ventana.setSize(600,600);
        Container panel = ventana.getContentPane();
        panel.setLayout(null); 
        
        //Etiqueta codigo y su campo de texto
        JLabel nombre= new JLabel("Nombre Producto:");
        nombre.setBounds(20,30,120,20);
        ventana.add(nombre).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(150,30,120,20);
        panel.add(tf1);
        tf1.setVisible(true);
                  
        //Area de texto
        TextArea areaT = new TextArea();
        areaT.setVisible(true);
        areaT.setBounds(20,100,550,450);
        panel.add(areaT);
        ventana.setVisible(true);
        
        //Boton Buscar
        JButton buscar=new JButton("Buscar");
        buscar.setVisible(true);
        buscar.setBounds(300,30,100,20);
        panel.add(buscar);
        buscar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                areaT.setText(inv.getPrNm(tf1.getText()));
                
                if(tf1.getText().isEmpty())
                {
                    mensaje(" ","Introduzca nombre");
                }
                else if(inv.getPrNm(tf1.getText()).equals(""))
                {error("No existe el producto");}
            }
        });
    }
    
    public void botonExportarInv(Inventario inv)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showSaveDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        try{
            inv.exportar(archivo.getAbsolutePath()+".txt");
        }catch(Exception er){error("No se pudo exportar");}
    }
    
    public void botonImportarInv(Inventario inv)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showOpenDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        inv.importar(archivo.getAbsolutePath());
        /**
         * 
         * 
         * Aqui acaba la construccion del boton Inventario
         * 
         * 
         */
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * 
     * Aqui empieza la construccion del boton Compra
     * 
     * 
     */
    public void botonCompra()
    {
        ventana = new JFrame("Compra");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        Tique tique = new Tique();
        botonesSecundarios(ventana,panel);
        
        //Etiqueta nombre y su campo de texto  
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,40,80,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,40,50,20);
        panel.add(tf1);
        tf1.setVisible(true);
           
           
        //Etiqueta nombre y su campo de texto  
        JLabel nomP= new JLabel("Producto:");
        nomP.setBounds(220,40,70,20);
        ventana.add(nomP).setVisible(true);
        tf4=new TextField();
        tf4.setBounds(290,40,60,20);
        panel.add(tf4);
        tf4.setVisible(true);
        
        //Area de texto para buscar el nombre
        TextArea areaTexNom = new TextArea();
        areaTexNom.setVisible(false);
        areaTexNom.setBounds(220,110,270,100);
        panel.add(areaTexNom);
        
        //Etiqueta de instruccion al usuario
        JLabel introducir1= new JLabel("Eliga el codigo e introduzcalo en el");
        JLabel introducir2= new JLabel("recuadro codigo");
        introducir1.setBounds(220,62,210,20);
        introducir2.setBounds(220,82,210,20);
        panel.add(introducir1).setVisible(false);
        panel.add(introducir2).setVisible(false);
        
        //Boton de busuqeda de nombre
        JButton btnBusN = new JButton("Buscar");
        btnBusN.setBounds(360, 40, 80, 20);
        panel.add(btnBusN);
        btnBusN.setVisible(true);
        btnBusN.addActionListener(new ActionListener()
        {
           
            public void actionPerformed(ActionEvent e)
            {
              areaTexNom.setText(tique.nombreProd(tf4.getText()));
              areaTexNom.setVisible(true);
              introducir1.setVisible(true);
              introducir2.setVisible(true);
            }
        });
        
           
        //Etiqueta nombre y su campo de texto  
        JLabel cantidad= new JLabel("Cantidad:");
        cantidad.setBounds(20,100,100,20);
        ventana.add(cantidad).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,100,50,20);
        panel.add(tf2);
        tf2.setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel nCl= new JLabel("Num. Cliente:");
        nCl.setBounds(20,200,100,20);
        ventana.add(nCl).setVisible(true);
        tf3=new TextField();
        tf3.setBounds(130,200,50,20);
        panel.add(tf3);
        tf3.setVisible(true);
           
           
        //Boton AñadirProducto
        JButton btnAPT = new JButton("Añadir Producto");
        btnAPT.setBounds(20, 150, 150, 30);
        panel.add(btnAPT);
        btnAPT.addActionListener(new ActionListener()
        {
           
            public void actionPerformed(ActionEvent e)
            {
                if(tf2.getText().isEmpty())
                {
                    tf2.setText("1");
                }
                botonAnadirPrTporCodigo(tique,tf1.getText(),tf2.getText());
                tf1.setText("");
                tf2.setText("");
                tf4.setText("");
                areaTexNom.setText("");
                areaTexNom.setVisible(false);
                introducir1.setVisible(false);
                introducir2.setVisible(false);
            }
        });
        
        //Boton ImprimirTiquet
        JButton btnIT = new JButton("Imprimir");
        btnIT.setBounds(300,250,150,50);
        panel.add(btnIT);
        btnIT.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(tf3.getText().isEmpty())
                {
                    tf3.setText("0");
                }
                int numCl=Integer.parseInt(tf3.getText());
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");                
                
                botonImprimir(tique,numCl);
            }
        
        });
        
        //Boton Lista Tiquets
        JButton btnLT = new JButton("Buscar Tique");
        btnLT.setBounds(10,340,150,50);
        panel.add(btnLT);
        btnLT.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                botonBuscarTiquet(tique);
            }
        });
        
        //Boton Importar
        JButton btnIm = new JButton ("Importar");
        btnIm.setBounds(100, 400, 100, 50);
        panel.add(btnIm);
        btnIm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonImportarT(tique); 
            }
        });
        
        //Boton Importar
        JButton btnEx = new JButton ("Exportar");
        btnEx.setBounds(0, 400, 100, 50);
        panel.add(btnEx);
        btnEx.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonExportarT(tique); 
            }
        });
    }
    
    private void botonAnadirPrTporCodigo(Tique tique,String codigoP, String cantidad)
    {
        try{
            if(!tique.buscarProducto(codigoP,cantidad))
            {error("Codigo incorrecto");}
        }
        catch(Exception e){error("Cantidad insuficiente");}
    }
    
    private void botonImprimir(Tique tique,int numCliente)
    {
        ventana = new JFrame("Tique");
        ventana.setSize(600,600);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        
        //Area de texto
        TextArea areaT = new TextArea();
        areaT.setVisible(true);
        areaT.setBounds(20,100,550,450);
        panel.add(areaT);
        ventana.setVisible(true);
        try{
            areaT.setText(tique.imprimeTS(numCliente,tique.listaCompra));
        }
        catch(Exception e){error("No se pudo guardar el tique");}
        tique.listaCompra.clear();
    }
    
    private void botonBuscarTiquet(Tique tique)
    {
        ventana = new JFrame("Tique");
        ventana.setSize(600,600);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        
        //Etiqueta nombre y su campo de texto  
        JLabel fch= new JLabel("Fecha(YYYYMMDDHHMM + num. Cliente:");
        fch.setBounds(20,20,200,20);
        ventana.add(fch).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(230,20,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
           
        //Area de texto
        TextArea areaT = new TextArea();
        areaT.setVisible(true);
        areaT.setBounds(20,100,550,450);
        panel.add(areaT);
        ventana.setVisible(true);        
        
        //Boton Lista Tiquets
        JButton btnBu = new JButton("Buscar");
        btnBu.setBounds(350,20,120,30);
        panel.add(btnBu);
        btnBu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
              try{
                  String tiquetImportado=tique.mostrarTiquet(tf1.getText());
                  if(tiquetImportado != "")
                  {
                      areaT.setText(tiquetImportado);
                  }
                  else{error("No se pudo encontrar el tique");}
              }
              catch(Exception er){}
            }
        });
    }
    
     public void botonExportarT(Tique tique)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showSaveDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        try{
            tique.exportarUno(archivo.getAbsolutePath()+".txt");
        }
        catch(Exception er){error("El archivo no se pudo guardar");}
    }
    
    public void botonImportarT(Tique tique)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showOpenDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        boolean importarDeFuera=true;
        try{
            tique.importarTiqueFecha(archivo.getAbsolutePath(),importarDeFuera);
        }
        catch(Exception er){error("No se pudo importar el archivo");}
        int numCl;
        try
        {
            numCl=Integer.parseInt(archivo.getName().substring(13));
        }
        catch(Exception er){numCl=0;}
        botonImprimir(tique,numCl);
        
        /**
         * 
         * 
         * Aqui acaba la construccion del boton Tique
         * 
         * 
         */
    }
    
    
    
    
    
    
    
    
    
    /**
    * 
    * 
    * Aqui empieza la construccion del boton Factura
    * 
    * 
    */
    private void botonFactura()
    {
        ventana = new JFrame("Factura");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        Factura fac= new Factura();
        botonesSecundarios(ventana,panel);
        
        //Boton crear Factura
        JButton btnCF = new JButton("Crear Factura");
        btnCF.setBounds(150,100,200,50);
        panel.add(btnCF);
        btnCF.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               botonCrearFactura(fac);
           }
        });        
        
        //Boton Importar
        JButton btnIm = new JButton ("Importar Factura");
        btnIm.setBounds(150, 200, 200, 50);
        panel.add(btnIm);
        btnIm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               botonImportarF(fac); 
            }
        });        
    }
    
    private void botonCrearFactura(Factura fac)
    {
        ventana = new JFrame("Nueva Factura");
        ventana.setSize(300,250);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);
        
        //Etiqueta nombre y su campo de texto  
        JLabel codCL= new JLabel("Codigo Cliente");
        codCL.setBounds(20,20,110,20);
        ventana.add(codCL).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,20,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel anno= new JLabel("Año:");
        anno.setBounds(20,60,110,20);
        ventana.add(anno).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,60,100,20);
        panel.add(tf2);
        tf2.setVisible(true); 
        
        //Etiqueta nombre y su campo de texto  
        JLabel vend= new JLabel("Vendedor:");
        vend.setBounds(20,100,110,20);
        ventana.add(vend).setVisible(true);
        tf3=new TextField();
        tf3.setBounds(130,100,100,20);
        panel.add(tf3);
        tf3.setVisible(true); 
        
        //Boton
        JButton btnBu = new JButton("Generar Factura");
        btnBu.setBounds(75,140,150,30);
        panel.add(btnBu);
        btnBu.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                ventana.setVisible(false);
                ventana = new JFrame("Clientes");
                ventana.setSize(600,600);
                Container panel = ventana.getContentPane();
                panel.setLayout(null);
                ventana.setVisible(true);
                
                //Boton Exportar
                JButton btnEx = new JButton ("Exportar");
                btnEx.setBounds(20, 25, 100, 50);
                panel.add(btnEx);
                btnEx.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                       if(tf1.getText().isEmpty()){
                           tf1.setText("0");
                       }
                       botonExportarF(fac,tf1.getText(),tf3.getText());
                    }
                });
                
                //Area de texto
                TextArea areaT = new TextArea();
                areaT.setVisible(true);
                areaT.setBounds(20,100,550,450);
                panel.add(areaT);
                Tique tique= new Tique();
                if(tique.esNumero(tf1.getText()) && tique.esNumero(tf2.getText())
                && tf2.getText().length()==4)
                {
                    try{
                        areaT.setText(fac.crearFactura(tf1.getText(),tf2.getText(),tf3.getText()));
                    }
                    catch(Exception er){error("No se pudo crear la factura");}
                    try{
                        //Guardamos la factura
                        fac.exportar(tf1.getText(),"",false,tf3.getText());
                    }
                    catch(Exception er){error("No se pudo guardar");}
                }
                else{error("Datos incorrectos");}
           }
        });
    }
    
    public void botonExportarF(Factura fac, String codCl, String codVend)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showSaveDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        try{
            fac.exportar(codCl,archivo.getAbsolutePath()+".txt",true,codVend);
        }
        catch(Exception er){error("Error al exportar");}
    }
    
    public void botonImportarF(Factura fac)
    {
        JFileChooser elegirArchivo = new JFileChooser(System.getProperty("user.dir"));
        int valor = elegirArchivo.showOpenDialog(ventana);

        if(valor != JFileChooser.APPROVE_OPTION) {
            return;  // No coincide el archivo
        }
        File archivo = elegirArchivo.getSelectedFile();
        
        ventana = new JFrame("Factura");
        ventana.setSize(600,600);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        
        //Area de texto
        TextArea areaT = new TextArea();
        areaT.setVisible(true);
        areaT.setBounds(20,100,550,450);
        panel.add(areaT);
        try{
            areaT.setText(fac.importar(archivo.getAbsolutePath(),"",true));
        }
        catch(Exception er){error("Error al importar");}
       
        /**
         * 
         * 
         * Aqui acaba la construccion del boton Factura
         * 
         * 
         */
    }
  
    
    
    
    
    
    
    
    
    
     /**
    * 
    * 
    * Aqui empieza la construccion del boton Clientes
    * 
    * 
    */
    private void botonClientes()
    {
        ventana.setVisible(false);
        ventana = new JFrame("Clientes");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);
        ListadoClientes listado=new ListadoClientes();
        
        //Boton Nuevo Cliente
        JButton btnNC = new JButton("Nuevo Cliente");
        btnNC.setBounds(150, 40, 200, 50);
        panel.add(btnNC);
        btnNC.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               botonNuevoCliente(listado);
           }
        });
        
        //Boton Borrar Cliente
        JButton btnBC = new JButton("Borrar Cliente");
        btnBC.setBounds(150, 110, 200, 50);
        panel.add(btnBC);
        btnBC.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               botonBorrarCliente(listado);
           }
        });
        
        //Boton Modificar Cliente
        JButton btnMC = new JButton("Modificar Cliente");
        btnMC.setBounds(150, 180, 200, 50);
        panel.add(btnMC);
        btnMC.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               botonModificarCliente(listado);
           }
        });
    }
        
    private void botonNuevoCliente(ListadoClientes listado)
    {
        ventana = new JFrame("Nuevo Cliente");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);
                
        //Etiqueta codigo y su campo de texto
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,30,50,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,30,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
                  
                
        //Etiqueta nombre y su campo de texto  
        JLabel nombre= new JLabel("N.I.F.:");
        nombre.setBounds(20,60,50,20);
        ventana.add(nombre).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,60,100,20);
        panel.add(tf2);
        tf2.setVisible(true);
                  
        //Etiqueta descripcion y su campo de texto
        JLabel descripcion= new JLabel("Nombre:");
        descripcion.setBounds(20,90,100,20);
        ventana.add(descripcion).setVisible(true);
        tf3=new TextField();
        tf3.setBounds(130,90,100,20);
        panel.add(tf3);
        tf3.setVisible(true);
                  
        //Etiqueta precio y su campo de texto
        JLabel precio= new JLabel("Apellidos:");
        precio.setBounds(20,120,50,20);
        ventana.add(precio).setVisible(true);
        tf4=new TextField();
        tf4.setBounds(130,120,100,20);
        panel.add(tf4);
        tf4.setVisible(true);
                  
        //Etiqueta cantidad y su campo de texto
        JLabel cantidad= new JLabel("Razon Social:");
        cantidad.setBounds(20,150,80,20);
        ventana.add(cantidad).setVisible(true);
        tf5=new TextField();
        tf5.setBounds(130,150,100,20);
        panel.add(tf5);
        tf5.setVisible(true);    
        
        //Etiqueta cantidad y su campo de texto
        JLabel domicilio= new JLabel("Domicilio:");
        domicilio.setBounds(20,180,80,20);
        ventana.add(domicilio).setVisible(true);
        tf6=new TextField();
        tf6.setBounds(130,180,100,20);
        panel.add(tf6);
        tf6.setVisible(true); 
        
        //Etiqueta cantidad y su campo de texto
        JLabel fecha= new JLabel("Fecha:");
        fecha.setBounds(20,210,80,20);
        ventana.add(fecha).setVisible(true);
        tf7=new TextField();
        tf7.setBounds(130,210,100,20);
        tf7.setEditable(false);
        panel.add(tf7);
        tf7.setVisible(true); 
        //Obtener la fecha actual
        Date fechaActual= new Date();
        SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
        tf7.setText(formato.format(fechaActual));
                
        //Boton Añadir
        JButton btnAnadir=new JButton("Añadir");
        btnAnadir.setBounds(100,260,100,50);
        ventana.add(btnAnadir);
        btnAnadir.setVisible(true);
        btnAnadir.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 if(!tf1.getText().isEmpty())
                 {    
                     try{
                         listado.nuevoCliente(tf1.getText(),tf2.getText(),tf3.getText(),
                         tf4.getText(),tf5.getText(),tf6.getText(),tf7.getText());
                         mensaje("OK","Cliente añadido");
                     }
                     catch(Exception er){error("No se pudo guardar");}
                 }
                 else{error("Falta codigo");}
            }
        });
    }
    
    private void botonBorrarCliente(ListadoClientes listado)
    {
        ventana = new JFrame("Borrar Cliente");
        ventana.setSize(250,200);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);       
        
        //Etiqueta codigo y su campo de texto
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,30,50,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,30,100,20);
        panel.add(tf1);
        tf1.setVisible(true); 
        //Boton Añadir
        JButton btnBorrar=new JButton("Borrar");
        btnBorrar.setBounds(50,120,100,30);
        ventana.add(btnBorrar);
        btnBorrar.setVisible(true);
        btnBorrar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!tf1.getText().isEmpty())
                {
                    listado.borrarClCod(tf1.getText());
                    ventana.setVisible(false);
                }
                else if(listado.borrarClCod(tf1.getText())==false)
                {
                    error("No se encontro el codigo");
                    ventana.setVisible(true);
                }
                else{error("Falta codigo");}
            }
        });
    }
    
    private void botonModificarCliente(ListadoClientes listado)
    {
        ventana = new JFrame("Modificar Cliente");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);
                
        //Etiqueta codigo y su campo de texto
        JLabel codigo= new JLabel("Codigo:");
        codigo.setBounds(20,30,50,20);
        ventana.add(codigo).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,30,100,20);
        panel.add(tf1);
        tf1.setVisible(true);
                  
                
        //Etiqueta nombre y su campo de texto  
        JLabel nombre= new JLabel("N.I.F.:");
        nombre.setBounds(20,60,50,20);
        ventana.add(nombre).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,60,100,20);
        panel.add(tf2);
        tf2.setVisible(true);
                  
        //Etiqueta descripcion y su campo de texto
        JLabel descripcion= new JLabel("Nombre:");
        descripcion.setBounds(20,90,100,20);
        ventana.add(descripcion).setVisible(true);
        tf3=new TextField();
        tf3.setBounds(130,90,100,20);
        panel.add(tf3);
        tf3.setVisible(true);
                  
        //Etiqueta precio y su campo de texto
        JLabel precio= new JLabel("Apellidos:");
        precio.setBounds(20,120,50,20);
        ventana.add(precio).setVisible(true);
        tf4=new TextField();
        tf4.setBounds(130,120,100,20);
        panel.add(tf4);
        tf4.setVisible(true);
                  
        //Etiqueta cantidad y su campo de texto
        JLabel cantidad= new JLabel("Razon Social:");
        cantidad.setBounds(20,150,80,20);
        ventana.add(cantidad).setVisible(true);
        tf5=new TextField();
        tf5.setBounds(130,150,100,20);
        panel.add(tf5);
        tf5.setVisible(true);    
        
        //Etiqueta cantidad y su campo de texto
        JLabel domicilio= new JLabel("Domicilio:");
        domicilio.setBounds(20,180,80,20);
        ventana.add(domicilio).setVisible(true);
        tf6=new TextField();
        tf6.setBounds(130,180,100,20);
        panel.add(tf6);
        tf6.setVisible(true); 
        
        //Etiqueta cantidad y su campo de texto
        JLabel fecha= new JLabel("Fecha:");
        fecha.setBounds(20,210,80,20);
        ventana.add(fecha).setVisible(true);
        tf7=new TextField();
        tf7.setBounds(130,210,100,20);
        panel.add(tf7);
        tf7.setVisible(true);        
        
        //Boton Buscar
        JButton btnBus=new JButton("Buscar");
        btnBus.setBounds(240,30,100,20);
        ventana.add(btnBus);
        btnBus.setVisible(true);
        btnBus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 if(!tf1.getText().isEmpty())
                 {
                     int code=Integer.parseInt(tf1.getText());
                     Cliente cl=listado.getCodigo(code);
                     if(cl.getCodigo() != 0)
                     {
                         tf1.setEnabled(false);
                         tf2.setText(cl.getCIF());
                         tf3.setText(cl.getNombre());
                         tf4.setText(cl.getApellidos());
                         tf5.setText(cl.getRazonSocial());
                         tf6.setText(cl.getDomicilio());
                         tf7.setText(cl.getFechaAlta());
                     }
                     else{error("Codigo no encontrado");}
                 }
                 else{error("Introduzca codigo");}
               }
            });
        
        //Boton Modificar
        JButton btnMod=new JButton("Guardar");
        btnMod.setBounds(100,260,100,30);
        ventana.add(btnMod);
        btnMod.setVisible(true);
        btnMod.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!tf1.getText().isEmpty() || !tf2.getText().isEmpty() || !tf3.getText().isEmpty() || 
                !tf4.getText().isEmpty() || !tf5.getText().isEmpty() || !tf6.getText().isEmpty() || 
                !tf7.getText().isEmpty())
                {    
                     try{
                         listado.borrarClCod(tf1.getText());
                         listado.nuevoCliente(tf1.getText(),tf2.getText(),tf3.getText(),
                         tf4.getText(),tf5.getText(),tf6.getText(),tf7.getText());
                     }
                     catch(Exception er){error("No se pudo guardar");}
                }
                else{error("Falta codigo");}
            }
        });
            
        //Boton Nueva Busqueda
        JButton btnNuBu=new JButton("Nueva Busqueda");
        btnNuBu.setBounds(220,260,150,30);
        ventana.add(btnNuBu);
        btnNuBu.setVisible(true);
        btnNuBu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tf1.setEnabled(true);
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
                tf7.setText("");
            }
        }); 
            
         /**
         * 
         * 
         * Aqui acaba la construccion del boton Clientes
         * 
         * 
         */  
    }
    
    
    
    
    
    
    
    
    
    /**
     * 
     * 
     * Aqui empieza la construccion del boton Listas
     * 
     * 
     */
    private void botonListas()
    {
        ventana = new JFrame("Listas");
        ventana.setSize(500,500);
        Container panel = ventana.getContentPane();
        panel.setLayout(null);
        ventana.setVisible(true);
        botonesSecundarios(ventana,panel);
        GeneracionListados listado = new GeneracionListados();
        
        //Etiqueta nombre y su campo de texto  
        JLabel codCl= new JLabel("Codigo cliente:");
        codCl.setBounds(20,60,110,20);
        ventana.add(codCl).setVisible(true);
        tf1=new TextField();
        tf1.setBounds(130,60,50,20);
        panel.add(tf1).setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel fchIn1= new JLabel("Fecha inicio:");
        fchIn1.setBounds(20,80,110,20);
        ventana.add(fchIn1).setVisible(true);
        tf2=new TextField();
        tf2.setBounds(130,80,50,20);
        panel.add(tf2).setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel fchFin1= new JLabel("Fecha fin:");
        fchFin1.setBounds(20,100,110,20);
        ventana.add(fchFin1).setVisible(true);
        tf3=new TextField();
        tf3.setBounds(130,100,50,20);
        panel.add(tf3).setVisible(true);     
        
        //Etiqueta de Información 
        JLabel informacion1 = new JLabel("Busqueda de tiquets de un cliente en un periodo de tiempo");
        informacion1.setBounds(20,30,400,20);
        ventana.add(informacion1).setVisible(true);
        
        //Boton Buscar
        JButton btnBu1 = new JButton("Buscar");
        btnBu1.setBounds(200,100,150,30);
        panel.add(btnBu1);
        btnBu1.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               try{
                   if(tf2.getText().isEmpty() || tf3.getText().isEmpty())
                   {error("Faltan datos");}
                   else{
                       ventana = new JFrame("Busqueda tiquets por cliente");
                       ventana.setSize(600,600);
                       Container panel = ventana.getContentPane();
                       panel.setLayout(null);
                       ventana.setVisible(true);
                   
                       //Etiqueta de Información 
                       JLabel informacion0= new JLabel("Tiquets del cliente:");
                       informacion0.setBounds(20,30,400,40);
                       ventana.add(informacion0).setVisible(true); 
                   
                       //Boton Salir
                       JButton btnSalir = new JButton("Salir");
                       btnSalir.setBounds(40,70,80,25);
                       panel.add(btnSalir);
                       btnSalir.addActionListener(new ActionListener()
                       {
                          public void actionPerformed(ActionEvent e)
                          {
                              ventana.setVisible(false);
                          }
                       });                   
                   
                       //Area de texto
                       TextArea areaT = new TextArea();
                       areaT.setVisible(true);
                       areaT.setBounds(20,100,550,450);
                       panel.add(areaT);
                       int fechaIn=Integer.parseInt(tf2.getText());
                       int fechaFin=Integer.parseInt(tf3.getText());
                       try{
                           areaT.setText(listado.busquedaPorCliente(tf1.getText(),fechaIn,fechaFin));
                       }
                       catch(EmptyStackException err){error("No existen tiquets en ese periodo");}
                   }
               }
               catch(Exception er){error("Datos incorrectos");}
            }
        });
                
        //Etiqueta nombre y su campo de texto  
        JLabel fchIn2= new JLabel("Fecha inicio:");
        fchIn2.setBounds(20,180,110,20);
        ventana.add(fchIn2).setVisible(true);
        tf4=new TextField();
        tf4.setBounds(130,180,50,20);
        panel.add(tf4).setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel fchFin2= new JLabel("Fecha fin:");
        fchFin2.setBounds(20,200,110,20);
        ventana.add(fchFin2).setVisible(true);
        tf5=new TextField();
        tf5.setBounds(130,200,50,20);
        panel.add(tf5).setVisible(true);
        
        //Etiqueta de Información 
        JLabel informacion2= new JLabel("Busqueda de tiquets emitidos en un periodo de tiempo");
        informacion2.setBounds(20,150,400,20);
        ventana.add(informacion2).setVisible(true);        
        //Boton Buscar
        JButton btnBu2 = new JButton("Buscar");
        btnBu2.setBounds(200,200,150,30);
        panel.add(btnBu2);
        btnBu2.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
              try{
                  if(tf4.getText().isEmpty() || tf5.getText().isEmpty())
                  {error("Faltan datos");}
                  else{
                      ventana = new JFrame("Busqueda tiquets por intervalo de tiempo");
                      ventana.setSize(600,600);
                      Container panel = ventana.getContentPane();
                      panel.setLayout(null);
                      ventana.setVisible(true);
                    
                      //Etiqueta de Información 
                      JLabel informacion0= new JLabel("Tiquets emitidos:");
                      informacion0.setBounds(20,30,400,40);
                      ventana.add(informacion0).setVisible(true); 
                   
                      //Boton Salir
                      JButton btnSalir = new JButton("Salir");
                      btnSalir.setBounds(40,70,80,25);
                      panel.add(btnSalir);
                      btnSalir.addActionListener(new ActionListener()
                      {
                         public void actionPerformed(ActionEvent e)
                         {
                             ventana.setVisible(false);
                         }
                      });                   
                   
                      //Area de texto
                      TextArea areaT = new TextArea();
                      areaT.setVisible(true);
                      areaT.setBounds(20,100,550,450);
                      panel.add(areaT);
                      int fechaIn=Integer.parseInt(tf4.getText());
                      int fechaFin=Integer.parseInt(tf5.getText());
                      try{
                          areaT.setText(listado.busquedaTodosClientes(fechaIn,fechaFin));
                      }
                      catch(EmptyStackException err){error("No existen tiquets en ese periodo");}
                  }
              }
              catch(Exception er){error("Datos incorrectos");}
            }
        });
        
        
        //Etiqueta nombre y su campo de texto  
        JLabel fchIn3= new JLabel("Fecha inicio:");
        fchIn3.setBounds(20,280,110,20);
        ventana.add(fchIn3).setVisible(true);
        tf6=new TextField();
        tf6.setBounds(130,280,50,20);
        panel.add(tf6).setVisible(true);
        
        //Etiqueta nombre y su campo de texto  
        JLabel fchFin3= new JLabel("Fecha fin:");
        fchFin3.setBounds(20,300,110,20);
        ventana.add(fchFin3).setVisible(true);
        tf7=new TextField();
        tf7.setBounds(130,300,50,20);
        panel.add(tf7).setVisible(true);
        
        //Etiqueta de Información 
        JLabel informacion3= new JLabel("Ranking de productos vendidos");
        informacion3.setBounds(20,250,400,20);
        ventana.add(informacion3).setVisible(true);
        
        //Boton Buscar
        JButton btnBu3 = new JButton("Buscar");
        btnBu3.setBounds(200,300,150,30);
        panel.add(btnBu3);
        btnBu3.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               try{
                   if(tf6.getText().isEmpty() || tf7.getText().isEmpty())
                   {error("Faltan datos");}
                   else{
                       ventana = new JFrame("Ranking de productos vendidos");
                       ventana.setSize(600,600);
                       Container panel = ventana.getContentPane();
                       panel.setLayout(null);
                       ventana.setVisible(true);
                       
                       //Etiqueta de Información 
                       JLabel informacion0= new JLabel("Ranking de productos vendidos:");
                       informacion0.setBounds(20,30,400,40);
                       ventana.add(informacion0).setVisible(true); 
                       
                       //Boton Salir
                       JButton btnSalir = new JButton("Salir");
                       btnSalir.setBounds(40,70,80,25);
                       panel.add(btnSalir);
                       btnSalir.addActionListener(new ActionListener()
                       {
                          public void actionPerformed(ActionEvent e)
                          {
                              ventana.setVisible(false);
                          }
                       });
                       
                       //Area de texto
                       TextArea areaT = new TextArea();
                       areaT.setVisible(true);
                       areaT.setBounds(20,100,550,450);
                       panel.add(areaT);
                       int fechaIn=Integer.parseInt(tf6.getText());
                       int fechaFin=Integer.parseInt(tf7.getText());
                       try{
                           areaT.setText(listado.imprimirRanking(fechaIn,fechaFin));
                       }
                       
                       catch(EmptyStackException err){error("No existen tiquets en ese periodo");}
                       }
               }            
               catch(Exception er){error("Datos incorrectos");}
            }
        });
    }
    

    
    /**
     * Muestra un mensaje en una ventana nueva
     */
    private void mensaje(String nombreMensaje,String mensaje)
    {
        JFrame err=new JFrame(nombreMensaje);
        Container panel = err.getContentPane();
        panel.setLayout(null);
        err.setSize(200,200);
        JLabel mensajeError= new JLabel(mensaje);
        mensajeError.setBounds(20,20,180,30);
        mensajeError.setVisible(true);
        panel.add(mensajeError);
        JButton ok=new JButton("OK");
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {err.setVisible(false);}
        });
        ok.setBounds(60,100,80,40);
        panel.add(ok);
        ok.setVisible(true);
        err.setVisible(true);
    }
    
    
    
    /**
     * Muestra un error en una ventana nueva 
     */
    public void error(String mensaje)
    {
        JFrame err=new JFrame("Error");
        Container panel = err.getContentPane();
        panel.setLayout(null);
        err.setSize(400,200);
        JLabel mensajeError= new JLabel(mensaje);
        mensajeError.setBounds(20,20,380,30);
        mensajeError.setVisible(true);
        panel.add(mensajeError);
        JButton ok=new JButton("OK");
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {err.setVisible(false);}
        });
        ok.setBounds(160,100,80,40);
        panel.add(ok);
        ok.setVisible(true);
        err.setVisible(true);
        
    }
}
