package proyectodesupermercado.Vista.paneles;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.TableUtils;
import proyectodesupermercado.Vista.interfaces.ControlInventario;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Optional;

/**
 * @author cheerio-pixel
 */
public class ProductosFueraDeServicio extends javax.swing.JPanel {

    /**
     * Creates new form ProductasFueraDeServicio
     */
    public ProductosFueraDeServicio(ControlInventario accionesInventario) {
        initComponents();
        this.accionesInventario = accionesInventario;
        refreshTable(accionesInventario.refreshInitialModel());
    }

    private ObjectTableModel<InventarioProducto> mainModel;

    private void refreshTable(ObjectTableModel<InventarioProducto> model) {
        inventarioTabla.setModel(mainModel = model);
    }

    private final ControlInventario accionesInventario;

    private void doUpdate() {
        int index = TableUtils.getSelectedIndex(inventarioTabla, "Debe de seleccionar una tabla");
        if (index == -1) {
            return;
        }
        InventarioProducto row = mainModel.getRow(index);
        row.setCantidad((int) cantidadSpinner.getValue());

        Optional<String> error = accionesInventario.editProduct(row);
        if (error.isPresent()) {
            ReportInView.error(this, error.get());
        } else {
            doSearch();
        }
    }

    private void doSearch() {
        if (busquedaTextfield.getText().isBlank()) {
            refreshTable(accionesInventario.refreshInitialModel());
        } else {
            refreshTable(accionesInventario.search(busquedaTextfield.getText()));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        inventarioTabla = new javax.swing.JTable();
        launchProductButton = new javax.swing.JButton();
        cantidadSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        busquedaTextfield = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();

        inventarioTabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(inventarioTabla);

        launchProductButton.setText("Lanzar Producto");
        launchProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchProductButtonActionPerformed(evt);
            }
        });

        cantidadSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel1.setText("Cantidad");

        busquedaTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaTextfieldActionPerformed(evt);
            }
        });

        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(launchProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cantidadSpinner)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(busquedaTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buscarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(busquedaTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buscarButton))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cantidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(launchProductButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void busquedaTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaTextfieldActionPerformed
        doSearch();
    }//GEN-LAST:event_busquedaTextfieldActionPerformed

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        doSearch();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void launchProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchProductButtonActionPerformed
        doUpdate();
    }//GEN-LAST:event_launchProductButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField busquedaTextfield;
    private javax.swing.JSpinner cantidadSpinner;
    private javax.swing.JTable inventarioTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton launchProductButton;
    // End of variables declaration//GEN-END:variables
}
