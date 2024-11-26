package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class Pedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtProveedor;
	private JTextField txtTel;
	private JTextField txtCant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedido frame = new Pedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pedido() {
		setTitle("Pedido");
		setResizable(false);
		setBounds(350, 150, 1280, 720);
		setBackground(new Color(9, 38, 53));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 38, 53));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle = new JPanel();
		panTitle.setBorder(new EmptyBorder(15, 0, 25, 0));
		panTitle.setBackground(new Color(9, 38, 53));
		contentPane.add(panTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Pedidos");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panBebidas = new JPanel();
		panBebidas.setBorder(new EmptyBorder(0, 25, 0, 0));
		panBebidas.setBackground(new Color(9, 38, 53));
		contentPane.add(panBebidas, BorderLayout.WEST);
		panBebidas.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBebidas = new JLabel("Bebidas");
		lblBebidas.setForeground(new Color(112, 235, 179));	
		lblBebidas.setFont(new Font("Tw Cen MT", Font.BOLD, 27));
		panBebidas.add(lblBebidas, BorderLayout.NORTH);
		
		JScrollPane panTable = new JScrollPane();
		panTable.setBorder(new EmptyBorder(5, 0, 25, 0));
		panTable.setBackground(new Color(9, 38, 53));
		panBebidas.add(panTable, BorderLayout.CENTER);
		
		JTable tblBebidas = new JTable();
		tblBebidas.setForeground(new Color(9, 38, 53));
		tblBebidas.setBackground(new Color(217, 236, 233));
		tblBebidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bebida"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panTable.add(tblBebidas);
		
		JPanel panDatos = new JPanel();
		panDatos.setBorder(new EmptyBorder(0, 150, 0, 0));
		panDatos.setBackground(new Color(9, 38, 53));
		contentPane.add(panDatos, BorderLayout.CENTER);
		panDatos.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel pan1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pan1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pan1.setBackground(new Color(9, 38, 53));
		panDatos.add(pan1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(158, 200, 185));
		lblNombre.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		pan1.add(lblNombre);
		
		txtNombre = new JTextField();
		pan1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel pan2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pan2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pan2.setBackground(new Color(9, 38, 53));
		panDatos.add(pan2);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setForeground(new Color(158, 200, 185));
		lblProveedor.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		pan2.add(lblProveedor);
		
		txtProveedor = new JTextField();
		pan2.add(txtProveedor);
		txtProveedor.setColumns(30);
		
		JPanel pan3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pan3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pan3.setBackground(new Color(9, 38, 53));
		panDatos.add(pan3);
		
		JLabel lblTel = new JLabel("Teléfono del proveedor:");
		lblTel.setForeground(new Color(158, 200, 185));
		lblTel.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		pan3.add(lblTel);
		
		txtTel = new JTextField();
		pan3.add(txtTel);
		txtTel.setColumns(30);
		
		JPanel pan4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pan4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pan4.setBackground(new Color(9, 38, 53));
		panDatos.add(pan4);
		
		JLabel lblCant = new JLabel("Cantidad:");
		lblCant.setForeground(new Color(158, 200, 185));
		lblCant.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		pan4.add(lblCant);
		
		txtCant = new JTextField();
		pan4.add(txtCant);
		txtCant.setColumns(5);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(new Color(9, 38, 53));
		panBtn.setBorder(new EmptyBorder(0, 15, 25, 15));
		contentPane.add(panBtn, BorderLayout.SOUTH);
		panBtn.setLayout(new BorderLayout(10, 0));
		
		JPanel panVolver = new JPanel();
		panVolver.setBackground(new Color(9, 38, 53));
		panBtn.add(panVolver, BorderLayout.WEST);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(217, 236, 233));
		btnVolver.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnVolver.setBackground(new Color(0, 198, 176));
		panVolver.add(btnVolver);
		
		JPanel panPedir = new JPanel();
		panPedir.setBackground(new Color(9, 38, 53));
		FlowLayout fl_panPedir = (FlowLayout) panPedir.getLayout();
		fl_panPedir.setHgap(15);
		fl_panPedir.setAlignment(FlowLayout.RIGHT);
		panBtn.add(panPedir, BorderLayout.EAST);
		
		JButton btnPedir = new JButton("Pedir");
		btnPedir.setForeground(new Color(217, 236, 233));
		btnPedir.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnPedir.setBackground(new Color(0, 198, 176));
		panPedir.add(btnPedir);
		
		JPanel pan0 = new JPanel();
		pan0.setBackground(new Color(9, 38, 53));
		panBtn.add(panPedir, BorderLayout.CENTER);
		
		setVisible(true);
	}

}

