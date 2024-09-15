package espotify.presentacion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.ListaReproduccion;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
public class PublicarLista extends javax.swing.JInternalFrame {
    
    IControlador controlador;
    
    public PublicarLista() {
        initComponents();
        Fabrica f = Fabrica.getInstance();
        controlador = f.getControlador();
  
        // Se añaden los nicknames de los Clientes
        DefaultListModel<String> listaNicknamesClientes = new DefaultListModel<>();
        ArrayList<String> nicknamesClientes = new ArrayList<>(controlador.getNicknamesClientesListasPrivadas());
        for (String nickname : nicknamesClientes) {
            listaNicknamesClientes.addElement(nickname);
        }
        jListClientes.setModel(listaNicknamesClientes);
        
        // Inicializo el jList vacío
        DefaultListModel<String> listaVacia = new DefaultListModel<>();
        jListListasPrivadas.setModel(listaVacia);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPublicarLista = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListClientes = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListListasPrivadas = new javax.swing.JList<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Publicar Lista");

        jLabel1.setText("Nickname de Clientes");

        jLabel2.setText("Listas Privadas");

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jListClientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListClientesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListClientes);

        jListListasPrivadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListListasPrivadas);

        javax.swing.GroupLayout jPanelPublicarListaLayout = new javax.swing.GroupLayout(jPanelPublicarLista);
        jPanelPublicarLista.setLayout(jPanelPublicarListaLayout);
        jPanelPublicarListaLayout.setHorizontalGroup(
            jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEnviar)
                    .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                        .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(129, 129, 129)))
                .addGap(23, 23, 23))
        );
        jPanelPublicarListaLayout.setVerticalGroup(
            jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButtonEnviar)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPublicarLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPublicarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
       
        Fabrica f = Fabrica.getInstance();
        controlador = f.getControlador();
        
        String nicknameCliente = jListClientes.getSelectedValue();
        String nombreLista = jListListasPrivadas.getSelectedValue();
        
        if (nicknameCliente != null) {
            if (nombreLista != null) {
                try {
                    controlador.setPrivadafalse(nicknameCliente, nombreLista);
                    JOptionPane.showMessageDialog(
                        null, 
                        "Lista Publicada exitosamente.", 
                        "Operacion exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Lista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        ActualizoListaListasPrivadas(nombreLista);
        ActualizoListaClientes(nicknameCliente);
        
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jListClientesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListClientesValueChanged
  
        // Nickname del Cliente Seleccionado
        String nicknameSeleccionado = jListClientes.getSelectedValue();
        
        if (nicknameSeleccionado != null) {
            // Se añaden los nombres de las ListasPrivadas del clienteSeleccionado
            DefaultListModel<String> listaListasPrivadas = new DefaultListModel<>();
            ArrayList<String> listasPrivadas = new ArrayList<>(controlador.listasCreadasEstadoPrivadoTrue(nicknameSeleccionado));
            for (String nombreLista : listasPrivadas) {
                listaListasPrivadas.addElement(nombreLista);
            }
            jListListasPrivadas.setModel(listaListasPrivadas);
        }
    }//GEN-LAST:event_jListClientesValueChanged

    private void ActualizoListaClientes(String nicknameSeleccionado) {
        
        // Cantidad de ListasPrivadas del Clientes es vacía
        if (jListListasPrivadas.getModel().getSize() == 0) {
            DefaultListModel<String> nicknamesClientesJList = new DefaultListModel<>();

            // Recorro los elementos del jListClientes
            for (int i = 0; i < jListClientes.getModel().getSize(); i++) {
                // Si NicknameActual != nicknameSeleccionado
                if (!(jListClientes.getModel().getElementAt(i).equals(nicknameSeleccionado))) {
                    nicknamesClientesJList.addElement(jListClientes.getModel().getElementAt(i));
                }
            }
            jListClientes.setModel(nicknamesClientesJList);
        }
    }
    
    private void ActualizoListaListasPrivadas(String nombreLista) {
        DefaultListModel<String> nombresListasJList = new DefaultListModel<>();

        // Recorro los elementos del jListListasPrivadas
        for (int i = 0; i < jListListasPrivadas.getModel().getSize(); i++) {
            // Si NombreListaActual != nombreLista
            if (!(jListListasPrivadas.getModel().getElementAt(i).equals(nombreLista))) {
                nombresListasJList.addElement(jListListasPrivadas.getModel().getElementAt(i));
            }
        }
        jListListasPrivadas.setModel(nombresListasJList);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListClientes;
    private javax.swing.JList<String> jListListasPrivadas;
    private javax.swing.JPanel jPanelPublicarLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
