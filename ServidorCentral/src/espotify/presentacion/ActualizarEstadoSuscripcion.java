/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTSuscripcion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ActualizarEstadoSuscripcion extends javax.swing.JInternalFrame {

    private IControlador controlador;

    public ActualizarEstadoSuscripcion() {
        initComponents();

        Fabrica f = Fabrica.getInstance();
        controlador = f.getControlador();

        InicializarJList();

        /* Cargo el jList con los nicknames del Cliente */
        DefaultListModel<String> listaSuscripcionCliente = new DefaultListModel<>();
        ArrayList<DTSuscripcion> dtSuscripciones = new ArrayList<>(controlador.getDTSuscripciones());
        String infoSuscripcion = null;

        for (DTSuscripcion dtS : dtSuscripciones) {
            infoSuscripcion = "id= " + dtS.getIdSuscripcion()
                    + ", cliente= " + dtS.getMiCliente().getNickname();
            listaSuscripcionCliente.addElement(infoSuscripcion);
        }
        jListSuscripciones.setModel(listaSuscripcionCliente);

        jComboBoxOpcionesEstados.removeAllItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneSuscripciones = new javax.swing.JScrollPane();
        jListSuscripciones = new javax.swing.JList<>();
        jButtonAceptar = new javax.swing.JButton();
        jLabelTituloInfo = new javax.swing.JLabel();
        jLabelNicknameCliente = new javax.swing.JLabel();
        jLabelTipoSuscripcion = new javax.swing.JLabel();
        jLabelEstadoSuscripcion = new javax.swing.JLabel();
        jLabelFechaActivacion = new javax.swing.JLabel();
        jLabelIdSuscripcion = new javax.swing.JLabel();
        jLabelInfoIdSuscripcion = new javax.swing.JLabel();
        jLabelInfoNicknameCliente = new javax.swing.JLabel();
        jLabelInfoTipoSuscripcion = new javax.swing.JLabel();
        jLabelInfoEstadoSuscripcion = new javax.swing.JLabel();
        jLabelInfoFechaActivacion = new javax.swing.JLabel();
        jComboBoxOpcionesEstados = new javax.swing.JComboBox<>();
        jButtonConfirmar = new javax.swing.JButton();
        jLabelSeleccioneOpcion = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Actualizar Estado de Suscripción");

        jListSuscripciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneSuscripciones.setViewportView(jListSuscripciones);

        jButtonAceptar.setText("Acceder");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jLabelTituloInfo.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabelTituloInfo.setText("Información Detallada");

        jLabelNicknameCliente.setText("Cliente Dueño:");

        jLabelTipoSuscripcion.setText("Tipo:");

        jLabelEstadoSuscripcion.setText("Estado:");

        jLabelFechaActivacion.setText("Fecha de Activación:");

        jLabelIdSuscripcion.setText("Id:");

        jLabelInfoIdSuscripcion.setText("...");

        jLabelInfoNicknameCliente.setText("...");

        jLabelInfoTipoSuscripcion.setText("...");

        jLabelInfoEstadoSuscripcion.setText("...");

        jLabelInfoFechaActivacion.setText("...");

        jComboBoxOpcionesEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jLabelSeleccioneOpcion.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        jLabelSeleccioneOpcion.setText("Seleccione opcion");

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAceptar)
                    .addComponent(jScrollPaneSuscripciones, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxOpcionesEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelIdSuscripcion)
                                            .addComponent(jLabelTipoSuscripcion)
                                            .addComponent(jLabelEstadoSuscripcion)
                                            .addComponent(jLabelNicknameCliente)
                                            .addComponent(jLabelFechaActivacion))
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInfoFechaActivacion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelInfoNicknameCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelInfoTipoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelInfoEstadoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelInfoIdSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabelSeleccioneOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabelTituloInfo)))
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConfirmar)
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneSuscripciones, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAceptar)
                            .addComponent(jButtonConfirmar)
                            .addComponent(jButtonCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTituloInfo)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelIdSuscripcion)
                            .addComponent(jLabelInfoIdSuscripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTipoSuscripcion)
                            .addComponent(jLabelInfoTipoSuscripcion))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEstadoSuscripcion)
                            .addComponent(jLabelInfoEstadoSuscripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNicknameCliente)
                            .addComponent(jLabelInfoNicknameCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFechaActivacion)
                            .addComponent(jLabelInfoFechaActivacion))
                        .addGap(29, 29, 29)
                        .addComponent(jLabelSeleccioneOpcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxOpcionesEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed

        // Obtengo el String seleccionado
        String stringSeleccionado = jListSuscripciones.getSelectedValue();

        // Encuentro la posicion del '=' y ','
        int igualIndex = stringSeleccionado.indexOf('=');
        int comaIndex = stringSeleccionado.indexOf(',');

        // Extraigo el substring que esta entre '=' y ','
        String idString = stringSeleccionado.substring(igualIndex + 1, comaIndex).trim();

        // Convierto el substring a Long
        Long idSuscripcion = Long.valueOf(idString);

        Fabrica f = Fabrica.getInstance();
        controlador = f.getControlador();

        DTSuscripcion dtSuscripcion = controlador.getDTSuscripcion(idSuscripcion);

        // Verificar is hay una seleccion
        if (stringSeleccionado != null) {

            jLabelInfoIdSuscripcion.setText(idString);
            jLabelInfoNicknameCliente.setText(dtSuscripcion.getMiCliente().getNickname());
            jLabelInfoTipoSuscripcion.setText(dtSuscripcion.getTipoSuscripcion());
            jLabelInfoEstadoSuscripcion.setText(dtSuscripcion.getEstadoSuscripcion());

            // Convertir Date a formato String
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fechaActivacion = dateFormat.format(dtSuscripcion.getFechaSuscripcion());
            jLabelInfoFechaActivacion.setText(fechaActivacion);

            // Cargo ComboBox con Opciones de Estados
            jComboBoxOpcionesEstados.removeAllItems();
            if (dtSuscripcion.getEstadoSuscripcion().equals("Pendiente")) {
                jLabelSeleccioneOpcion.setText("Seleccione opcion");
                jComboBoxOpcionesEstados.setVisible(true);
                jComboBoxOpcionesEstados.addItem("Vigente");
                jComboBoxOpcionesEstados.addItem("Cancelada");
            } else {
                jLabelSeleccioneOpcion.setText("Estado no modificable!");
                jComboBoxOpcionesEstados.setVisible(false);
            }
        }

    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed

        // Obtengo el String seleccionado
        String stringSeleccionado = jListSuscripciones.getSelectedValue();

        // Verifica si hay una seleccion en el JList
        if (stringSeleccionado != null) {

            // Encuentro la posicion del '=' y ','
            int igualIndex = stringSeleccionado.indexOf('=');
            int comaIndex = stringSeleccionado.indexOf(',');

            // Extraigo el substring que esta entre '=' y ','
            String idString = stringSeleccionado.substring(igualIndex + 1, comaIndex).trim();

            // Convierto el substring a Long
            Long idSuscripcion = Long.valueOf(idString);
            DTSuscripcion dtS = controlador.getDTSuscripcion(idSuscripcion);

            // Estado de suscripcion es Pendiente
            if (dtS.getEstadoSuscripcion().equals("Pendiente")) {
                
                // Verifica si hay una seleccion en el JComboBox
                if (jComboBoxOpcionesEstados.getSelectedIndex() != -1) {

                    // Obtengo el estado de la suscripcion
                    String estadoString = jComboBoxOpcionesEstados.getSelectedItem().toString();
                    EstadoSuscripcion estadoSuscripcion = EstadoSuscripcion.valueOf(estadoString);

                    // Obtengo la fecha de modificacion de la suscripcion
                    Date fechaSuscripcion = new Date();

                    try {
                        this.controlador.ActualizarEstadoSuscripcion(idSuscripcion, estadoSuscripcion, fechaSuscripcion);
                        JOptionPane.showMessageDialog(
                                null,
                                "Estado modificado exitosamente.",
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
                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una opcion",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }else {
                JOptionPane.showMessageDialog(
                            null,
                            "Estado no modificable.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione una suscripcion",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void InicializarJList() {
        DefaultListModel<String> emptyModel = new DefaultListModel<>();
        jListSuscripciones.setModel(emptyModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxOpcionesEstados;
    private javax.swing.JLabel jLabelEstadoSuscripcion;
    private javax.swing.JLabel jLabelFechaActivacion;
    private javax.swing.JLabel jLabelIdSuscripcion;
    private javax.swing.JLabel jLabelInfoEstadoSuscripcion;
    private javax.swing.JLabel jLabelInfoFechaActivacion;
    private javax.swing.JLabel jLabelInfoIdSuscripcion;
    private javax.swing.JLabel jLabelInfoNicknameCliente;
    private javax.swing.JLabel jLabelInfoTipoSuscripcion;
    private javax.swing.JLabel jLabelNicknameCliente;
    private javax.swing.JLabel jLabelSeleccioneOpcion;
    private javax.swing.JLabel jLabelTipoSuscripcion;
    private javax.swing.JLabel jLabelTituloInfo;
    private javax.swing.JList<String> jListSuscripciones;
    private javax.swing.JScrollPane jScrollPaneSuscripciones;
    // End of variables declaration//GEN-END:variables
}
