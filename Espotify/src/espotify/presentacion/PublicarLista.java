package espotify.presentacion;
import javax.swing.JScrollPane;
public class PublicarLista extends javax.swing.JInternalFrame {
    public PublicarLista() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPublicarLista = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldnickUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArealistaPublica = new javax.swing.JTextArea();
        jToggleButtonenviar = new javax.swing.JToggleButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Publicar Lista");

        jLabel1.setText("nickname de usuario");

        jLabel2.setText("lista que se hará pública");

        jTextArealistaPublica.setColumns(20);
        jTextArealistaPublica.setRows(5);
        jScrollPane1.setViewportView(jTextArealistaPublica);

        jToggleButtonenviar.setText("enviar");
        jToggleButtonenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonenviarActionPerformed(evt);
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
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldnickUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPublicarListaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButtonenviar)
                .addGap(227, 227, 227))
        );
        jPanelPublicarListaLayout.setVerticalGroup(
            jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPublicarListaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldnickUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelPublicarListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonenviar)
                .addContainerGap(146, Short.MAX_VALUE))
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

    private void jToggleButtonenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonenviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonenviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPublicarLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArealistaPublica;
    private javax.swing.JTextField jTextFieldnickUsuario;
    private javax.swing.JToggleButton jToggleButtonenviar;
    // End of variables declaration//GEN-END:variables
}
