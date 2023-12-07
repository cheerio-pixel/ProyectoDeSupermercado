/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectodesupermercado.Vista.dialogs;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.TableUtils;
import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.Vista.interfaces.DialogSource;
import proyectodesupermercado.lib.celleditors.SpinnerEditor;
import proyectodesupermercado.lib.celleditors.SpinnerTableCellRenderer;
import proyectodesupermercado.lib.swingadditions.PlaceholderTextField;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;
import proyectodesupermercado.modelo.SolicitudCompra;
import proyectodesupermercado.modelo.SolicitudCompraProducto;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import java.awt.Component;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author cheerio-pixel
 */
public class CreadorDeSolicitud extends javax.swing.JDialog {

    /**
     * Creates new form CreadorDeSolicitud
     */
    public CreadorDeSolicitud(Component parent, boolean modal,
                              BuscableEnInventario buscador,
                              DialogSource<SolicitudCompra> source,
                              List<SolicitudCompraProducto> defaultList
    ) {
        super((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, parent), modal);
        setLocationRelativeTo(parent);
        initComponents();
        this.buscador = buscador;
        this.source = source;
        refreshSolicitudModel(
                new ObjectTableModel<>(
                        SolicitudCompraProducto.class,
                        defaultList
                )
        );

        refreshInventarioModel(buscador.refreshInitialModel());
        SpinnerEditor numbersEditor = new SpinnerEditor(new JSpinner(new SpinnerNumberModel(1, 1, null, 1)));
        SpinnerTableCellRenderer numbersCellRenderer = new SpinnerTableCellRenderer(new JSpinner(new SpinnerNumberModel(1, 1, null, 1)));
        SpinnerEditor decimalsEditor = new SpinnerEditor(new JSpinner(new SpinnerNumberModel(1.99, 1.0, null, 0.1)));
        SpinnerTableCellRenderer decimalsCellRenderer = new SpinnerTableCellRenderer(new JSpinner(new SpinnerNumberModel(1.99, 1.0, null, 0.1)));
        solicitudTabla.setDefaultEditor(Integer.class, numbersEditor);
        solicitudTabla.setDefaultRenderer(Integer.class, numbersCellRenderer);
        solicitudTabla.setDefaultEditor(Double.class, decimalsEditor);
        solicitudTabla.setDefaultRenderer(Double.class, decimalsCellRenderer);
    }

    private BuscableEnInventario buscador;
    private DialogSource<SolicitudCompra> source;
    private ObjectTableModel<InventarioProducto> inventarioModel;
    private ObjectTableModel<SolicitudCompraProducto> solicitudModel;

    private void refreshSolicitudModel(ObjectTableModel<SolicitudCompraProducto> solicitudModel) {
        solicitudTabla.setModel(this.solicitudModel = solicitudModel);
    }

    private void refreshInventarioModel(ObjectTableModel<InventarioProducto> inventarioModel) {
        inventarioTabla.setModel(
                this.inventarioModel = inventarioModel
        );
    }

    private void doSearch() {
        if (busquedaTextField.getText().isBlank()) {
            refreshInventarioModel(buscador.refreshInitialModel());
        } else {
            refreshInventarioModel(buscador.search(busquedaTextField.getText()));
        }
    }

    private void close() {
        setVisible(false);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        añadirProductoButton = new javax.swing.JButton();
        borrarProductoButton = new javax.swing.JButton();
        enviarButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        solicitudTabla = new javax.swing.JTable();
        busquedaTextField = new PlaceholderTextField().setPlaceholder("Busqueda");
        busquedaButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventarioTabla = new javax.swing.JTable();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        añadirProductoButton.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        añadirProductoButton.setText("Añadir a solicitud");
        añadirProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirProductoButtonActionPerformed(evt);
            }
        });

        borrarProductoButton.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        borrarProductoButton.setText("Borrar producto");
        borrarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarProductoButtonActionPerformed(evt);
            }
        });

        enviarButton.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        enviarButton.setText("Enviar Solicitud");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        solicitudTabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String[]{
                        "Nombre Producto", "Unidades", "Precio por Unidad"
                }
        ));
        jScrollPane2.setViewportView(solicitudTabla);

        busquedaTextField.setColumns(9);
        busquedaTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        busquedaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaTextFieldActionPerformed(evt);
            }
        });

        busquedaButton.setText("Buscar");
        busquedaButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, null, java.awt.Color.gray));
        busquedaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaButtonActionPerformed(evt);
            }
        });

        inventarioTabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null}
                },
                new String[]{
                        "Nombre Producto", "Cantidad en Inventario"
                }
        ));
        jScrollPane1.setViewportView(inventarioTabla);

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                                        .addComponent(busquedaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(enviarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                        .addComponent(añadirProductoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(busquedaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(borrarProductoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(busquedaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(busquedaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(añadirProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(borrarProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void borrarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarProductoButtonActionPerformed
        int index = TableUtils.getSelectedIndex(
                solicitudTabla,
                "Debe de seleccionar algo en la tabla de solicitud."
        );
        if (index == -1) {
            return;
        }
        solicitudModel.removeRow(index);
    }//GEN-LAST:event_borrarProductoButtonActionPerformed

    private void busquedaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaButtonActionPerformed
        doSearch();
    }//GEN-LAST:event_busquedaButtonActionPerformed

    private void busquedaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaTextFieldActionPerformed
        doSearch();
    }//GEN-LAST:event_busquedaTextFieldActionPerformed

    private void añadirProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirProductoButtonActionPerformed
        int index = TableUtils.getSelectedIndex(
                inventarioTabla,
                "Debe de seleccionar algo en la tabla de inventario."
        );
        if (index == -1) {
            return;
        }
        InventarioProducto row = inventarioModel.getRow(index);
        SolicitudCompraProducto otherRow = SolicitudCompraProducto.getDefault(row.getIdProductoRegistro(), row.getNombre());
        if (solicitudModel.contains(otherRow)) {
            ReportInView.warning(this, "Producto ya en solicitud");
        } else {
            solicitudModel.addRow(otherRow);
        }
    }//GEN-LAST:event_añadirProductoButtonActionPerformed

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        Optional<String> error = source.accept(new SolicitudCompra(
                Timestamp.from(Instant.now()),
                solicitudModel.getObjectList()));
        if (error.isPresent()) {
            ReportInView.error(this, error.get());
        } else {
            close();
        }
    }//GEN-LAST:event_enviarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        close();
    }//GEN-LAST:event_cancelarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton añadirProductoButton;
    private javax.swing.JButton borrarProductoButton;
    private javax.swing.JButton busquedaButton;
    private javax.swing.JTextField busquedaTextField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton enviarButton;
    private javax.swing.JTable inventarioTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable solicitudTabla;
    // End of variables declaration//GEN-END:variables
}