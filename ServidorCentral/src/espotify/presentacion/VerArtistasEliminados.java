/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTTemaGenerico;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerArtistasEliminados extends javax.swing.JInternalFrame {

    IControlador controlador;
    ArrayList<DTAlbum> datosAlbumesArtista = new ArrayList<>();
    
    public VerArtistasEliminados() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        InicializarJLists();
        
        /* Cargo el JList con los Nicknames de Artistas Eliminados */
        DefaultListModel<String> listaNicknamesArtistas = new DefaultListModel<>();
        ArrayList<String> nicknamesArtistas = new ArrayList<>(controlador.getNicknamesArtistasEliminados());
        
        for (String nickname : nicknamesArtistas) {
            listaNicknamesArtistas.addElement(nickname);
        }
        jListArtistas.setModel(listaNicknamesArtistas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListArtistas = new javax.swing.JList<>();
        jLabelAlbumes = new javax.swing.JLabel();
        jLabelArtistas = new javax.swing.JLabel();
        jButtonMostrarInformacion = new javax.swing.JButton();
        jButtonCargarAlbumes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAlbumes = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        ImageArtistaLabel = new javax.swing.JLabel();
        jLabelBiografia = new javax.swing.JLabel();
        jScrollPaneBiografiaInfo = new javax.swing.JScrollPane();
        jTextAreaBiografiaInfo = new javax.swing.JTextArea();
        jLabelDirSitioWebInfo = new javax.swing.JLabel();
        jLabelFecBaja = new javax.swing.JLabel();
        jLabelNicknameInfo = new javax.swing.JLabel();
        jLabelNombreCompletoInfo = new javax.swing.JLabel();
        jLabelFecBajaInfo = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jLabelFecNacInfo = new javax.swing.JLabel();
        jLabelCorreoInfo = new javax.swing.JLabel();
        jLabelDirSitioWeb = new javax.swing.JLabel();
        jLabelFecNac = new javax.swing.JLabel();
        jLabelNombreCompleto = new javax.swing.JLabel();
        jLabelTituloDatos = new javax.swing.JLabel();
        jLabelNickname = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTemas = new javax.swing.JTable();
        jLabelInformacionDeLosTemas = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ver Artistas Eliminados");

        jListArtistas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListArtistas);

        jLabelAlbumes.setText("Albumes:");

        jLabelArtistas.setText("Artistas:");

        jButtonMostrarInformacion.setText("Mostrar Informacion");
        jButtonMostrarInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarInformacionActionPerformed(evt);
            }
        });

        jButtonCargarAlbumes.setText("Cargar Albumes");
        jButtonCargarAlbumes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarAlbumesActionPerformed(evt);
            }
        });

        jListAlbumes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListAlbumes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlbumes)
                    .addComponent(jLabelArtistas))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMostrarInformacion)
                    .addComponent(jButtonCargarAlbumes)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelArtistas)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonCargarAlbumes)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAlbumes))
                .addGap(18, 18, 18)
                .addComponent(jButtonMostrarInformacion)
                .addContainerGap())
        );

        ImageArtistaLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelBiografia.setText("Biografia:");

        jTextAreaBiografiaInfo.setEditable(false);
        jTextAreaBiografiaInfo.setColumns(20);
        jTextAreaBiografiaInfo.setLineWrap(true);
        jTextAreaBiografiaInfo.setRows(5);
        jTextAreaBiografiaInfo.setText("...");
        jTextAreaBiografiaInfo.setWrapStyleWord(true);
        jScrollPaneBiografiaInfo.setViewportView(jTextAreaBiografiaInfo);

        jLabelDirSitioWebInfo.setText("...");

        jLabelFecBaja.setText("Fecha de baja:");

        jLabelNicknameInfo.setText("...");

        jLabelNombreCompletoInfo.setText("...");

        jLabelFecBajaInfo.setText("...");

        jLabelCorreo.setText("Correo electronico:");

        jLabelFecNacInfo.setText("...");

        jLabelCorreoInfo.setText("...");

        jLabelDirSitioWeb.setText("Dirección Sitio Web:");

        jLabelFecNac.setText("Fecha de nacimiento:");

        jLabelNombreCompleto.setText("Nombre Completo:");

        jLabelTituloDatos.setFont(new java.awt.Font("Liberation Sans", 1, 21)); // NOI18N
        jLabelTituloDatos.setText("DATOS DEL ARTISTA");

        jLabelNickname.setText("Nickname:");

        jTableTemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTableTemas);

        jLabelInformacionDeLosTemas.setText("Información de los temas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelInformacionDeLosTemas))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ImageArtistaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelTituloDatos))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelFecNac)
                                            .addComponent(jLabelNickname)
                                            .addComponent(jLabelNombreCompleto)
                                            .addComponent(jLabelFecBaja)
                                            .addComponent(jLabelCorreo)
                                            .addComponent(jLabelDirSitioWeb)
                                            .addComponent(jLabelBiografia))
                                        .addGap(45, 45, 45)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabelNicknameInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelNombreCompletoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelFecBajaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelFecNacInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelCorreoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelDirSitioWebInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPaneBiografiaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTituloDatos)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNickname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNicknameInfo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNombreCompleto)
                            .addComponent(jLabelNombreCompletoInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFecBaja)
                            .addComponent(jLabelFecBajaInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFecNac)
                            .addComponent(jLabelFecNacInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCorreo)
                            .addComponent(jLabelCorreoInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDirSitioWeb)
                            .addComponent(jLabelDirSitioWebInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBiografia)
                            .addComponent(jScrollPaneBiografiaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ImageArtistaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addGap(3, 3, 3)
                .addComponent(jLabelInformacionDeLosTemas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMostrarInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarInformacionActionPerformed
        
        // Obtengo el nombre Album seleccionado
        String nombreAlbumSeleccionado = jListAlbumes.getSelectedValue();
        Long idAlbumSeleccionado = null;
        
        // Verifico que se haya seleccionado un Album
        if (nombreAlbumSeleccionado != null) {
            // Busco el idAlbum seleccionado
            for (DTAlbum dtA : datosAlbumesArtista) {
                if (dtA.getNombreAlbum().equals(nombreAlbumSeleccionado)) {
                    idAlbumSeleccionado = dtA.getIdAlbum();
                }
            }

            // Obtengo el Album
            DTAlbum dtAlbum = controlador.ConsultaAlbum(idAlbumSeleccionado);

            // Defino columnas de la tabla
            String[] columnas = {"Nombre Tema", "Duración (seg)", "Posición en Álbum", "Descargas", "Favoritos", "Reproducciones"};

            // Convierto la lista de Temas a formato para JTable
            Object[][] datos = new Object[dtAlbum.getMisTemas().size()][columnas.length];
            for (int i = 0; i < dtAlbum.getMisTemas().size(); i++) {
                DTTemaGenerico tema = dtAlbum.getMisTemas().get(i);
                datos[i][0] = tema.getNombreTema();
                datos[i][1] = tema.getDuracionSegundos();
                datos[i][2] = tema.getPosicionEnAlbum();
                datos[i][3] = tema.getCantidadDescargas();
                datos[i][4] = tema.getCantidadFavoritos();
                datos[i][5] = tema.getCantidadReproducciones();
            }

            // Crear y configurar modelo de la tabla
            DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);

            // Asignar modelo a la JTable y evitar que se edite
            jTableTemas.setModel(modeloTabla);
            jTableTemas.setDefaultEditor(Object.class, null);
            jTableTemas.getTableHeader().setReorderingAllowed(false);
        } else {
            
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un Álbum",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        
    }//GEN-LAST:event_jButtonMostrarInformacionActionPerformed

    private void jButtonCargarAlbumesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarAlbumesActionPerformed
        
        // Obtengo el Nickname seleccionado
        String nicknameArtistaSeleccionado = jListArtistas.getSelectedValue();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        // Verifico que se haya seleccionado un Artista
        if (nicknameArtistaSeleccionado != null) {
        
            // Obtengo sus datos
            DTDatosArtista datosArtista = controlador.ConsultarPerfilArtista(nicknameArtistaSeleccionado);
            
            jLabelNicknameInfo.setText(datosArtista.getNicknameUsuario());
            jLabelNombreCompletoInfo.setText(datosArtista.getNombreUsuario() + " " + datosArtista.getApellidoUsuario());
            
            // Convertir Date a formato String
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecBajaString = dateFormat.format(datosArtista.getFechaBaja());
            jLabelFecBajaInfo.setText(fecBajaString);
            
            String fecNacString = dateFormat.format(datosArtista.getFecNac());
            jLabelFecNacInfo.setText(fecNacString);
            
            jLabelCorreoInfo.setText(datosArtista.getEmail());
            jLabelDirSitioWebInfo.setText(datosArtista.getDirSitioWeb());
            jTextAreaBiografiaInfo.setText(datosArtista.getBiografia());
            
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // Obtener los DTAlbumes segun el artista
            datosAlbumesArtista = controlador.getDTAlbumsConTemasDeArtista(nicknameArtistaSeleccionado);
            
            // Cargo el JList con los nombres de los Albumes
            DefaultListModel<String> listaNombresAlbumes = new DefaultListModel<>();
            
            for (DTAlbum dtA : datosAlbumesArtista) {
                listaNombresAlbumes.addElement(dtA.getNombreAlbum());
            }
            jListAlbumes.setModel(listaNombresAlbumes);
            
            // Creo un modelo de tabla vacio
            DefaultTableModel emptyModel = new DefaultTableModel();
            
            // Le asigno un modelo vacio a la JTable de Temas
            jTableTemas.setModel(emptyModel);
        } else {
            
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un Artista",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButtonCargarAlbumesActionPerformed

    private void InicializarJLists() {
        DefaultListModel<String> emptyModel = new DefaultListModel<>();
        jListArtistas.setModel(emptyModel);
        jListAlbumes.setModel(emptyModel);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageArtistaLabel;
    private javax.swing.JButton jButtonCargarAlbumes;
    private javax.swing.JButton jButtonMostrarInformacion;
    private javax.swing.JLabel jLabelAlbumes;
    private javax.swing.JLabel jLabelArtistas;
    private javax.swing.JLabel jLabelBiografia;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelCorreoInfo;
    private javax.swing.JLabel jLabelDirSitioWeb;
    private javax.swing.JLabel jLabelDirSitioWebInfo;
    private javax.swing.JLabel jLabelFecBaja;
    private javax.swing.JLabel jLabelFecBajaInfo;
    private javax.swing.JLabel jLabelFecNac;
    private javax.swing.JLabel jLabelFecNacInfo;
    private javax.swing.JLabel jLabelInformacionDeLosTemas;
    private javax.swing.JLabel jLabelNickname;
    private javax.swing.JLabel jLabelNicknameInfo;
    private javax.swing.JLabel jLabelNombreCompleto;
    private javax.swing.JLabel jLabelNombreCompletoInfo;
    private javax.swing.JLabel jLabelTituloDatos;
    private javax.swing.JList<String> jListAlbumes;
    private javax.swing.JList<String> jListArtistas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneBiografiaInfo;
    private javax.swing.JTable jTableTemas;
    private javax.swing.JTextArea jTextAreaBiografiaInfo;
    // End of variables declaration//GEN-END:variables
}
