package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.pdf.AcroFields;

import conexionBase.conexionBD;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Relacionador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tblMesas;
	private static JTable tblDetalle;
	
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
		tblMesas.setForeground(new Color(9, 38, 53));
		tblMesas.setBackground(new Color(217, 236, 233));
		tblMesas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Mesa", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panTable.setViewportView(tblMesas);

		actualizarTablaMesas();
		
		JPanel panReservar = new JPanel();
		panReservar.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout_2 = (FlowLayout) panReservar.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panMesas.add(panReservar, BorderLayout.SOUTH);
		
		JButton btnReservar = new JButton("Reservar mesa");
		btnReservar.setForeground(new Color(217, 236, 233));
		btnReservar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 22));
		btnReservar.setBackground(new Color(0, 198, 176));
		panReservar.add(btnReservar);		
		
		btnReservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblMesas.getSelectedRow() != -1){
					int idMesa = (Integer) tblMesas.getValueAt(tblMesas.getSelectedRow(), 0);    
		            double precio = (Double) tblMesas.getValueAt(tblMesas.getSelectedRow(), 1);

					String queryReserva = "INSERT INTO Discoteca.Reserva (ID_Cliente, ID_Mesa, Fecha, Estado) VALUES (?, ?, ?, ?)";
					String queryMesa = "UPDATE Discoteca.Mesa SET Estado = 0 WHERE ID_Mesa = ?";
					String queryIdReserva = "SELECT MAX(ID_Reserva) AS MaxReserva FROM Discoteca.Reserva";
					String queryDetalle = "INSERT INTO Discoteca.DetalleFactura (ID_Factura, ID_Reserva, ID_Producto, Cantidad, Subtotal) " +
		                               "VALUES (?, ?, ?, ?, ?)";

					try{
						Connection conn = conexionBD.conexion();

						// Insertar reserva
						PreparedStatement stmt = conn.prepareStatement(queryReserva);

						stmt.setInt(1, 0);  
						stmt.setInt(2, idMesa); 
						stmt.setDate(3, new java.sql.Date(new Date().getTime()));
						stmt.setInt(4, 1); 
	
						stmt.executeUpdate();
						
						// Actualizar estado de la reserva
						stmt = conn.prepareStatement(queryMesa);

						stmt.setInt(1, idMesa);  
	
						stmt.executeUpdate();
						actualizarTablaMesas();

						// Obtener el ID de la Reserva
						stmt = conn.prepareStatement(queryIdReserva);
						ResultSet rs = stmt.executeQuery();
						
						int idReserva = 0;
						if(rs.next()){
							idReserva = rs.getInt("MaxReserva");
						}
						stmt.executeQuery();

						// Insertar Detalle Factura
						stmt = conn.prepareStatement(queryDetalle);

						stmt.setInt(1, -1);  
						stmt.setInt(2, idReserva); 
						stmt.setInt(3, 1); 
						stmt.setInt(4, 1); 
						stmt.setDouble(5, precio); 
	
						stmt.executeUpdate();

						actualizarTablaDetalle();

					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al agregar a la base de datos.");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Seleccione una fila.");
				}
			}
			
		});

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

		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int cantidad = Integer.parseInt(txtCant.getText().trim());
					if (cantidad < 1) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.");
						return;
					}

					String queryManilla = "SELECT m.ID_ProductoManilla, m.Precio "
										+ "FROM Discoteca.Manilla AS m "
										+ "WHERE m.ID_ProductoManilla";

					String queryDetalle = "INSERT INTO Discoteca.DetalleFactura (ID_Factura, ID_Reserva, ID_Producto, Cantidad, Subtotal) " +
									"VALUES (?, ?, ?, ?, ?)";

					String queryActualizarStock = "UPDATE Discoteca.Manilla SET Stock = Stock - ? WHERE ID_ProductoManilla = ?";

					try{
						Connection conn = conexionBD.conexion();
						Statement stmt = conn.createStatement();

						//Obtener datos de la manilla
						ResultSet rs = stmt.executeQuery(queryManilla);
						double precio = 0;
						int idManilla = 0;
						if(rs.next()){
							idManilla = rs.getInt("ID_ProductoManilla");
							precio = rs.getDouble("Precio");
						}

						//Calcular subtotal
						double subtotal = cantidad * precio;

						//Insertar DetalleFactura
						PreparedStatement pstmt = conn.prepareStatement(queryDetalle);
						pstmt.setInt(1, -1);  
						pstmt.setInt(2, 0); 
						pstmt.setInt(3, 3);
						pstmt.setInt(4, cantidad);   
						pstmt.setDouble(5, subtotal); 

						pstmt.executeUpdate();

						actualizarTablaDetalle();

						//Actualizar stock
						pstmt = conn.prepareStatement(queryActualizarStock);
						pstmt.setInt(1, cantidad);  
						pstmt.setInt(2, idManilla);  

						pstmt.executeUpdate();
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al agregar bebida a la base de datos.");
					}
				}catch(NumberFormatException er){
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
				}
			}
			
		});
		
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
		
		tblDetalle = new JTable();
		tblDetalle.setForeground(new Color(9, 38, 53));
		tblDetalle.setBackground(new Color(217, 236, 233));
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
		
		panTablaDet.setViewportView(tblDetalle);
		
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

		btnSalir.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String query = "DELETE FROM Discoteca.DetalleFactura WHERE ID_Factura = -1";
		        Connection conn = null;
		        java.sql.Statement stmt = null;

		        try {
		            conn = conexionBD.conexion();
		            stmt = conn.createStatement();

		            int rowsAffected = stmt.executeUpdate(query);

		            System.out.println(rowsAffected + " registros eliminados con ID_Factura = -1.");
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al eliminar los registros.");
		        } finally {

		            try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }

		        Login l = new Login();
		        l.setVisible(true);    
		        dispose();           
		    }
		});
		
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

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tblDetalle.getSelectedRow() != -1){
					String concepto = (String) tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 0); 
					
					// En caso de que sea una mesa
					if(concepto.equals("Mesa")){

						try{
							Connection conn = conexionBD.conexion();

							// Obtener el id de la mesa a eliminar
							String queryIdMesa = "SELECT m.ID_Mesa FROM Discoteca.Mesa m WHERE estado = 0";
							PreparedStatement stmt = conn.prepareStatement(queryIdMesa);
							ResultSet rs = stmt.executeQuery();
							
							int idMesa = 0;
							if(rs.next()){
								idMesa = rs.getInt("ID_Mesa");
							}
							stmt.executeQuery();

							// Poner la mesa como disponible
							String queryMesa = "UPDATE Discoteca.Mesa SET Estado = 1 WHERE ID_Mesa = ?";
							stmt = conn.prepareStatement(queryMesa);

							stmt.setInt(1, idMesa);  
		
							stmt.executeUpdate();
							actualizarTablaMesas();

							// Obtener idReserva
							String queryIdReserva = "SELECT m.ID_Reserva FROM Discoteca.Reserva m WHERE ID_Mesa = ? AND estado = 1";
							stmt = conn.prepareStatement(queryIdReserva);
							stmt.setInt(1, idMesa);
							rs = stmt.executeQuery();
							
							int idReserva = 0;
							if(rs.next()){
								idReserva = rs.getInt("ID_Reserva");
							}
							stmt.executeQuery();

							// Eliminar el detalle
							String queryDetalle = "DELETE FROM Discoteca.DetalleFactura WHERE ID_Reserva = ?";
							stmt = conn.prepareStatement(queryDetalle);
							stmt.setInt(1, idReserva);

							stmt.executeUpdate();

							actualizarTablaDetalle();

							} catch (SQLException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al agregar a la base de datos.");
						}
					}else{
						int cantidad = (Integer) tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 1); 

						try{
							Connection conn = conexionBD.conexion();

							// Obtener el id de las manillas
							String queryIdManilla = "SELECT MAX(ID_ProductoManilla)AS MaxManilla FROM Discoteca.Manilla";
							PreparedStatement stmt = conn.prepareStatement(queryIdManilla);
							ResultSet rs = stmt.executeQuery();
							
							int MaxManilla = 0;
							if(rs.next()){
								MaxManilla = rs.getInt("MaxManilla");
							}
							stmt.executeQuery();

							// Actualizar stock
							String queryStock = "UPDATE Discoteca.Manilla SET Stock = Stock + ? WHERE ID_ProductoManilla = ?";
							stmt = conn.prepareStatement(queryStock);

							stmt.setInt(1, cantidad);  
							stmt.setInt(2, MaxManilla);  
		
							stmt.executeUpdate();

							// Eliminar el detalle
							String queryDetalle = "DELETE FROM Discoteca.DetalleFactura WHERE ID_Factura = ? AND ID_Producto = 3";
							stmt = conn.prepareStatement(queryDetalle);
							stmt.setInt(1, -1);

							stmt.executeUpdate();

							actualizarTablaDetalle();

						} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al agregar a la base de datos.");
						}
					}
				}
			}
			
		});
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setForeground(new Color(217, 236, 233));
		btnFacturar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnFacturar.setBackground(new Color(0, 198, 176));
		pan2.add(btnFacturar);
		
		btnFacturar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Connection conn = null;
		        java.sql.Statement stmt = null;

	            int nuevoIdFactura = -1; 

		        try {
		            conn = conexionBD.conexion();
		            stmt = conn.createStatement();

		            String queryUltimoId = "SELECT MAX(ID_Factura) FROM Factura";
		            ResultSet rs = stmt.executeQuery(queryUltimoId);

		            if (rs.next()) {
		                nuevoIdFactura = rs.getInt(1) + 1; 
		            }

		            String queryActualizar = "UPDATE Discoteca.DetalleFactura SET ID_Factura = ? WHERE ID_Factura = -1";
		            PreparedStatement pstmt = conn.prepareStatement(queryActualizar);
		            pstmt.setInt(1, nuevoIdFactura); 

		            int rowsAffected = pstmt.executeUpdate();

		            System.out.println(rowsAffected + " registros actualizados con el nuevo ID_Factura: " + nuevoIdFactura);


		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al generar la factura.");
		        } finally {
		            try {
		                if (stmt != null) stmt.close();
		                if (conn != null) conn.close();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        Factura l = new Factura(1);
		        l.setVisible(true);    
		        dispose();  
		        }
		    }
		});


		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(9, 38, 53));
		panBtn.add(pan2, BorderLayout.CENTER);
		
		setVisible(true);
	}

	private static void actualizarTablaMesas() {
	    if (tblMesas == null) {
	        System.err.println("Error: tblMesas no ha sido inicializada.");
	        return;
	    }
		
		DefaultTableModel model = (DefaultTableModel) tblMesas.getModel();
		model.setRowCount(0);

		String query = "SELECT m.ID_MESA, m.Costo " +
		                   "FROM Discoteca.Mesa AS m " +
						   "WHERE Estado = 1";

		conexionBD conec = new conexionBD();
		try (Connection conn = conec.conexion();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

				while (rs.next()) {
					int idMesa = rs.getInt("ID_Mesa");     
					double costo = rs.getDouble("Costo"); 
	
					model.addRow(new Object[]{idMesa, costo});
				}

	        tblMesas.revalidate(); 
	        tblMesas.repaint();  

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar la tabla.");
	    }
	}

	private static void actualizarTablaDetalle() {
	    if (tblDetalle == null) {
	        System.err.println("Error: tblDetalle no ha sido inicializada.");
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tblDetalle.getModel();
	    model.setRowCount(0); 

	    String query = "SELECT p.Nombre, df.ID_Reserva, df.Cantidad, df.Subtotal " +
	                   "FROM Discoteca.DetalleFactura df, Discoteca.Producto p " +
	                   "WHERE df.ID_Factura = -1 AND p.ID_Producto = df.ID_Producto";
	    conexionBD conec= new conexionBD();

	    try (Connection conn = conec.conexion();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            String nombreProducto = rs.getString("Nombre");
				int idReserva = rs.getInt("ID_Reserva");
	            int cantidad = rs.getInt("Cantidad");
	            double subtotal = rs.getDouble("Subtotal");

				if(idReserva == 0){
					model.addRow(new Object[]{nombreProducto, cantidad, subtotal});
				}else{
					model.addRow(new Object[]{"Mesa" , cantidad, subtotal});
				}
	        }

	        tblDetalle.revalidate(); 
	        tblDetalle.repaint();  
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar la tabla.");
	    }
	}
}

