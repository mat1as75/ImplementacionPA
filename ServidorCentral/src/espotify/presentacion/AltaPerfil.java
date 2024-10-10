package espotify.presentacion;

import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AltaPerfil extends javax.swing.JInternalFrame {
    private String fotoPerfil;
    private File archivoFotoPerfil;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public AltaPerfil() {
        initComponents();
        jLabelBiografia.setVisible(false);
        jTextAreaBiografia.setVisible(false);
        jScrollPane2.setVisible(false);
        jLabelwebpromocion.setVisible(false);
        jTextFieldwebpromocion.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxUsuario = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabelnickname = new javax.swing.JLabel();
        jLabelnombre = new javax.swing.JLabel();
        jLabelapellido = new javax.swing.JLabel();
        jLabelemail = new javax.swing.JLabel();
        jLabelfechanacimiento = new javax.swing.JLabel();
        jTextFieldnickname = new javax.swing.JTextField();
        jTextFieldnombre = new javax.swing.JTextField();
        jTextFieldapellido = new javax.swing.JTextField();
        jTextFieldemail = new javax.swing.JTextField();
        openButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        jDateChooserfechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabelBiografia = new javax.swing.JLabel();
        jLabelwebpromocion = new javax.swing.JLabel();
        jTextFieldwebpromocion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaBiografia = new javax.swing.JTextArea();
        jComboBoxusuario = new javax.swing.JComboBox<>();
        buttonAceptar = new java.awt.Button();
        buttonCancelar = new java.awt.Button();
        jLabelcontrasena = new javax.swing.JLabel();
        jTextFieldcontrasena = new javax.swing.JTextField();
        btnSalir = new java.awt.Button();

        jComboBoxUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alta de Perfil");

        jLabelnickname.setText("nickname");

        jLabelnombre.setText("nombre");

        jLabelapellido.setText("apellido");

        jLabelemail.setText("email");

        jLabelfechanacimiento.setText("fecha de nacimiento");

        jTextFieldapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldapellidoActionPerformed(evt);
            }
        });

        openButton.setText("Abrir imagen");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelBiografia.setText("Biografia");

        jLabelwebpromocion.setText("web de promocion");

        jTextAreaBiografia.setColumns(20);
        jTextAreaBiografia.setRows(5);
        jScrollPane2.setViewportView(jTextAreaBiografia);

        jComboBoxusuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cliente", "artista" }));
        jComboBoxusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxusuarioActionPerformed(evt);
            }
        });

        buttonAceptar.setLabel("Aceptar");
        buttonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAceptarActionPerformed(evt);
            }
        });

        buttonCancelar.setLabel("Limpiar Formulario");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        jLabelcontrasena.setText("contraseña");

        jTextFieldcontrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldcontrasenaActionPerformed(evt);
            }
        });

        btnSalir.setLabel("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelnickname)
                    .addComponent(jLabelnombre)
                    .addComponent(jLabelapellido)
                    .addComponent(jLabelemail)
                    .addComponent(jLabelfechanacimiento)
                    .addComponent(openButton)
                    .addComponent(jLabelBiografia)
                    .addComponent(jLabelwebpromocion)
                    .addComponent(jLabelcontrasena)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldapellido, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jTextFieldnombre)
                            .addComponent(jTextFieldnickname)
                            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserfechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldemail)
                            .addComponent(jTextFieldcontrasena))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldwebpromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(102, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelnickname)
                    .addComponent(jTextFieldnickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelnombre)
                            .addComponent(jTextFieldnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelapellido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelcontrasena)
                            .addComponent(jTextFieldcontrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelemail)
                            .addComponent(jTextFieldemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelfechanacimiento)
                            .addComponent(jDateChooserfechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openButton))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelBiografia)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelwebpromocion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldwebpromocion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAceptar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldapellidoActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser buscar = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Seleccionar imagen", "jpg", "png");
        buscar.setFileFilter(extension);

        if (buscar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = buscar.getSelectedFile();
            String ruta = selectedFile.getAbsolutePath();
            // Configura la carpeta de destino
            File destinoCarpeta = new File("./Resource/ImagenesPerfil");

            if (!destinoCarpeta.exists()) {
                destinoCarpeta.mkdirs();
            }

            /* Define el nuevo archivo en la carpeta de destino
               esto es lo que se guarda en la base de datos */
            File destinoArchivo;
            destinoArchivo = new File(destinoCarpeta, selectedFile.getName());
            fotoPerfil = destinoArchivo.getPath();
            try {
                // Copia el archivo a la carpeta de destino
                Files.copy(selectedFile.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Cargar la imagen para mostrarla en el JLabel
                Toolkit tool = Toolkit.getDefaultToolkit();
                Image imagen = tool.createImage(ruta);
                imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_AREA_AVERAGING)));
                archivoFotoPerfil = destinoArchivo;
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de excepciones en caso de error al copiar
            }
        }
            }//GEN-LAST:event_openButtonActionPerformed
    
    private void borrarFotoSubida() {
        Path ruta = archivoFotoPerfil.toPath();
        Boolean borradoExitosamente = false;
        try {
            borradoExitosamente = Files.deleteIfExists(ruta);
        } catch(Exception ex) {
            if (!borradoExitosamente) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void jComboBoxusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxusuarioActionPerformed
        
        String opcion=(String)jComboBoxusuario.getSelectedItem();
        if(opcion.equals("cliente")){
            jLabelBiografia.setVisible(false);
            jTextAreaBiografia.setVisible(false);
            jScrollPane2.setVisible(false);
            jLabelwebpromocion.setVisible(false);
            jTextFieldwebpromocion.setVisible(false);
        }else{
            jLabelBiografia.setVisible(true);
            jTextAreaBiografia.setVisible(true);
            jScrollPane2.setVisible(true);
            jLabelwebpromocion.setVisible(true);
            jTextFieldwebpromocion.setVisible(true);
        }    
       
    }//GEN-LAST:event_jComboBoxusuarioActionPerformed

    private void buttonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarActionPerformed
        String nickname=jTextFieldnickname.getText();
        String nombre=jTextFieldnombre.getText();
        String apellido=jTextFieldapellido.getText();
        String contrasena=jTextFieldcontrasena.getText();
        String email=jTextFieldemail.getText();
        Date fecNac=jDateChooserfechaNacimiento.getDate();
        String opcion=(String)jComboBoxusuario.getSelectedItem();
        String biografia="";
        String webPromocion="";
        Fabrica f=Fabrica.getInstance();
        IControlador i=f.getControlador();
        boolean existeNicName= i.ExisteNickName(nickname);
        boolean existeEmail= i.ExisteEmail(email);
        boolean escorrectoEmail=EMAIL_PATTERN.matcher(email).matches();
        
        if (nickname.isEmpty()){
            JOptionPane.showMessageDialog(null, "El nickname esta vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(existeNicName){
            JOptionPane.showMessageDialog(null, "El nickname ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldnickname.setText("");
            return;
        }
        
        if (email.isEmpty()){
            JOptionPane.showMessageDialog(null, "El email esta vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(existeEmail){
            JOptionPane.showMessageDialog(null, "El email ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldemail.setText("");
            return;
        }
        
        if(!escorrectoEmail){
            JOptionPane.showMessageDialog(null, "El mail no es correcto.", "Error", JOptionPane.ERROR_MESSAGE);
            jTextFieldemail.setText("");
            return;
        }

        if (nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "El nombre esta vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (apellido.isEmpty()){
            JOptionPane.showMessageDialog(null, "El apellido esta vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La contraseña esta vacia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (fecNac == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento esta vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean borrar = ((!nickname.isEmpty()) && (!nombre.isEmpty()) && (!apellido.isEmpty()) && (!contrasena.isEmpty()) && (!email.isEmpty()) && (fecNac != null)&&(!existeNicName)&&(!existeEmail)&&(escorrectoEmail));
        if (borrar) {
            if (opcion.equals("artista")) {
                biografia = jTextAreaBiografia.getText();
                webPromocion = jTextFieldwebpromocion.getText();
                DTArtista dtArtista = new DTArtista(
                        nickname, 
                        nombre, 
                        apellido, 
                        contrasena, 
                        email, 
                        fecNac, 
                        fotoPerfil,
                        null,
                        biografia, 
                        webPromocion,
                        null
                );
                i.AltaArtista(dtArtista);
                
                JOptionPane.showMessageDialog(this, 
                        "Artista creado exitosamente.",
                        "Operacion exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if (opcion.equals("cliente")) {
                DTCliente dtCliente = new DTCliente(
                        nickname,
                        nombre,
                        apellido,
                        contrasena,
                        email,
                        fecNac,
                        fotoPerfil
                );
                i.AltaCliente(dtCliente);
                
                JOptionPane.showMessageDialog(this, 
                        "Cliente creado exitosamente.",
                        "Operacion exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            jTextFieldnickname.setText("");
            jTextFieldnombre.setText("");
            jTextFieldapellido.setText("");
            jTextFieldcontrasena.setText("");
            jTextFieldemail.setText("");
            jDateChooserfechaNacimiento.setDate(null);
            imageLabel.setIcon(null);
            jTextAreaBiografia.setText("");
            jTextFieldwebpromocion.setText("");
        }    
    }//GEN-LAST:event_buttonAceptarActionPerformed
    
    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        jTextFieldnickname.setText("");
        jTextFieldnombre.setText("");
        jTextFieldapellido.setText("");
        jTextFieldcontrasena.setText("");
        jTextFieldemail.setText("");
        jDateChooserfechaNacimiento.setDate(null);
        this.imageLabel.setIcon(null);
        jTextAreaBiografia.setText("");
        jTextFieldwebpromocion.setText("");
        if (archivoFotoPerfil != null) {
            borrarFotoSubida();
        }
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void jTextFieldcontrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldcontrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldcontrasenaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (archivoFotoPerfil != null) {
            borrarFotoSubida();
        }
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnSalir;
    private java.awt.Button buttonAceptar;
    private java.awt.Button buttonCancelar;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JComboBox<String> jComboBoxUsuario;
    private javax.swing.JComboBox<String> jComboBoxusuario;
    private com.toedter.calendar.JDateChooser jDateChooserfechaNacimiento;
    private javax.swing.JLabel jLabelBiografia;
    private javax.swing.JLabel jLabelapellido;
    private javax.swing.JLabel jLabelcontrasena;
    private javax.swing.JLabel jLabelemail;
    private javax.swing.JLabel jLabelfechanacimiento;
    private javax.swing.JLabel jLabelnickname;
    private javax.swing.JLabel jLabelnombre;
    private javax.swing.JLabel jLabelwebpromocion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaBiografia;
    private javax.swing.JTextField jTextFieldapellido;
    private javax.swing.JTextField jTextFieldcontrasena;
    private javax.swing.JTextField jTextFieldemail;
    private javax.swing.JTextField jTextFieldnickname;
    private javax.swing.JTextField jTextFieldnombre;
    private javax.swing.JTextField jTextFieldwebpromocion;
    private javax.swing.JButton openButton;
    // End of variables declaration//GEN-END:variables
}
