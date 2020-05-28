package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnlistarSociedadGrafico extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EnlistarSociedadGrafico() {
		setTitle("ENLISTAR MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnlistarScoiedad = new JLabel("Desea ver los detalles de la sociedad ");
		lblEnlistarScoiedad.setBounds(35, 26, 293, 22);
		contentPane.add(lblEnlistarScoiedad);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(76, 91, 605, 238);
		contentPane.add(textPane);
		
		
		JButton btnSi = new JButton("SI");
		btnSi.setBounds(360, 26, 89, 23);
		contentPane.add(btnSi);
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String datos = MenuPrincipalGrafico.mdp.listar(1);
				
				textPane.setText(datos);
			}
		});
		
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String datos=MenuPrincipalGrafico.mdp.listar(0);
				textPane.setText(datos);
				
			}
		});
		btnNo.setBounds(475, 26, 89, 23);
		contentPane.add(btnNo);
		
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnSalir.setBounds(155, 400, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnVolveralMenui = new JButton("Volver al menu principal");
		btnVolveralMenui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuPrincipalGrafico mpg= new MenuPrincipalGrafico();
				mpg.setVisible(true);
				
			}
		});
		btnVolveralMenui.setBounds(292, 400, 157, 23);
		contentPane.add(btnVolveralMenui);
	}
}
