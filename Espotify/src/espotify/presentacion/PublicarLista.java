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
        jButtonEnviar = new javax.swing.JButton();

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

        jButtonEnviar.setText("Enviar");

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
                    .addComponent(jButtonEnviar)
                    .addComponent(jTextFieldnickUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
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
                .addGap(35, 35, 35)
                .addComponent(jButtonEnviar)
                .addContainerGap(129, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelPublicarLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArealistaPublica;
    private javax.swing.JTextField jTextFieldnickUsuario;
    // End of variables declaration//GEN-END:variables
}
