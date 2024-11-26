package Modelo;

import conexionBase.conexionBD;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class desplegarPorCategoria {
	public DefaultTableModel datos(int categoria, String[] columnas, String palabra) {
		DefaultTableModel model = new DefaultTableModel(null, columnas);
		
		String consulta= "SELECT productos.ID_producto, productos.nombre, productos.precio_venta "
				+ "from productos, pedidosReporte "
				+ "WHERE productos.categoria = "+categoria+" "
				+ "AND productos.ID_producto = pedidosReporte.ID_producto "
				+ "AND pedidosReporte.stock > 0.0 "
				+ "AND pedidosReporte.Estado = true "
				+ "AND (productos.ID_producto LIKE '%"+ palabra+"%' OR productos.nombre LIKE '%"+palabra+"%') "
				+ "group by productos.ID_producto;";
		
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		String[] tabla = new String[4];
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				tabla[0]= rs.getString("productos.id_producto");
				tabla[1]= rs.getString("productos.nombre");
				tabla[2]= rs.getString("productos.precio_venta");
				model.addRow(tabla);
			}
			return model;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "no se puedo cargar la tabla");
			System.out.println(e);

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
		return model;
	}
	
}
