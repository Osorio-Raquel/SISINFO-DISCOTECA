package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Modelo.ReporteInventario;
import Modelo.ReportePedido;
import Modelo.ReporteVentas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reportes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Reportes() {
		setTitle("Reportes");
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
		Image dimg = new ImageIcon(this.getClass().getResource("/green_planet2.jpg")).getImage();
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
		
		JLabel lblTitle = new JLabel("Reportes");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panTitle.add(lblTitle);
		
		JPanel panMenu = new JPanel();
		panMenu.setBorder(new EmptyBorder(20, 35, 0, 35));
		panMenu.setBackground(new Color(9, 38, 53));
		panel.add(panMenu, BorderLayout.CENTER);
		panMenu.setLayout(new GridLayout(5, 1, 0, 50));
		
		JButton btn1 = new JButton("Reporte de ventas");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteVentas rev = new ReporteVentas();
				rev.GenerarReporte();
			}
		});
		btn1.setForeground(new Color(217, 236, 233));
		btn1.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btn1.setBackground(new Color(0, 198, 176));
		panMenu.add(btn1);
		
		JButton btn2 = new JButton("Reporte de inventario");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteInventario re = new ReporteInventario();
				re.GenerarReporte();
			}
		});
		btn2.setForeground(new Color(217, 236, 233));
		btn2.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btn2.setBackground(new Color(0, 198, 176));
		panMenu.add(btn2);
		
		JButton btn3 = new JButton("Reporte de pedidos");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePedido rep = new ReportePedido();
				rep.GenerarReporte();
			}
		});
		btn3.setForeground(new Color(217, 236, 233));
		btn3.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btn3.setBackground(new Color(0, 198, 176));
		panMenu.add(btn3);
		
		JPanel panLogout = new JPanel();
		panLogout.setBorder(new EmptyBorder(0, 0, 25, 0));
		panLogout.setBackground(new Color(9, 38, 53));
		panLogout.setForeground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panLogout.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panLogout, BorderLayout.SOUTH);
		
		JButton btnLogout = new JButton("Volver");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrador admin = new Administrador();
				admin.setVisible(true);
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

