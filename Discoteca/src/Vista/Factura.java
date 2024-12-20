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

import Modelo.EnviarEmailFactura;
import Modelo.FacturaEnPDF;
import conexionBase.conexionBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Factura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNit;
	private JTextField txtRazon;
	private JTextField txtMonto;
	private JTextField txtCorreo;
	
	public Factura(int volv) {
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
		
		JLabel lblMonto_1 = new JLabel("Correo:");
		lblMonto_1.setForeground(new Color(158, 200, 185));
		lblMonto_1.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		pan21.add(lblMonto_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		txtCorreo.setColumns(40);
		pan21.add(txtCorreo);
		
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
		
		JTable tblDetalle = new JTable();
		tblDetalle.setModel(new DefaultTableModel(
		    new Object[][] {},
		    new String[] {"Concepto", "Cantidad", "Costo"}
		) {
		    Class[] columnTypes = new Class[] {
		        String.class, Integer.class, Double.class
		    };
		    public Class getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }
		});

		JScrollPane panTablaDet = new JScrollPane();
		panTablaDet.setBackground(new Color(9, 38, 53));
		panDetalle.add(panTablaDet, BorderLayout.CENTER);
		panTablaDet.setViewportView(tblDetalle);
		JLabel lblCostoTotal = new JLabel("Costo Total: $0.00");
		lblCostoTotal.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
		lblCostoTotal.setForeground(new Color(112, 235, 179));
		lblCostoTotal.setHorizontalAlignment(SwingConstants.RIGHT);

		DefaultTableModel model = (DefaultTableModel) tblDetalle.getModel();
		conexionBD conec= new conexionBD();
		model.setRowCount(0); 
		if(volv == 1) {
			String queryIdMax = "SELECT MAX(ID_Factura) AS MaxFactura FROM DetalleFactura";
	
			String queryDetalles = "SELECT df.ID_Producto, p.Nombre, df.Cantidad, df.Subtotal " +
			                       "FROM DetalleFactura df " +
			                       "JOIN Producto p ON df.ID_Producto = p.ID_Producto " +
			                       "WHERE df.ID_Factura = ?";
			
	
			try (Connection conn = conec.conexion();
			     Statement stmt = conn.createStatement()) {
	
			    ResultSet rsMax = stmt.executeQuery(queryIdMax);
			    int maxIdFactura = -1;
			    if (rsMax.next()) {
			        maxIdFactura = rsMax.getInt("MaxFactura");
			    }
	
			    if (maxIdFactura == -1) {
			        JOptionPane.showMessageDialog(null, "No hay facturas en la base de datos.");
			        return;
			    }
			    double costoTotal = 0.0; 
	
	
			    try (PreparedStatement pstmt = conn.prepareStatement(queryDetalles)) {
			        pstmt.setInt(1, maxIdFactura); 
			        ResultSet rsDetalles = pstmt.executeQuery();
	
			        while (rsDetalles.next()) {
			            String concepto = rsDetalles.getString("Nombre");
			            int cantidad = rsDetalles.getInt("Cantidad");
			            double costo = rsDetalles.getDouble("Subtotal");
			            costoTotal += costo; 
	
			            model.addRow(new Object[]{concepto, cantidad, costo});
			        }
			    }
			    lblCostoTotal.setText(String.format("Costo Total: $%.2f", costoTotal));
	
			    tblDetalle.revalidate();
			    tblDetalle.repaint();
	
			} catch (SQLException ex) {
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error al cargar los detalles de la factura.");
			}
		} else {
			String queryIdMax = "SELECT MAX(ID_Factura) AS MaxFactura FROM DetalleFactura";
			
			String queryDetalles = "SELECT df.ID_Producto, p.Nombre, df.Cantidad, df.Subtotal " +
			                       "FROM DetalleFactura df " +
			                       "JOIN Producto p ON df.ID_Producto = p.ID_Producto " +
			                       "WHERE df.ID_Factura = ? " + 
			                       "and p.ID_Producto = 3";
			
	
			try (Connection conn = conec.conexion();
			     Statement stmt = conn.createStatement()) {
	
			    ResultSet rsMax = stmt.executeQuery(queryIdMax);
			    int maxIdFactura = -1;
			    if (rsMax.next()) {
			        maxIdFactura = rsMax.getInt("MaxFactura");
			    }
	
			    if (maxIdFactura == -1) {
			        JOptionPane.showMessageDialog(null, "No hay facturas en la base de datos.");
			        return;
			    }
			    double costoTotal = 0.0; 
	
	
			    try (PreparedStatement pstmt = conn.prepareStatement(queryDetalles)) {
			        pstmt.setInt(1, maxIdFactura); 
			        ResultSet rsDetalles = pstmt.executeQuery();
	
			        while (rsDetalles.next()) {
			            String concepto = rsDetalles.getString("Nombre");
			            int cantidad = rsDetalles.getInt("Cantidad");
			            double costo = rsDetalles.getDouble("Subtotal");
			            costoTotal += costo; 
	
			            model.addRow(new Object[]{concepto, cantidad, costo});
			        }
			    }
			    
			    String queryDetallesMesas = "select me.ID_Mesa, me.Costo, dt.Cantidad\r\n"
						+ "from Mesa as me, DetalleFactura as dt, Reserva as re\r\n"
						+ "where re.ID_Reserva = dt.ID_Reserva\r\n"
						+ "and re.ID_Mesa = me.ID_Mesa\r\n"
						+ "and dt.ID_Factura = ?;";
			    
			    try (PreparedStatement pstmt = conn.prepareStatement(queryDetallesMesas)) {
			        pstmt.setInt(1, maxIdFactura); 
			        ResultSet rsDetalles = pstmt.executeQuery();
	
			        while (rsDetalles.next()) {
			            String concepto = "Mesa " + rsDetalles.getInt("ID_Mesa");
			            int cantidad = rsDetalles.getInt("Cantidad");
			            double costo = rsDetalles.getDouble("Costo");
			            costoTotal += costo; 
	
			            model.addRow(new Object[]{concepto, cantidad, costo});
			        }
			    }
			    lblCostoTotal.setText(String.format("Costo Total: $%.2f", costoTotal));
	
			    tblDetalle.revalidate();
			    tblDetalle.repaint();
	
			} catch (SQLException ex) {
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error al cargar los detalles de la factura.");
			}
		}

		panDetalle.add(lblCostoTotal, BorderLayout.SOUTH); 
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
		btnFacturar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        double monto = 0;
		        try {
		        	String montoStr = txtMonto.getText().replace(",", ".");
		            monto = Double.parseDouble(montoStr);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Por favor ingrese un monto válido.");
		            return;
		        }

		        double costoTotal = Double.parseDouble(lblCostoTotal.getText().replace("Costo Total: $", "").replace(",", ".").trim());

		        if (monto < costoTotal) {
		            JOptionPane.showMessageDialog(null, "El monto debe ser mayor o igual al costo total.");
		            return;
		        }

		        double cambio = monto - costoTotal;

		        String insertFacturaQuery = "INSERT INTO Factura (ID_Factura, Fecha, ID_cliente, Total) VALUES (?, ?, ?, ?)";
		        String selectMaxIdQuery = "SELECT MAX(ID_Factura) AS MaxFactura FROM Factura";

		        try (Connection conn = conec.conexion()) {

		            Statement stmt = conn.createStatement();
		            ResultSet rsMax = stmt.executeQuery(selectMaxIdQuery);
		            int nuevoIdFactura = 1; 
		            if (rsMax.next()) {
		                nuevoIdFactura = rsMax.getInt("MaxFactura") + 1;  
		            }

		            try (PreparedStatement pstmt = conn.prepareStatement(insertFacturaQuery)) {
		                pstmt.setInt(1, nuevoIdFactura);  
		                pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));  
		                pstmt.setInt(3, Integer.parseInt(txtNit.getText())); 
		                pstmt.setDouble(4, costoTotal); 

		                int rowsAffected = pstmt.executeUpdate();
		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(null, "Factura registrada exitosamente.");

		                    String insertDetalleQuery = "INSERT INTO DetalleFactura (ID_Factura, ID_Producto, ID_Reserva, Cantidad, Subtotal) VALUES (?, ?, ?, ?, ?)";
		                    try (PreparedStatement pstmtDetalle = conn.prepareStatement(insertDetalleQuery)) {
		                        for (int i = 0; i < model.getRowCount(); i++) {
		                            String concepto = (String) model.getValueAt(i, 0);
		                            int cantidad = (Integer) model.getValueAt(i, 1);
		                            double subtotal = (Double) model.getValueAt(i, 2);  

		                            String selectProductoIdQuery = "SELECT ID_Producto FROM Producto WHERE Nombre = ?";
		                            try (PreparedStatement pstmtProducto = conn.prepareStatement(selectProductoIdQuery)) {
		                                pstmtProducto.setString(1, concepto);
		                                ResultSet rsProducto = pstmtProducto.executeQuery();
		                                if (rsProducto.next()) {
		                                    int idProducto = rsProducto.getInt("ID_Producto");

		                                    double totalDetalle = cantidad * subtotal;
		                                }
		                            }
		                        }
		                    }

		                    JOptionPane.showMessageDialog(null, String.format("Factura registrada.\nCambio: $%.2f", cambio));
		                    String queryIdMax = "SELECT MAX(ID_Factura) AS MaxFactura FROM DetalleFactura";
		            		conexionBD conec= new conexionBD();

		            		
		                    if (volv == 1) {
		                    	try (Connection connec = conec.conexion();
			            		     Statement stmt1 = connec.createStatement()) {

			            		    ResultSet rsMax1 = stmt1.executeQuery(queryIdMax);
			            		    int maxIdFactura = -1;
			            		    if (rsMax1.next()) {
			            		        maxIdFactura = rsMax.getInt("MaxFactura");
			            		    }
			            		    maxIdFactura++;
			            		    System.out.println("ID de la factura" + maxIdFactura);
			            		    
			            		    FacturaEnPDF fac = new FacturaEnPDF(maxIdFactura, txtNit.getText(), txtRazon.getText());
			            		    fac.GenerarReporte(Double.parseDouble(txtMonto.getText()), 1);
			            		    EnviarEmailFactura env = new EnviarEmailFactura(txtCorreo.getText());
			            		    env.EnviarCorreo();

			            		} catch (SQLException ex) {
			            		    ex.printStackTrace();
			            		    JOptionPane.showMessageDialog(null, "No se puedo generar la factura.");
			            		}
		                        Bartender bar = new Bartender();
		                        bar.setVisible(true);
		                        dispose();
		                    } else {
		                    	try (Connection connec = conec.conexion();
			            		     Statement stmt1 = connec.createStatement()) {

			            		    ResultSet rsMax1 = stmt1.executeQuery(queryIdMax);
			            		    int maxIdFactura = -1;
			            		    if (rsMax1.next()) {
			            		        maxIdFactura = rsMax.getInt("MaxFactura");
			            		    }
			            		    maxIdFactura++;
			            		    System.out.println("ID de la factura" + maxIdFactura);
			            		    FacturaEnPDF fac = new FacturaEnPDF(maxIdFactura, txtNit.getText(), txtRazon.getText());
			            		    fac.GenerarReporte(Double.parseDouble(txtMonto.getText()), 2);
			            		    EnviarEmailFactura env = new EnviarEmailFactura(txtCorreo.getText());
			            		    env.EnviarCorreo();

			            		} catch (SQLException ex) {
			            		    ex.printStackTrace();
			            		    JOptionPane.showMessageDialog(null, "No se puedo generar la factura.");
			            		}
		                        Relacionador rel = new Relacionador();
		                        rel.setVisible(true);
		                        dispose();
		                    }
		                } else {
		           
		                }
		            }

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, String.format("Factura registrada.\nCambio: $%.2f", cambio));
		            Bartender l = new Bartender();
			        l.setVisible(true);    
			        dispose();
		        }
		    }
		});

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

