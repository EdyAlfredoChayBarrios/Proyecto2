package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ModificarSociedadGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarSociedadGrafico frame = new ModificarSociedadGrafico();
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
	public ModificarSociedadGrafico() {
		setTitle("MOFICAR SOCIEDAD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelecciondesociedad = new JLabel("Seleccione la sociedad existente");
		lblSelecciondesociedad.setBounds(29, 75, 302, 14);
		contentPane.add(lblSelecciondesociedad);
		
		JComboBox list = new JComboBox();
		list.setBounds(334, 71, 236, 22);
		contentPane.add(list);
		
		JLabel lblModificarsociedad = new JLabel("Modificar Sociedad");
		lblModificarsociedad.setFont(lblModificarsociedad.getFont().deriveFont(lblModificarsociedad.getFont().getSize() + 15f));
		lblModificarsociedad.setBounds(29, 9, 457, 36);
		contentPane.add(lblModificarsociedad);
		
		JButton btnVolveralmenu = new JButton("Volver al Menu Principal");
		btnVolveralmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGrafico mpg=new MenuPrincipalGrafico();
				mpg.setVisible(true);
			}
		});
		btnVolveralmenu.setBounds(661, 71, 179, 23);
		contentPane.add(btnVolveralmenu);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnSalir.setBounds(397, 423, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblAdvertencia = new JLabel("Advertencia solo puede  modificar Sociedades Vacias (sociedades que no tengan datos)");
		lblAdvertencia.setForeground(Color.ORANGE);
		lblAdvertencia.setFont(lblAdvertencia.getFont().deriveFont(lblAdvertencia.getFont().getSize() + 3f));
		lblAdvertencia.setBounds(42, 126, 828, 36);
		contentPane.add(lblAdvertencia);
		
		JButton btnAgregarNuevaCualidad = new JButton("Agregar Nueva Cualidad");
		btnAgregarNuevaCualidad.setBounds(42, 173, 188, 23);
		contentPane.add(btnAgregarNuevaCualidad);
		
		JButton btnEliminar = new JButton("Eliminar Cualidad");
		btnEliminar.setBounds(42, 207, 188, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCambiarNombre = new JButton("Editar nombre");
		btnCambiarNombre.setBounds(42, 241, 188, 23);
		contentPane.add(btnCambiarNombre);
		
		JButton btnCambiartipodedato = new JButton("Cambiar tipo de dato");
		btnCambiartipodedato.setBounds(42, 275, 188, 23);
		contentPane.add(btnCambiartipodedato);
		
		JComboBox list_1_1 = new JComboBox();
		list_1_1.setBounds(250, 207, 236, 22);
		contentPane.add(list_1_1);
		
		JComboBox list_1_2 = new JComboBox();
		list_1_2.setBounds(250, 241, 236, 22);
		contentPane.add(list_1_2);
		
		JComboBox list_1_3 = new JComboBox();
		list_1_3.setBounds(250, 275, 236, 22);
		contentPane.add(list_1_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(661, 242, 156, 20);
		contentPane.add(textField_1);
		
		JComboBox list_2 = new JComboBox();
		list_2.setBounds(661, 275, 157, 23);
		contentPane.add(list_2);
		
		JLabel lblSeleccioneTipoDe = new JLabel("Seleccione tipo de dato");
		lblSeleccioneTipoDe.setBounds(495, 279, 156, 14);
		contentPane.add(lblSeleccioneTipoDe);
		
		JLabel lblEscribaElNombre = new JLabel("Escriba el nombre");
		lblEscribaElNombre.setBounds(496, 250, 156, 14);
		contentPane.add(lblEscribaElNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(250, 174, 156, 20);
		contentPane.add(textField);
		
		JComboBox list_2_1 = new JComboBox();
		list_2_1.setBounds(660, 173, 157, 23);
		contentPane.add(list_2_1);
		
		JLabel lblSeleccioneTipoDe_1 = new JLabel("Seleccione tipo de dato");
		lblSeleccioneTipoDe_1.setBounds(495, 177, 156, 14);
		contentPane.add(lblSeleccioneTipoDe_1);
	}
}
