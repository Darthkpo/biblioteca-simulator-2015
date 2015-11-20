package frontend.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.Console;
import frontend.MyDialog;

import java.awt.Color;

@SuppressWarnings("serial")
public class AgregarA extends JFrame implements ActionListener{
	
	private JLabel lblNombre, lblCodigo;
	private JTextField txtNombre, txtCodigo;
	private JButton btnIngresar;
	private JTextArea lblError;
	
	private Console curConsole;
	private JLabel lblApellido;
	private JTextField txtApellido;
	
	public AgregarA(Console console){
		
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
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(20, 71, 46, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(73, 68, 100, 20);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		btnIngresar.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(210,300);
		setTitle("Añadir");
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnIngresar){
			
			if(!txtNombre.getText().equals("") && !txtCodigo.getText().equals("") && !txtApellido.getText().equals("")){
				
				try{
					
					int id = Integer.parseInt(txtCodigo.getText());
					
					int result = curConsole.alumnoAdd(txtNombre.getText(), txtApellido.getText(), id);
					
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