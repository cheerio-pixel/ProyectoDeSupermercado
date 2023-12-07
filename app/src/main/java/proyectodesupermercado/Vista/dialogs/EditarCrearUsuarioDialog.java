/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectodesupermercado.Vista.dialogs;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.interfaces.DialogSource;
import proyectodesupermercado.controller.authentication.PasswordFactory;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.modelo.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author cheerio-pixel
 */
public abstract class EditarCrearUsuarioDialog extends javax.swing.JDialog {

    protected DialogSource<Usuario> source;
    protected PasswordFactory passwordFactory;

    /**
     * Creates new form EditarCrearUsuarioDialog
     */
    public EditarCrearUsuarioDialog(JComponent parent, boolean modal,
                                    DialogSource<Usuario> source, PasswordFactory passwordFactory) {
        super((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, parent), modal);
        setLocationRelativeTo(parent);
        initComponents();
        rolComboBox.setModel(new DefaultComboBoxModel<>(Rol.values()));

        this.source = source;
        this.passwordFactory = passwordFactory;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardarButton = new javax.swing.JButton();
        rolComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nombreTextfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cancelarButton = new javax.swing.JButton();
        passwordTextfield = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Rol");

        jLabel3.setText("Nombre");

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        passwordTextfield.setColumns(9);

        passwordLabel.setText("Cambio de contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(107, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(guardarButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cancelarButton))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(passwordTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2)
                                                .addComponent(nombreTextfield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                                .addComponent(rolComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 219, Short.MAX_VALUE)
                                                .addComponent(passwordLabel)))
                                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(33, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelarButton)
                                        .addComponent(guardarButton))
                                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected abstract void guardarButtonActionPerformed(java.awt.event.ActionEvent evt);

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        close();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    protected void close() {
        setVisible(false);
        dispose();
    }

    /**
     * Chequea si las entradas de usuario son validas
     *
     * @return Verdadero si no son validas, falso de lo contrario
     */
    protected boolean isInvalidDataInputs() {
        if (getNombreTextfield().getText().isBlank()) {
            ReportInView.warning(this, "El nombre no puede estar vacio");
            return true;
        }
        if (getRolComboBox().getSelectedItem() == null) {
            ReportInView.warning(this, "Debe de seleccionar un rol");
            return true;
        }
        return false;
    }

    protected JLabel getPasswordLabel() {
        return passwordLabel;
    }

    protected JPasswordField getPasswordTextfield() {
        return passwordTextfield;
    }

    protected JComboBox<Rol> getRolComboBox() {
        return rolComboBox;
    }

    protected JTextField getNombreTextfield() {
        return nombreTextfield;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nombreTextfield;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextfield;
    private javax.swing.JComboBox<Rol> rolComboBox;
    // End of variables declaration//GEN-END:variables
}
