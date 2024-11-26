package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Relacionador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblMesas;
	
	public Relacionador() {
		setTitle("Relacionador");
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
		
		JLabel lblTitle = new JLabel("Relacionador");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panMM = new JPanel();
		panMM.setBorder(new EmptyBorder(5, 25, 20, 15));
		panMM.setBackground(new Color(9, 38, 53));
		contentPane.add(panMM, BorderLayout.WEST);
		panMM.setLayout(new BorderLayout(0, 0));
		
		JPanel panMesas = new JPanel();
		panMM.add(panMesas, BorderLayout.CENTER);
		panMesas.setLayout(new BorderLayout(0, 0));
		
		JPanel panMesasTitle = new JPanel();
		panMesasTitle.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_3 = (FlowLayout) panMesasTitle.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panMesas.add(panMesasTitle, BorderLayout.NORTH);
		
		JLabel lblMesas = new JLabel("Mesas");
		lblMesas.setForeground(new Color(112, 235, 179));
		lblMesas.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
		panMesasTitle.add(lblMesas);
		
		JScrollPane panTable = new JScrollPane();
		panTable.setBorder(new EmptyBorder(5, 0, 5, 0));
		panTable.setBackground(new Color(9, 38, 53));
		panMesas.add(panTable, BorderLayout.CENTER);
		
		tblMesas = new JTable();
		tblMesas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mesa", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panTable.add(tblMesas);
		
		JPanel panReservar = new JPanel();
		panReservar.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_2 = (FlowLayout) panReservar.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panMesas.add(panReservar, BorderLayout.SOUTH);
		
		JButton btnReservar = new JButton("Reservar mesas");
		btnReservar.setForeground(new Color(217, 236, 233));
		btnReservar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 22));
		btnReservar.setBackground(new Color(0, 198, 176));
		panReservar.add(btnReservar);		
		
		JPanel panManillas = new JPanel();
		panManillas.setBackground(new Color(9, 38, 53));
		panMM.add(panManillas, BorderLayout.SOUTH);
		panManillas.setLayout(new BorderLayout(0, 0));
		
		JPanel panManillasTitle = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panManillasTitle.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panManillasTitle.setBackground(new Color(9, 38, 53));
		panManillas.add(panManillasTitle, BorderLayout.NORTH);
		
		JLabel lblManillas = new JLabel("Manillas");
		lblManillas.setForeground(new Color(112, 235, 179));
		lblManillas.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
		panManillasTitle.add(lblManillas);
		
		JPanel panCant = new JPanel();
		panCant.setBackground(new Color(9, 38, 53));
		panManillas.add(panCant, BorderLayout.CENTER);
		panCant.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblCant = new JLabel("Cantidad:");
		lblCant.setForeground(new Color(158, 200, 185));
		lblCant.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panCant.add(lblCant);
		
		JTextField txtCant = new JTextField();
		txtCant.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panCant.add(txtCant);
		txtCant.setColumns(3);
		
		JPanel panAgregar = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panAgregar.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panAgregar.setBackground(new Color(9, 38, 53));
		panManillas.add(panAgregar, BorderLayout.EAST);
		
		JButton btnAgregar = new JButton("Agregar manilla(s)");
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
				Login l = new Login ();
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
		
		JButton btnEliminar = new JButton("Eliminar concepto");
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

