/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectodesupermercado.Vista.paneles;

import proyectodesupermercado.Vista.dialogs.CreadorDeSolicitud;
import proyectodesupermercado.Vista.dialogs.HistorialSolicitudesDialog;
import proyectodesupermercado.Vista.dialogs.ListaPendientes;
import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.Vista.interfaces.ControlHistorialSolicitud;
import proyectodesupermercado.Vista.interfaces.ControlListaPendientes;
import proyectodesupermercado.Vista.interfaces.ControlManejoSolicitudes;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.Vista.utils.PopupSolicitudesOnDoubleClick;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.SolicitudCompra;
import proyectodesupermercado.modelo.SolicitudCompraProducto;

import javax.swing.SwingUtilities;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author IA
 */
public class ControlSolicitudes extends javax.swing.JPanel {

    /**
     * Creates new form ControlSolicitudes
     */
    public ControlSolicitudes(
            ControlManejoSolicitudes accionesManejoSolicitudes,
            ControlListaPendientes accionesListaPendiente,
            BuscableEnInventario buscadorDeInventario,
            ControlHistorialSolicitud accionesHistorial,
            SesionUsuario sesionUsuario
    ) {
        initComponents();

        this.mainModel = new AtomicReference<>();
        refreshTable(accionesManejoSolicitudes.refreshSolicitudCompra());

        this.accionesManejoSolicitudes = accionesManejoSolicitudes;
        this.accionesListaPendiente = accionesListaPendiente;
        this.buscadorDeInventario = buscadorDeInventario;
        this.sesionUsuario = sesionUsuario;
        this.accionesHistorial = accionesHistorial;
        this.lastOpenedTimeOfNotifications = new AtomicReference<>();

//        this.notificacionCheckbox.setSelected(accionesManejoSolicitudes.areNotificationsActivated());
        pool = Executors.newFixedThreadPool(1,
                r -> {
                    Thread t = Executors.defaultThreadFactory().newThread(r);
                    t.setDaemon(true);
                    return t;
                });

        boolean activatedNotifications = accionesManejoSolicitudes.areNotificationsActivated(sesionUsuario.getLoggedUser());
        if (activatedNotifications) {
            activarButton.setSelected(true);
            limiteSpinner.setValue(accionesManejoSolicitudes.getLimit(sesionUsuario.getLoggedUser())
                    .orElse(20));
        }
        toggleActualizarButton();
        togglePoller();

        mainTable.addMouseListener(
                PopupSolicitudesOnDoubleClick.create(
                        this,
                        mainTable,
                        mainModel,
                        accionesManejoSolicitudes
                )
        );
    }

    private final ExecutorService pool;
    private final SesionUsuario sesionUsuario;
    private final ControlManejoSolicitudes accionesManejoSolicitudes;
    private final ControlListaPendientes accionesListaPendiente;
    private final BuscableEnInventario buscadorDeInventario;
    private final AtomicReference<ObjectTableModel<SolicitudCompra>> mainModel;
    private final ControlHistorialSolicitud accionesHistorial;


    private final AtomicReference<Timestamp> lastOpenedTimeOfNotifications;

    private void refreshTable(ObjectTableModel<SolicitudCompra> model) {
        mainModel.set(model);
        mainTable.setModel(model);
    }

    private CreadorDeSolicitud createNewCreator(List<SolicitudCompraProducto> solicitudCompra) {
        return new CreadorDeSolicitud(
                this, true,
                buscadorDeInventario,
                s -> {
                    Optional<String> error = accionesManejoSolicitudes.creaNuevaSolicitud(s);
                    if (error.isEmpty()) {
                        refreshTable(accionesManejoSolicitudes.refreshSolicitudCompra());
                    }
                    return error;
                },
                solicitudCompra

        );
    }

    private void updateLimit() {
        accionesManejoSolicitudes.activateLimit(
                sesionUsuario.getLoggedUser(),
                activarButton.isSelected(),
                (int) limiteSpinner.getValue()
        );
    }

    private void togglePoller() {
        // TODO: This has to close the thread the moment we log out
//        if (activarButton.isSelected()) {
//            notificationPoller = pool.submit(this::pollNotifications);
//        } else {
//            if (notificationPoller != null) {
//                notificationPoller.cancel(true);
//                notificationPoller = null;
//            }
//        }
    }

