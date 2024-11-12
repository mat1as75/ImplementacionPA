package espotify.presentacion;

import espotify.DataTypes.DTRegistroAcceso;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ConsultaRegistroAcceso extends javax.swing.JInternalFrame {

    IControlador controlador;
    
    
    
    public ConsultaRegistroAcceso() {
        initComponents();
        
        Fabrica fb = Fabrica.getInstance();
        controlador = fb.getControlador();
        
        // Obtengo los Registros de Acceso al Sitio del Sistema
        ArrayList<DTRegistroAcceso> datosRegistros = controlador.getDTRegistrosAccesoDisponibles();
        
        // Ordeno los Registros & Cargo los Registros
        CargarDatosTabla(OrdenarRegistros(datosRegistros));
        
        // Ajusto el tamanio de las columnas
        AjustarTamanioColumnas();
        
        // Centro el contenido de las celdas
        CentrarContenidoCeldas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTituloRegistroAcceso = new javax.swing.JLabel();
        jScrollPaneRegistroAcceso = new javax.swing.JScrollPane();
        jTableRegistroAcceso = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registros de Actividad");

        jLabelTituloRegistroAcceso.setFont(new java.awt.Font("Liberation Sans", 1, 21)); // NOI18N
        jLabelTituloRegistroAcceso.setText("REGISTRO DE ACCESO AL SITIO");

        jTableRegistroAcceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneRegistroAcceso.setViewportView(jTableRegistroAcceso);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTituloRegistroAcceso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jScrollPaneRegistroAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabelTituloRegistroAcceso)
                .addGap(38, 38, 38)
                .addComponent(jScrollPaneRegistroAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AjustarTamanioColumnas() {
        
        // Ajustar el ancho de las columnas automaticamente al contenido
        for (int i = 0; i < jTableRegistroAcceso.getColumnCount(); i++) {
            int width = 0;
            for (int row = 0; row < jTableRegistroAcceso.getRowCount(); row++) {
                TableCellRenderer renderer = jTableRegistroAcceso.getCellRenderer(row, i);
                Component comp = jTableRegistroAcceso.prepareRenderer(renderer, row, i);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            jTableRegistroAcceso.getColumnModel().getColumn(i).setPreferredWidth(width + 5);
        }
    }
    
    private void CentrarContenidoCeldas() {
        
        // Crear un renderizador para centrar el contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        // Aplicar el renderizador a todas las columnas
        for (int i = 0; i < jTableRegistroAcceso.getColumnCount(); i++) {
            jTableRegistroAcceso.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
    private ArrayList<DTRegistroAcceso> OrdenarRegistros(ArrayList<DTRegistroAcceso> registros) {
        
        // Ordeno lista 
        Collections.sort(
                registros, 
                (DTRegistroAcceso o1, DTRegistroAcceso o2) 
                -> 
                o1.getFechaRegistro().compareTo(o2.getFechaRegistro())
        );
        
        return registros;
    }
    
    private void CargarDatosTabla(ArrayList<DTRegistroAcceso> registros) {
        
        // Nombres de las columnas
        String[] columnas = {"#", "IP", "URL", "BROWSER", "SO"};
        
        // Convertir lista de Registros a Object
        Object[][] datosRegistros = new Object[registros.size()][columnas.length];
        for (int i = 0; i < registros.size(); i++) {
            DTRegistroAcceso registro = registros.get(i);
            datosRegistros[i][0] = registro.getIdRegistro();
            datosRegistros[i][1] = registro.getIpRegistro();
            datosRegistros[i][2] = registro.getUrlRegistro();
            datosRegistros[i][3] = registro.getBrowserRegistro();
            datosRegistros[i][4] = registro.getSoRegistro();
        }
        
        // Crear modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(datosRegistros, columnas);
        
        // Asignar modelo al JTable & Evitar que se edite
        jTableRegistroAcceso.setModel(modelo);
        jTableRegistroAcceso.setDefaultEditor(Object.class, null);
        jTableRegistroAcceso.getTableHeader().setReorderingAllowed(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelTituloRegistroAcceso;
    private javax.swing.JScrollPane jScrollPaneRegistroAcceso;
    private javax.swing.JTable jTableRegistroAcceso;
    // End of variables declaration//GEN-END:variables
}
