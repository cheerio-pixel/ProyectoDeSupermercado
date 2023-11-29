package proyectodesupermercado.lib.celleditors;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class SpinnerEditor extends DefaultCellEditor implements TableCellRenderer
{
    JSpinner spinner;
    JSpinner.DefaultEditor editor;
    JTextField textField;
    boolean valueSet;

    public static void setEditorAndRendererOnTable(JTable table, int index, SpinnerEditor editor) {
        TableColumn column = table.getColumn(index);
        column.setCellEditor(editor);
        column.setCellRenderer(editor);
    }

    // Initializes the spinner.
    public SpinnerEditor(JSpinner spinner) {
        super(new JTextField());
        this.spinner = spinner;
        editor = ((JSpinner.DefaultEditor)spinner.getEditor());
        textField = editor.getTextField();
        textField.addFocusListener( new FocusListener() {
            public void focusGained( FocusEvent fe ) {
                //textField.setSelectionStart(0);
                //textField.setSelectionEnd(1);
                SwingUtilities.invokeLater(() -> {
                    if ( valueSet ) {
                        textField.setCaretPosition(1);
                    }
                });
            }
            public void focusLost( FocusEvent fe ) {
            }
        });
        textField.addActionListener(ae -> stopCellEditing());
    }

    // Prepares the spinner component and returns it.
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
    ) {
        if ( !valueSet ) {
            spinner.setValue(value);
        }
        SwingUtilities.invokeLater(() -> textField.requestFocus());
        return spinner;
    }

    public boolean isCellEditable( EventObject eo ) {
        if ( eo instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent)eo;
            textField.setText(String.valueOf(ke.getKeyChar()));
            //textField.select(1,1);
            //textField.setCaretPosition(1);
            //textField.moveCaretPosition(1);
            valueSet = true;
        } else {
            valueSet = false;
        }
        return true;
    }

    // Returns the spinners current value.
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    public boolean stopCellEditing() {
        try {
            editor.commitEdit();
            spinner.commitEdit();
        } catch ( java.text.ParseException e ) {
            JOptionPane.showMessageDialog(null,
                    "Invalid value, discarding.");
        }
        return super.stopCellEditing();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        spinner.setValue(o);
        return spinner;
    }
}