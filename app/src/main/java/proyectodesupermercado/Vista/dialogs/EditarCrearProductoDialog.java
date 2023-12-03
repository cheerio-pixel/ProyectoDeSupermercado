/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectodesupermercado.Vista.dialogs;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.interfaces.DialogSource;
import proyectodesupermercado.Vista.utils.SuplidoresListRenderer;
import proyectodesupermercado.modelo.ProductoRegistro;
import proyectodesupermercado.modelo.Suplidor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.util.List;
import java.util.Optional;

/**
 * @author cheerio-pixel
 */
public class EditarCrearProductoDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditarCrearProductoDialog
     * Si se pasa un productoRegistroParaEditar nulo, este creara en vez de editar
     */
    public EditarCrearProductoDialog(Component parent, boolean modal,
                                     DialogSource<ProductoRegistro> source,
                                     List<Suplidor> suplidorList,
                                     ProductoRegistro productoRegistroParaEditar) {
        super((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, parent), modal);
        setLocationRelativeTo(parent);
        initComponents();
        this.source = source;
        this.productoRegistroParaEditar = productoRegistroParaEditar;

        DefaultComboBoxModel<Suplidor> suplidor = new DefaultComboBoxModel<>(
                new Suplidor[]{null}
        );
        suplidor.addAll(suplidorList);
        suplidoresCombobox.setModel(suplidor);
        suplidoresCombobox.setRenderer(new SuplidoresListRenderer());

        if (productoRegistroParaEditar != null) {
            nombreTextfield.setText(productoRegistroParaEditar.getNombre());
            suplidoresCombobox.setSelectedItem(productoRegistroParaEditar.getSuplidor());
            precioDeVentaSpinner.setValue(productoRegistroParaEditar.getPrecioDeVenta());
        }
    }

    private final DialogSource<ProductoRegistro> source;
    private final ProductoRegistro productoRegistroParaEditar;

    protected void close() {
        setVisible(false);
        dispose();
    }

    protected boolean thereAreInvalidInputs() {
        if (nombreTextfield.getText().isBlank()) {
            ReportInView.warning(guardarButton, "La casilla de nombre no puede estar vacia");
            return true;
        } else if (((double) precioDeVentaSpinner.getValue()) <= 0) {
            ReportInView.warning(guardarButton, "La casilla de precio de venta no puede estar en cero.");
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreTextfield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        suplidoresCombobox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cancelarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        precioDeVentaSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombreTextfield.setColumns(9);

        jLabel1.setText("Nombre");

        jLabel2.setText("Suplidor");

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio de venta");

        precioDeVentaSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 0.1d));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelarButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(guardarButton))
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(suplidoresCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nombreTextfield)
                                        .addComponent(precioDeVentaSpinner))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(9, 9, 9)
                                .addComponent(suplidoresCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(precioDeVentaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelarButton)
                                        .addComponent(guardarButton))
                                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        close();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (thereAreInvalidInputs()) {
            return;
        }
        ProductoRegistro toSend = new ProductoRegistro(
                nombreTextfield.getText(),
                ((Suplidor) suplidoresCombobox.getSelectedItem()),
                ((double) precioDeVentaSpinner.getValue())
        );
        if (productoRegistroParaEditar != null) {
            toSend.setId(productoRegistroParaEditar.getId());
        }
        Optional<String> error = source.accept(toSend);
        if (error.isPresent()) {
            ReportInView.error(this, error.get());
            return;
        }
        close();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nombreTextfield;
    private javax.swing.JSpinner precioDeVentaSpinner;
    private javax.swing.JComboBox<Suplidor> suplidoresCombobox;
    // End of variables declaration//GEN-END:variables
}
