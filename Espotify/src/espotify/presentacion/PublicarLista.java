package espotify.presentacion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
public class PublicarLista extends javax.swing.JInternalFrame {
    private IControlador ictrl = null;
    
    public PublicarLista() {
        initComponents();
        Fabrica f = Fabrica.getInstance();
        ictrl = f.getControlador();
        List<String> clientes = ictrl.getNicknamesClientes();
        for (String c : clientes) {
            this.jComboBoxNickName.addItem(c);
        }
        cargarListas();
    }

    private void cargarListas() {
        jComboBoxListaSeraPublica.removeAllItems();
        if (jComboBoxNickName.getSelectedItem() != null) {
            String cliente =(String)jComboBoxNickName.getSelectedItem();
            List<String> privadotrue = ictrl.listasCreadasEstadoPrivadoTrue(cliente);
            for (String p : privadotrue) {
                jComboBoxListaSeraPublica.addItem(p);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPublicarLista = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonEnviar = new javax.swing.JButton();
        jComboBoxListaSeraPublica = new javax.swing.JComboBox<>();
        jComboBoxNickName = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Publicar Lista");

        jLabel1.setText("nickname de usuario");

        jLabel2.setText("lista que se hará pública");

        jButtonEnviar.setText("Enviar");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jComboBoxListaSeraPublica.setPreferredSize(new java.awt.Dimension(150, 24));

        jComboBoxNickName.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxNickName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxNickNameItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelPublicarListaLayout = new javax.swing.GroupLayout(jPanelPublicarLista);
        jPanelPublicarLista.setLayout(jPanelPublicarListaLayout);
        jPanelPublicarListaLayout.setHorizontalGroup(
            jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxListaSeraPublica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxNickName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(267, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPublicarListaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEnviar)
                .addGap(23, 23, 23))
        );
        jPanelPublicarListaLayout.setVerticalGroup(
            jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxNickName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxListaSeraPublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
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
        String cliente =(String)jComboBoxNickName.getSelectedItem();
        String lista=(String)this.jComboBoxListaSeraPublica.getSelectedItem();
        Fabrica f = Fabrica.getInstance();
        IControlador i = f.getControlador();
        i.setPrivadafalse(cliente,lista);
    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jComboBoxNickNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxNickNameItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            cargarListas();
        }
    }//GEN-LAST:event_jComboBoxNickNameItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JComboBox<String> jComboBoxListaSeraPublica;
    private javax.swing.JComboBox<String> jComboBoxNickName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPublicarLista;
    // End of variables declaration//GEN-END:variables
}
