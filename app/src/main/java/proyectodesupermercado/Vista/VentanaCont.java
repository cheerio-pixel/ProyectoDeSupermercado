package proyectodesupermercado.Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;

public class VentanaCont {

	private JFrame frmConta;
	private JTable tableFinan;
	private JScrollPane scrollPaneFinan, scrollPaneBuscar;
	private JMenuBar menuBar;
	private JMenu MenuSi, MenuNo;
	private JMenuItem mntmNewMenuItem;
	private DefaultTableModel tableModel, tableModelBuscar;
	private JTable tableBuscar;
	private JTextField textField, textField_1;
	private JButton btnAnadir, btnMostrar;
	private JLabel labelNewLabel, lblBuscar;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCont window = new VentanaCont();
					window.frmConta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaCont() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConta = new JFrame();
		frmConta.setBounds(100, 100, 650, 550);
		frmConta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConta.getContentPane().setLayout(null);		
		
		tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Productos");
        tableModel.addColumn("Estado");
        tableModel.addColumn("Cantidad");
		
		tableFinan = new JTable(tableModel);
		tableFinan.setBounds(321, 55, 194, 165);
		frmConta.getContentPane().add(tableFinan);
		
		scrollPaneFinan = new JScrollPane(tableFinan);
		scrollPaneFinan.setBounds(363, 107, 261, 274);
		scrollPaneFinan.setColumnHeaderView(tableFinan.getTableHeader());
		scrollPaneFinan.setEnabled(false);
		frmConta.getContentPane().add(scrollPaneFinan);
		
		
		tableModelBuscar = new DefaultTableModel();
		tableModelBuscar.addColumn("ID");
		tableModelBuscar.addColumn("Productos");
		tableModelBuscar.addColumn("Estado");
		tableModelBuscar.addColumn("Cantidad");
		
		scrollPaneBuscar = new JScrollPane(tableFinan);
		
		tableBuscar = new JTable(tableModelBuscar);
		scrollPaneFinan.setViewportView(tableBuscar);
		scrollPaneBuscar.setBounds(34, 78, 305, 305);
		scrollPaneBuscar.setColumnHeaderView(tableFinan.getTableHeader());
		scrollPaneBuscar.setEnabled(false);
		frmConta.getContentPane().add(scrollPaneBuscar);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(34, 389, 305, 20);
		frmConta.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(363, 76, 261, 20);
		frmConta.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.setBounds(363, 392, 118, 23);
		frmConta.getContentPane().add(btnAnadir);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(506, 392, 118, 23);
		frmConta.getContentPane().add(btnMostrar);
		
		labelNewLabel = new JLabel("Finanzas");
		labelNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		labelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelNewLabel.setBounds(34, 44, 305, 23);
		Border bordelblFin = BorderFactory.createLineBorder(Color.BLACK);
		labelNewLabel.setBorder(bordelblFin);
		frmConta.getContentPane().add(labelNewLabel);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblBuscar.setBounds(363, 44, 261, 23);
		Border bordelblBus = BorderFactory.createLineBorder(Color.BLACK);
		lblBuscar.setBorder(bordelblBus);
		frmConta.getContentPane().add(lblBuscar);
		
		menuBar = new JMenuBar();
		frmConta.setJMenuBar(menuBar);
		
		MenuSi = new JMenu("Si");
		menuBar.add(MenuSi);
		
		mntmNewMenuItem = new JMenuItem("New menu item");
		MenuSi.add(mntmNewMenuItem);
		
		MenuNo = new JMenu("Nel");
		menuBar.add(MenuNo);
	}
}
