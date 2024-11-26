package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bartender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblBebidas;
	private JTextField txtCant;
	private JTable tblDetalle;
	
	public Bartender() {
		setTitle("Bartender");
		setResizable(false);
		setBounds(350, 150, 1280, 720);
		setBackground(new Color(9, 38, 53));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 38, 53));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle = new JPanel();
		panTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
		panTitle.setBackground(new Color(9, 38, 53));
		contentPane.add(panTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Bartender");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panMenu = new JPanel();
		panMenu.setBorder(new EmptyBorder(5, 25, 20, 15));
		panMenu.setBackground(new Color(9, 38, 53));
		contentPane.add(panMenu, BorderLayout.WEST);
		panMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panBebidasTitle = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panBebidasTitle.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panBebidasTitle.setBackground(new Color(9, 38, 53));
		panMenu.add(panBebidasTitle, BorderLayout.NORTH);
		
		JLabel lblBebidas = new JLabel("Bebidas");
		lblBebidas.setForeground(new Color(112, 235, 179));
		lblBebidas.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
		panBebidasTitle.add(lblBebidas);
		
		JScrollPane panTable = new JScrollPane();
		panTable.setBorder(new EmptyBorder(5, 0, 25, 0));
		panTable.setBackground(new Color(9, 38, 53));
		panMenu.add(panTable, BorderLayout.CENTER);
		
		tblBebidas = new JTable();
		tblBebidas.setForeground(new Color(9, 38, 53));
		tblBebidas.setBackground(new Color(217, 236, 233));
		tblBebidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bebida", "Disponibilidad", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panTable.add(tblBebidas);
		
		JPanel panOther = new JPanel();
		panOther.setBackground(new Color(9, 38, 53));
		panMenu.add(panOther, BorderLayout.SOUTH);
		panOther.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panCant = new JPanel();
		panCant.setBackground(new Color(9, 38, 53));
		panOther.add(panCant);
		panCant.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblCant = new JLabel("Cantidad:");
		lblCant.setForeground(new Color(158, 200, 185));
		lblCant.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panCant.add(lblCant);
		
		txtCant = new JTextField();
		txtCant.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panCant.add(txtCant);
		txtCant.setColumns(3);
		
		JPanel panAgregar = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panAgregar.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panAgregar.setBackground(new Color(9, 38, 53));
		panOther.add(panAgregar);
		
		JButton btnAgregar = new JButton("Agregar bebida");
		btnAgregar.setForeground(new Color(217, 236, 233));
		btnAgregar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 22));
		btnAgregar.setBackground(new Color(0, 198, 176));
		panAgregar.add(btnAgregar);
		
		JPanel panDetalle = new JPanel();
		panDetalle.setBorder(new EmptyBorder(5, 15, 20, 25));
		panDetalle.setBackground(new Color(9, 38, 53));
		contentPane.add(panDetalle);
		
		JPanel panDetTitle = new JPanel();
		panDetTitle.setBackground(new Color(9, 38, 53));
		panDetalle.setLayout(new BorderLayout(0, 0));
		FlowLayout fl_panDetTitle = (FlowLayout) panDetTitle.getLayout();
		fl_panDetTitle.setAlignment(FlowLayout.LEFT);
		panDetalle.add(panDetTitle, BorderLayout.NORTH);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setForeground(new Color(112, 235, 179));
		lblDetalle.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
		panDetTitle.add(lblDetalle);
		
		JScrollPane panTablaDet = new JScrollPane();
		panTablaDet.setBackground(new Color(9, 38, 53));
		panDetalle.add(panTablaDet, BorderLayout.CENTER);
		
		JTable tblDetalle = new JTable();
		tblDetalle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Concepto", "Cantidad", "Costo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panTablaDet.add(tblDetalle);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(new Color(9, 38, 53));
		panBtn.setBorder(new EmptyBorder(0, 15, 25, 15));
		contentPane.add(panBtn, BorderLayout.SOUTH);
		panBtn.setLayout(new BorderLayout(10, 0));
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(new Color(9, 38, 53));
		panBtn.add(pan1, BorderLayout.WEST);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnSalir.setForeground(new Color(217, 236, 233));
		btnSalir.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnSalir.setBackground(new Color(0, 198, 176));
		pan1.add(btnSalir);
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout = (FlowLayout) pan2.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panBtn.add(pan2, BorderLayout.EAST);
		
		JButton btnEliminar = new JButton("Eliminar bebida");
		btnEliminar.setForeground(new Color(217, 236, 233));
		btnEliminar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnEliminar.setBackground(new Color(0, 198, 176));
		pan2.add(btnEliminar);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setForeground(new Color(217, 236, 233));
		btnFacturar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnFacturar.setBackground(new Color(0, 198, 176));
		pan2.add(btnFacturar);
		
		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(9, 38, 53));
		panBtn.add(pan2, BorderLayout.CENTER);
		
		setVisible(true);
	}

}