package proyectodesupermercado.Vista.dialogs;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.interfaces.DialogSource;
import proyectodesupermercado.controller.authentication.PasswordFactory;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.modelo.Usuario;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class CrearUsuarioDialog extends EditarCrearUsuarioDialog {

    /**
     * Creates new form EditarCrearUsuarioDialog
     *
     * @param parent
     * @param modal
     * @param source
     * @param passwordFactory
     */
    public CrearUsuarioDialog(JComponent parent, boolean modal, DialogSource<Usuario> source, PasswordFactory passwordFactory) {
        super(parent, modal, source, passwordFactory);
        getPasswordLabel().setText("Contraseña");
    }

    @Override
    protected boolean isInvalidDataInputs() {
        if (super.isInvalidDataInputs()) {
            return true;
        }
        if (getPasswordTextfield().getPassword().length < 8) {
            ReportInView.error(this, "La constraseña debe de tener al menos 8 caracteres");
            return true;
        }
        return false;
    }

    @Override
    protected void guardarButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        if (isInvalidDataInputs()) {
            return;
        }
        Usuario usuarioSimple = new Usuario(
                getNombreTextfield().getText(),
                passwordFactory.createPassword(getPasswordTextfield().getPassword()),
                ((Rol) getRolComboBox().getSelectedItem())
        );
        Optional<String> error = source.accept(usuarioSimple);
        if (error.isPresent()) {
            ReportInView.error(this, error.get());
            return;
        }
        close();
    }//GEN-LAST:event_guardarButtonActionPerformed
}
