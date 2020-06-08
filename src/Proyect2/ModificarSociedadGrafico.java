package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ModificarSociedadGrafico extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;

	//Este metodo crea la ventana de la opcion 3 de menu principa de modificar sociedad
	public ModificarSociedadGrafico() {
		//Esta parte se crea la ventada de modificar sociedad
		setTitle("MOFICAR SOCIEDAD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//El JcomboBox se crea
		JComboBox list_1_1 = new JComboBox();
		list_1_1.setBounds(268, 179, 236, 22);
		contentPane.add(list_1_1);
		JLabel lblSelecciondesociedad = new JLabel("Seleccione la sociedad existente");
		lblSelecciondesociedad.setBounds(29, 75, 302, 14);
		contentPane.add(lblSelecciondesociedad);
		
		//Este Jcombox se va actualizadon conforme lo que el usuario va ingresando en las sociedad que ha creado 
		JComboBox list = new JComboBox();
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 String sociedad = list.getSelectedItem().toString();
			 String strindice = sociedad.split("-")[0];
			 for(Sociedad s : MenuPrincipalGrafico.mdp.listadoSociedades ) {
				  if(s.getIndice() == Integer.parseInt(strindice)) {
					 DefaultComboBoxModel modeloCualidad = new DefaultComboBoxModel();
					 list_1_1.setModel(modeloCualidad);
					 for(Cualidades c : s.getCualidades()) {
						 modeloCualidad.addElement(c.getNombrec() + "-"+ c.getNombredeDato()); 
					 }
				 }
			 }
			}
		});
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
		lblAdvertencia.setBounds(39, 105, 828, 36);
		contentPane.add(lblAdvertencia);
		
		//Este boton activara la funcio de agregar nueva cualidad
		JButton btnAgregarNuevaCualidad = new JButton("Agregar Nueva Cualidad");
		btnAgregarNuevaCualidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sociedad = list.getSelectedItem().toString();
				String strindice = sociedad.split("-")[0];
				int indice = Integer.parseInt(strindice);
				
				Cualidades cualidad = new Cualidades();
				cualidad.setNombrec(textNombre.getText());
				cualidad.setValordeDato(list_1_1.getSelectedIndex()+1);
				cualidad.setNombredeDato();
				cualidad.setIndicec(indice);
				cualidad.setTamano(20);	
				MenuPrincipalGrafico.mdp.agregarCualidad(indice, cualidad);
				MenuPrincipalGrafico.mdp.accederArchivo();
			}
		});
		btnAgregarNuevaCualidad.setBounds(42, 309, 188, 23);
		contentPane.add(btnAgregarNuevaCualidad);
		
		//Este boton accionara la funcion eliminar cualidad
		JButton btnEliminar = new JButton("Eliminar Cualidad");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sociedad = list.getSelectedItem().toString();
				System.out.println(sociedad);
				String strindice = sociedad.split("-")[0];
				String cualidadselect = list_1_1.getSelectedItem().toString();
				String nombre = cualidadselect.split("-")[0];
				MenuPrincipalGrafico.mdp.eliminarCualidad(Integer.parseInt(strindice),nombre.trim());
				MenuPrincipalGrafico.mdp.accederArchivo();
								
			}
		});
		btnEliminar.setBounds(39, 207, 188, 23);
		contentPane.add(btnEliminar);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(268, 242, 156, 20);
		contentPane.add(textNombre);
		
		//En esta parte Jbutton Cambiar Nombre recibe la sociedad seleccionada y despues recibe lo seleccionado Cualidades disponibles de la misma Sociedad y recibe el nombre de caja de texto
		//y pulsar al boton cambiar nombre este cambiara de nombre
		JButton btnCambiarNombre = new JButton("Editar nombre");
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sociedad = list.getSelectedItem().toString();
				System.out.println(sociedad);
				String strindice = sociedad.split("-")[0];
				String cualidadselect = list_1_1.getSelectedItem().toString();
				String nombre = cualidadselect.split("-")[0];
				MenuPrincipalGrafico.mdp.modificarNombreCualidad(Integer.parseInt(strindice),nombre.trim(), textNombre.getText());
				MenuPrincipalGrafico.mdp.accederArchivo();
			}
		});
		btnCambiarNombre.setBounds(39, 241, 188, 23);
		contentPane.add(btnCambiarNombre);
		
		JComboBox listTipodeDato = new JComboBox();
		listTipodeDato.setBounds(472, 241, 157, 23);
		listTipodeDato.setModel(new DefaultComboBoxModel(new String[] {"INT", "LONG", "FLOAT", "DOUBLE", "CHAR", "STRING", "DATE"}));
		contentPane.add(listTipodeDato);
		
		
		//En esta parte Jbutton Cambiar tipo de dato recibe la sociedad seleccionada y despues recibe lo seleccionado Cualidades disponibles de la misma Sociedad y recibe lo seleccionado en listado de tipo de dato
		//y pulsar al boton cambiar nombre este cambiara tipo de dato
		JButton btnCambiartipodedato = new JButton("Cambiar tipo de dato");
		btnCambiartipodedato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sociedad = list.getSelectedItem().toString();
				String strindice = sociedad.split("-")[0];
				String cualidadselect = list_1_1.getSelectedItem().toString();
				String nombre = cualidadselect.split("-")[0];
				int tipo = listTipodeDato.getSelectedIndex()+1;
				MenuPrincipalGrafico.mdp.modificarTipoCualidad(Integer.parseInt(strindice),nombre.trim(), tipo);
				MenuPrincipalGrafico.mdp.accederArchivo();
			}
		});
		btnCambiartipodedato.setBounds(42, 275, 188, 23);
		contentPane.add(btnCambiartipodedato);
		
		JLabel lblSeleccioneTipoDe_1 = new JLabel("Seleccione tipo de dato");
		lblSeleccioneTipoDe_1.setBounds(473, 216, 156, 14);
		contentPane.add(lblSeleccioneTipoDe_1);
		
		JLabel lblSeleccioneTipoDe_1_1 = new JLabel("Nombre");
		lblSeleccioneTipoDe_1_1.setBounds(268, 217, 156, 14);
		contentPane.add(lblSeleccioneTipoDe_1_1);
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		list.setModel(modelo);
		for(Sociedad a : MenuPrincipalGrafico.mdp.listadoSociedades)
		{
			modelo.addElement(a.getIndice() + "-" + a.getNombredesoc());
		}
		
		
	}
}
