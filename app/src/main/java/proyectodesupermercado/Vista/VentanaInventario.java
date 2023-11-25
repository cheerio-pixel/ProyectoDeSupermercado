package proyectodesupermercado.Vista;



import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaInventario {

	private JFrame frmInventario;
	private JTable table;
	private JScrollPane panelTablaInv;
	private DefaultTableModel tableModel;
	private JLabel lblTablaInv;
	private JRadioButton rdbtnLlego, rdbtnNoLlego;
	private JButton btnCambioEst;
	private JLabel lblCambioDeEstado;
	private JLabel lblBuscador;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInventario window = new VentanaInventario();
					window.frmInventario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInventario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInventario = new JFrame();
		frmInventario.setTitle("Inventario");
		frmInventario.setBounds(100, 100, 750, 450);
		frmInventario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventario.getContentPane().setLayout(null);
		
		tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Productos");
        tableModel.addColumn("Estado");
        tableModel.addColumn("Cantidad");
		
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		frmInventario.getContentPane().setLayout(null);;
		
		panelTablaInv = new JScrollPane(table);
		panelTablaInv.setBounds(38, 50, 452, 350);
		panelTablaInv.setColumnHeaderView(table.getTableHeader());
		panelTablaInv.setEnabled(false);
		frmInventario.getContentPane().add(panelTablaInv);
		
		btnCambioEst = new JButton("Cambiar Estado");
		btnCambioEst.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCambioEst.setBounds(550, 179, 140, 30);
		frmInventario.getContentPane().add(btnCambioEst);
		
		rdbtnLlego = new JRadioButton("Llego");
		rdbtnLlego.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnLlego.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnLlego.setBounds(520, 113, 89, 23);
		frmInventario.getContentPane().add(rdbtnLlego);
		
		rdbtnNoLlego = new JRadioButton("No Llego");
		rdbtnNoLlego.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnNoLlego.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNoLlego.setBounds(611, 113, 89, 23);
		frmInventario.getContentPane().add(rdbtnNoLlego);
		
		lblTablaInv = new JLabel("Tabla de Inventario");
		lblTablaInv.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTablaInv.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaInv.setBounds(38, 11, 452, 28);
		Border bordelblInv = BorderFactory.createLineBorder(Color.BLACK);
		lblTablaInv.setBorder(bordelblInv);
		frmInventario.getContentPane().add(lblTablaInv);
		
		lblCambioDeEstado = new JLabel("Cambio de Estado");
		lblCambioDeEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambioDeEstado.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblCambioDeEstado.setBounds(499, 50, 225, 28);
		Border bordelblEst = BorderFactory.createLineBorder(Color.BLACK);
		lblCambioDeEstado.setBorder(bordelblEst);
		frmInventario.getContentPane().add(lblCambioDeEstado);
		
		lblBuscador = new JLabel("Buscador");
		lblBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscador.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblBuscador.setBounds(499, 246, 225, 28);
		frmInventario.getContentPane().add(lblBuscador);
		
		textField = new JTextField();
		textField.setBounds(500, 295, 224, 20);
		frmInventario.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnLlego_1 = new JRadioButton("Llego");
		rdbtnLlego_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnLlego_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnLlego_1.setBounds(520, 332, 89, 23);
		frmInventario.getContentPane().add(rdbtnLlego_1);
		
		JRadioButton rdbtnNoLlego_1 = new JRadioButton("No Llego");
		rdbtnNoLlego_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNoLlego_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnNoLlego_1.setBounds(611, 332, 89, 23);
		frmInventario.getContentPane().add(rdbtnNoLlego_1);
	}
}
