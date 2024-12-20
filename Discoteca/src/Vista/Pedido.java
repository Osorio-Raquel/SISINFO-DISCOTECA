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

import Modelo.Bebidas;
import conexionBase.conexionBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Pedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProveedor;
	private JTextField txtTel;
	private JTextField txtCant;
	JTable tblBebidas = new JTable();
	ArrayList<String> bebidas = new ArrayList<String>();
	public Pedido() {
		bebidas =  obtenerDatos();
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
		
		
		tblBebidas.setForeground(new Color(9, 38, 53));
		tblBebidas.setBackground(new Color(217, 236, 233));
		tblBebidas.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Bebida"}
			));
		panTable.setViewportView(tblBebidas);
		
		actualizarTablaBebidas();
		
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
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrador admin = new Administrador();
				admin.setVisible(true);
				dispose();
			}
		});
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
		btnPedir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cant = validard(txtCant.getText());
				int numero = validard(txtTel.getText());
				if(cant > 0 && txtProveedor.getText().length() > 0 && numero > 0) {
					int idprov = buscarPRoveedor(txtProveedor.getText());
					if(idprov != -1) {
						idprov = registrarProveedor(txtProveedor.getText());
						int selectedIndex = tblBebidas.getSelectedRow();
						if (selectedIndex != -1) {
							System.out.println("Indice tabla" + selectedIndex);
							int prodID = obtenerProductoID(bebidas.get(selectedIndex));
							actualizarBD(prodID, idprov, cant);
							JOptionPane.showMessageDialog(null, "Se ha realizado el pedido", "MENSAJE", JOptionPane.INFORMATION_MESSAGE );
				            txtTel.setText("");
				            txtProveedor.setText("");
				            txtCant.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "No hay ningun producto seleccionado", "MENSAJE", JOptionPane.WARNING_MESSAGE);
				            
						}
					}
					System.out.println(idprov);
				} else {
					JOptionPane.showMessageDialog(null, "Alguno de los datos no es valido", "MENSAJE", JOptionPane.WARNING_MESSAGE);
		            
				}
			}
		});
		btnPedir.setForeground(new Color(217, 236, 233));
		btnPedir.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnPedir.setBackground(new Color(0, 198, 176));
		panPedir.add(btnPedir);
		
		JPanel pan0 = new JPanel();
		pan0.setBackground(new Color(9, 38, 53));
		panBtn.add(panPedir, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static int validard(String v) {
		int s = 0;
        try {
            s = Integer.parseInt(v);
            if(s > 0){
            	return s;
            } else {
                JOptionPane.showMessageDialog(null, "Debe ser un valor numerico positivo", "MENSAJE", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe ser un valor numerico", "MENSAJE", JOptionPane.WARNING_MESSAGE);
        }
        return s;
	}
	
	public static int buscarPRoveedor(String nombre) {
		int id = -1;
		String consulta = "select ID_Proveedor from Proveedor where nombre = '" + nombre + "';";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("ID_Proveedor");
			}
			System.out.println("Funciona sql");
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return id;
	}
	
	public static int registrarProveedor(String nombre) {
		int id = 0;
		try {

            String query = "insert into Proveedor (Nombre) values ('" + nombre + "');";

            try (Connection conn = conexionBD.conexion();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.executeUpdate();

                System.out.println("PRoveedor registrado");
                id = buscarPRoveedor(nombre);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al agregar bebida a la base de datos.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
        }
		return id;
	}
	
	public static ArrayList<String> obtenerDatos (){
		ArrayList<String> bebidas = new ArrayList<String>();
		String consulta = "select pr.nombre \r\n"
				+ "from Producto as pr, Bebidas as be\r\n"
				+ "where pr.ID_Producto = be.ID_ProductoBebida\r\n;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				String nombre = rs.getString("pr.Nombre");
                bebidas.add(nombre);
			}
			System.out.println("Funciona sql");
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return bebidas;
	}
	
	public void actualizarTablaBebidas() {
	    // Obtén el modelo de la tabla
	    DefaultTableModel modelBebidas = (DefaultTableModel) tblBebidas.getModel();
	    
	    // Limpia la tabla antes de llenarla
	    modelBebidas.setRowCount(0);
	    
	    // Itera sobre el ArrayList y agrega las filas
	    for (String b : bebidas) {
	        Object[] fila = {
	            b
	        };
	        modelBebidas.addRow(fila); // Añade la fila al modelo
	    }
	}
	
	public static int obtenerProductoID (String nombre) {
		int id = 0;
		String consulta = "select ID_Producto from Producto where nombre = '" + nombre + "';";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("ID_Producto");
			}
			System.out.println("Funciona sql");
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return id;
	}
	
	public static void actualizarBD (int prod, int prov, int cant) {
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			String actualizarFactura = "insert into Pedido (ID_ProductoBebida, ID_Proveedor, Cantidad)\r\n"
					+ "values (" + prod + ", " + prov + ", " + cant + ");";
			PreparedStatement ps4= null;
			ps4 = conn.prepareStatement(actualizarFactura);
			ps4.executeUpdate();
			System.out.println("Funciona ps4");
			
			String actualizarStock = "update Bebidas set Stock = Stock + " + cant + " where ID_ProductoBebida = " + prod + ";";
			PreparedStatement ps5= null;
			ps5 = conn.prepareStatement(actualizarStock);
			ps5.executeUpdate();
			System.out.println("Funciona ps5");
		}catch(Exception e) {
			 e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

}

