/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTDatosArtista;
import espotify.logica.Artista;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author tecnologo
 */
public class ConsultaPerfilArtista extends javax.swing.JInternalFrame {

    IControlador controlador;
    
    public ConsultaPerfilArtista() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        InicializarJLists();
        
        /* Cargo el jList con los Nicknames de Artistas del Sistema */
        DefaultListModel<String> listaNicknamesArtistas = new DefaultListModel<>();
        ArrayList<String> nicknamesArtistas = new ArrayList<>(controlador.getNicknamesArtistas());

        for (String nickname: nicknamesArtistas) {
            listaNicknamesArtistas.addElement(nickname);
        }
        jListArtistas.setModel(listaNicknamesArtistas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNickname = new javax.swing.JLabel();
        jLabelTituloDatos = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTituloSeguidores = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabelFecNac = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        ImageArtistaLabel = new javax.swing.JLabel();
        jLabelNicknameInfo = new javax.swing.JLabel();
        jScrollPaneSeguidores = new javax.swing.JScrollPane();
        jListSeguidores = new javax.swing.JList<>();
        jLabelSeleccioneArtista = new javax.swing.JLabel();
        jLabelNombreInfo = new javax.swing.JLabel();
        jScrollPaneListArtistas = new javax.swing.JScrollPane();
        jListArtistas = new javax.swing.JList<>();
        jLabelApellidoInfo = new javax.swing.JLabel();
        jLabelFecNacInfo = new javax.swing.JLabel();
        jButtonAceptar = new javax.swing.JButton();
        jLabelCorreoInfo = new javax.swing.JLabel();
        jScrollPaneListasRCreadas = new javax.swing.JScrollPane();
        jListAlbumesPublicados = new javax.swing.JList<>();
        jLabelDirSitioWeb = new javax.swing.JLabel();
        jLabelDirSitioWebInfo = new javax.swing.JLabel();
        jLabelBiografia = new javax.swing.JLabel();
        jScrollPaneBiografiaInfo = new javax.swing.JScrollPane();
        jTextAreaBiografiaInfo = new javax.swing.JTextArea();
        jLabelCantSeguidores = new javax.swing.JLabel();
        jLabelTituloAlbumesPublicados = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de Perfil de Artista");
        setPreferredSize(new java.awt.Dimension(1150, 600));

        jLabelNickname.setText("Nickname:");

        jLabelTituloDatos.setFont(new java.awt.Font("Liberation Sans", 1, 21)); // NOI18N
        jLabelTituloDatos.setText("DATOS DEL ARTISTA");

        jLabelNombre.setText("Nombre:");

        jLabelTituloSeguidores.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        jLabelTituloSeguidores.setText("SEGUIDORES");

        jLabelApellido.setText("Apellido:");

        jLabelFecNac.setText("Fecha de nacimiento:");

        jLabelCorreo.setText("Correo electronico:");

        ImageArtistaLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelNicknameInfo.setText("...");

        jListSeguidores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneSeguidores.setViewportView(jListSeguidores);

        jLabelSeleccioneArtista.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        jLabelSeleccioneArtista.setText("SELECCIONE UN ARTISTA");

        jLabelNombreInfo.setText("...");

        jListArtistas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneListArtistas.setViewportView(jListArtistas);

        jLabelApellidoInfo.setText("...");

        jLabelFecNacInfo.setText("...");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jLabelCorreoInfo.setText("...");

        jListAlbumesPublicados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneListasRCreadas.setViewportView(jListAlbumesPublicados);

        jLabelDirSitioWeb.setText("Dirección Sitio Web:");

        jLabelDirSitioWebInfo.setText("...");

        jLabelBiografia.setText("Biografia:");

        jTextAreaBiografiaInfo.setEditable(false);
        jTextAreaBiografiaInfo.setColumns(20);
        jTextAreaBiografiaInfo.setLineWrap(true);
        jTextAreaBiografiaInfo.setRows(5);
        jTextAreaBiografiaInfo.setText("...");
        jTextAreaBiografiaInfo.setWrapStyleWord(true);
        jScrollPaneBiografiaInfo.setViewportView(jTextAreaBiografiaInfo);

        jLabelCantSeguidores.setText("0");

        jLabelTituloAlbumesPublicados.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        jLabelTituloAlbumesPublicados.setText("ÁLBUMES PUBLICADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSeleccioneArtista)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPaneListArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAceptar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(338, 338, 338)
                                .addComponent(jLabelTituloDatos))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(ImageArtistaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelFecNac)
                                            .addComponent(jLabelNickname)
                                            .addComponent(jLabelNombre)
                                            .addComponent(jLabelApellido)
                                            .addComponent(jLabelCorreo)
                                            .addComponent(jLabelDirSitioWeb)
                                            .addComponent(jLabelBiografia)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPaneSeguidores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabelTituloSeguidores)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelCantSeguidores)))
                                        .addGap(98, 98, 98)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabelNicknameInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                                .addComponent(jLabelNombreInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelApellidoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelFecNacInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelCorreoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelDirSitioWebInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jScrollPaneBiografiaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(127, 127, 127)
                                        .addComponent(jScrollPaneListasRCreadas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabelTituloAlbumesPublicados, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)))))))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabelSeleccioneArtista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTituloDatos)
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNickname, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelNicknameInfo, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelNombre)
                                    .addComponent(jLabelNombreInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelApellido)
                                    .addComponent(jLabelApellidoInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelFecNac)
                                    .addComponent(jLabelFecNacInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelCorreo)
                                    .addComponent(jLabelCorreoInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelDirSitioWeb)
                                    .addComponent(jLabelDirSitioWebInfo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBiografia)
                                    .addComponent(jScrollPaneBiografiaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ImageArtistaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTituloSeguidores)
                            .addComponent(jLabelCantSeguidores)
                            .addComponent(jLabelTituloAlbumesPublicados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneListasRCreadas)
                            .addComponent(jScrollPaneSeguidores)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneListArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAceptar)))
                .addGap(98, 98, 98))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        
        // Obtengo el Nickname seleccionado
        String nicknameArtistaSeleccionado = jListArtistas.getSelectedValue();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        // Obtengo sus datos
        DTDatosArtista datosArtista = controlador.ConsultarPerfilArtista(nicknameArtistaSeleccionado);
        
        // Verifica si hay una selección
        if (nicknameArtistaSeleccionado != null) {
            
            jLabelNicknameInfo.setText(datosArtista.getNickname());
            jLabelNombreInfo.setText(datosArtista.getNombreUsuario());
            jLabelApellidoInfo.setText(datosArtista.getApellidoUsuario());
            
            // Convertir Date a formato String
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecNacString = dateFormat.format(datosArtista.getFecNac());
            jLabelFecNacInfo.setText(fecNacString);
            
            jLabelCorreoInfo.setText(datosArtista.getEmail());
            jLabelDirSitioWebInfo.setText(datosArtista.getDirSitioWeb());
            jTextAreaBiografiaInfo.setText(datosArtista.getBiografia());
            jLabelCantSeguidores.setText(String.valueOf(datosArtista.getCantidadSeguidores()));
            
            
            try {
                
                BufferedImage archivoAlmacenado;
                ImageIcon imagen;
                
                // Definir el tamaño deseado de la imagen
                int sizeHorizontal = 156;
                int sizeVertical = 163;
                Image escalaImagen;
                
                // Verificar si tiene foto perfil
                if (datosArtista.getFotoPerfil() != null) {
                    
                    // Lee la imagen desde el archivo(ruta)
                    archivoAlmacenado = ImageIO.read(new File(datosArtista.getFotoPerfil()));
                    
                    // Verifica si la imagen se ha cargado correctamente
                    if (archivoAlmacenado != null) {
                        
                        // Redimensionar la imagen
                        escalaImagen = archivoAlmacenado.getScaledInstance(sizeHorizontal, sizeVertical, Image.SCALE_SMOOTH);

                        // Convierte archivoAlmacenado a ImageIcon
                        imagen = new ImageIcon(escalaImagen);
                        
                        ImageArtistaLabel.setIcon(imagen);
                        ImageArtistaLabel.setVisible(true);
                    }
                } else { // Asignar foto de perfil predeterminada
                    
                    archivoAlmacenado = ImageIO.read(new File("./Resource/ImagenesPerfil/Default-Photo-Profile.jpg"));
                    
                    // Verifica si la imagen se ha cargado correctamente
                    if (archivoAlmacenado != null) {
                        
                        // Redimensionar la imagen
                        escalaImagen = archivoAlmacenado.getScaledInstance(sizeHorizontal, sizeVertical, Image.SCALE_SMOOTH);
                        
                        // Convierte archivoAlmacenado a ImageIcon
                        imagen = new ImageIcon(escalaImagen);
                        
                        ImageArtistaLabel.setIcon(imagen);
                        ImageArtistaLabel.setVisible(true);
                    }
                }
                
            } catch(IOException e) {
                e.printStackTrace();
            }

            /* Recorro lista Nicknames de Seguidores del Artista, 
            mientras que lo agrego como elemento al modelo. Luego 
            setteo el modelo con todos los Nicknames a la JList*/
            DefaultListModel<String> listaSeguidores = new DefaultListModel<>();
            ArrayList<String> nicknamesSeguidores = datosArtista.getNicknamesSeguidores();
            
            for (String nickname: nicknamesSeguidores) {
                listaSeguidores.addElement(nickname);
            }
            jListSeguidores.setModel(listaSeguidores);
            
            /* Recorro lista NombresAlbumesP del Artista, 
            mientras que lo agrego como elemento al modelo. Luego 
            setteo el modelo con todos los NombresAlbumesP a la JList*/
            DefaultListModel<String> listaAlbumesPublicados = new DefaultListModel<>();
            ArrayList<String> nombresAlbumesP = datosArtista.getNombresAlbumesPublicados();
            
            for (String nombreAlbum: nombresAlbumesP) {
                listaAlbumesPublicados.addElement(nombreAlbum);
            }
            jListAlbumesPublicados.setModel(listaAlbumesPublicados);
            
            // Quito la posibilidad de volver a Consultar
            //jButtonAceptar.setVisible(false);
        }
        
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void InicializarJLists() {
        DefaultListModel<String> emptyModel = new DefaultListModel<>();
        jListSeguidores.setModel(emptyModel);
        jListAlbumesPublicados.setModel(emptyModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageArtistaLabel;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelApellidoInfo;
    private javax.swing.JLabel jLabelBiografia;
    private javax.swing.JLabel jLabelCantSeguidores;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelCorreoInfo;
    private javax.swing.JLabel jLabelDirSitioWeb;
    private javax.swing.JLabel jLabelDirSitioWebInfo;
    private javax.swing.JLabel jLabelFecNac;
    private javax.swing.JLabel jLabelFecNacInfo;
    private javax.swing.JLabel jLabelNickname;
    private javax.swing.JLabel jLabelNicknameInfo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreInfo;
    private javax.swing.JLabel jLabelSeleccioneArtista;
    private javax.swing.JLabel jLabelTituloAlbumesPublicados;
    private javax.swing.JLabel jLabelTituloDatos;
    private javax.swing.JLabel jLabelTituloSeguidores;
    private javax.swing.JList<String> jListAlbumesPublicados;
    private javax.swing.JList<String> jListArtistas;
    private javax.swing.JList<String> jListSeguidores;
    private javax.swing.JScrollPane jScrollPaneBiografiaInfo;
    private javax.swing.JScrollPane jScrollPaneListArtistas;
    private javax.swing.JScrollPane jScrollPaneListasRCreadas;
    private javax.swing.JScrollPane jScrollPaneSeguidores;
    private javax.swing.JTextArea jTextAreaBiografiaInfo;
    // End of variables declaration//GEN-END:variables
}