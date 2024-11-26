package Vista;

import java.awt.BorderLayout;
import java.sql.Statement;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexionBase.conexionBD;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Bartender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblBebidas;
	private JTextField txtCant;
	private static JTable tblDetalle;
	
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

		JTable tblBebidas = new JTable();
		tblBebidas.setForeground(new Color(9, 38, 53));
		tblBebidas.setBackground(new Color(217, 236, 233));
		tblBebidas.setModel(new DefaultTableModel(
		    new Object[][] {},
		    new String[] {
		        "ID Producto", "Bebida", "Disponibilidad", "Precio"
		    }
		) {
		    Class[] columnTypes = new Class[] {
		        Integer.class, String.class, Integer.class, Double.class
		    };
		    public Class getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }
		});

		panTable.setViewportView(tblBebidas);  

		try {
		    String query = "SELECT pr.ID_Producto, pr.Nombre, b.stock, pr.precio " +
		                   "FROM Discoteca.Producto AS pr " +
		                   "JOIN Discoteca.Bebidas AS b ON b.ID_ProductoBebida = pr.ID_Producto " +
		                   "where pr.ID_Producto != 3";

		    conexionBD conec = new conexionBD();
		    Connection conn = conec.conexion();
		    java.sql.Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(query);

		    DefaultTableModel model = (DefaultTableModel) tblBebidas.getModel();

		    while (rs.next()) {
		        int idProducto = rs.getInt("ID_Producto");   
		        String nombreBebida = rs.getString("Nombre"); 
		        int disponibilidad = rs.getInt("stock");     
		        double precio = rs.getDouble("pr.precio"); 

		        model.addRow(new Object[]{idProducto, nombreBebida, disponibilidad, precio});
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		tblBebidas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && tblBebidas.getSelectedRow() != -1) {
		            int selectedRow = tblBebidas.getSelectedRow();

		            int idProductoSeleccionado = (Integer) tblBebidas.getValueAt(selectedRow, 0); 

		            String bebidaSeleccionada = (String) tblBebidas.getValueAt(selectedRow, 1); 
		            int disponibilidadSeleccionada = (Integer) tblBebidas.getValueAt(selectedRow, 2); 
		            double precioSeleccionado = (Double) tblBebidas.getValueAt(selectedRow, 3);  

		            int idProducto = idProductoSeleccionado;
		            String bebida = bebidaSeleccionada;
		            int disponibilidad = disponibilidadSeleccionada;
		            double precio = precioSeleccionado;

		            System.out.println("ID del producto seleccionado: " + idProducto);
		            System.out.println("Bebida seleccionada: " + bebida);
		            System.out.println("Disponibilidad: " + disponibilidad);
		            System.out.println("Precio: " + precio);
		        }
		    }
		});

		
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
		
		btnAgregar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (tblBebidas.getSelectedRow() != -1) {
		            int idProducto = (Integer) tblBebidas.getValueAt(tblBebidas.getSelectedRow(), 0); 
		            String bebida = (String) tblBebidas.getValueAt(tblBebidas.getSelectedRow(), 1);    
		            double precio = (Double) tblBebidas.getValueAt(tblBebidas.getSelectedRow(), 3);    

		            try {
		                int cantidad = Integer.parseInt(txtCant.getText().trim());  
		                if (cantidad <= 0) {
		                    JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
		                    return;
		                }

		                double subtotal = cantidad * precio;
		                int idReserva = 0; 

		                String query = "INSERT INTO Discoteca.DetalleFactura (ID_Factura, ID_Reserva, ID_Producto, Cantidad, Subtotal) " +
		                               "VALUES (?, ?, ?, ?, ?)";
		                String queryActualizarStock = "UPDATE Discoteca.Bebidas SET Stock = Stock - ? WHERE ID_ProductoBebida = ?";

		                try (Connection conn = conexionBD.conexion();
		                     PreparedStatement stmt = conn.prepareStatement(query)) {

		                    stmt.setInt(1, -1);  
		                    stmt.setInt(2, idReserva); 
		                    stmt.setInt(3, idProducto);
		                    stmt.setInt(4, cantidad);   
		                    stmt.setDouble(5, subtotal); 

		                    stmt.executeUpdate();
		                    try (PreparedStatement stmtStock = conn.prepareStatement(queryActualizarStock)) {
		                        stmtStock.setInt(1, cantidad); 
		                        stmtStock.setInt(2, idProducto); 
		                        stmtStock.executeUpdate();
		                    }

		                    txtCant.setText("");
		                    
		                    actualizarTablaDetalle();
		                    		 
		                    JOptionPane.showMessageDialog(null, "Bebida agregada a la factura.");
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error al agregar bebida a la base de datos.");
		                }

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione una bebida primero.");
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

		tblDetalle = new JTable();
		tblDetalle.setModel(new DefaultTableModel(
		    new Object[][] {},
		    new String[] { "Concepto", "Cantidad", "Costo" } 
		) {
		    Class[] columnTypes = new Class[] {
		        String.class, Integer.class, Double.class
		    };

		    @Override
		    public Class getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }
		});

		JScrollPane panTablaDet = new JScrollPane(tblDetalle);
		panTablaDet.setBackground(new Color(9, 38, 53));

		panDetalle.add(panTablaDet, BorderLayout.CENTER);

		
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
	private static void actualizarTablaDetalle() {
	    if (tblDetalle == null) {
	        System.err.println("Error: tblDetalle no ha sido inicializada.");
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tblDetalle.getModel();
	    model.setRowCount(0); 

	    String query = "SELECT df.ID_Producto, p.Nombre, df.Cantidad, df.Subtotal " +
	                   "FROM Discoteca.DetalleFactura df " +
	                   "JOIN Discoteca.Producto p ON df.ID_Producto = p.ID_Producto " +
	                   "WHERE df.ID_Factura = -1";
	    conexionBD conec= new conexionBD();

	    try (Connection conn = conec.conexion();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            String nombreProducto = rs.getString("Nombre");
	            int cantidad = rs.getInt("Cantidad");
	            double subtotal = rs.getDouble("Subtotal");

	            model.addRow(new Object[]{nombreProducto, cantidad, subtotal});
	        }

	        tblDetalle.revalidate(); 
	        tblDetalle.repaint();  

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar la tabla.");
	    }
	}

	}
