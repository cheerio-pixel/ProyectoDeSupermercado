package proyectodesupermercado.Vista.dialogs;

import proyectodesupermercado.Vista.ReportInView;
import proyectodesupermercado.Vista.interfaces.DialogSource;
import proyectodesupermercado.controller.authentication.PasswordFactory;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.modelo.Usuario;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class EditarUsuarioDialog extends EditarCrearUsuarioDialog {
    private final Usuario usuarioParaEditar;

    /**
     * Creates new form EditarCrearUsuarioDialog
     *
     * @param parent
     * @param modal
     * @param source
     * @param passwordFactory
     */
    public EditarUsuarioDialog(JComponent parent, boolean modal,
                               DialogSource<Usuario> source,
                               PasswordFactory passwordFactory,
                               Usuario usuarioParaEditar
    ) {
        super(parent, modal, source, passwordFactory);
        this.usuarioParaEditar = usuarioParaEditar;
        getPasswordLabel().setText("Cambio de contraseña");
        setUserDetailsInForm(usuarioParaEditar);
    }

    private void setUserDetailsInForm(Usuario usuarioSimple) {
        getNombreTextfield().setText(usuarioSimple.getNombre());
        getRolComboBox().setSelectedItem(usuarioSimple.getRol());
    }

    @Override
    protected boolean isInvalidDataInputs() {
        if (super.isInvalidDataInputs()) {
            return true;
        }
        if (getPasswordTextfield().getPassword().length < 8) {
            if (getPasswordTextfield().getPassword().length != 0) {
                ReportInView.error(this, "La contraseña debe de ser al menos de 8 caracteres");
                return true;
            }
        }
        return false;
    }

    @Override
    protected void guardarButtonActionPerformed(ActionEvent evt) {
        if (isInvalidDataInputs()) {
            return;
        }
        usuarioParaEditar.setNombre(getNombreTextfield().getText());
        if (getPasswordTextfield().getPassword().length < 8) {
            if (getPasswordTextfield().getPassword().length != 0) {
                ReportInView.error(this, "La contraseña debe de ser al menos de 8 caracteres");
                return;
            }
            usuarioParaEditar.setPassword(passwordFactory
                    .createPassword(getPasswordTextfield()
                            .getPassword()));
        }
        usuarioParaEditar.setRol(((Rol) getRolComboBox().getSelectedItem()));
        Optional<String> error = source.accept(usuarioParaEditar);
        if (error.isPresent()) {
            ReportInView.error(this, error.get());
            return;
        }
        close();
    }
}
