package espotify.presentacion;

import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Tema;
import espotify.logica.TemaConRuta;
import espotify.logica.TemaConURL;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author ms
 */

public class ConsultaListaReproduccion extends javax.swing.JInternalFrame {
    
    private IControlador controlador;
    private String imgLista;

    
    public ConsultaListaReproduccion() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        // Cargar el jList con los Nicknames de los Clientes del Sistema
        DefaultListModel<String> listaNicknamesClientes = new DefaultListModel<>();
        ArrayList<String> nicknamesClientes = new ArrayList<>(controlador.getNicknamesClientes());

        nicknamesClientes.forEach(listaNicknamesClientes::addElement);
        jListC.setModel(listaNicknamesClientes);
        
        // Cargar los generos en el JTree
        cargarGenerosEnJTree(); 
        
        jLabelListaDeGeneros.setVisible(true);
        jTreeGeneros.setVisible(true);
        jScrollPaneGenero.setVisible(true);
        jLabelGenero.setVisible(true);
        jTextFieldGenero.setVisible(true);
        
        
        jLabelListaDeClientes.setVisible(false);
        jListC.setVisible(false);
        JScrollPanelCliente.setVisible(false);
        jLabelCliente.setVisible(false);
        jTextFieldCliente.setVisible(false);
        
    }
    
    private void cargarGenerosEnJTree() {
    
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Generos");
        ArrayList<DTGenero> generos = new ArrayList<>(controlador.getGenerosjTree());

        // Mapa para relacionar nombres de generos con sus nodos
        Map<String, DefaultMutableTreeNode> nodosGeneros = new HashMap<>();

        // Primera pasada: Crear nodos para todos los generos y agregarlos al mapa
        for (DTGenero genero : generos) {
            DefaultMutableTreeNode nodoGenero = new DefaultMutableTreeNode(genero.getNombreGenero());
            nodosGeneros.put(genero.getNombreGenero(), nodoGenero);

            // Si el genero no tiene padre agregarlo a la raiz
            if (genero.getMiPadre() == null) {
                raiz.add(nodoGenero);
            }
        }

        // Segunda pasada: Establecer relaciones padre-hijo basadas
        for (DTGenero genero : generos) {
            if (genero.getMiPadre() != null) {
                DefaultMutableTreeNode nodoGenero = nodosGeneros.get(genero.getNombreGenero());
                DefaultMutableTreeNode nodoPadre = nodosGeneros.get(genero.getMiPadre().getNombreGenero());

                // Comprobar que ambos nodos existan 
                if (nodoPadre != null && nodoGenero != null) {
                    nodoPadre.add(nodoGenero);
                }
            }
        }

        // Establecer modelo del arbol
        jTreeGeneros.setModel(new DefaultTreeModel(raiz));
    }

    private void mostrarTemasEnTabla(List<DTTemaSimple> temas) {
    
        // Definir columnas de la tabla
        String[] columnas = {"Nombre Tema", "Duración (seg)", "Posición en Álbum", "Nombre del Álbum", "Artista"};

        // Convertir la lista de temas a formato para JTable
        Object[][] datos = new Object[temas.size()][columnas.length];
        for (int i = 0; i < temas.size(); i++) {
            DTTemaSimple tema = temas.get(i);
            datos[i][0] = tema.getNombreTema();
            datos[i][1] = tema.getDuracionSegundos();
            datos[i][2] = tema.getPosicionEnAlbum();
            datos[i][3] = tema.getNombreAlbum();
            datos[i][4] = tema.getNombreCompletoArtista();
        }

        // Crear y configurar modelo de la tabla
        DefaultTableModel modeloTabla;
        modeloTabla = new DefaultTableModel(datos, columnas);

        // Asignar modelo a la JTable y evitar que se edite
        jTableTemas.setModel(modeloTabla);
        jTableTemas.setDefaultEditor(Object.class, null);
        jTableTemas.getTableHeader().setReorderingAllowed(false);
    }

    private void fotoLista(String rutaImagen) {
        
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
                ImageIcon iconoImagen = new ImageIcon(rutaImagen);

                // Escalar imagen para ajustarla al JLabel 
                Image imagenEscalada = iconoImagen.getImage().getScaledInstance(imageLabelFotoLista.getWidth(), imageLabelFotoLista.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

                // Asignar imagen al JLabel
                imageLabelFotoLista.setIcon(iconoEscalado);
            } catch (Exception e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
                imageLabelFotoLista.setIcon(null); // Deja vacia la foto si hay error
            }
        } else {
            // Dejar vacia la foto si no tiene
            imageLabelFotoLista.setIcon(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelConsultarPor = new javax.swing.JLabel();
        jComboBoxConsultarPor = new javax.swing.JComboBox<>();
        jLabelListaDeGeneros = new javax.swing.JLabel();
        jLabelListaDeClientes = new javax.swing.JLabel();
        JScrollPanelCliente = new javax.swing.JScrollPane();
        jListC = new javax.swing.JList<>();
        jScrollPaneGenero = new javax.swing.JScrollPane();
        jTreeGeneros = new javax.swing.JTree();
        jLabelListasDeReproduccion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListListaDeReproduccion = new javax.swing.JList<>();
        jLabelNombreDeLaLista = new javax.swing.JLabel();
        jLabelGenero = new javax.swing.JLabel();
        jLabelCliente = new javax.swing.JLabel();
        jLabelInformacionDeLosTemas = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jTextFieldGenero = new javax.swing.JTextField();
        imageLabelFotoLista = new javax.swing.JLabel();
        jButtonDescargarArchivosDeMusica = new javax.swing.JButton();
        jButtonVerDireccionWeb = new javax.swing.JButton();
        jTextFieldNombreDeLaLista = new javax.swing.JTextField();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonConsultarLista = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTemas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de Lista de Reproduccion");

        jLabelConsultarPor.setText("Consultar por: ");

        jComboBoxConsultarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Género", "Cliente" }));
        jComboBoxConsultarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsultarPorActionPerformed(evt);
            }
        });

        jLabelListaDeGeneros.setText("Lista de Géneros:");

        jLabelListaDeClientes.setText("Lista de Clientes:");

        JScrollPanelCliente.setViewportView(jListC);

        jScrollPaneGenero.setViewportView(jTreeGeneros);

        jLabelListasDeReproduccion.setText("Listas de Reproducción:");

        jScrollPane1.setViewportView(jListListaDeReproduccion);

        jLabelNombreDeLaLista.setText("Nombre de la Lista:");

        jLabelGenero.setText("Género:");

        jLabelCliente.setText("Cliente:");

        jLabelInformacionDeLosTemas.setText("Información de los temas:");

        imageLabelFotoLista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonDescargarArchivosDeMusica.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonDescargarArchivosDeMusica.setText("Descargar Archivo de Música");
        jButtonDescargarArchivosDeMusica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonDescargarArchivosDeMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescargarArchivosDeMusicaActionPerformed(evt);
            }
        });

        jButtonVerDireccionWeb.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonVerDireccionWeb.setText("Ver Dirección Web");
        jButtonVerDireccionWeb.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVerDireccionWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDireccionWebActionPerformed(evt);
            }
        });

        jButtonSeleccionar.setText("Seleccionar");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        jButtonConsultarLista.setText("Consultar Lista");
        jButtonConsultarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarListaActionPerformed(evt);
            }
        });

        jTableTemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableTemas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelConsultarPor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxConsultarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelListaDeGeneros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelListaDeClientes))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPaneGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JScrollPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButtonSeleccionar)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelListasDeReproduccion)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(imageLabelFotoLista, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabelNombreDeLaLista, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextFieldNombreDeLaLista, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabelCliente)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 62, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelGenero)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldGenero))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInformacionDeLosTemas)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButtonVerDireccionWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonDescargarArchivosDeMusica)))
                                        .addGap(182, 182, 182))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButtonConsultarLista)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConsultarPor)
                    .addComponent(jComboBoxConsultarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageLabelFotoLista, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelNombreDeLaLista)
                                    .addComponent(jTextFieldNombreDeLaLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelGenero))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelCliente)
                                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15)
                        .addComponent(jLabelInformacionDeLosTemas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonVerDireccionWeb)
                            .addComponent(jButtonDescargarArchivosDeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelListaDeGeneros)
                            .addComponent(jLabelListaDeClientes)
                            .addComponent(jLabelListasDeReproduccion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JScrollPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSeleccionar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonConsultarLista)))))
                .addContainerGap(333, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxConsultarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsultarPorActionPerformed
        
        String opcion=(String)jComboBoxConsultarPor.getSelectedItem();
        if("Género".equals(opcion)){          
            //Limpiar los campos
            jTextFieldNombreDeLaLista.setText("");
            jTextFieldGenero.setText("");
            jTextFieldCliente.setText("");
            imageLabelFotoLista.setIcon(null);
            
            DefaultTableModel modeloTabla = (DefaultTableModel) jTableTemas.getModel();
            modeloTabla.setRowCount(0);
            jListListaDeReproduccion.setModel(new DefaultListModel<>());
            
            jLabelListaDeGeneros.setVisible(true);
            jTreeGeneros.setVisible(true);
            jScrollPaneGenero.setVisible(true);
            jLabelGenero.setVisible(true);
            jTextFieldGenero.setVisible(true);
            
            jLabelListaDeClientes.setVisible(false);
            jListC.setVisible(false);
            JScrollPanelCliente.setVisible(false);
            jLabelCliente.setVisible(false);
            jTextFieldCliente.setVisible(false);          
        } else{           
            //Limpiar los campos
            jTextFieldNombreDeLaLista.setText("");
            jTextFieldGenero.setText("");
            jTextFieldCliente.setText("");
            imageLabelFotoLista.setIcon(null);
            
            DefaultTableModel modeloTabla = (DefaultTableModel) jTableTemas.getModel();
            modeloTabla.setRowCount(0);
            jListListaDeReproduccion.setModel(new DefaultListModel<>());
        
            jLabelListaDeGeneros.setVisible(false);
            jTreeGeneros.setVisible(false);
            jScrollPaneGenero.setVisible(false);
            jLabelGenero.setVisible(false);
            jTextFieldGenero.setVisible(false);
            
            
            jLabelListaDeClientes.setVisible(true);
            jListC.setVisible(true);
            JScrollPanelCliente.setVisible(true);
            jLabelCliente.setVisible(true);
            jTextFieldCliente.setVisible(true);
        }
    }//GEN-LAST:event_jComboBoxConsultarPorActionPerformed

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
       
      DefaultListModel<String> modeloLista = new DefaultListModel<>();
      jListListaDeReproduccion.setModel(modeloLista);

        String op = (String) jComboBoxConsultarPor.getSelectedItem();
        if (op.equals("Género")) {
            // Obtener genero del JTree
            DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTreeGeneros.getLastSelectedPathComponent();  
            if (nodoSeleccionado != null) {
                String gen = nodoSeleccionado.toString();
                // Obtener nombres de las listas por defecto para el genero seleccionado 
                List<String> nombresListas = controlador.ConsultarNombresListasPorTipo("Por Defecto", gen);
                if (nombresListas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron listas de reproducción para el género seleccionado", "--", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Mostrar listas en el jList
                    for (String nombreLista : nombresListas) {
                        modeloLista.addElement(nombreLista);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un género de la lista de generos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (op.equals("Cliente")) {
            // Obtener el cliente del jList
            String cl = jListC.getSelectedValue();
            if (cl != null) {
                List<String> nombresListas = controlador.ConsultarNombresListasPorTipo("Particular", cl);
                if (nombresListas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron listas de reproducción para el cliente seleccionado", "--", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Mostrar las listas en el jList
                    for (String nombreLista : nombresListas) {
                        modeloLista.addElement(nombreLista);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente de la lista de clientes", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //Limpiar los campos
        jTextFieldNombreDeLaLista.setText("");
        jTextFieldGenero.setText("");
        jTextFieldCliente.setText("");
        imageLabelFotoLista.setIcon(null);
        DefaultTableModel modeloTabla = (DefaultTableModel) jTableTemas.getModel();
        modeloTabla.setRowCount(0);
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jButtonConsultarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarListaActionPerformed
           
        String nombreLista = jListListaDeReproduccion.getSelectedValue();
        // Comprobar que hay una lista seleccionada
        if (nombreLista != null) { 
            String op = jComboBoxConsultarPor.getSelectedItem().toString();
            if (op.equals("Género")) {
                // Obtener datos de la lista asociada al genero
                DTDatosListaReproduccion datosLista = controlador.ConsultarListaReproduccion("Por Defecto", nombreLista);
                if (datosLista != null) {
                    // Mostrar datos de la lista 
                    jTextFieldNombreDeLaLista.setText(datosLista.getNombreLista());
                    jTextFieldGenero.setText(datosLista.getGenero());
                    fotoLista(datosLista.getFotoLista());
                    jTextFieldCliente.setText("");

                    // Mostrar temas en el JTable
                    mostrarTemasEnTabla(datosLista.getTemas());
                }
            } else if (op.equals("Cliente")) {
                // Obtener datos de la lista asociada al cliente
                DTDatosListaReproduccion datosLista = controlador.ConsultarListaReproduccion("Particular", nombreLista);
                if (datosLista != null) {
                    // Mostrar datos de la lista 
                    jTextFieldNombreDeLaLista.setText(datosLista.getNombreLista());
                    jTextFieldCliente.setText(datosLista.getCliente());
                    fotoLista(datosLista.getFotoLista());
                    jTextFieldGenero.setText("");

                    // Mostrar temas en el JTable
                    mostrarTemasEnTabla(datosLista.getTemas());
                }
            }
        }
    }//GEN-LAST:event_jButtonConsultarListaActionPerformed

    private void jButtonVerDireccionWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDireccionWebActionPerformed
    
        // Obtener la fila seleccionada en la tabla
        int fila = jTableTemas.getSelectedRow();
        
        // Comprobar que hay una fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tema de la tabla en caso de tener", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String op = jComboBoxConsultarPor.getSelectedItem().toString();
        String nombreLista = (String) jListListaDeReproduccion.getSelectedValue();
        String tipoDeLista = (String) jComboBoxConsultarPor.getSelectedItem().toString();
        String nombreTema = (String) jTableTemas.getValueAt(fila, 0);
                
        Tema tema = null;
        if (op.equals("Género")) {
            tema = controlador.getTemaPorLista(nombreLista, tipoDeLista, nombreTema); 
        } else if (op.equals("Cliente")) {
            tema = controlador.getTemaPorLista(nombreLista, tipoDeLista, nombreTema); 
        }
        
        //Comprobar si tiene URL
        if (tema instanceof TemaConURL temaConURL) {
            String urlTema = temaConURL.getUrlTema();
            if (urlTema != null && !urlTema.isEmpty()) {
                // JTextArea para mostrar la URL y que se pueda copiar
                JTextArea textArea = new JTextArea(urlTema);
                textArea.setEditable(false); 
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "URL del Tema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El tema seleccionado no tiene una URL disponible", "--", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El tema seleccionado no tiene una URL disponible", "--", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVerDireccionWebActionPerformed

    private void jButtonDescargarArchivosDeMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescargarArchivosDeMusicaActionPerformed
          
        // Obtener la fila seleccionada en la tabla
        int fila = jTableTemas.getSelectedRow();
    
        // Comprobar que hay una fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tema de la tabla en caso de tener", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String op = jComboBoxConsultarPor.getSelectedItem().toString();
        String nombreLista = (String) jListListaDeReproduccion.getSelectedValue();
        String tipoDeLista = (String) jComboBoxConsultarPor.getSelectedItem().toString();
        String nombreTema = (String) jTableTemas.getValueAt(fila, 0);
        
        Tema tema = null;
        if (op.equals("Género")) {
            tema = controlador.getTemaPorLista(nombreLista, tipoDeLista, nombreTema);
        } else if (op.equals("Cliente")) {
            tema = controlador.getTemaPorLista(nombreLista, tipoDeLista, nombreTema);
        }

        // Comprobar si se encuentra disponible para descargar
        if (tema instanceof TemaConRuta temaConRuta) {
            String rutaTema = temaConRuta.getRutaTema();
            if (rutaTema != null && !rutaTema.isEmpty()) {
                // Descargar el archivo de musica desde la ruta
                File archivo = new File(rutaTema);
                if (archivo.exists()) {
                    try {
                        // Logica para copiar el archivo 
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Guardar archivo de música");
                        int userSelection = fileChooser.showSaveDialog(this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File destino = fileChooser.getSelectedFile();
                            Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(this, "Archivo descargado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Ocurrió un error al descargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe en la ruta especificada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El tema seleccionado no se encuentra disponible para descargar", "--", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El tema seleccionado no se encuentra disponible para descargar", "--", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDescargarArchivosDeMusicaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanelCliente;
    private javax.swing.JLabel imageLabelFotoLista;
    private javax.swing.JButton jButtonConsultarLista;
    private javax.swing.JButton jButtonDescargarArchivosDeMusica;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JButton jButtonVerDireccionWeb;
    private javax.swing.JComboBox<String> jComboBoxConsultarPor;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelConsultarPor;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelInformacionDeLosTemas;
    private javax.swing.JLabel jLabelListaDeClientes;
    private javax.swing.JLabel jLabelListaDeGeneros;
    private javax.swing.JLabel jLabelListasDeReproduccion;
    private javax.swing.JLabel jLabelNombreDeLaLista;
    private javax.swing.JList<String> jListC;
    private javax.swing.JList<String> jListListaDeReproduccion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneGenero;
    private javax.swing.JTable jTableTemas;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldGenero;
    private javax.swing.JTextField jTextFieldNombreDeLaLista;
    private javax.swing.JTree jTreeGeneros;
    // End of variables declaration//GEN-END:variables
}
