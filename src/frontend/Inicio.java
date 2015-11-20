package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import backend.Console;
import frontend.abms.AgregarA;
import frontend.abms.AgregarL;
import frontend.abms.BorrarA;
import frontend.abms.BorrarL;
import frontend.abms.BuscarA;
import frontend.abms.BuscarL;
import frontend.abms.ModA;
import frontend.abms.ModL;
import frontend.abms.extras.PrestamosMenu;

@SuppressWarnings("serial")
public class Inicio extends JFrame implements ActionListener {
	
	private JLabel lblLibros, lblUsuarios;
	private JButton btnAgregarL, btnBuscarL, btnModL, btnAgregarU, btnBuscarU, btnModU, btnConsole;
	private Console curConsole;
	private JButton btnBorrarLibro;
	private JButton btnBorrarAlumno;
	private JSeparator separator;
	private JButton btnVerPrestamos;
	
	public Inicio(){
		setResizable(false);
		getContentPane().setLayout(null);
		btnAgregarL= new JButton("Agregar");
		btnBuscarL= new JButton("Buscar");
		btnModL= new JButton("Modificar");
		btnAgregarU= new JButton("Agregar");
		btnBuscarU= new JButton("Buscar");
		btnModU= new JButton("Modificar");
		lblLibros= new JLabel("Libros");
		lblUsuarios= new JLabel("Alumnos");
		btnAgregarL.setBounds(34,72,100,20);
		btnBuscarL.setBounds(34,103,100,20);
		btnModL.setBounds(34,134,100,20);
		btnAgregarU.setBounds(178,72,100,20);
		btnBuscarU.setBounds(178,103,100,20);
		btnModU.setBounds(178,134,100,20);
		btnAgregarL.addActionListener(this);
		btnBuscarL.addActionListener(this);
		btnModL.addActionListener(this);
		btnAgregarU.addActionListener(this);
		btnBuscarU.addActionListener(this);
		btnModU.addActionListener(this);
		lblLibros.setBounds(34,30,100,20);
		lblUsuarios.setBounds(178,30,100,20);
		getContentPane().add(btnAgregarL);
		getContentPane().add(btnAgregarL);
		getContentPane().add(btnBuscarL);
		getContentPane().add(btnModL);
		getContentPane().add(btnAgregarU);
		getContentPane().add(btnBuscarU);
		getContentPane().add(btnModU);
		getContentPane().add(lblLibros);
		getContentPane().add(lblUsuarios);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		btnConsole = new JButton("");
		btnConsole.addActionListener(this);
		btnConsole.setIcon(new ImageIcon(Inicio.class.getResource("/resources/favicon.png")));
		btnConsole.setBounds(0, 0, 23, 23);
		getContentPane().add(btnConsole);
		
		btnBorrarLibro = new JButton("Borrar");
		btnBorrarLibro.addActionListener(this);
		btnBorrarLibro.setBounds(34, 165, 100, 20);
		getContentPane().add(btnBorrarLibro);
		
		btnBorrarAlumno = new JButton("Borrar");
		btnBorrarAlumno.addActionListener(this);
		btnBorrarAlumno.setBounds(178, 165, 100, 20);
		getContentPane().add(btnBorrarAlumno);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(156, 21, 2, 159);
		getContentPane().add(separator);
		
		btnVerPrestamos = new JButton("Ver Prestamos");
		btnVerPrestamos.addActionListener(this);
		btnVerPrestamos.setBounds(86, 217, 146, 23);
		getContentPane().add(btnVerPrestamos);
		setSize(320,281);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Buen dia");
		curConsole = new Console(false);
		setVisible(true);
		this.addWindowListener( new WindowAdapter() {
		    public void windowClosed( WindowEvent e ){
		    	curConsole.dispose();
		    }
		}); 
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnConsole){
			
			curConsole.setVisible(!curConsole.isVisible());
			
		}
		
		if (e.getSource() == btnAgregarL) {

			new AgregarL(curConsole);
			
		}
		if (e.getSource() == btnBuscarL) {
			
			new BuscarL(curConsole);
			
		}
		if (e.getSource() == btnModL) {
			
			new ModL(curConsole);
			
		}
		if (e.getSource() == btnBorrarLibro) {
			
			new BorrarL(curConsole);
			
		}
		if(e.getSource() == btnAgregarU){
			
			new AgregarA(curConsole);
			
		}
		if(e.getSource() == btnBuscarU){
			
			new BuscarA(curConsole);
			
		}
		if (e.getSource() == btnModU) {
			
			new ModA(curConsole);
			
		}
		if (e.getSource() == btnBorrarAlumno) {
			
			new BorrarA(curConsole);
			
		}
		if(e.getSource() == btnVerPrestamos){
			
			new PrestamosMenu(curConsole);
			
		}
	}
}