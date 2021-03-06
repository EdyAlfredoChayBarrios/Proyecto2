package src.Proyect2;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class MenuSociedadGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextPane textField_2;

	public List<Cualidades> cualidades;
	
	//Se crea el metodo Jframe para abrir la ventana emergente de Agregar Sociedad
	public MenuSociedadGrafico() {
		cualidades = new ArrayList<Cualidades>();
		
		setTitle("1...AGREGAR SOCIEDAD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngresarsociedad = new JLabel("Ingrese sociedad");
		lblIngresarsociedad.setFont(lblIngresarsociedad.getFont().deriveFont(lblIngresarsociedad.getFont().getSize() + 9f));
		lblIngresarsociedad.setBounds(21, 11, 300, 25);
		contentPane.add(lblIngresarsociedad);
		
		JLabel lblingresenombre = new JLabel("Ingrese nombre de la sociedad");
		lblingresenombre.setBounds(21, 59, 178, 14);
		contentPane.add(lblingresenombre);
		
		textField = new JTextField();
		textField.setBounds(267, 56, 235, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIngresar = new JLabel("Ingrese el nombre de la cualidades");
		lblIngresar.setBounds(24, 172, 204, 14);
		contentPane.add(lblIngresar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(267, 169, 235, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnVolveralMenu = new JButton("Volver al menu principal");
		btnVolveralMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGrafico mpg=new MenuPrincipalGrafico();
				mpg.setVisible(true);
				
			}
		});
		btnVolveralMenu.setBounds(189, 413, 185, 23);
		contentPane.add(btnVolveralMenu);
		
		JLabel lblTipodeDato = new JLabel("Seleccione el tipo de dato que va a guardar en las cualidad");
		lblTipodeDato.setBounds(21, 215, 364, 25);
		contentPane.add(lblTipodeDato);
		
		JComboBox list = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(596, 172, 219, 181);
		contentPane.add(scrollPane);
		textField_2 = new JTextPane();
		scrollPane.setViewportView(textField_2);
		textField_2.setEditable(false);
		
		
		list.setModel(new DefaultComboBoxModel(new String[] {"INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "STRING", "DATE"}));
		list.setBounds(386, 215, 157, 25);
		contentPane.add(list);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnSalir.setBounds(425, 413, 89, 23);
		contentPane.add(btnSalir);
		
		
		Sociedad sociedad = new Sociedad();
		sociedad.setIndice(MenuPrincipalGrafico.mdp.listadoSociedades.size() + 1); 
		
		//Este boton agregara a la lista de cualidades por agregar a la sociedad
		JButton btnAgregarCualidad = new JButton("Agregar cualidad");
		btnAgregarCualidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cualidades cualidades = new Cualidades();
				cualidades.setNombrec(textField_1.getText());
				cualidades.setValordeDato(list.getSelectedIndex()+1);
				cualidades.setNombredeDato();
				cualidades.setIndicec(sociedad.getIndice());
				cualidades.setTamano(20);
				sociedad.setCualidades(cualidades);
				
				String datos = "";
				for(Cualidades c : sociedad.getCualidades()) {
					datos+= "\r\n"+c.getNombrec() + " : " + c.getNombredeDato() + System.lineSeparator();
				}
				textField_2.setText(datos);
			}
		});
		btnAgregarCualidad.setBounds(315, 251, 144, 23);
		contentPane.add(btnAgregarCualidad);
		
		//Este boton agregar sociedad esta agregara la sociedad y con sus cualidades he debera ingresar el usuario
		JButton btnAgregarSociedad = new JButton("Agregar sociedad");
		btnAgregarSociedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sociedad.setNombredesoc(textField.getText());
				boolean r = MenuPrincipalGrafico.mdp.agregarSociedad(sociedad);
				if(r) {
					//mostrar mensaje de accion correcta
				}
			}
		});
		btnAgregarSociedad.setBounds(291, 330, 144, 40);
		contentPane.add(btnAgregarSociedad);
		
		
		
	}
}
