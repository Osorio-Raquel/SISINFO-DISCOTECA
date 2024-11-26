package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBase.conexionBD;

public class ClienteFactura extends persona{
	
	public int nit;
	
	@Override
	public void setCorreo(String correo) {
		// TODO Auto-generated method stub
		super.setCorreo(correo);
	}
	
	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}
	
	public void setNit(int nit) {
		this.nit = nit;
	}
	
	@Override
	public String getCorreo() {
		// TODO Auto-generated method stub
		return super.getCorreo();
	}
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}
	
	public int getNit() {
		return nit;
	}
	
	public ClienteFactura(String nombre, String correo, int nit) {
		super(nombre, correo);
		this.nit = nit;
	}
	
	public void agregarFactura (int idFac, int metPago, int idcliente) {
		String consulta = "update factura set id_persona = " + idcliente + ", metodo_pago = " + metPago + " where id_factura = " + idFac + ";";

		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			int actualizado = ps.executeUpdate();
			if(actualizado > 0) {
				System.out.println("Actualizado con exito");
			} else {
				System.out.println("No se pudo actualizar");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void ingresarClienteNuevo () {
		String consulta= super.ingresar();
		consulta += " persona (nombre, NIT, correo_electronico) values ('" + nombre + "', " + nit + ", '" + correo + "');";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			int aniadido = ps.executeUpdate();
			if(aniadido > 0) {
				System.out.println("Agregado con exito");
			} else {
				System.out.println("no se pudo agregar");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public boolean buscarCliente () {
		ArrayList<Integer> nits = new ArrayList<>();
		String consulta= "SELECT NIT from persona;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("NIT");
				nits.add(num);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		if(nits.contains(nit)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int datosEncontrados (){
		int idPersona = 1;
		String consulta= "SELECT id_persona, nombre, correo_electronico from persona where NIT = " + nit + ";";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				String nombreI = rs.getString("nombre");
				String correoI = rs.getString("correo_electronico");
				idPersona = Integer.parseInt(rs.getString("id_persona"));
				setNombre(nombreI);
				setCorreo(correoI);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		
		return idPersona;
	}
	
	
}
