/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author tecnologo
 */
public class GuardarFavorito extends javax.swing.JInternalFrame {

    private IControlador controlador;
    private Map<Long, String> mapaAlbums;
    private Map<Long, DTTemaSimple> mapaDTTemas;
    
    public GuardarFavorito() {
        
        Fabrica fb = Fabrica.getInstance();
        this.controlador = fb.getControlador();
        
        initComponents();
         //De predeterminado se inicializa el Panel de Temas
        cargarTemas();
    }
    
    private void cargarTemas() {
        this.mapaDTTemas = new HashMap<>();
        this.mapaDTTemas = controlador.getDTTemasDisponibles();
        Map<Long, DTTemaSimple> mapDTTemasOrdenado = new TreeMap<>(this.mapaDTTemas);
        
        DefaultListModel<String> listaDatosTemas = new DefaultListModel<>();
        for (DTTemaSimple dataTema : mapDTTemasOrdenado.values()) {
            listaDatosTemas.addElement(dataTema.getDatosTemaToString());
        }
        jListTemas.setModel(listaDatosTemas);
    }
    
    private void cargarListas() {
        DefaultListModel<String> listaNombresListas = new DefaultListModel<>();
        ArrayList<String> nombresListas = new ArrayList<>(controlador.getListasReproduccionDisponibles());
        for (String nombreListaR : nombresListas) {
            listaNombresListas.addElement(nombreListaR);
        }
        jListListas.setModel(listaNombresListas);
    }
    
