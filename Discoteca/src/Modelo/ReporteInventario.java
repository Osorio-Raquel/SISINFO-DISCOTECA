package Modelo;

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

import java.awt.Desktop;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReporteInventario extends ReportePapa{
	
	public ReporteInventario() {
		super();
	}
	
	@Override
	public void GenerarReporte() {
        String dest = "ReporteInventario.pdf";
        
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
            Paragraph title = new Paragraph("Reporte de Inventario", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph fechaReporte = new Paragraph("Fecha: " + Fechita() + "            Hora:" + horita(), fechayHora);
            document.add(fechaReporte);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Categoría");
            addTableHeader(table, headerFont, "Nombre");
            addTableHeader(table, headerFont, "Stock");
            addTableHeader(table, headerFont, "Fecha de Vencimiento");
            
            ArrayList<DatosInventario> inv = new ArrayList<>();
            inv = obtenerDatos();
            
            for(DatosInventario d : inv) {
                LocalDate today = LocalDate.now();
                LocalDate expiryDate = d.getFechaVencimiento();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = d.getFechaVencimiento().format(formatter);
                
                if (expiryDate != null && !expiryDate.isBefore(today) && !expiryDate.isAfter(today.plusDays(7))) {
                	addTableCellRed(table, "" + d.getNumero());
                	addTableCellRed(table, d.getCategoria());
                	addTableCellRed(table, d.getNombre());
                	addTableCellRed(table, "" + d.getStock());
                	addTableCellRed(table, formattedDate);
                } else {
                	addTableCell(table, "" + d.getNumero());
                    addTableCell(table, d.getCategoria());
                    addTableCell(table, d.getNombre());
                    addTableCell(table, "" + d.getStock());
                    addTableCell(table, formattedDate);
                }
            }

            document.add(table);

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
	
	private static ArrayList<DatosInventario> obtenerDatos (){
		ArrayList<DatosInventario> inv = new ArrayList<>();
		String consulta= "SELECT nombre, stock, categoria, Fecha_Vencimiento\r\n"
				+ "from productos, pedidosReporte\r\n"
				+ "where productos.ID_producto = pedidosReporte.ID_producto\r\n"
				+ "and pedidosReporte.Estado = true\r\n"
				+ "ORDER BY categoria;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			int num = 1;
			String[] cat = {"","Frutas","Verduras","Carnes","Lacteos","Cereales","Dulces","Limpieza","Aseo Personal"};
			while(rs.next()) {
				int c = Integer.parseInt(rs.getString("categoria"));
				String categoria = cat[c];
				String nombre = rs.getString("nombre");
				double stock = Double.parseDouble(rs.getString("stock"));
				Date sqlDate = rs.getDate("Fecha_Vencimiento");
                LocalDate localDate = sqlDate.toLocalDate();
                System.out.println("Fecha: " + localDate);
				DatosInventario datos = new DatosInventario(categoria, num, nombre, stock, localDate);
				System.out.println("Numero: " + datos.getNumero());
            	System.out.println("Categoria: " + datos.getCategoria());
            	System.out.println("Nombre: " + datos.getNombre());
            	System.out.println("Stock: " + datos.getStock());
            	System.out.println("Fecha: " + datos.getFechaVencimiento());
				inv.add(datos);
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
    
    @Override
    public void addTableCellRed(PdfPTable table, String text) {
    	// TODO Auto-generated method stub
    	super.addTableCellRed(table, text);
    }
	
	
}
