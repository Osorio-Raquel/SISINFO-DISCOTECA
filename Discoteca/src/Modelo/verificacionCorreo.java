package Modelo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import conexionBase.conexionBD;

public class verificacionCorreo {
	public ArrayList<String> correos = new ArrayList<>();
	public boolean verificador(String Correo, String contrasena ) {
		String password="";
		String id="";
		int estado = 0;
		conexionBD conec = new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consulta= "SELECT ID_Empleado, Estado from Empleado WHERE Usuario= '"+ Correo + "'";
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				id=rs.getString(1);
				estado=Integer.parseInt(rs.getString(2));
				String consulta2= "SELECT Contrasenia from Empleado where ID_Empleado="+id+";";
				ps=conn.prepareStatement(consulta2);
				rs=ps.executeQuery();
				if(rs.next()) {
					password=rs.getString(1);
					if(contrasena.equals(password) && estado == 1) {
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta o la cuenta ya no es valida");
						return false;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Correo no encontrado!");
				return false;
			}
			
		}catch(Exception e) {
			return false;
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return false;
	}
	public int verificadorTipo(String correo) {
	    String consulta = "SELECT ID_Cargo FROM Empleado WHERE Usuario=?";
	    
	    try (Connection conn = conexionBD.conexion();
	         PreparedStatement ps = conn.prepareStatement(consulta)) {
	        
	        ps.setString(1, correo);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            String cargo = rs.getString("ID_Cargo");
	            if (cargo.equals("1")) {
	                return 1;
	            } else if (cargo.equals("2")) {
	                return 2;
	            } else if (cargo.equals("3")) {
	                return 3;
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 4;
	    }
	    
	    return 4;
	}


	
	
}
