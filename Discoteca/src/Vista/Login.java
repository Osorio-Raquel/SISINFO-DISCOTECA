package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.verificacionCorreo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;

	public Login() {
		setTitle("Inicio de sesión");
		setResizable(false);
		setBounds(350, 150, 1280, 720);
		setBackground(new Color(9, 38, 53));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(9, 38, 53));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panLogin = new JPanel();
		panLogin.setBorder(new EmptyBorder(0, 35, 0, 35));
		panLogin.setBackground(new Color(9, 38, 53));
		contentPane.add(panLogin, BorderLayout.WEST);
		panLogin.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panWelcome = new JPanel();
		panWelcome.setBorder(new EmptyBorder(50, 0, 30, 0));
		panWelcome.setBackground(new Color(9, 38, 53));
		panWelcome.setForeground(new Color(255, 255, 255));
		panLogin.add(panWelcome);
		
		JLabel lblWelcome = new JLabel("¡Bienvenido!");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 30));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		panWelcome.add(lblWelcome);
		
		JPanel panUser = new JPanel();
		panUser.setBorder(new EmptyBorder(0, 0, 70, 0));
		panUser.setBackground(new Color(9, 38, 53));
		panUser.setForeground(new Color(255, 255, 255));
		panLogin.add(panUser);
		panUser.setLayout(new BoxLayout(panUser, BoxLayout.Y_AXIS));
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setForeground(new Color(158, 200, 185));
		lblUser.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		panUser.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panUser.add(txtUser);
		txtUser.setColumns(25);
		
		JPanel panPass = new JPanel();
		panPass.setBorder(new EmptyBorder(0, 0, 70, 0));
		panPass.setBackground(new Color(9, 38, 53));
		panLogin.add(panPass);
		panPass.setLayout(new BoxLayout(panPass, BoxLayout.Y_AXIS));
		
		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setForeground(new Color(158, 200, 185));
		lblPass.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		panPass.add(lblPass);
		
		txtPass = new JTextField();
		txtPass.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		panPass.add(txtPass);
		txtPass.setColumns(25);
		
		JPanel panBtn = new JPanel();
		panBtn.setBorder(new EmptyBorder(0, 0, 35, 0));
		panBtn.setForeground(new Color(9, 38, 53));
		panBtn.setBackground(new Color(9, 38, 53));
		panLogin.add(panBtn);
		FlowLayout fl_panBtn = new FlowLayout(FlowLayout.TRAILING, 5, 5);
		panBtn.setLayout(fl_panBtn);
		
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificacionCorreo ver1= new verificacionCorreo();
                String contrasena= new String(txtPass.getText());
                try {
                    if(ver1.verificador(txtUser.getText(), contrasena)) {
                        JOptionPane.showMessageDialog(null, "ingreso permitido");
                        int cargoo = ver1.verificadorTipo(txtUser.getText());
                        if(cargoo == 1) {
                        	Administrador ger = new Administrador();
                        	ger.setVisible(true);
                        	dispose();
                        }else if(cargoo == 2) {
                        	Bartender bar = new Bartender();
                            bar.setVisible(true);
                            dispose();
                        } else if(cargoo == 3) {
                        	Relacionador rel = new Relacionador();
                        	rel.setVisible(true);
                            dispose();
                        }
                        txtUser.setText("");
                        txtPass.setText("");
               
                    }else {
                        JOptionPane.showMessageDialog(null, "ingreso denegado");
                    }
                }catch(Exception error) {
                	System.out.println(error);
                }

                txtUser.setText("");
                txtPass.setText("");
			}
		});
		btnLogin.setForeground(new Color(217, 236, 233));
		btnLogin.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 26));
		btnLogin.setBackground(new Color(0, 198, 176));
		panBtn.add(btnLogin);
		
		JPanel panImg = new JPanel();
		contentPane.add(panImg, BorderLayout.CENTER);
		
		JLabel lblImg = new JLabel("");
		lblImg.setForeground(new Color(9, 38, 53));
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBackground(new Color(9, 38, 53));
		Image dimg = new ImageIcon(this.getClass().getResource("/green_planet.jpg")).getImage();
		ImageIcon imageIcon = new ImageIcon(dimg.getScaledInstance(1354, 720, Image.SCALE_SMOOTH));
		lblImg.setIcon(imageIcon);
		panImg.add(lblImg);		
		
		setVisible(true);
	}

}