    private void toggleActualizarButton() {
        actualizarButton.setEnabled(activarButton.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refrescarButton = new javax.swing.JButton();
        crearSolicitudButton = new javax.swing.JButton();
        abrirHistorialButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        activarButton = new javax.swing.JCheckBox();
        limiteSpinner = new javax.swing.JSpinner();
        actualizarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        abrirNotificacionesButton = new javax.swing.JButton();
        notificacionCheckbox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        setToolTipText("Control de Solicitudes");

        refrescarButton.setText("Refrescar");
        refrescarButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray));
        refrescarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarButtonActionPerformed(evt);
            }
        });

        crearSolicitudButton.setText("<html>Crear solicitud<br>de compra<html>");
        crearSolicitudButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray));
        crearSolicitudButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearSolicitudButtonActionPerformed(evt);
            }
        });

        abrirHistorialButton.setText("Abrir historial");
        abrirHistorialButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray));
        abrirHistorialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirHistorialButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Notificaciones"));

        activarButton.setText("Activar");
        activarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarButtonActionPerformed(evt);
            }
        });

        limiteSpinner.setModel(new javax.swing.SpinnerNumberModel(20, 1, null, 1));

        actualizarButton.setText("Actualizar");
        actualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Limite");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(activarButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(actualizarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(limiteSpinner)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(activarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(3, 3, 3)
                                .addComponent(limiteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actualizarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        abrirNotificacionesButton.setText("Notificaciones");
        abrirNotificacionesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirNotificacionesButtonActionPerformed(evt);
            }
        });

        notificacionCheckbox.setText("Nuevas");
        notificacionCheckbox.setEnabled(false);

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String[]{
                        "Fecha", "Nombre", "Cantidad"
                }
        ));
        jScrollPane1.setViewportView(mainTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(refrescarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(notificacionCheckbox)
                                                        .addComponent(abrirHistorialButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(abrirNotificacionesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(crearSolicitudButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(refrescarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(crearSolicitudButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(abrirHistorialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(abrirNotificacionesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(notificacionCheckbox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void abrirHistorialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirHistorialButtonActionPerformed
        new HistorialSolicitudesDialog(
                this, true, accionesHistorial, accionesManejoSolicitudes
        ).setVisible(true);
    }//GEN-LAST:event_abrirHistorialButtonActionPerformed

    private void crearSolicitudButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearSolicitudButtonActionPerformed
        createNewCreator(new ArrayList<>()).setVisible(true);
    }//GEN-LAST:event_crearSolicitudButtonActionPerformed

    private void abrirNotificacionesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirNotificacionesButtonActionPerformed
        lastOpenedTimeOfNotifications.set(Timestamp.from(Instant.now()));
        new ListaPendientes(
                this, true,
                accionesListaPendiente,
                sesionUsuario,
                notificaciones -> {
                    SwingUtilities.invokeLater(
                            () -> createNewCreator(
                                    notificaciones.stream()
                                            .map(n -> SolicitudCompraProducto.getDefault(
                                                    n.getIdProductoRegistro(), n.getNombreProducto()))
                                            .collect(Collectors.toCollection(ArrayList::new))
                            ).setVisible(true)
                    );
                    return Optional.empty();
                }
        ).setVisible(true);
    }//GEN-LAST:event_abrirNotificacionesButtonActionPerformed

    private void refrescarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarButtonActionPerformed
        refreshTable(accionesManejoSolicitudes.refreshSolicitudCompra());
    }//GEN-LAST:event_refrescarButtonActionPerformed

    private void activarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarButtonActionPerformed
        updateLimit();
        togglePoller();
        toggleActualizarButton();
    }//GEN-LAST:event_activarButtonActionPerformed

    private void actualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarButtonActionPerformed
        updateLimit();
    }//GEN-LAST:event_actualizarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirHistorialButton;
    private javax.swing.JButton abrirNotificacionesButton;
    private javax.swing.JCheckBox activarButton;
    private javax.swing.JButton actualizarButton;
    private javax.swing.JButton crearSolicitudButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner limiteSpinner;
    private javax.swing.JTable mainTable;
    private javax.swing.JCheckBox notificacionCheckbox;
    private javax.swing.JButton refrescarButton;
    // End of variables declaration//GEN-END:variables
}
