package Modelo;

import java.awt.Desktop;
import java.beans.PropertyEditor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexionBase.conexionBD;

public class ReportePedido extends ReportePapa{
	
	public ReportePedido() {
		super();
	}
	
	public void GenerarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        String dest = "ReportePedidos.pdf";
        
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            document.open();
            
            String imagePath = "C:\\Documentos\\imag\\logo330x200.png";
            Image imagen = Image.getInstance(imagePath);
            imagen.scaleToFit(100, 100);
            imagen.setAbsolutePosition(10, document.getPageSize().getHeight() - imagen.getScaledHeight() - 10);
            document.add(imagen);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("Reporte de Pedidos", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph fechaReporte = new Paragraph("Fecha de Reporte: " + Fechita() + "            Hora de Reporte:" + horita(), fechayHora);
            document.add(fechaReporte);
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActualStrI = fechaInicio.format(formato);
            String fechaActualStrF = fechaFin.format(formato);
            
            
            document.add(espaciador);
            
            Paragraph intervaloInforme = new Paragraph("Reporte del: " + fechaActualStrI + " al " + fechaActualStrF, fechayHora);
            document.add(intervaloInforme);
            
            document.add(espaciador);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Producto");
            addTableHeader(table, headerFont, "Fecha");
            addTableHeader(table, headerFont, "Cantidad");
            addTableHeader(table, headerFont, "Total");
            
            ArrayList<PedidosDatos> inv = new ArrayList<>();
            inv = obtenerDatos(fechaInicio, fechaFin);
            
            for(PedidosDatos d : inv) {
            	addTableCell(table, "" + d.getNumero());
                addTableCell(table, "" + d.getNombre());
                addTableCell(table, d.getFecha());
                addTableCell(table, "" + d.getCantidad());
                addTableCell(table, "" + d.getCosto());
            }
             
            double suma = SumaTotal(inv);
            
            addTableCell(table, "TOTAL");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "" + suma);

            document.add(table);

            document.close();
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
	

	@Override
	public String horita() {
		// TODO Auto-generated method stub
		return super.horita();
	}
	
	@Override
	public String Fechita() {
		// TODO Auto-generated method stub
		return super.Fechita();
	}
	
	public static double SumaTotal (ArrayList<PedidosDatos> ped) {
		double suma = 0.0;
		for(PedidosDatos p : ped) {
			suma += p.getCosto();
		}
		return suma;
	}
	
	private static ArrayList<PedidosDatos> obtenerDatos (LocalDate inicio, LocalDate fin){
		ArrayList<PedidosDatos> inv = new ArrayList<>();
		String consulta = "SELECT productos.nombre, productos.precio_compra, pedidosReporte.fecha, pedidosReporte.cantidad " +
                "FROM productos, pedidosReporte " +
                "WHERE productos.ID_producto = pedidosReporte.id_producto " +
                "AND (pedidosReporte.fecha >= ? AND pedidosReporte.fecha <= ?)"
                + "ORDER BY pedidosReporte.fecha;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			ps.setString(1, inicio.format(DateTimeFormatter.ISO_LOCAL_DATE));
			ps.setString(2, fin.format(DateTimeFormatter.ISO_LOCAL_DATE));
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				String nombre = rs.getString("nombre");
                double precioCompra = rs.getDouble("precio_compra");
                Date fecha = rs.getDate("fecha");
                LocalDate fechaLocal = fecha.toLocalDate();
                fechaLocal = fechaLocal.plusDays(1);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaActualStr = fechaLocal.format(formato);
                int cantidad = rs.getInt("cantidad");
                double total = precioCompra * cantidad;
                PedidosDatos ped = new PedidosDatos(num, nombre, cantidad, fechaActualStr, total);
                inv.add(ped);
				num++;
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
		return inv;
	}


	@Override
	public void addTableHeader(PdfPTable table, Font font, String text) {
		// TODO Auto-generated method stub
		super.addTableHeader(table, font, text);
	}

    @Override
    public void addTableCell(PdfPTable table, String text) {
    	// TODO Auto-generated method stub
    	super.addTableCell(table, text);
    }
}
