/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTAlbum;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
    
    public GuardarFavorito() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        this.controlador = fb.getControlador();
              
        /*
        // De predeterminado se inicializa el Panel de Temas
        DefaultListModel<String> listaNombresTemas = new DefaultListModel<>();
        Map<Long, String> mapTemas = controlador.getTemasDisponibles();
        for (String nombreTema : mapTemas.values()) {
            listaNombresTemas.addElement(nombreTema);
        }
        jListTemas.setModel(listaNombresTemas);
        */
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = jTabbedPane.getSelectedIndex();
                String selectedTabTitle = jTabbedPane.getTitleAt(selectedIndex);
                
                if (selectedTabTitle.equals("Listas")) {
                    
                    DefaultListModel<String> listaNombresListas = new DefaultListModel<>();
                    ArrayList<String> nombresListas = new ArrayList<>(controlador.getListasReproduccionDisponibles());
                    for (String nombreListaR : nombresListas) {
                        listaNombresListas.addElement(nombreListaR);
                    }
                    jListListas.setModel(listaNombresListas);
                } else {
                    
                    if (selectedTabTitle.equals("Albumes")) {
                        
                        //
                        //DefaultListModel<String> listaNombresAlbumes = new DefaultListModel<>();
                        //ArrayList<String> nombresAlbumes = new ArrayList<>(controlador.getAlbumesDisponibles());
                        //for (String nombreAlbum : nombresAlbumes) {
                        //    listaNombresAlbumes.addElement(nombreAlbum);
                        //}
                        //jListAlbumes.setModel(listaNombresAlbumes);

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

                    } else {
                        
                        DefaultListModel<String> listaNombresTemas = new DefaultListModel<>();
                        Map<Long, String> mapTemas = controlador.getTemasDisponibles();
                        for (String nombreTema : mapTemas.values()) {
                            listaNombresTemas.addElement(nombreTema);
                        }
                        jListTemas.setModel(listaNombresTemas);
                    }
                }
                
                switch (selectedTabTitle) {
                    case "Listas" -> {

                        DefaultListModel<String> listaNombresListas = new DefaultListModel<>();
                        ArrayList<String> nombresListas = new ArrayList<>(controlador.getListasReproduccionDisponibles());
                        for (String nombreListaR : nombresListas) {
                            listaNombresListas.addElement(nombreListaR);
                        }
                        jListListas.setModel(listaNombresListas);

                    }
                    case "Albumes" -> {

                        DefaultListModel<String> listaNombresAlbumes = new DefaultListModel<>();
                        ArrayList<String> nombresAlbumes = new ArrayList<>(controlador.getAlbumesDisponibles());
                        for (String nombreAlbum : nombresAlbumes) {
                            listaNombresAlbumes.addElement(nombreAlbum);
                        }
                        jListAlbumes.setModel(listaNombresAlbumes);

                    }
                    default -> {
                        
                        DefaultListModel<String> listaNombresTemas = new DefaultListModel<>();
                        Map<Long, String> mapTemas = controlador.getTemasDisponibles();
                        for (String nombreTema : mapTemas.values()) {
                            listaNombresTemas.addElement(nombreTema);
                        }
                        jListTemas.setModel(listaNombresTemas);
                        
                    }
                }
            }
            
        });
        
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
        setPreferredSize(new java.awt.Dimension(406, 410));

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

        jListTemas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
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
                .addComponent(jScrollPaneTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonGuardarTemaFavorito)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanelTemaLayout.setVerticalGroup(
            jPanelTemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTemaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardarTemaFavorito)
                    .addComponent(jScrollPaneTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Temas", jPanelTema);

        jListListas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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
                .addComponent(jScrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonGuardarListaFavorito)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanelListaLayout.setVerticalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGuardarListaFavorito)
                    .addComponent(jScrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Listas", jPanelLista);

        jListAlbumes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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
                .addComponent(jScrollPaneAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonGuardarAlbumFavorito)
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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
                        System.out.println(datosAlbum);
                        System.out.println("id seleccionado: " + idAlbumSeleccionado);
                        break;
                    }
                }

                // Verifica si se seleccionó Album
                if (datosAlbum != null && idAlbumSeleccionado != null) {
                    Fabrica fb = Fabrica.getInstance();
                    controlador = fb.getControlador();

                    controlador.GuardarAlbumFavorito(nicknameCliente, idAlbumSeleccionado);
                } else { // Album no se seleccionó

                    JOptionPane.showMessageDialog(this, "Seleccione un album.");
                }
            } else { // nicknameCliente no existe en el Sistema

                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.");
            }
        } else { // nicknameCliete es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.");
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
                    Fabrica fb = Fabrica.getInstance();
                    controlador = fb.getControlador();

                    controlador.GuardarListaFavorito(nicknameCliente, nombreListaReproduccion);
                } else { // Lista no se seleccionó
                    
                    JOptionPane.showMessageDialog(this, "Seleccione una lista de reproduccion.");
                }
            } else { // nicknameCliente no existe en el Sistema
                
                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.");
            }
        } else { // nicknameCliente es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.");
        }
    }//GEN-LAST:event_jButtonGuardarListaFavoritoActionPerformed

    private void jButtonGuardarTemaFavoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarTemaFavoritoActionPerformed
        
        // Obtengo el Nickname del Cliente
        String nicknameCliente = jTextFieldNicknameClienteInfo.getText();
        
        // Verificar si nicknameCliente no es vacío
        if (!(nicknameCliente.equals(""))) {
            // Verificar si nicknameCliente existe en el Sistema
            if (this.validarNicknameCliente(nicknameCliente)) {
        
                // Obtengo el idTema del Tema seleccionado
                Map<Long, String> mapTemas = controlador.getTemasDisponibles();

                // mapTemas Invertido
                Map<String, Long> mapTemasInvertido = new HashMap<>();
                for (Map.Entry<Long, String> entry : mapTemas.entrySet()) {
                    mapTemasInvertido.put(entry.getValue(), entry.getKey());
                }

                Fabrica fb = Fabrica.getInstance();
                controlador = fb.getControlador();

                jListTemas.addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        // Obtener el Nombre del Tema seleccionado
                        String nombreTemaSeleccionado = jListTemas.getSelectedValue();

                        if (nombreTemaSeleccionado != null) {
                            // Buscar la clave correspondiente en el Map invertido
                            Long idTema = mapTemasInvertido.get(nombreTemaSeleccionado);
                            controlador.GuardarTemaFavorito(nicknameCliente, idTema);
                        } else { // Tema no se selecccionó
                            
                            JOptionPane.showMessageDialog(this, "Seleccione un Tema.");
                        }
                    }
                });
            } else { // nicknameCliente no existe en el Sistema
                
                JOptionPane.showMessageDialog(this, "El cliente " + nicknameCliente + " no existe.");
            }
        } else { // nicknameCliente es vacío
            
            JOptionPane.showMessageDialog(this, "Ingrese nickname del cliente.");
        }
    }//GEN-LAST:event_jButtonGuardarTemaFavoritoActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        int indiceSeleccionado = jTabbedPane.getSelectedIndex();
        String tituloPanelSeleccionado = jTabbedPane.getTitleAt(indiceSeleccionado);
        
        switch(tituloPanelSeleccionado) {
            case "Temas" -> {
                
                DefaultListModel<String> listaNombresTemas = new DefaultListModel<>();
                Map<Long, String> mapTemas = controlador.getTemasDisponibles();
                for (String nombreTema : mapTemas.values()) {
                    listaNombresTemas.addElement(nombreTema);
                }
                jListTemas.setModel(listaNombresTemas);
                break;
            }
            case "Listas" -> {
                
                DefaultListModel<String> listaNombresListas = new DefaultListModel<>();
                ArrayList<String> nombresListas = new ArrayList<>(controlador.getListasReproduccionDisponibles());
                for (String nombreListaR : nombresListas) {
                    listaNombresListas.addElement(nombreListaR);
                }
                jListListas.setModel(listaNombresListas);
                break;
            }
            case "Albumes" -> {
                
                DefaultListModel<String> listaNombresAlbumes = new DefaultListModel<>();
                ArrayList<String> nombresAlbumes = new ArrayList<>(controlador.getAlbumesDisponibles());
                for (String nombreAlbum : nombresAlbumes) {
                    listaNombresAlbumes.addElement(nombreAlbum);
                }
                jListAlbumes.setModel(listaNombresAlbumes);
                break;
            }
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private boolean validarNicknameCliente(String nickname) {
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        return controlador.ExisteCliente(nickname);
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
