package frontend.abms.extras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import backend.Console;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PrestamosMenu extends JFrame implements ActionListener{
	
	private Console curConsole;
	private JButton btnBorrar, btnNuevo; 
	
	public PrestamosMenu (Console console) {
		
		this.curConsole = console;
		setSize(210,300);
		setResizable(false);
		getContentPane().setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(56, 100, 89, 23);
		getContentPane().add(btnNuevo);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(56, 144, 89, 23);
		getContentPane().add(btnBorrar);
		
		JLabel lblMenuDePrestamos = new JLabel("Menu de prestamos");
		lblMenuDePrestamos.setBounds(52, 37, 152, 14);
		getContentPane().add(lblMenuDePrestamos);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnNuevo){
			
			new AgregarP(curConsole);
			
		}
		if(e.getSource() == btnBorrar){
			
			new BorrarP(curConsole);
			
		}
		
	}

}
