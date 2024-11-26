package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Factura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNit;
	private JTextField txtRazon;
	private JTextField txtMonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factura frame = new Factura();
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
	public Factura() {
		setTitle("Factura");
		setResizable(false);
		setBounds(350, 150, 1280, 720);
		setBackground(new Color(9, 38, 53));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 38, 53));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle = new JPanel();
		panTitle.setBorder(new EmptyBorder(25, 0, 25, 0));
		panTitle.setBackground(new Color(9, 38, 53));
		contentPane.add(panTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("FACTURA");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panCont = new JPanel();
		panCont.setBackground(new Color(9, 38, 53));
		contentPane.add(panCont, BorderLayout.CENTER);
		panCont.setLayout(new BoxLayout(panCont, BoxLayout.Y_AXIS));
		
		JPanel panDatos = new JPanel();
		panDatos.setBorder(new EmptyBorder(0, 25, 0, 25));
		panDatos.setBackground(new Color(9, 38, 53));
		panCont.add(panDatos);
		panDatos.setLayout(new GridLayout(2, 1, 0, 15));
		
		JPanel pan11 = new JPanel();
		pan11.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_1 = (FlowLayout) pan11.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panDatos.add(pan11);
		
		JPanel panNit = new JPanel();
		panNit.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_0 = (FlowLayout) pan11.getLayout();
		flowLayout_0.setAlignment(FlowLayout.LEFT);
		pan11.add(panNit);
		
		JLabel lblNit = new JLabel("NIT:");
		lblNit.setForeground(new Color(158, 200, 185));
		lblNit.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panNit.add(lblNit);
		
		txtNit = new JTextField();
		txtNit.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panNit.add(txtNit);
		txtNit.setColumns(10);
		
		JPanel panRazon = new JPanel();
		panRazon.setBorder(new EmptyBorder(0, 50, 0, 0));
		panRazon.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_2 = (FlowLayout) pan11.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pan11.add(panRazon);
		
		JLabel lblRazon = new JLabel("Razón Social / Nombre:");
		lblRazon.setForeground(new Color(158, 200, 185));
		lblRazon.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panRazon.add(lblRazon);
		
		txtRazon = new JTextField();
		txtRazon.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panRazon.add(txtRazon);
		txtRazon.setColumns(40);
		
		JPanel pan21 = new JPanel();
		pan21.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_3 = (FlowLayout) pan21.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panDatos.add(pan21);
		
		JRadioButton rdbtnEfectivo = new JRadioButton("Efectivo");
		rdbtnEfectivo.setForeground(new Color(255, 255, 255));
		rdbtnEfectivo.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		rdbtnEfectivo.setBackground(new Color(9, 38, 53));
		pan21.add(rdbtnEfectivo);
		
		JRadioButton rdbtnTarjeta = new JRadioButton("Tarjeta");
		rdbtnTarjeta.setForeground(new Color(255, 255, 255));
		rdbtnTarjeta.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		rdbtnTarjeta.setBackground(new Color(9, 38, 53));
		pan21.add(rdbtnTarjeta);
		
		ButtonGroup g = new ButtonGroup();
		g.add(rdbtnEfectivo);
		g.add(rdbtnTarjeta);
		
		JPanel panMonto = new JPanel();
		panMonto.setBorder(new EmptyBorder(0, 50, 0, 0));
		panMonto.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_4 = (FlowLayout) panMonto.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pan21.add(panMonto);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setForeground(new Color(158, 200, 185));
		lblMonto.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panMonto.add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panMonto.add(txtMonto);
		txtMonto.setColumns(5);
		
		JPanel panDetalle = new JPanel();
		panDetalle.setBorder(new EmptyBorder(5, 25, 20, 25));
		panDetalle.setBackground(new Color(9, 38, 53));
		panCont.add(panDetalle);
		
		JPanel panDetTitle = new JPanel();
		panDetTitle.setBackground(new Color(9, 38, 53));
		panDetalle.setLayout(new BorderLayout(0, 0));
		FlowLayout fl_panDetTitle = (FlowLayout) panDetTitle.getLayout();
		panDetalle.add(panDetTitle, BorderLayout.NORTH);
		
		JLabel lblDetalle = new JLabel("Detalle Venta");
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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(217, 236, 233));
		btnCancelar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnCancelar.setBackground(new Color(0, 198, 176));
		pan1.add(btnCancelar);
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout = (FlowLayout) pan2.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panBtn.add(pan2, BorderLayout.EAST);
		
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

