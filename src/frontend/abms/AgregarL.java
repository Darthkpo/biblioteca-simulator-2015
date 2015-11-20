package frontend.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.Console;
import frontend.MyDialog;

import java.awt.Color;

@SuppressWarnings("serial")
public class AgregarL extends JFrame implements ActionListener{
	
	private JLabel lblNombre, lblCodigo;
	private JTextField txtNombre, txtCodigo;
	private JButton btnIngresar;
	private JTextPane txtPane;
	private JTextArea lblError;
	
	private Console curConsole;
	
	public AgregarL(Console console){
		
		curConsole = console;
		setResizable(false);
		getContentPane().setLayout(null);
		lblNombre= new JLabel("Nombre");
		lblCodigo= new JLabel("Codigo");
		lblError= new JTextArea("");
		lblError.setLineWrap(true);
		lblError.setBackground(UIManager.getColor("Button.background"));
		lblError.setForeground(Color.RED);
		txtNombre= new JTextField();
		txtCodigo= new JTextField();
		btnIngresar= new JButton("Ingresar");
		lblNombre.setBounds(20,10,100,20);
		lblCodigo.setBounds(20,40,100,20);
		lblError.setBounds(10,199,185,41);
		txtNombre.setBounds(73,10,100,20);
		txtCodigo.setBounds(73,40,100,20);
		btnIngresar.setBounds(50,240,100,20);
		getContentPane().add(lblNombre);
		getContentPane().add(lblCodigo);
		getContentPane().add(lblError);
		getContentPane().add(txtNombre);
		getContentPane().add(txtCodigo);
		getContentPane().add(btnIngresar);
		
		btnIngresar.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(64, 71, 86, 14);
		getContentPane().add(lblDescripcion);
		
		txtPane = new JTextPane();
		txtPane.setBounds(10, 91, 185, 109);
		getContentPane().add(txtPane);
		setSize(210,300);
		setTitle("Añadir");
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnIngresar){
			
			if(!txtNombre.getText().equals("") && !txtCodigo.getText().equals("") && !txtPane.getText().equals("")){
				
				String titulo = txtNombre.getText();
				String[] separatedTitle = titulo.split(" ");
				
				titulo = "";
				
				for(String word : separatedTitle){
					
					titulo = titulo + "-" + word;
					
				}
				
				String descripcion = txtPane.getText();
				String[] separatedDesc = descripcion.split(" ");
				
				descripcion = "";
				
				for(String word : separatedDesc){
					
					descripcion = descripcion + "-" + word;
					
				}
				
				try{
					
					int id = Integer.parseInt(txtCodigo.getText());
					
					int result = curConsole.libroAdd(titulo, descripcion, id);
					
					new MyDialog(result);
					
					this.dispose();
					
				} catch(NumberFormatException exe){
					
					lblError.setText("El valor de codigo debe ser un numero entero");
					
				}
				
			} else {
				
				lblError.setText("Debe ingresar todos los campos");
				
			}
			
		}
		
	}
}
