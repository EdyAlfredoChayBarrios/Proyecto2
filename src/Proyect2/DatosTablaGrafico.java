package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosTablaGrafico extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public DatosTablaGrafico() {
		setTitle("TABLA DE DATOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVertabla = new JLabel("Ver datos de tabla de la sociedad");
		lblVertabla.setFont(lblVertabla.getFont().deriveFont(lblVertabla.getFont().getSize() + 15f));
		lblVertabla.setBounds(10, 21, 457, 36);
		contentPane.add(lblVertabla);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 128, 866, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSelecciondesociedad = new JLabel("Seleccione la sociedad existente");
		lblSelecciondesociedad.setBounds(31, 80, 302, 14);
		contentPane.add(lblSelecciondesociedad);
		
		JComboBox list = new JComboBox();
		list.setBounds(308, 79, 236, 22);
		contentPane.add(list);
		
		JButton btnVolveralmenu = new JButton("Volver al Menu Principal");
		btnVolveralmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGrafico mpg= new MenuPrincipalGrafico();
				mpg.setVisible(true);
				
			}
		});
		btnVolveralmenu.setBounds(670, 76, 179, 23);
		contentPane.add(btnVolveralmenu);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnSalir.setBounds(404, 457, 89, 23);
		contentPane.add(btnSalir);
		
	}
}
