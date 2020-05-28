package src.Proyect2;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class MenuPrincipalGrafico extends JFrame {
	private List<Sociedad> listadoSociedades = new ArrayList<>();
	private JPanel contentPane;
	private JTextField textField;
	Scanner sc = new Scanner(System.in);
	private String rutaOrigen="C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
	private  String direccionSociedad = "Sociedad.dat";
	private String direccionCualidades = "Cualidades.dat";
	private final int totalBytes = 90, bytesSociedad = 45, bytesCualidades = 45;
	private final static String formatoFecha = "dd/MM/yyyy";
	static DateFormat formato = new SimpleDateFormat(formatoFecha);
	public static MaindeProyecto2 mdp;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mdp = new MaindeProyecto2();
					mdp.accederArchivo();
									
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
		
		JLabel lblMensajedeBorrado = new JLabel("");
		lblMensajedeBorrado.setBounds(10, 201, 432, 23);
		contentPane.add(lblMensajedeBorrado);
		
		JButton btnAgregarSociedad = new JButton("1. Agregar Sociedad");
		btnAgregarSociedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuSociedadGrafico ms=new MenuSociedadGrafico();
				ms.setVisible(true);
			
			}
		});
		btnAgregarSociedad.setBounds(10, 31, 211, 23);
		contentPane.add(btnAgregarSociedad);
				
		JButton btnModificarSociedad = new JButton("2. Modificar Sociedad");
		btnModificarSociedad.setBounds(10, 65, 211, 23);
		contentPane.add(btnModificarSociedad);
		
		JButton btnenListarSociedad = new JButton("3. Enlistar Sociedad");
		btnenListarSociedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				EnlistarSociedadGrafico ens= new EnlistarSociedadGrafico();
				ens.setVisible(true);
								
			}
		});
		btnenListarSociedad.setBounds(10, 99, 211, 23);
		contentPane.add(btnenListarSociedad);
		
		JButton btnAgregardatosalaSociedad = new JButton("4. Agregar Datos a la Sociedad");
		btnAgregardatosalaSociedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AgregarDatosGraficos agd=new AgregarDatosGraficos();
				agd.setVisible(true);
			
			}
		});
		btnAgregardatosalaSociedad.setBounds(10, 133, 211, 23);
		contentPane.add(btnAgregardatosalaSociedad);
		
		JButton btnBorrarbasedeDatos = new JButton("5. Borrar Base de Datos");
		btnBorrarbasedeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblMensajedeBorrado.setText("Usted borro toda la base de datos ");		
				
				
			}
		});
		btnBorrarbasedeDatos.setBounds(10, 167, 211, 23);
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
	/*protected int getmostrarSociedad(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void mostrarSociedad(Sociedad sociedad) {
		// TODO Auto-generated method stub
		
	}*/
}
