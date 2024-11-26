package Modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexionBase.conexionBD;

public class FacturaEnPDF extends ReportePapa{
	
	public int facID;
	
	public void setFacID(int facID) {
		this.facID = facID;
	}
	
	public int getFacID() {
		return facID;
	}
	
	public FacturaEnPDF(int facID) {
		this.facID = facID;
	}
	
	@Override
	public void addTableCell(PdfPTable table, String text) {
		// TODO Auto-generated method stub
		super.addTableCell(table, text);
	}
	
	@Override
	public void addTableHeader(PdfPTable table, Font font, String text) {
		// TODO Auto-generated method stub
		super.addTableHeader(table, font, text);
	}
	
	@Override
	public String Fechita() {
		// TODO Auto-generated method stub
		return super.Fechita();
	}
	
	@Override
	public String horita() {
		// TODO Auto-generated method stub
		return super.horita();
	}
	
	public void GenerarReporte(double monto) {
        String dest = "C:/Documentos/Factura.pdf";
        
        Document document = new Document();

        try {
        	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            
            String text = "Sucursal #1\nAv. Hernando Siles\nCalle 3 de Obrajes\nTelefono: 72036743\nLa Paz - Bolivia";
            Font encabezado = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            Paragraph paragraph = new Paragraph(text, encabezado);
            document.add(paragraph);
            
            String imagePath = "C:\\Documentos\\imag\\logo330x200.png";
            Image imagen = Image.getInstance(imagePath);
            imagen.scaleToFit(150, 150);
            float xPos = document.getPageSize().getWidth() - imagen.getScaledWidth() - 20; // Ajustar posición X para la derecha
            float yPos = document.getPageSize().getHeight() - imagen.getScaledHeight() - 20; // Ajustar posición Y para la parte superior
            imagen.setAbsolutePosition(xPos, yPos);
            document.add(imagen);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("FACTURA", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            document.add(espaciador);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            
            String datosC = "NIT: " + nitCliente() + "\nNombre/RazonSocial: " + nombreCliente() + "\nFecha y hora: " + fechaFactura();
            
            Paragraph datosCliente = new Paragraph(datosC, fechayHora);
            document.add(datosCliente);
            
            document.add(espaciador);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Nombre");
            addTableHeader(table, headerFont, "Cantidad");
            addTableHeader(table, headerFont, "Precio Unitario");
            addTableHeader(table, headerFont, "Subtotal");
            
            ArrayList<DatosFacturaPDF> inv = new ArrayList<>();
            inv = obtenerDatos();
            
            for(DatosFacturaPDF d : inv) {
                addTableCell(table, "" + d.getNumero());
                addTableCell(table, d.getNombre());
                addTableCell(table, "" + d.getCantidad());
                addTableCell(table, "" + d.getPrecio());
                addTableCell(table, "" + d.getSubtotal());
            }
            
            double total = sumatotales(inv);
            BigDecimal bd = new BigDecimal(Double.toString(total));
            bd = bd.setScale(2, RoundingMode.FLOOR);
            
            addTableHeader(table, headerFont, "TOTAL");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableHeader(table, headerFont, "" + bd.doubleValue());

            document.add(table);
            
            document.add(espaciador);
            
            double cambio = monto - bd.doubleValue();
            
            BigDecimal bd1 = new BigDecimal(Double.toString(cambio));
            bd1 = bd1.setScale(2, RoundingMode.FLOOR);
            
            Paragraph montos = new Paragraph("Monto pagado: " + monto + "             Cambio: " + bd1.doubleValue(), fechayHora);
            document.add(montos);
            
            document.add(espaciador);
            document.add(espaciador);
            document.add(espaciador);
            document.add(espaciador);
            document.add(espaciador);
            document.add(espaciador);
            
            String textoFinal = "ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS, EL USO ILÍCITO SERÁ SANCIONADO\r\n"
            		+ "PENALMENTE DE ACUERDO A LEY\r\n"
            		+ "Ley N° 453: El proveedor debe exhibir certificaciones de habilitación o documentos que acrediten las\r\n"
            		+ "capacidades u ofertas de servicios especializados.\r\n"
            		+ "Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido en una\r\n"
            		+ "modalidad de facturación fuera de línea";
            
            					
            Paragraph Partefinal = new Paragraph(textoFinal, encabezado);
            document.add(Partefinal);
            
            document.close();

            System.out.println("¡Reporte PDF creado exitosamente!");
            
            File pdfFile = new File(dest);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Apertura de archivos no soportada en este sistema.");
                }
            } else {
                System.out.println("El archivo PDF no fue encontrado.");
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private double sumatotales (ArrayList<DatosFacturaPDF> datos) {
		double suma = 0.0;
		for(DatosFacturaPDF d : datos) {
			suma += d.getSubtotal();
		}
		return suma;
	}
	
	private ArrayList<DatosFacturaPDF> obtenerDatos (){
		ArrayList<DatosFacturaPDF> inv = new ArrayList<>();
		String consulta= "select productos.nombre, productos.precio_venta, producto_factura.cantidad, producto_factura.subtotal\r\n"
				+ "from productos, producto_factura\r\n"
				+ "where producto_factura.id_factura = " + facID + "\r\n"
				+ "and producto_factura.ID_producto = productos.ID_producto;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				double precioU = rs.getDouble("precio_venta");
				double cantidad = rs.getDouble("cantidad");
				double subtotal = rs.getDouble("subtotal");
				DatosFacturaPDF datos = new DatosFacturaPDF(num, nombre, cantidad, precioU, subtotal);
				inv.add(datos);
				num++;
			}
		}catch(Exception e) {
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
		return inv;
	}
	
	private String nitCliente () {
		String nit = "";
		String consulta= "select NIT\r\n"
				+ "from persona\r\n"
				+ "where ID_persona = (select id_persona\r\n"
				+ "from factura\r\n"
				+ "where factura.id_factura = " + facID + ");";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				nit = rs.getString("NIT");
			}
		}catch(Exception e) {
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
		return nit;
	}
	
	private String fechaFactura () {
		String fecha = "";
		String consulta= "select fecha from factura where id_factura = " + facID + ";";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				fecha = rs.getString("fecha");
			}
		}catch(Exception e) {
			
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
		return fecha;
	}
	
	private String nombreCliente () {
		String nom = "";
		String nitB = nitCliente();
		String consulta= "select nombre from persona where NIT = " + nitB + ";";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			if(rs.next()) {
				nom = rs.getString("nombre");
			}
		}catch(Exception e) {
			
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
		return nom;
	}
}
