/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package espotify.presentacion;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Tema;
import espotify.logica.TemaConRuta;
import espotify.logica.TemaConURL;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brisa
 */
public class ConsultaAlbum extends javax.swing.JInternalFrame {
    private IControlador controlador;
    int op=0; //1 si es por genero, 2 si es por Artista
    private List<String> AoGlistaString= new ArrayList<>();
    private Map<Long,String> AlbumsLista= new HashMap<>();
    private DTAlbum albumAMostrar;
    /**
     * Creates new form ConsultaDeAlbum
     */
    public ConsultaAlbum() {
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        initComponents();
        inicializarJLists();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EtiquetaConsulta = new javax.swing.JLabel();
        GoAComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        etiquetaGoA = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaAoG = new javax.swing.JList<>();
        etiquetaAlbumes = new javax.swing.JLabel();
        mostrarInfoAlbumBoton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaAlbumes = new javax.swing.JList<>();
        cargarAlbumesBoton = new javax.swing.JButton();
        panelInfoAlbum = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        EtiquetaConsulta1 = new javax.swing.JLabel();
        nombreAlbumtextField = new javax.swing.JTextField();
        EtiquetaConsulta2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        generosAlbum = new javax.swing.JList<>();
        EtiquetaConsulta3 = new javax.swing.JLabel();
        nombreArtistatextField = new javax.swing.JTextField();
        jLabelInformacionDeLosTemas = new javax.swing.JLabel();
        jButtonDescargarArchivosDeMusica = new javax.swing.JButton();
        jButtonVerDireccionWeb = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTemas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consilta De Álbum");

        EtiquetaConsulta.setText("Consulta por:");

        GoAComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genero", "Artista" }));
        GoAComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoAComboBoxActionPerformed(evt);
            }
        });

        etiquetaGoA.setText("Generos:");

        listaAoG.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaAoG);

        etiquetaAlbumes.setText("Albumes:");

        mostrarInfoAlbumBoton.setText("Mostrar Informacion");
        mostrarInfoAlbumBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarInfoAlbumBotonActionPerformed(evt);
            }
        });

        listaAlbumes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaAlbumes);

        cargarAlbumesBoton.setText("Cargar Albumes");
        cargarAlbumesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarAlbumesBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaGoA)
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaAlbumes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarInfoAlbumBoton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cargarAlbumesBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaGoA)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(cargarAlbumesBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaAlbumes))
                .addGap(18, 18, 18)
                .addComponent(mostrarInfoAlbumBoton)
                .addGap(24, 24, 24))
        );

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        EtiquetaConsulta1.setText("Nombre de Album:");

        nombreAlbumtextField.setText("jTextField1");

        EtiquetaConsulta2.setText("Generos:");

        generosAlbum.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(generosAlbum);

        EtiquetaConsulta3.setText("Artista:");

        nombreArtistatextField.setText("jTextField2");

        jLabelInformacionDeLosTemas.setText("Información de los temas:");

        jButtonDescargarArchivosDeMusica.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonDescargarArchivosDeMusica.setText("Descargar Archivo de Música");
        jButtonDescargarArchivosDeMusica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonDescargarArchivosDeMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDescargarArchivosDeMusicaActionPerformed(evt);
            }
        });

        jButtonVerDireccionWeb.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonVerDireccionWeb.setText("Ver Dirección Web");
        jButtonVerDireccionWeb.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVerDireccionWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDireccionWebActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelInfoAlbumLayout = new javax.swing.GroupLayout(panelInfoAlbum);
        panelInfoAlbum.setLayout(panelInfoAlbumLayout);
        panelInfoAlbumLayout.setHorizontalGroup(
            panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAlbumLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInformacionDeLosTemas)
                    .addGroup(panelInfoAlbumLayout.createSequentialGroup()
                        .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoAlbumLayout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EtiquetaConsulta3)
                                    .addComponent(EtiquetaConsulta2)
                                    .addComponent(EtiquetaConsulta1)))
                            .addComponent(jButtonDescargarArchivosDeMusica))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVerDireccionWeb)
                            .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nombreAlbumtextField, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addComponent(nombreArtistatextField)))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoAlbumLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelInfoAlbumLayout.setVerticalGroup(
            panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoAlbumLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoAlbumLayout.createSequentialGroup()
                        .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EtiquetaConsulta1)
                            .addComponent(nombreAlbumtextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EtiquetaConsulta3)
                            .addComponent(nombreArtistatextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EtiquetaConsulta2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabelInformacionDeLosTemas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInfoAlbumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDescargarArchivosDeMusica)
                    .addComponent(jButtonVerDireccionWeb))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(EtiquetaConsulta)
                        .addGap(46, 46, 46)
                        .addComponent(GoAComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EtiquetaConsulta)
                    .addComponent(GoAComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoAlbum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GoAComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoAComboBoxActionPerformed
        // TODO add your handling code here:
        String opcion = (String) GoAComboBox.getSelectedItem();
        if (opcion == "Genero") {
            this.etiquetaGoA.setText("Genero");
            AoGlistaString = controlador.getNombresGenerosPadre();//se puede consultar por generos hijos?
            if (!AoGlistaString.isEmpty()) {
                DefaultListModel<String> listaGeneros = new DefaultListModel<>();
                for (String genero : AoGlistaString) {
                    listaGeneros.addElement(genero);
                }
                listaAoG.setModel(listaGeneros);
                op = 1;
            }else{
                JOptionPane.showMessageDialog(null, "No hay generos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else{
            this.etiquetaGoA.setText("Artista");
            AoGlistaString = controlador.getNicknamesArtistas();
            if(!AoGlistaString.isEmpty()){
                DefaultListModel<String> listaArtistas = new DefaultListModel<>();
                for (String artista : AoGlistaString) {
                    listaArtistas.addElement(artista);
                }
                listaAoG.setModel(listaArtistas);
                op=2;
            }else{
                JOptionPane.showMessageDialog(null, "No hay artistas ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_GoAComboBoxActionPerformed

    private void mostrarInfoAlbumBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarInfoAlbumBotonActionPerformed
        // TODO add your handling code here:
        String seleccion = listaAlbumes.getSelectedValue();
        Long idAlbumSeleccionado = null;
        
        for(Entry<Long,String> entry: this.AlbumsLista.entrySet()){
            if(entry.getValue().equals(seleccion)){
                idAlbumSeleccionado = entry.getKey();
                break;
            }
        }
        
        if (!seleccion.equals("") && idAlbumSeleccionado != null) {//SI SE SELECCIONO ALGO
            
            albumAMostrar = this.controlador.ConsultaAlbum(idAlbumSeleccionado);

            //el nombre del album
            this.nombreAlbumtextField.setText(albumAMostrar.getNombreAlbum());

            //el artista
            this.nombreArtistatextField.setText(albumAMostrar.getMiArtista().getNickname());

            //para los generos
            DefaultListModel<String> generosA = new DefaultListModel<>();
            for (DTGenero g : albumAMostrar.getMisgeneros()) {
                generosA.addElement(g.getNombreGenero());
            }
            this.generosAlbum.setModel(generosA);

            //para la imagen
            try {
                
                BufferedImage archivoAlmacenado;
                ImageIcon imagen;

                // Definir el tamaño deseado de la imagen
                int sizeHorizontal = 156;
                int sizeVertical = 163;
                Image escalaImagen;

                // Verificar si tiene foto perfil
                if (albumAMostrar.getFotoAlbum() != null) {

                    // Lee la imagen desde el archivo(ruta)
                    archivoAlmacenado = ImageIO.read(new File(albumAMostrar.getFotoAlbum()));

                    // Verifica si la imagen se ha cargado correctamente
                    if (archivoAlmacenado != null) {

                        // Redimensionar la imagen
                        escalaImagen = archivoAlmacenado.getScaledInstance(sizeHorizontal, sizeVertical, Image.SCALE_SMOOTH);

                        // Convierte archivoAlmacenado a ImageIcon
                        imagen = new ImageIcon(escalaImagen);
                        
                        imageLabel.setIcon(imagen);
                        imageLabel.setVisible(true);
                    }
                } 
                
            } catch (IOException e) {
                e.printStackTrace();
            }

            //TABLA DE TEMAS
            // Definir columnas de la tabla
            String[] columnas = {"Nombre Tema", "Duración (seg)", "Posición en Álbum"};

            // Convertir la lista de temas a formato para JTable
            Object[][] datos = new Object[albumAMostrar.getMisTemas().size()][columnas.length];
            for (int i = 0; i < albumAMostrar.getMisTemas().size(); i++) {
                DTTemaGenerico tema = albumAMostrar.getMisTemas().get(i);
                datos[i][0] = tema.getNombreTema();
                datos[i][1] = tema.getDuracionSegundos();
                datos[i][2] = tema.getPosicionEnAlbum();
            }

            // Crear y configurar modelo de la tabla
            DefaultTableModel modeloTabla;
            modeloTabla = new DefaultTableModel(datos, columnas) {
            };

            // Asignar modelo a la JTable y evitar que se edite
            jTableTemas.setModel(modeloTabla);
            jTableTemas.setDefaultEditor(Object.class, null);
            jTableTemas.getTableHeader().setReorderingAllowed(false);
            
        }
    }//GEN-LAST:event_mostrarInfoAlbumBotonActionPerformed

    private void cargarAlbumesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarAlbumesBotonActionPerformed
        // TODO add your handling code here:
        //inicializarJLists();
        String seleccion = listaAoG.getSelectedValue();
        if(op == 1){//ES GENERO
            AlbumsLista = controlador.getMapAlbumesGenero(seleccion);
            if(!AlbumsLista.isEmpty()){
                DefaultListModel<String> listaAuxAlbums = new DefaultListModel<>();
                for (String album : AlbumsLista.values()) {
                        listaAuxAlbums.addElement(album);
                    }
                listaAlbumes.setModel(listaAuxAlbums);
            }else{
                JOptionPane.showMessageDialog(null, "No hay albumes vinculados al genero seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            AlbumsLista = controlador.getMapAlbumesArtista(seleccion);
            if(!AlbumsLista.isEmpty()){
                DefaultListModel<String> listaAuxAlbums = new DefaultListModel<>();
                for (String album : AlbumsLista.values()) {
                        listaAuxAlbums.addElement(album);
                    }
                listaAlbumes.setModel(listaAuxAlbums);
            }else{
                JOptionPane.showMessageDialog(null, "No hay albumes vinculados al artista seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_cargarAlbumesBotonActionPerformed

    private void jButtonDescargarArchivosDeMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDescargarArchivosDeMusicaActionPerformed
        // TODO add your handling code here:
          // Obtener la fila seleccionada en la tabla
        int fila = jTableTemas.getSelectedRow();
    
        // Comprobar que hay una fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tema de la tabla en caso de tener", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
       
        
        DTTemaGenerico tema = albumAMostrar.getMisTemas().get(fila);
        
        // Comprobar si se encuentra disponible para descargar
        if (tema instanceof DTTemaConRuta temaConRuta) {
            String rutaTema = temaConRuta.getRutaTema();
            if (rutaTema != null && !rutaTema.isEmpty()) {
                // Descargar el archivo de musica desde la ruta
                File archivo = new File(rutaTema);
                if (archivo.exists()) {
                    try {
                        // Logica para copiar el archivo 
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Guardar archivo de música");
                        int userSelection = fileChooser.showSaveDialog(this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File destino = fileChooser.getSelectedFile();
                            Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(this, "Archivo descargado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Ocurrió un error al descargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe en la ruta especificada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El tema seleccionado no se encuentra disponible para descargar", "--", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El tema seleccionado no se encuentra disponible para descargar", "--", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonDescargarArchivosDeMusicaActionPerformed

    private void jButtonVerDireccionWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDireccionWebActionPerformed
        // TODO add your handling code here:
        
        // Obtener la fila seleccionada en la tabla
        int fila = jTableTemas.getSelectedRow();
        
        // Comprobar que hay una fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tema de la tabla en caso de tener", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
      
        //String nombreTema = (String) jTableTemas.getValueAt(fila, 0);
              
        DTTemaGenerico tema = albumAMostrar.getMisTemas().get(fila);
        
        
        //Comprobar si tiene URL
        if (tema instanceof DTTemaConURL temaConURL) {
            System.out.println(((DTTemaConURL) tema).getUrlTema());
            String urlTema = ((DTTemaConURL)temaConURL).getUrlTema();
            if (!urlTema.equals("") ) {
                // JTextArea para mostrar la URL y que se pueda copiar
                JTextArea textArea = new JTextArea(urlTema);
                textArea.setEditable(false); 
                JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "URL del Tema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El tema seleccionado no tiene una URL disponible", "--", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El tema seleccionado no tiene una URL disponible", "--", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVerDireccionWebActionPerformed
    private void inicializarJLists(){
        DefaultListModel modelo = new DefaultListModel<>();
        listaAlbumes.setModel(modelo);
        listaAoG.setModel(modelo);
        generosAlbum.setModel(modelo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EtiquetaConsulta;
    private javax.swing.JLabel EtiquetaConsulta1;
    private javax.swing.JLabel EtiquetaConsulta2;
    private javax.swing.JLabel EtiquetaConsulta3;
    private javax.swing.JComboBox<String> GoAComboBox;
    private javax.swing.JButton cargarAlbumesBoton;
    private javax.swing.JLabel etiquetaAlbumes;
    private javax.swing.JLabel etiquetaGoA;
    private javax.swing.JList<String> generosAlbum;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButtonDescargarArchivosDeMusica;
    private javax.swing.JButton jButtonVerDireccionWeb;
    private javax.swing.JLabel jLabelInformacionDeLosTemas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTemas;
    private javax.swing.JList<String> listaAlbumes;
    private javax.swing.JList<String> listaAoG;
    private javax.swing.JButton mostrarInfoAlbumBoton;
    private javax.swing.JTextField nombreAlbumtextField;
    private javax.swing.JTextField nombreArtistatextField;
    private javax.swing.JPanel panelInfoAlbum;
    // End of variables declaration//GEN-END:variables
}
