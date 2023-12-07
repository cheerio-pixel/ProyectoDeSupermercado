/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectodesupermercado.Vista.paneles;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.TableUtils;
import proyectodesupermercado.Vista.dialogs.MenuAnadirProductosDialog;
import proyectodesupermercado.Vista.dialogs.PendientesStockPuntoDeVentaDialog;
import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.Vista.interfaces.ControlPuntoDeVenta;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.PuntoDeVentaProducto;
import proyectodesupermercado.modelo.PuntoDeVentaStock;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author DELL
 */
public class PanelPuntoDeVenta extends javax.swing.JPanel {

    /**
     * Creates new form PanelPuntoDeVenta
     */
    public PanelPuntoDeVenta(SesionUsuario sesionUsuario,
                             ControlPuntoDeVenta accionesPuntoDeVenta,
                             BuscableEnInventario buscador) {
        initComponents();
        this.sesionUsuario = sesionUsuario;
        this.accionesPuntoDeVenta = accionesPuntoDeVenta;
        this.buscador = buscador;
        refreshTable();
        totalResultLabel.setText("");
    }

    private ObjectTableModel<PuntoDeVentaProducto> mainModel;
    private final BuscableEnInventario buscador;
    private final SesionUsuario sesionUsuario;
    private final ControlPuntoDeVenta accionesPuntoDeVenta;
    private PuntoDeVentaStock stockActual;

    private void setCurrentStock(PuntoDeVentaStock stock) {
        stockActual = stock;
        refreshTable(accionesPuntoDeVenta.pullProductosFrom(stockActual));
    }

    private void refreshTable() {
        refreshTable(new ObjectTableModel<>(
                PuntoDeVentaProducto.class,
                new ArrayList<>()
        ));
    }

