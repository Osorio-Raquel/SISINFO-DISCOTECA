package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
import javax.swing.table.DefaultTableModel;

import Modelo.Bebidas;
import Modelo.DatosVentas;
import Modelo.RegaliasD;
import conexionBase.conexionBD;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Regalias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Bebidas> beb = new ArrayList<Bebidas>();
	private ArrayList<RegaliasD> regalo = new ArrayList<RegaliasD>();
	JTable tblBebidas = new JTable();
	JTable tblDetalle = new JTable();

	public Regalias() {
		beb = obtenerDatos();
		
		setTitle("Regalías");
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
		
		JLabel lblTitle = new JLabel("Regalías");
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
		
		tblBebidas.setForeground(new Color(9, 38, 53));
		tblBebidas.setBackground(new Color(217, 236, 233));
		tblBebidas.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Bebida", "Disponibilidad", "Precio" }
			));
		panTable.setViewportView(tblBebidas);

		actualizarTablaBebidas();
		
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
		
		JTextField txtCant = new JTextField();
		txtCant.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panCant.add(txtCant);
		txtCant.setColumns(3);
		
		JPanel panAgregar = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panAgregar.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panAgregar.setBackground(new Color(9, 38, 53));
		panOther.add(panAgregar);
		
		JButton btnAgregar = new JButton("Agregar bebida");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = validard(txtCant.getText());
				if(num > 0) {
					int selectedIndex = tblBebidas.getSelectedRow();
					if (selectedIndex != -1) {
					    actualizarArray(selectedIndex, num);
					    int ide = beb.get(selectedIndex).getId();
					    String nom = beb.get(selectedIndex).getNombre();
					    RegaliasD re = new RegaliasD(ide, nom, num);
					    regalo.add(re);
					    
					    actualizarTablaBebidas();
					    actualizarTablaDetalle();
					} else {
			            JOptionPane.showMessageDialog(null, "Seleccione un producto", "MENSAJE", JOptionPane.WARNING_MESSAGE);

					}
				}
			}
		});
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
		
		tblDetalle.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Bebida", "Cantidad"}
			));
		panTablaDet.setViewportView(tblDetalle);
		
		JPanel panBtn = new JPanel();
		panBtn.setBackground(new Color(9, 38, 53));
		panBtn.setBorder(new EmptyBorder(0, 15, 25, 15));
		contentPane.add(panBtn, BorderLayout.SOUTH);
		panBtn.setLayout(new BorderLayout(10, 0));
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(new Color(9, 38, 53));
		panBtn.add(pan1, BorderLayout.WEST);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrador admin = new Administrador();
				admin .setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(217, 236, 233));
		btnVolver.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnVolver.setBackground(new Color(0, 198, 176));
		pan1.add(btnVolver);
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(9, 38, 53));
		FlowLayout flowLayout = (FlowLayout) pan2.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panBtn.add(pan2, BorderLayout.EAST);
		
		JButton btnRegalar= new JButton("Regalar");
		btnRegalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regalo.size() > 0) {
					actualizarBD();
					JOptionPane.showMessageDialog(null, "Se ha registrado la regalia", "MENSAJE", JOptionPane.INFORMATION_MESSAGE );
		            
					DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalle.getModel();
					modelDetalle.setRowCount(0);
					regalo.clear();
					txtCant.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Ningun producto seleccionado", "MENSAJE", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		btnRegalar.setForeground(new Color(217, 236, 233));
		btnRegalar.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnRegalar.setBackground(new Color(0, 198, 176));
		pan2.add(btnRegalar);
		
		JPanel pan3 = new JPanel();
		pan3.setBackground(new Color(9, 38, 53));
		panBtn.add(pan2, BorderLayout.CENTER);
		
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
	
	public ArrayList<Bebidas> obtenerDatos (){
		ArrayList<Bebidas> inv = new ArrayList<>();
		String consulta = "select pr.ID_Producto, pr.nombre, be.stock, pr.precio\r\n"
				+ "from Producto as pr, Bebidas as be\r\n"
				+ "where pr.ID_Producto = be.ID_ProductoBebida\r\n"
				+ "and be.stock > 0;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				int id = rs.getInt("pr.ID_Producto");
				String nombre = rs.getString("pr.Nombre");
				int cantidad = rs.getInt("be.stock");
                double precio = rs.getDouble("pr.precio");
                Bebidas ped = new Bebidas(id, nombre, cantidad, precio);
                inv.add(ped);
				num++;
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
		return inv;
	}
	
	public void actualizarBD () {
		for(Bebidas be : beb) {
			conexionBD conec= new conexionBD();
			Connection conn= conec.conexion();
			PreparedStatement ps= null;
			ResultSet rs= null;
			try {
				String actualizarFactura = "update Bebidas set stock = " + be.getCantidad() + " where ID_ProductoBebida = " + be.getId() + ";";
				PreparedStatement ps4= null;
				ps4 = conn.prepareStatement(actualizarFactura);
				ps4.executeUpdate();
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
			
		}
	}
	

	public void actualizarTablaBebidas() {
	    // Obtén el modelo de la tabla
	    DefaultTableModel modelBebidas = (DefaultTableModel) tblBebidas.getModel();
	    
	    // Limpia la tabla antes de llenarla
	    modelBebidas.setRowCount(0);
	    
	    // Itera sobre el ArrayList y agrega las filas
	    for (Bebidas bebida : beb) {
	        Object[] fila = {
	            bebida.getNombre(),
	            bebida.getCantidad(),
	            bebida.getPrecio()
	        };
	        System.out.println(bebida.getNombre());
	        System.out.println(bebida.getCantidad());
	        System.out.println(bebida.getPrecio());
	        modelBebidas.addRow(fila); // Añade la fila al modelo
	    }
	}
	
	public void actualizarTablaDetalle() {
	    // Obtén el modelo de la tabla
	    DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalle.getModel();
	    
	    // Limpia la tabla antes de llenarla
	    modelDetalle.setRowCount(0);

	    for (RegaliasD detalle : regalo) {
	        Object[] fila = {
	            detalle.getNombre(), // Nombre de la bebida
	            detalle.getCantidad()      // Cantidad de la bebida
	        };
	        modelDetalle.addRow(fila); // Añade la fila al modelo
	    }
	}

	
	public void actualizarArray (int ind, int cant) {
		if(cant > beb.get(ind).getCantidad()) {
			JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida", "MENSAJE", JOptionPane.WARNING_MESSAGE);
	        return;
		} else {
			int act = beb.get(ind).getCantidad();
			beb.get(ind).setCantidad(act - cant);
		}
	}
	
}

