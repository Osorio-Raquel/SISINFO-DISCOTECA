package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexionBase.conexionBD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Administrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Administrador() {
		setTitle("Administrador");
		setResizable(false);
		setBounds(350, 150, 1280, 720);
		setBackground(new Color(9, 38, 53));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 38, 53));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panImg = new JPanel();
		contentPane.add(panImg, BorderLayout.CENTER);
		
		JLabel lblImg = new JLabel("");
		lblImg.setForeground(new Color(9, 38, 53));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBackground(new Color(9, 38, 53));
		Image dimg = new ImageIcon(this.getClass().getResource("/green_planet1.jpg")).getImage();
		ImageIcon imageIcon = new ImageIcon(dimg.getScaledInstance(900, 720, Image.SCALE_SMOOTH));
		lblImg.setIcon(imageIcon);
		panImg.add(lblImg);	
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setBorder(new EmptyBorder(0, 35, 0, 35));
		panel.setBackground(new Color(9, 38, 53));
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitle = new JPanel();
		panTitle.setBorder(new EmptyBorder(70, 0, 40, 0));
		panTitle.setBackground(new Color(9, 38, 53));
		panTitle.setForeground(new Color(255, 255, 255));
		panel.add(panTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Menú de Administrador");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panMenu = new JPanel();
		panMenu.setBorder(new EmptyBorder(20, 55, 0, 55));
		panMenu.setBackground(new Color(9, 38, 53));
		panel.add(panMenu, BorderLayout.CENTER);
		panMenu.setLayout(new GridLayout(5, 1, 0, 50));
		
		JButton btn1 = new JButton("Pedido");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pedido pe = new Pedido();
				pe.setVisible(true);
				dispose();
			}
		});
		btn1.setForeground(new Color(217, 236, 233));
		btn1.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btn1.setBackground(new Color(0, 198, 176));
		panMenu.add(btn1);
		
		JButton btn2 = new JButton("Reportes");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reportes repp = new Reportes();
				repp.setVisible(true);
				dispose();
			}
		});
		btn2.setForeground(new Color(217, 236, 233));
		btn2.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btn2.setBackground(new Color(0, 198, 176));
		panMenu.add(btn2);
		
		JButton btnMesas = new JButton("Actualizar mesas");
		btnMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexionBD conec= new conexionBD();
				Connection conn= conec.conexion();
				PreparedStatement ps= null;
				ResultSet rs= null;
				try {
					String actualizarFactura = "update Mesa set Estado = true;";
					PreparedStatement ps4= null;
					ps4 = conn.prepareStatement(actualizarFactura);
					ps4.executeUpdate();
					System.out.println("Funciona sql");
				}catch(Exception ex) {
					 ex.printStackTrace();
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
				
				JOptionPane.showMessageDialog(null, "Mesas actualizadas con exito.");
				
			}
		});
		
		JButton btn3_1 = new JButton("Regalías");
		btn3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regalias re = new Regalias();
				re.setVisible(true);
				dispose();
			}
		});
		btn3_1.setForeground(new Color(217, 236, 233));
		btn3_1.setFont(new Font("Dialog", Font.BOLD, 26));
		btn3_1.setBackground(new Color(0, 198, 176));
		panMenu.add(btn3_1);
		btnMesas.setForeground(new Color(217, 236, 233));
		btnMesas.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnMesas.setBackground(new Color(0, 198, 176));
		panMenu.add(btnMesas);
		
		JPanel panLogout = new JPanel();
		panLogout.setBorder(new EmptyBorder(0, 0, 25, 0));
		panLogout.setBackground(new Color(9, 38, 53));
		panLogout.setForeground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panLogout.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panLogout, BorderLayout.SOUTH);
		
		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(new Color(217, 236, 233));
		btnLogout.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnLogout.setBackground(new Color(0, 198, 176));
		panLogout.add(btnLogout);
		
		setVisible(true);
	}

}

