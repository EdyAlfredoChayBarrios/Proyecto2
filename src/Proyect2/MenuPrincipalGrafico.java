package src.Proyect2;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class MenuPrincipalGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalGrafico frame = new MenuPrincipalGrafico();
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
	public MenuPrincipalGrafico() {
		setType(Type.POPUP);
		setTitle("MENU GENERAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAgregarSociedad = new JButton("1. Agregar Sociedad");
		btnAgregarSociedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuSociedadGrafico ms=new MenuSociedadGrafico();
				ms.setVisible(true);
			
			}
		});
		btnAgregarSociedad.setBounds(10, 31, 164, 23);
		contentPane.add(btnAgregarSociedad);
				
		JButton btnModificarSociedad = new JButton("2. Modificar Sociedad");
		btnModificarSociedad.setBounds(10, 65, 164, 23);
		contentPane.add(btnModificarSociedad);
		
		JButton btnenListarSociedad = new JButton("3. Enlistar Sociedad");
		btnenListarSociedad.setBounds(10, 99, 164, 23);
		contentPane.add(btnenListarSociedad);
		
		JButton btnAgregardatosalaSociedad = new JButton("4. Agregar Datos a la Sociedad");
		btnAgregardatosalaSociedad.setBounds(10, 133, 164, 23);
		contentPane.add(btnAgregardatosalaSociedad);
		
		JButton btnBorrarbasedeDatos = new JButton("5. Borrar Base de Datos");
		btnBorrarbasedeDatos.setBounds(10, 167, 164, 23);
		contentPane.add(btnBorrarbasedeDatos);
		
		JButton btnSalir = new JButton("0. Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnSalir.setBounds(230, 352, 89, 23);
		contentPane.add(btnSalir);
	}
}
