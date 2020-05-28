package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuSociedadGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuSociedadGrafico frame = new MenuSociedadGrafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public MenuSociedadGrafico() {
		setTitle("1...AGREGAR SOCIEDAD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngresedatos = new JLabel("Ingrese datos a la sociedad");
		lblIngresedatos.setBounds(21, 26, 201, 25);
		contentPane.add(lblIngresedatos);
		
		JLabel lblingresenombre = new JLabel("Ingrese nombre de la sociedad");
		lblingresenombre.setBounds(21, 59, 178, 14);
		contentPane.add(lblingresenombre);
		
		textField = new JTextField();
		textField.setBounds(204, 56, 235, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIngresar = new JLabel("Ingrese el nombre de la cualidades");
		lblIngresar.setBounds(24, 172, 175, 14);
		contentPane.add(lblIngresar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 169, 235, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSi = new JButton("Si");
		btnSi.setBounds(106, 298, 89, 23);
		contentPane.add(btnSi);
		
		JLabel lblSioNo = new JLabel("Desea ingresar otra cualidad a la sociedad ?");
		lblSioNo.setBounds(110, 262, 329, 14);
		contentPane.add(lblSioNo);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(231, 298, 89, 23);
		contentPane.add(btnNo);
		
		JButton btnVolveralMenu = new JButton("Volver al menu principal");
		btnVolveralMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGrafico mpg=new MenuPrincipalGrafico();
				mpg.setVisible(true);
				
			}
		});
		btnVolveralMenu.setBounds(367, 298, 145, 23);
		contentPane.add(btnVolveralMenu);
	}
}
