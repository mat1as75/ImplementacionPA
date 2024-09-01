package espotify.presentacion;

import espotify.persistencia.ControladoraPersistencia;
import espotify.presentacion.AltaPerfil;
import espotify.presentacion.ConsultaPerfilCliente;

public class Espotify extends javax.swing.JFrame {

    public Espotify() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuUsuarios = new javax.swing.JMenu();
        jMenuItemRegistrarUsuario = new javax.swing.JMenuItem();
        jMenuItemSeguirUsuario = new javax.swing.JMenuItem();
        jMenuItemlDejarSeguirUsuario = new javax.swing.JMenuItem();
        jMenuItemConsultaPerfilCliente = new javax.swing.JMenuItem();
        jMenuItemConsultaPerfilArtista = new javax.swing.JMenuItem();
        jMenuMultimedia = new javax.swing.JMenu();
        jMenuItemAltaGenero = new javax.swing.JMenuItem();
        jMenuItemAltaAlbum = new javax.swing.JMenuItem();
        jMenuItemCrearListaReproducción = new javax.swing.JMenuItem();
        jMenuItemAgregarTemaLista = new javax.swing.JMenuItem();
        jMenuItemQuitarTemaLista = new javax.swing.JMenuItem();
        jMenuItemPublicarLista = new javax.swing.JMenuItem();
        jMenuItemGuardarTemaListaAlbum = new javax.swing.JMenuItem();
        jMenuItemEliminarTemaListaAlbum = new javax.swing.JMenuItem();
        jMenuItemConsultaAlbum = new javax.swing.JMenuItem();
        jMenuItemConsultaListaReproducción = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar3.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar3.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(1400, 960));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        jMenuUsuarios.setText("Usuario");

        jMenuItemRegistrarUsuario.setText("Alta Perfil");
        jMenuItemRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistrarUsuarioActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemRegistrarUsuario);

        jMenuItemSeguirUsuario.setText("Seguir Usuario");
        jMenuItemSeguirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSeguirUsuarioActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemSeguirUsuario);

        jMenuItemlDejarSeguirUsuario.setText("Dejar de Seguir a Usuario");
        jMenuItemlDejarSeguirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemlDejarSeguirUsuarioActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemlDejarSeguirUsuario);

        jMenuItemConsultaPerfilCliente.setText("Consulta Perfil Cliente");
        jMenuItemConsultaPerfilCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaPerfilClienteActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemConsultaPerfilCliente);

        jMenuItemConsultaPerfilArtista.setText("Consulta Perfil Artista");
        jMenuItemConsultaPerfilArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaPerfilArtistaActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemConsultaPerfilArtista);

        jMenuBar1.add(jMenuUsuarios);

        jMenuMultimedia.setText("Multimedia");

        jMenuItemAltaGenero.setText("Alta Genero");
        jMenuItemAltaGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAltaGeneroActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemAltaGenero);

        jMenuItemAltaAlbum.setText("Alta Álbum");
        jMenuItemAltaAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAltaAlbumActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemAltaAlbum);

        jMenuItemCrearListaReproducción.setText("Crear Lista Reproducción");
        jMenuItemCrearListaReproducción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCrearListaReproducciónActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemCrearListaReproducción);

        jMenuItemAgregarTemaLista.setText("Agregar Tema a Lista");
        jMenuItemAgregarTemaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarTemaListaActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemAgregarTemaLista);

        jMenuItemQuitarTemaLista.setText("Quitar Tema de Lista");
        jMenuItemQuitarTemaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitarTemaListaActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemQuitarTemaLista);

        jMenuItemPublicarLista.setText("Publicar Lista");
        jMenuItemPublicarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPublicarListaActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemPublicarLista);

        jMenuItemGuardarTemaListaAlbum.setText("Guardar Tema/Lista/Album");
        jMenuItemGuardarTemaListaAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarTemaListaAlbumActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemGuardarTemaListaAlbum);

        jMenuItemEliminarTemaListaAlbum.setText("Eliminar Tema/Lista/Album");
        jMenuItemEliminarTemaListaAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarTemaListaAlbumActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemEliminarTemaListaAlbum);

        jMenuItemConsultaAlbum.setText("Consulta Álbum");
        jMenuItemConsultaAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaAlbumActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemConsultaAlbum);

        jMenuItemConsultaListaReproducción.setText("Consulta Lista Reproducción");
        jMenuItemConsultaListaReproducción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaListaReproducciónActionPerformed(evt);
            }
        });
        jMenuMultimedia.add(jMenuItemConsultaListaReproducción);

        jMenuBar1.add(jMenuMultimedia);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegistrarUsuarioActionPerformed
        AltaPerfil ap = new AltaPerfil();
        escritorio.add(ap);
        ap.show();
    }//GEN-LAST:event_jMenuItemRegistrarUsuarioActionPerformed

    private void jMenuItemCrearListaReproducciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrearListaReproducciónActionPerformed
        CrearListaReproduccion clr = new CrearListaReproduccion();
        escritorio.add(clr);
        clr.show();
    }//GEN-LAST:event_jMenuItemCrearListaReproducciónActionPerformed


    private void jMenuItemConsultaPerfilArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultaPerfilArtistaActionPerformed
        ConsultaPerfilArtista cpa = new ConsultaPerfilArtista();
        escritorio.add(cpa);
        cpa.show();
    }//GEN-LAST:event_jMenuItemConsultaPerfilArtistaActionPerformed

    private void jMenuItemlDejarSeguirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemlDejarSeguirUsuarioActionPerformed
        DejarSeguirAUsuario dsa = new DejarSeguirAUsuario();
        escritorio.add(dsa);
        dsa.show();
    }//GEN-LAST:event_jMenuItemlDejarSeguirUsuarioActionPerformed

    private void jMenuItemSeguirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeguirUsuarioActionPerformed
        SeguirUsuario su = new SeguirUsuario();
        escritorio.add(su);
        su.show();
    }//GEN-LAST:event_jMenuItemSeguirUsuarioActionPerformed

    private void jMenuItemQuitarTemaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitarTemaListaActionPerformed
        QuitarTemaDeLista qtl = new QuitarTemaDeLista();
        escritorio.add(qtl);
        qtl.show();
    }//GEN-LAST:event_jMenuItemQuitarTemaListaActionPerformed

    private void jMenuItemAltaAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAltaAlbumActionPerformed
        AltaAlbum aa = new AltaAlbum();
        escritorio.add(aa);
        aa.show();
    }//GEN-LAST:event_jMenuItemAltaAlbumActionPerformed

    private void jMenuItemGuardarTemaListaAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarTemaListaAlbumActionPerformed
        GuardarFavorito gtla = new GuardarFavorito();
        escritorio.add(gtla);
        gtla.show();
    }//GEN-LAST:event_jMenuItemGuardarTemaListaAlbumActionPerformed

    private void jMenuItemPublicarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPublicarListaActionPerformed
        PublicarLista pl = new PublicarLista();
        escritorio.add(pl);
        pl.show();
    }//GEN-LAST:event_jMenuItemPublicarListaActionPerformed

    private void jMenuItemConsultaPerfilClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultaPerfilClienteActionPerformed
        ConsultaPerfilCliente cpc = new ConsultaPerfilCliente();
        escritorio.add(cpc);
        cpc.show();
    }//GEN-LAST:event_jMenuItemConsultaPerfilClienteActionPerformed

    private void jMenuItemEliminarTemaListaAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarTemaListaAlbumActionPerformed
        EliminarFavorito ef = new EliminarFavorito();
        escritorio.add(ef);
        ef.show();
    }//GEN-LAST:event_jMenuItemEliminarTemaListaAlbumActionPerformed

    private void jMenuItemAgregarTemaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarTemaListaActionPerformed
        AgregarTemaALista atl = new AgregarTemaALista();
        escritorio.add(atl);
        atl.show();
    }//GEN-LAST:event_jMenuItemAgregarTemaListaActionPerformed

    private void jMenuItemConsultaAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultaAlbumActionPerformed
        ConsultaAlbum ca = new ConsultaAlbum();
        escritorio.add(ca);
        ca.show();
    }//GEN-LAST:event_jMenuItemConsultaAlbumActionPerformed

    private void jMenuItemConsultaListaReproducciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultaListaReproducciónActionPerformed
        ConsultaListaReproduccion clr = new ConsultaListaReproduccion();
        escritorio.add(clr);
        clr.show();
    }//GEN-LAST:event_jMenuItemConsultaListaReproducciónActionPerformed

    private void jMenuItemAltaGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAltaGeneroActionPerformed
        AltaGenero ag = new AltaGenero();
        escritorio.add(ag);
        ag.show();
    }//GEN-LAST:event_jMenuItemAltaGeneroActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Espotify().setVisible(true);
                
                ControladoraPersistencia controlPersis = new ControladoraPersistencia();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAgregarTemaLista;
    private javax.swing.JMenuItem jMenuItemAltaAlbum;
    private javax.swing.JMenuItem jMenuItemAltaGenero;
    private javax.swing.JMenuItem jMenuItemConsultaAlbum;
    private javax.swing.JMenuItem jMenuItemConsultaListaReproducción;
    private javax.swing.JMenuItem jMenuItemConsultaPerfilArtista;
    private javax.swing.JMenuItem jMenuItemConsultaPerfilCliente;
    private javax.swing.JMenuItem jMenuItemCrearListaReproducción;
    private javax.swing.JMenuItem jMenuItemEliminarTemaListaAlbum;
    private javax.swing.JMenuItem jMenuItemGuardarTemaListaAlbum;
    private javax.swing.JMenuItem jMenuItemPublicarLista;
    private javax.swing.JMenuItem jMenuItemQuitarTemaLista;
    private javax.swing.JMenuItem jMenuItemRegistrarUsuario;
    private javax.swing.JMenuItem jMenuItemSeguirUsuario;
    private javax.swing.JMenuItem jMenuItemlDejarSeguirUsuario;
    private javax.swing.JMenu jMenuMultimedia;
    private javax.swing.JMenu jMenuUsuarios;
    // End of variables declaration//GEN-END:variables
}
