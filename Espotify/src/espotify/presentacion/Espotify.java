package espotify.presentacion;

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
        jMenuAltaAlbun = new javax.swing.JMenu();
        jMenuItemAltaGenero = new javax.swing.JMenuItem();
        jMenuCrearListaReproduccion = new javax.swing.JMenu();
        jMenuItemAgrgarTemaLista = new javax.swing.JMenuItem();
        jMenuItemQuitarTemaLista = new javax.swing.JMenuItem();
        jMenuItemPublicarLista = new javax.swing.JMenuItem();
        AgregarTemaLista = new javax.swing.JMenu();
        jMenuItemCrearListaReproducción = new javax.swing.JMenuItem();
        jMenuItemConsultaListaReproducción = new javax.swing.JMenuItem();
        AgregarTemaLista1 = new javax.swing.JMenu();
        jMenuItemAltaÁlbum = new javax.swing.JMenuItem();
        jMenuItemConsultaÁlbum = new javax.swing.JMenuItem();
        AgregarTemaLista2 = new javax.swing.JMenu();
        jMenuItemCrearListaReproducción2 = new javax.swing.JMenuItem();
        jMenuItemConsultaListaReproducción2 = new javax.swing.JMenuItem();

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

        jMenuItemRegistrarUsuario.setText("Alta de Perfil");
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

        jMenuItemConsultaPerfilCliente.setText("Consulta de Perfil de Cliente");
        jMenuItemConsultaPerfilCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaPerfilClienteActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemConsultaPerfilCliente);

        jMenuItemConsultaPerfilArtista.setText("Consulta de Perfil de Artista");
        jMenuItemConsultaPerfilArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultaPerfilArtistaActionPerformed(evt);
            }
        });
        jMenuUsuarios.add(jMenuItemConsultaPerfilArtista);

        jMenuBar1.add(jMenuUsuarios);

        jMenuAltaAlbun.setText("Genero");

        jMenuItemAltaGenero.setText("Alta Genero");
        jMenuAltaAlbun.add(jMenuItemAltaGenero);

        jMenuBar1.add(jMenuAltaAlbun);

        jMenuCrearListaReproduccion.setText("Lista");

        jMenuItemAgrgarTemaLista.setText("Agrgar Tema a Lista");
        jMenuCrearListaReproduccion.add(jMenuItemAgrgarTemaLista);

        jMenuItemQuitarTemaLista.setText("Quitar Tema de Lista");
        jMenuItemQuitarTemaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitarTemaListaActionPerformed(evt);
            }
        });
        jMenuCrearListaReproduccion.add(jMenuItemQuitarTemaLista);

        jMenuItemPublicarLista.setText("Publicar Lista");
        jMenuItemPublicarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPublicarListaActionPerformed(evt);
            }
        });
        jMenuCrearListaReproduccion.add(jMenuItemPublicarLista);

        jMenuBar1.add(jMenuCrearListaReproduccion);

        AgregarTemaLista.setText("Lista de Reproducción");

        jMenuItemCrearListaReproducción.setText("Crear Lista de Reproducción");
        jMenuItemCrearListaReproducción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCrearListaReproducciónActionPerformed(evt);
            }
        });
        AgregarTemaLista.add(jMenuItemCrearListaReproducción);

        jMenuItemConsultaListaReproducción.setText("Consulta de Lista de Reproducción");
        AgregarTemaLista.add(jMenuItemConsultaListaReproducción);

        jMenuBar1.add(AgregarTemaLista);

        AgregarTemaLista1.setText("Albun");

        jMenuItemAltaÁlbum.setText("Alta de Álbum");
        jMenuItemAltaÁlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAltaÁlbumActionPerformed(evt);
            }
        });
        AgregarTemaLista1.add(jMenuItemAltaÁlbum);

        jMenuItemConsultaÁlbum.setText("Consulta de Álbum");
        AgregarTemaLista1.add(jMenuItemConsultaÁlbum);

        jMenuBar1.add(AgregarTemaLista1);

        AgregarTemaLista2.setText("Tema/Lista/Álbum");

        jMenuItemCrearListaReproducción2.setText("Crear Lista de Reproducción");
        jMenuItemCrearListaReproducción2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCrearListaReproducción2ActionPerformed(evt);
            }
        });
        AgregarTemaLista2.add(jMenuItemCrearListaReproducción2);

        jMenuItemConsultaListaReproducción2.setText("Consulta de Lista de Reproducción");
        AgregarTemaLista2.add(jMenuItemConsultaListaReproducción2);

        jMenuBar1.add(AgregarTemaLista2);

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
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCrearListaReproducciónActionPerformed


    private void jMenuItemConsultaPerfilArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultaPerfilArtistaActionPerformed
        ConsultaPerfilArtista cpa = new ConsultaPerfilArtista();
        escritorio.add(cpa);
        cpa.show();
    }//GEN-LAST:event_jMenuItemConsultaPerfilArtistaActionPerformed

    private void jMenuItemlDejarSeguirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemlDejarSeguirUsuarioActionPerformed

    }//GEN-LAST:event_jMenuItemlDejarSeguirUsuarioActionPerformed

    private void jMenuItemSeguirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeguirUsuarioActionPerformed
        SeguirUsuario su = new SeguirUsuario();
        escritorio.add(su);
        su.show();

    }//GEN-LAST:event_jMenuItemSeguirUsuarioActionPerformed

    private void jMenuItemQuitarTemaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitarTemaListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemQuitarTemaListaActionPerformed

    private void jMenuItemAltaÁlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAltaÁlbumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAltaÁlbumActionPerformed

    private void jMenuItemCrearListaReproducción2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCrearListaReproducción2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCrearListaReproducción2ActionPerformed

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
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Espotify().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AgregarTemaLista;
    private javax.swing.JMenu AgregarTemaLista1;
    private javax.swing.JMenu AgregarTemaLista2;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenuAltaAlbun;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenu jMenuCrearListaReproduccion;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAgrgarTemaLista;
    private javax.swing.JMenuItem jMenuItemAltaGenero;
    private javax.swing.JMenuItem jMenuItemAltaÁlbum;
    private javax.swing.JMenuItem jMenuItemConsultaListaReproducción;
    private javax.swing.JMenuItem jMenuItemConsultaListaReproducción2;
    private javax.swing.JMenuItem jMenuItemConsultaPerfilArtista;
    private javax.swing.JMenuItem jMenuItemConsultaPerfilCliente;
    private javax.swing.JMenuItem jMenuItemConsultaÁlbum;
    private javax.swing.JMenuItem jMenuItemCrearListaReproducción;
    private javax.swing.JMenuItem jMenuItemCrearListaReproducción2;
    private javax.swing.JMenuItem jMenuItemPublicarLista;
    private javax.swing.JMenuItem jMenuItemQuitarTemaLista;
    private javax.swing.JMenuItem jMenuItemRegistrarUsuario;
    private javax.swing.JMenuItem jMenuItemSeguirUsuario;
    private javax.swing.JMenuItem jMenuItemlDejarSeguirUsuario;
    private javax.swing.JMenu jMenuUsuarios;
    // End of variables declaration//GEN-END:variables
}