    private void cargarAlbums() {
        //creo el model para cargar en la JList
        DefaultListModel<String> listaDataAlbumStringsModel = new DefaultListModel<>();
        //obtengo los data album
        ArrayList<DTAlbum> dataAlbumes = controlador.getDTAlbumesDisponibles();
        //mapeo el id del album con el string que va en la JList
        mapaAlbums = new HashMap<Long,String>(dataAlbumes.size());

        for (DTAlbum dataAlb : dataAlbumes) {
            listaDataAlbumStringsModel.addElement(dataAlb.toStringSimple());
            mapaAlbums.put(dataAlb.getIdAlbum(), dataAlb.toStringSimple());
        }
        jListAlbumes.setModel(listaDataAlbumStringsModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNicknameClienteInfo = new javax.swing.JTextField();
        jLabelNicknameCliente = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelTema = new javax.swing.JPanel();
        jScrollPaneTemas = new javax.swing.JScrollPane();
        jListTemas = new javax.swing.JList<>();
        jButtonGuardarTemaFavorito = new javax.swing.JButton();
        jPanelLista = new javax.swing.JPanel();
        jScrollPaneLista = new javax.swing.JScrollPane();
        jListListas = new javax.swing.JList<>();
        jButtonGuardarListaFavorito = new javax.swing.JButton();
        jPanelAlbum = new javax.swing.JPanel();
        jScrollPaneAlbum = new javax.swing.JScrollPane();
        jListAlbumes = new javax.swing.JList<>();
        jButtonGuardarAlbumFavorito = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Guardar Tema/Lista/Album");
        setMinimumSize(new java.awt.Dimension(520, 410));
        setPreferredSize(new java.awt.Dimension(620, 410));

        jTextFieldNicknameClienteInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNicknameClienteInfoActionPerformed(evt);
            }
        });

        jLabelNicknameCliente.setText("Nickname cliente:");

        jTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jScrollPaneTemas.setViewportView(jListTemas);

        jButtonGuardarTemaFavorito.setText("Guardar en favoritos");
        jButtonGuardarTemaFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarTemaFavoritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTemaLayout = new javax.swing.GroupLayout(jPanelTema);
        jPanelTema.setLayout(jPanelTemaLayout);
        jPanelTemaLayout.setHorizontalGroup(
            jPanelTemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTemaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTemas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTemaLayout.createSequentialGroup()
                        .addGap(0, 218, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarTemaFavorito)
                        .addGap(212, 212, 212)))
                .addContainerGap())
        );
        jPanelTemaLayout.setVerticalGroup(
            jPanelTemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardarTemaFavorito)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Temas", jPanelTema);

        jScrollPaneLista.setViewportView(jListListas);

        jButtonGuardarListaFavorito.setText("Guardar en favoritos");
        jButtonGuardarListaFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarListaFavoritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelListaLayout = new javax.swing.GroupLayout(jPanelLista);
        jPanelLista.setLayout(jPanelListaLayout);
        jPanelListaLayout.setHorizontalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonGuardarListaFavorito)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelListaLayout.setVerticalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardarListaFavorito)
                    .addComponent(jScrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 38, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Listas", jPanelLista);

        jScrollPaneAlbum.setViewportView(jListAlbumes);

        jButtonGuardarAlbumFavorito.setText("Guardar en favoritos");
        jButtonGuardarAlbumFavorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarAlbumFavoritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlbumLayout = new javax.swing.GroupLayout(jPanelAlbum);
        jPanelAlbum.setLayout(jPanelAlbumLayout);
        jPanelAlbumLayout.setHorizontalGroup(
            jPanelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlbumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonGuardarAlbumFavorito)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelAlbumLayout.setVerticalGroup(
            jPanelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlbumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardarAlbumFavorito)
                    .addComponent(jScrollPaneAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Albumes", jPanelAlbum);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelNicknameCliente)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldNicknameClienteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNicknameCliente)
                    .addComponent(jTextFieldNicknameClienteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNicknameClienteInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNicknameClienteInfoActionPerformed
        //String nicknameCliente = evt.get
    }//GEN-LAST:event_jTextFieldNicknameClienteInfoActionPerformed

    private void jButtonGuardarAlbumFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarAlbumFavoritoActionPerformed
        
        // Obtengo el Nickname del Cliente
        String nicknameCliente = jTextFieldNicknameClienteInfo.getText();
        
        // Verificar si nicknameCliente no es vacío
        if (!(nicknameCliente.equals(""))) {
            // Verificar si nicknameCliente existe en el Sistema
            if (this.validarNicknameCliente(nicknameCliente)) {
              
                // Obtengo el Nombre del Album
                String datosAlbum = jListAlbumes.getSelectedValue();
                Long idAlbumSeleccionado = null;
                
                for (Entry<Long, String> entry : this.mapaAlbums.entrySet()) {
                    if (entry.getValue().equals(datosAlbum)) {
                        idAlbumSeleccionado = entry.getKey();
                        break;
                    }
                }

                // Verifica si se seleccionó Album
                if (datosAlbum != null && idAlbumSeleccionado != null) {
                    
                    try {
                        this.controlador.GuardarAlbumFavorito(nicknameCliente, idAlbumSeleccionado);
                        JOptionPane.showMessageDialog(
                        null, 
                        "Album agregado exitosamente.", 
                        "Operacion exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                        null, 
                        ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                } else { // Album no se seleccionó

                    JOptionPane.showMessageDialog(this, "Seleccione un album.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else { // nicknameCliente no existe en el Sistema

                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else { // nicknameCliete es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonGuardarAlbumFavoritoActionPerformed

    private void jButtonGuardarListaFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarListaFavoritoActionPerformed
        
        // Obtengo el Nickname del Cliente
        String nicknameCliente = jTextFieldNicknameClienteInfo.getText();
        
        // Verificar si nicknameCliente no es vacío
        if (!(nicknameCliente.equals(""))) {
            // Verificar si nicknameCliente existe en el Sistema
            if (this.validarNicknameCliente(nicknameCliente)) {
        
                // Obtengo el Nombre de la ListaReproduccion
                String nombreListaReproduccion = jListListas.getSelectedValue();

                // Verifica si se seleccionó Lista
                if (nombreListaReproduccion != null) {
                    
                    try {
                        this.controlador.GuardarListaFavorito(nicknameCliente, nombreListaReproduccion);
                        JOptionPane.showMessageDialog(
                        null, 
                        "Lista agregada exitosamente.", 
                        "Operacion exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                        null, 
                        ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                    
                } else { // Lista no se seleccionó
                    
                    JOptionPane.showMessageDialog(this, "Seleccione una lista de reproduccion.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else { // nicknameCliente no existe en el Sistema
                
                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else { // nicknameCliente es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarListaFavoritoActionPerformed

    private void jButtonGuardarTemaFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarTemaFavoritoActionPerformed
        
        // Obtengo el Nickname del Cliente
        String nicknameCliente = jTextFieldNicknameClienteInfo.getText();
        
        // Verificar si nicknameCliente no es vacío
        if (!(nicknameCliente.equals(""))) {
            // Verificar si nicknameCliente existe en el Sistema
            if (this.validarNicknameCliente(nicknameCliente)) {
                
                Fabrica fb = Fabrica.getInstance();
                controlador = fb.getControlador();
        
                // Obtengo el tema seleccionado
                String datosTema = jListTemas.getSelectedValue();
                Long idTemaSeleccionado = null;
                
                for (Entry<Long, DTTemaSimple> entry : this.mapaDTTemas.entrySet()) {
                    if (entry.getValue().getDatosTemaToString().equals(datosTema)) {
                        idTemaSeleccionado = entry.getKey();
                        break;
                    }
                }
                
                if (datosTema != null && idTemaSeleccionado != null) {
                    try {
                        controlador.GuardarTemaFavorito(nicknameCliente, idTemaSeleccionado);
                        JOptionPane.showMessageDialog(
                        null, 
                        "Tema agregado exitosamente.", 
                        "Operacion exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                        null, 
                        ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un Tema.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else { // nicknameCliente no existe en el Sistema
                
                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else { // nicknameCliente es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarTemaFavoritoActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        
        int indiceSeleccionado = jTabbedPane.getSelectedIndex();
        String tituloPanelSeleccionado = jTabbedPane.getTitleAt(indiceSeleccionado);
        
        switch(tituloPanelSeleccionado) {
            case "Temas" -> {
                this.cargarTemas();
                break;
            }
            case "Listas" -> {
                this.cargarListas();
                break;
            }
            case "Albumes" -> {
                this.cargarAlbums();
                break;
            }
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private boolean validarNicknameCliente(String nickname) {
        
        return this.controlador.ExisteCliente(nickname);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardarAlbumFavorito;
    private javax.swing.JButton jButtonGuardarListaFavorito;
    private javax.swing.JButton jButtonGuardarTemaFavorito;
    private javax.swing.JLabel jLabelNicknameCliente;
    private javax.swing.JList<String> jListAlbumes;
    private javax.swing.JList<String> jListListas;
    private javax.swing.JList<String> jListTemas;
    private javax.swing.JPanel jPanelAlbum;
    private javax.swing.JPanel jPanelLista;
    private javax.swing.JPanel jPanelTema;
    private javax.swing.JScrollPane jScrollPaneAlbum;
    private javax.swing.JScrollPane jScrollPaneLista;
    private javax.swing.JScrollPane jScrollPaneTemas;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTextField jTextFieldNicknameClienteInfo;
    // End of variables declaration//GEN-END:variables
}
