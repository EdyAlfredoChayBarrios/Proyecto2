package src.Proyect2;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class DatosTablaGrafico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public List<Cualidades> cualidadesSociedad;

	//Metodo que se crea la ventana de tabla de datos 
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 128, 866, 306);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSelecciondesociedad = new JLabel("Seleccione la sociedad existente");
		lblSelecciondesociedad.setBounds(31, 80, 302, 14);
		contentPane.add(lblSelecciondesociedad);
		
		JComboBox list = new JComboBox();
		list.setBounds(253, 77, 236, 22);
		contentPane.add(list);

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		list.setModel(modelo);
		for (Sociedad a : MenuPrincipalGrafico.mdp.listadoSociedades) {
			modelo.addElement(a.getIndice() + "-" + a.getNombredesoc());
		}
		
		JComboBox list1 = new JComboBox();
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sociedad = list.getSelectedItem().toString();
				String strindice = sociedad.split("-")[0];
				for (Sociedad s : MenuPrincipalGrafico.mdp.listadoSociedades) {
					if (s.getIndice() == Integer.parseInt(strindice)) {
						ArrayList<Object[]> elementos = MenuPrincipalGrafico.mdp.datosTabla(s);
						DefaultTableModel tableModel  = new DefaultTableModel();
						cualidadesSociedad = s.getCualidades();
						for (Cualidades cualidad : s.getCualidades()) {
							tableModel.addColumn(cualidad.getNombrec());
						}
						for(Object[] item : elementos) {
							tableModel.addRow(item);
						}
						
						table.setModel(tableModel);
						
						break;
					}

				}
			}

		});
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
		
		
		//Este boton cuenta con propia funcio de eliminar un dato seleccionado en la tabla
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				String sociedadSeleccionada = list.getSelectedItem().toString();
				String nombreSociedad = sociedadSeleccionada.split("-")[1];
				
				Sociedad sociedad = MenuPrincipalGrafico.mdp.listadoSociedades.get(list.getSelectedIndex());
				String rutaOrigen = "C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
				String r = rutaOrigen + sociedad.getNombredesoc().trim() + ".dat";
				try {
					RandomAccessFile DatosdeTabla = new RandomAccessFile(r, "rw");
					DatosdeTabla.seek(DatosdeTabla.length());
					boolean valido;
					byte[] bytesString;
					String tmpString = "";
					int tamanio = 0;
					for (Cualidades cualidades : sociedad.getCualidades()) {
							switch (cualidades.getValorDato()) {
							case INT:
								tamanio += Integer.BYTES;
								break;
							case LONG:
								tamanio += Long.BYTES;
								break;

							case FLOAT:
								tamanio += Float.BYTES;
								break;

							case DOUBLE:
								tamanio +=Double.BYTES;
								break;

							case CHAR:
								tamanio+= 1;
								break;
							case STRING:
								tamanio+=20;
								break;

							case DATE:
								tamanio+=20;
								break;
							}
					}
					
					int position = fila * tamanio;
					DatosdeTabla.seek(position);
					
					for (Cualidades cualidades : sociedad.getCualidades()) {
						
						switch (cualidades.getValorDato()) {
						case INT:
							int tmpInt ;
							DatosdeTabla. writeInt(0);
							break;
						case LONG:
							DatosdeTabla.writeLong(0);
							break;

						case FLOAT:
							
							DatosdeTabla.writeFloat(0);
							break;

						case DOUBLE:
							DatosdeTabla.writeDouble(0);
							break;

						case CHAR:							
							DatosdeTabla.writeByte(' ');
							break;
						case STRING:
							bytesString = new byte[cualidades.getTamano()];
							DatosdeTabla.write(bytesString);
							break;

						case DATE:							
							bytesString = new byte[cualidades.getBytes()];
							DatosdeTabla.write(bytesString);
							break;
						}

					}
								
					

				} catch (Exception error) {
					System.out.println(
							"Error " + error.getMessage() + " al capturar tipo de dato, vuelva a ingresar el valor: ");

				}
				
			}
		});
		btnBorrar.setBounds(563, 34, 89, 23);
		contentPane.add(btnBorrar);
		
	}
}
