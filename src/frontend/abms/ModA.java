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

@SuppressWarnings("serial")
public class ModA extends JFrame implements ActionListener{

	private Console curConsole;
	private JTextField txtCodigo;
	private JButton btnBuscar, btnOk;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtNombre;
	private JTextField txtApellido;
	
	public ModA(Console console){
		
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
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(56, 252, 89, 23);
		getContentPane().add(btnOk);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 112, 46, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(37, 183, 46, 14);
		getContentPane().add(lblApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(93, 109, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(93, 180, 86, 20);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnBuscar){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				String[] datos = curConsole.alumnoSearch(givenID);
				
				if(datos != null){
					
					txtNombre.setText(datos[0]);
					
					txtApellido.setText(datos[1]);
					
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
				curConsole.alumnoDel(givenID);
				curConsole.alumnoAdd(txtNombre.getText(), txtApellido.getText(), givenID);
				new MyDialog(0);
				
			} catch (NumberFormatException ex) {
				
				txtCodigo.setText("");
				new MyDialog(404);
				
			}
			
		}
		
	}
	
}
