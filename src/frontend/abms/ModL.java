package frontend.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import backend.Console;
import frontend.MyDialog;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ModL extends JFrame implements ActionListener{

	private Console curConsole;
	private JTextField txtCodigo;
	private JTextField txtTitulo;
	private JButton btnBuscar, btnOk;
	private JTextArea textArea;
	
	public ModL(Console console){
		
		this.curConsole = console;
		setSize(210,325);
		getContentPane().setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(56, 11, 90, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(56, 32, 90, 20);
		getContentPane().add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 60, 175, 2);
		getContentPane().add(separator);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(80, 63, 46, 14);
		getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(56, 78, 86, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(66, 99, 80, 14);
		getContentPane().add(lblDescripcion);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 113, 175, 137);
		getContentPane().add(textArea);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(56, 252, 89, 23);
		getContentPane().add(btnOk);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnBuscar){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				String[] datos = curConsole.libroSearch(givenID);
				
				if(datos != null){
					
					txtTitulo.setText(datos[0]);
					
					String[] desc = datos[1].split("-");
					datos[1] = "";
					for(String word : desc){
						
						datos[1] = datos[1] + " " + word;
						
					}
					
					textArea.setText(datos[1]);
					
				} else {
					
					txtCodigo.setText("");
					new MyDialog(405);
					
				}
				
			} catch (NumberFormatException ex) {
				
				txtCodigo.setText("");
				new MyDialog(404);
				
			}
			
		}
		if(e.getSource() == btnOk){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				curConsole.libroDel(givenID);
				curConsole.libroAdd(txtTitulo.getText(), textArea.getText(), givenID);
				new MyDialog(0);
				
			} catch (NumberFormatException ex) {
				
				txtCodigo.setText("");
				new MyDialog(404);
				
			}
			
		}
		
	}
	
}
