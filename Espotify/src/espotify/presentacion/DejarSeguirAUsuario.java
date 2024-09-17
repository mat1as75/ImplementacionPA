package espotify.presentacion;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ms
 */

public class DejarSeguirAUsuario extends javax.swing.JInternalFrame {
    
    private IControlador controlador;
    
    public DejarSeguirAUsuario() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        // Cargar el ComboBoxC con los Nicknames de los Clientes 
        DefaultComboBoxModel<String> comboBoxModelClientes = new DefaultComboBoxModel<>();
        ArrayList<String> nicknamesClientes = new ArrayList<>(controlador.getNicknamesClientes());
        nicknamesClientes.forEach(comboBoxModelClientes::addElement);
        jComboBoxC.setModel(comboBoxModelClientes);

        // Cargar el ComboBoxCyA con los Nicknames de Clientes y Artistas
        DefaultComboBoxModel<String> comboBoxModelCyA = new DefaultComboBoxModel<>();
        ArrayList<String> nicknamesCya = new ArrayList<>(nicknamesClientes);
        nicknamesCya.addAll(controlador.getNicknamesArtistas());
        nicknamesCya.forEach(comboBoxModelCyA::addElement);
        jComboBoxCyA.setModel(comboBoxModelCyA);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelNickname = new javax.swing.JLabel();
        jLabelNickname1 = new javax.swing.JLabel();
        jButtonConfirmar = new javax.swing.JButton();
        jComboBoxC = new javax.swing.JComboBox<>();
        jComboBoxCyA = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Dejar de Seguir a Usuario");

        jLabelNickname.setText("Nickname del Cliente que desea finalizar el seguimiento:");

        jLabelNickname1.setText("Nickname del Usuario (Cliente/Artista) al que desea dejar de seguir:");

        jButtonConfirmar.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jComboBoxC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxCyA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxCyA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConfirmar)
                    .addComponent(jLabelNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNickname1)
                    .addComponent(jComboBoxC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelNickname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNickname1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCyA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonConfirmar)
                .addContainerGap(297, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
     
        String C = (String) jComboBoxC.getSelectedItem();  // Cliente que desea finalizar el seguimiento
        String U = (String) jComboBoxCyA.getSelectedItem();  // Usuario (Cliente/Artista) al que desea dejar de seguir

        if (!C.equals(U)) {
            // Comprobar si el cliente sigue al usuario
            if (this.controlador.clienteSigueAUsuario(C, U)) {
                try {
                    this.controlador.dejarDeSeguir(C, U);
                    JOptionPane.showMessageDialog(null, "Se ha dejado de seguir al usuario", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (HeadlessException ex) {
                    throw ex;
                    //JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                    
            } else { // No lo sigue
                JOptionPane.showMessageDialog(this, "El cliente no sigue a este usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Un cliente no puede dejar de seguirse a sí mismo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxC;
    private javax.swing.JComboBox<String> jComboBoxCyA;
    private javax.swing.JLabel jLabelNickname;
    private javax.swing.JLabel jLabelNickname1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
