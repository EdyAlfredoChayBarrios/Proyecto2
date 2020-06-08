package src.Proyect2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class AgregarDatosTabla extends JFrame {

	private JPanel contentPane;

	//Metodo que se crea la ventana de agregar datos
	public AgregarDatosTabla() {
		setTitle("AGREGAR DATOS A TABLA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAgregardatos = new JLabel("Agregar Datos a Tabla");
		lblAgregardatos.setFont(lblAgregardatos.getFont().deriveFont(lblAgregardatos.getFont().getSize() + 15f));
		lblAgregardatos.setBounds(25, 21, 457, 36);
		contentPane.add(lblAgregardatos);

		JLabel lblSelecciondesociedad = new JLabel("Seleccione la sociedad existente");
		lblSelecciondesociedad.setBounds(22, 81, 302, 14);
		contentPane.add(lblSelecciondesociedad);

		JComboBox list = new JComboBox();
		list.setBounds(253, 77, 236, 22);
		contentPane.add(list);
		
		//Este Jbutton volver al menu al accionar los cerrar la venta actual y mostrar la ventana principal de Menu Principal Grafico
		JButton btnVolveralmenu = new JButton("Volver al Menu Principal");
		btnVolveralmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGrafico mpg=new MenuPrincipalGrafico();
				mpg.setVisible(true);
			}
		});
		
		btnVolveralmenu.setBounds(564, 77, 179, 23);
		contentPane.add(btnVolveralmenu);

		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		
		btnSalir.setBounds(377, 405, 112, 23);
		contentPane.add(btnSalir);
		

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		list.setModel(modelo);
		for (Sociedad a : MenuPrincipalGrafico.mdp.listadoSociedades) {
			modelo.addElement(a.getIndice() + "-" + a.getNombredesoc());
		}
		
		//Este Boton Agregar datos, la funcion hara que despliegue uan ventana emergente que podemos ingresar los datos que solicita la funcion
		JButton btnAgergarDatos = new JButton("Agregar Datos");
		btnAgergarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ingresa a la sociedad en el archivo y nos pides los datos a ingresar ya que es dinamico
				Sociedad sociedad = MenuPrincipalGrafico.mdp.listadoSociedades.get(list.getSelectedIndex());
				String rutaOrigen = "C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
				String r = rutaOrigen + sociedad.getNombredesoc().trim() + ".dat";
				System.out.println(r);
				try {
					RandomAccessFile DatosdeTabla = new RandomAccessFile(
							r, "rw");
					DatosdeTabla.seek(DatosdeTabla.length());
					boolean valido;
					byte[] bytesString;
					String tmpString = "";
					for (Cualidades cualidades : sociedad.getCualidades()) {
						valido = false;
						System.out.println("Ingrese " + cualidades.getNombrec().trim());
						while (!valido) {
							switch (cualidades.getValorDato()) {
							// estas opciones ingresara dependiendo el tipo de dato de la cualidad que se ha creado con anterioridad
							case INT:
								int tmpInt = Integer
										.parseInt(JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec()));
								DatosdeTabla.writeInt(tmpInt);
								break;
							case LONG:
								long tmpLong = (long) Integer
										.parseInt(JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec()));
								;
								DatosdeTabla.writeLong(tmpLong);
								break;

							case FLOAT:
								float tmpFloat = Float
										.parseFloat(JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec()));
								DatosdeTabla.writeFloat(tmpFloat);
								break;

							case DOUBLE:
								double tmpDouble = Double
										.parseDouble(JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec()));
								DatosdeTabla.writeDouble(tmpDouble);
								break;

							case CHAR:
								int tamano = 0;
								do {
									tmpString = JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec());
									tamano = tmpString.length();
								} while (tamano < 1 || tamano > 1);
								byte caracter = (byte) tmpString.charAt(0);
								DatosdeTabla.writeByte(caracter);
								break;
							case STRING:
								do {
									tmpString = JOptionPane.showInputDialog("ingrese " + cualidades.getNombrec());
									tamano = tmpString.length();
									if (tamano <= 1 || tamano > cualidades.getTamano()) {
										System.out.println("La tamano de " + cualidades.getNombrec().trim()
												+ " no es valida [1 - " + cualidades.getTamano() + "]");
									}
								} while (tamano <= 0 || tamano > cualidades.getTamano());
								bytesString = new byte[cualidades.getTamano()];
								for (int i = 0; i < tmpString.length(); i++) {
									bytesString[i] = (byte) tmpString.charAt(i);
								}
								DatosdeTabla.write(bytesString);
								break;

							case DATE:
								Date date = null;
								tmpString = "";
								while (date == null) {
									tmpString = JOptionPane.showInputDialog(
											"ingrese " + cualidades.getNombrec() + " Formato de fecha: dd/MM/yyyy");
									
								}
								bytesString = new byte[cualidades.getBytes()];
								for (int i = 0; i < tmpString.length(); i++) {
									bytesString[i] = (byte) tmpString.charAt(i);
								}
								DatosdeTabla.write(bytesString);
								break;
							}
							valido = true;
						}

					}

				} catch (Exception error) {
					System.out.println(
							"Error " + error.getMessage() + " al capturar tipo de dato, vuelva a ingresar el valor: ");

				}

			}
		});
		btnAgergarDatos.setBounds(412, 154, 179, 23);
		contentPane.add(btnAgergarDatos);
	}
}