    private void refreshTable(ObjectTableModel<PuntoDeVentaProducto> model) {
        TablaVentas.setModel(mainModel = model);
        if (stockActual != null) {
            accionesPuntoDeVenta.getTotal(stockActual)
                    .ifPresent(d -> totalResultLabel.setText(String.valueOf(d)));
        } else {
            totalResultLabel.setText("");
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

        botonVerPendientes = new javax.swing.JButton();
        botonCrearTransaccion = new javax.swing.JButton();
        botonCancelarActual = new javax.swing.JButton();
        botonAnadirProducto = new javax.swing.JButton();
        botonEliminarProducto2 = new javax.swing.JButton();
        botonConfirmarPedido = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        labelPuntodeVenta = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        totalResultLabel = new javax.swing.JLabel();

        botonVerPendientes.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonVerPendientes.setText("Ver Pendientes");
        botonVerPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerPendientesActionPerformed(evt);
            }
        });

        botonCrearTransaccion.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonCrearTransaccion.setText("Crear Transaccion");
        botonCrearTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearTransaccionActionPerformed(evt);
            }
        });

        botonCancelarActual.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonCancelarActual.setText("Cancelar Pedudo");
        botonCancelarActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActualActionPerformed(evt);
            }
        });

        botonAnadirProducto.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonAnadirProducto.setText("Añadir Porducto");
        botonAnadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirProductoActionPerformed(evt);
            }
        });

        botonEliminarProducto2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonEliminarProducto2.setText("Eliminar Producto");
        botonEliminarProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProducto2ActionPerformed(evt);
            }
        });

        botonConfirmarPedido.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        botonConfirmarPedido.setText("Confirmar Pedido");
        botonConfirmarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarPedidoActionPerformed(evt);
            }
        });

        TablaVentas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad", "Impuestos", "Total"
            }
        ));
        jScrollPane2.setViewportView(TablaVentas);

        jScrollPane3.setViewportView(jScrollPane2);

        labelPuntodeVenta.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelPuntodeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPuntodeVenta.setText("Punto de Venta");
        labelPuntodeVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelPuntodeVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        totalLabel.setText("Total");

        totalResultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalResultLabel.setText("TEMPLATE");
        totalResultLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botonVerPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(botonCancelarActual)
                                    .addComponent(botonEliminarProducto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(botonConfirmarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(160, 160, 160)
                                                    .addComponent(totalLabel)
                                                    .addGap(48, 48, 48))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(totalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap())))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(botonCrearTransaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(botonAnadirProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(154, 154, 154)
                                .addComponent(labelPuntodeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonVerPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonCrearTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonAnadirProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminarProducto2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelPuntodeVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(totalLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonConfirmarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCancelarActual, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalResultLabel))
                    .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActualActionPerformed
        if (stockActual != null) {
            accionesPuntoDeVenta.removeStock(stockActual);
            stockActual = null;
        }
        refreshTable();
        totalResultLabel.setText("");
    }//GEN-LAST:event_botonCancelarActualActionPerformed

    private void botonCrearTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearTransaccionActionPerformed
        setCurrentStock(accionesPuntoDeVenta.createStock(
                sesionUsuario.getLoggedUser(),
                Timestamp.from(Instant.now())
        ));
    }//GEN-LAST:event_botonCrearTransaccionActionPerformed

    private void botonVerPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerPendientesActionPerformed
        new PendientesStockPuntoDeVentaDialog(
                TablaVentas, true,
                s -> {
                    setCurrentStock(s);
                    return Optional.empty();
                },
                accionesPuntoDeVenta.pullPendienteStock(sesionUsuario.getLoggedUser())
        ).setVisible(true);
    }//GEN-LAST:event_botonVerPendientesActionPerformed

    private void botonConfirmarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarPedidoActionPerformed
        if (stockActual != null) {
            Optional<String> error = accionesPuntoDeVenta.confirmStock(stockActual);
            if (error.isPresent()) {
                ReportInView.error(this, error.get());
                return;
            }
            refreshTable(new ObjectTableModel<>(PuntoDeVentaProducto.class, new ArrayList<>()));
            totalResultLabel.setText("");
        }
    }//GEN-LAST:event_botonConfirmarPedidoActionPerformed

    private void botonAnadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirProductoActionPerformed
        if (stockActual == null) {
            ReportInView.error(this, "Debe de seleccionar o crear alguna transaccion");
            return;
        }
        new MenuAnadirProductosDialog(
                this, true,
                prod -> {
                    Optional<String> error;
                    // PARANOIA
                    if (stockActual != null) {
                        error = accionesPuntoDeVenta.addProduct(stockActual, prod);
                        refreshTable(accionesPuntoDeVenta.pullProductosFrom(stockActual));
                    } else {
                        // Okay, let's be real cautious
                        error = Optional.of("Debe de seleccionar o crear alguna transaccion");
                    }
                    return error;
                },
                buscador
        ).setVisible(true);
    }//GEN-LAST:event_botonAnadirProductoActionPerformed

    private void botonEliminarProducto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProducto2ActionPerformed
        int index = TableUtils.getSelectedIndex(TablaVentas, "Debe de seleccionar algun producto");
        if (index == -1) {
            return;
        }
        // Cautious
        if (stockActual != null) {
            Optional<String> error = accionesPuntoDeVenta.removeProduct(mainModel.getRow(index));
            if (error.isPresent()) {
                ReportInView.error(this, error.get());
            } else {
                refreshTable(accionesPuntoDeVenta.pullProductosFrom(stockActual));
            }
        }
    }//GEN-LAST:event_botonEliminarProducto2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaVentas;
    private javax.swing.JButton botonAnadirProducto;
    private javax.swing.JButton botonCancelarActual;
    private javax.swing.JButton botonConfirmarPedido;
    private javax.swing.JButton botonCrearTransaccion;
    private javax.swing.JButton botonEliminarProducto2;
    private javax.swing.JButton botonVerPendientes;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelPuntodeVenta;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalResultLabel;
    // End of variables declaration//GEN-END:variables
}
