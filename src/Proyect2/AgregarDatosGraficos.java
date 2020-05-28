package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AgregarDatosGraficos extends JFrame {

	private JPanel contentPane;
	private JTextField textOpciondeDato;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public AgregarDatosGraficos() {
		setTitle("AGREGAR DATOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(64, 88, 822, 237);
		contentPane.add(textPane);
		
		JButton btnVolveralMenu = new JButton("Volver al menu principal");
		btnVolveralMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipalGrafico mpg=new MenuPrincipalGrafico();
				mpg.setVisible(true);
							
			}
		});
		btnVolveralMenu.setBounds(694, 54, 167, 23);
		contentPane.add(btnVolveralMenu);
		
		
		
		JLabel lblInformaciondedatos = new JLabel("Seleccione la sociedad que desea agregar Datos");
		lblInformaciondedatos.setBounds(30, 336, 349, 14);
		contentPane.add(lblInformaciondedatos);
		
		textOpciondeDato = new JTextField();
		textOpciondeDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		textOpciondeDato.setBounds(412, 333, 27, 20);
		contentPane.add(textOpciondeDato);
		textOpciondeDato.setColumns(10);
		
		JButton btnVerdatos = new JButton("ver todas las sociedades en donde puede trabajar");
		btnVerdatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String datos=MenuPrincipalGrafico.mdp.listar(0);
				
				textPane.setText("ESTA ES LA LISTA DE LA SOCIEDADES QUE PUEDE INGRESAR DATOS \n"+datos);
				
				
			}
		});
		btnVerdatos.setBounds(30, 54, 349, 23);
		contentPane.add(btnVerdatos);
		
		JButton btnAgergarDatos = new JButton("Ageragr Datos");
		btnAgergarDatos.setBounds(74, 361, 118, 23);
		contentPane.add(btnAgergarDatos);
		
		JButton btnListarDatos = new JButton("Listar Datos");
		btnListarDatos.setBounds(217, 361, 118, 23);
		contentPane.add(btnListarDatos);
		
		JButton btnBuscarDatos = new JButton("Buscar Datos");
		btnBuscarDatos.setBounds(355, 361, 118, 23);
		contentPane.add(btnBuscarDatos);
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBounds(629, 361, 150, 23);
		contentPane.add(btnModificarDatos);
		
		JTextPane textBuscarDatos = new JTextPane();
		textBuscarDatos.setBounds(355, 395, 118, 20);
		contentPane.add(textBuscarDatos);
		
		JTextPane textModificarDatos = new JTextPane();
		textModificarDatos.setBounds(629, 395, 150, 20);
		contentPane.add(textModificarDatos);;
	}
}
