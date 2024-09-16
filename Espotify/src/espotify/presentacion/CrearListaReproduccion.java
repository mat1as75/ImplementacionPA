package espotify.presentacion;

import espotify.DataTypes.DTGenero;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author ms
 */

public class CrearListaReproduccion extends javax.swing.JInternalFrame {
    
    private IControlador controlador;
    private String imgLista;

    public CrearListaReproduccion() {
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
        
        jLabelGenero.setVisible(true);
        jTreeGeneros.setVisible(true);
        jScrollPaneGeneros.setVisible(true);
        jLabelClientePropietario.setVisible(false);
        jListC.setVisible(false);
        jScrollPaneClientes.setVisible(false);
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

        // Segunda pasada: Establecer relaciones padre-hijo basadas en el mapa
        for (DTGenero genero : generos) {
            if (genero.getMiPadre() != null) {
                DefaultMutableTreeNode nodoGenero = nodosGeneros.get(genero.getNombreGenero());
                DefaultMutableTreeNode nodoPadre = nodosGeneros.get(genero.getMiPadre().getNombreGenero());

                // Comprobar que ambos nodos existan antes de establecer la relacion
                if (nodoPadre != null && nodoGenero != null) {
                    nodoPadre.add(nodoGenero);
                }
            }
        }

        // Establecer el modelo del arbol
        jTreeGeneros.setModel(new DefaultTreeModel(raiz));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelClientePropietario = new javax.swing.JLabel();
        jComboBoxTipodeLista = new javax.swing.JComboBox<>();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelGenero = new javax.swing.JLabel();
        openButtonImagen = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        jButtonCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneGeneros = new javax.swing.JScrollPane();
        jTreeGeneros = new javax.swing.JTree();
        jScrollPaneClientes = new javax.swing.JScrollPane();
        jListC = new javax.swing.JList<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Crear Lista de Reproduccion");

        jLabelClientePropietario.setText("Seleccione Cliente Propietario:");

        jComboBoxTipodeLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por defecto", "Particular" }));
        jComboBoxTipodeLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipodeListaActionPerformed(evt);
            }
        });

        jLabelNombre.setText("Nombre:");

        jLabelGenero.setText("Seleccione el Género:");

        openButtonImagen.setText("Agregar IMG");
        openButtonImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonImagenActionPerformed(evt);
            }
        });

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonCrear.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonCrear.setText("Crear");
        jButtonCrear.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de Lista:");

        jScrollPaneGeneros.setViewportView(jTreeGeneros);

        jListC.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneClientes.setViewportView(jListC);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(openButtonImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGenero)
                            .addComponent(jLabelClientePropietario)
                            .addComponent(jButtonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipodeLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jComboBoxTipodeLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelGenero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelClientePropietario)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCrear))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPaneGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(openButtonImagen)))
                .addContainerGap(386, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jComboBoxTipodeListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipodeListaActionPerformed
        
        String opcion=(String)jComboBoxTipodeLista.getSelectedItem();
        if("Por defecto".equals(opcion)){
            jLabelGenero.setVisible(true);
            jTreeGeneros.setVisible(true);
            jScrollPaneGeneros.setVisible(true);
            jLabelClientePropietario.setVisible(false);
            jListC.setVisible(false);
            jScrollPaneClientes.setVisible(false);
        }
        else{
            jLabelGenero.setVisible(false);
            jTreeGeneros.setVisible(false);
            jScrollPaneGeneros.setVisible(false);
            jLabelClientePropietario.setVisible(true);
            jListC.setVisible(true);
            jScrollPaneClientes.setVisible(true);
        }           
    }//GEN-LAST:event_jComboBoxTipodeListaActionPerformed

    private void openButtonImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonImagenActionPerformed
        
        JFileChooser buscar = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Seleccionar imagen", "jpg", "png");
        buscar.setFileFilter(extension);

        if (buscar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = buscar.getSelectedFile();
            String ruta = selectedFile.getAbsolutePath();
            // Configura la carpeta de destino
            File destinoCarpeta = new File("./src/imagenesPerfil");
            // Crea la carpeta si no existe
            if (!destinoCarpeta.exists()) {
                destinoCarpeta.mkdirs(); 
            }
            // Define el nuevo archivo en la carpeta de destino
            File destinoArchivo = new File(destinoCarpeta, selectedFile.getName());
            imgLista = destinoArchivo.getPath();

            try {
                // Copia el archivo a la carpeta de destino
                Files.copy(selectedFile.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Cargar la imagen para mostrarla en el JLabel
                Toolkit tool = Toolkit.getDefaultToolkit();
                Image imagen = tool.createImage(ruta);
                imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_AREA_AVERAGING)));

            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
    }//GEN-LAST:event_openButtonImagenActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        
        Fabrica fb = Fabrica.getInstance();
        IControlador i = fb.getControlador();

        String op = (String) jComboBoxTipodeLista.getSelectedItem();
        String nombreLista = jTextFieldNombre.getText();

        // Comprobar si el nombre de la lista está vacio
        if (nombreLista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un nombre para la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Comprobar si el nombre de la lista ya existe
        if (i.existeNombreLista(nombreLista)) {
            JOptionPane.showMessageDialog(null, "El nombre de la lista ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if ("Por defecto".equalsIgnoreCase(op)) {
                DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) jTreeGeneros.getLastSelectedPathComponent();
                if (nodoSeleccionado == null || "Generos".equalsIgnoreCase(nodoSeleccionado.toString())) {
                    JOptionPane.showMessageDialog(null, "Seleccione un género de la lista", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String gen = nodoSeleccionado.toString();
                i.CrearListaPorDefecto(nombreLista, imgLista, gen);
            } else if ("Particular".equalsIgnoreCase(op)) {
                String nickcliente = jListC.getSelectedValue();
                
                if (nickcliente == null || nickcliente.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean soyPrivada = true; // Crear privada
                i.CrearListaParticular(nombreLista, imgLista, nickcliente, soyPrivada);
            }
            JOptionPane.showMessageDialog(null, "Lista Creada con Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Limpiar el campo del nombre al crear
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jButtonCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JComboBox<String> jComboBoxTipodeLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelClientePropietario;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JList<String> jListC;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneClientes;
    private javax.swing.JScrollPane jScrollPaneGeneros;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTree jTreeGeneros;
    private javax.swing.JButton openButtonImagen;
    // End of variables declaration//GEN-END:variables
}
