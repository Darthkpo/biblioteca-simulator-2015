package frontend.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import backend.Console;
import frontend.MyDialog;

@SuppressWarnings("serial")
public class BorrarA extends JFrame implements ActionListener{
	
	private JTextField txtCodigo;
	private JButton btnBuscar;
	private Console curConsole;
	private JButton btnBorrar;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblFNombre;
	private JLabel lblFApellido;
	
	public BorrarA (Console console) {
		setResizable(false);
		
		this.curConsole = console;
		
		setSize(210,325);
		getContentPane().setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(50, 11, 90, 24);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(50, 36, 90, 24);
		getContentPane().add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 175, 2);
		getContentPane().add(separator);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(51, 252, 89, 23);
		getContentPane().add(btnBorrar);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 117, 46, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(33, 186, 46, 14);
		getContentPane().add(lblApellido);
		
		lblFNombre = new JLabel("");
		lblFNombre.setBounds(89, 117, 105, 14);
		getContentPane().add(lblFNombre);
		
		lblFApellido = new JLabel("");
		lblFApellido.setBounds(89, 186, 105, 14);
		getContentPane().add(lblFApellido);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnBuscar){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				String[] datos = curConsole.alumnoSearch(givenID);
				
				if(datos != null){
					
					lblFNombre.setText(datos[0]);
					
					lblFApellido.setText(datos[1]);
					
				} else {
					
					txtCodigo.setText("");
					new MyDialog(405);
					
				}
				
			} catch (NumberFormatException ex) {
				
				txtCodigo.setText("");
				new MyDialog(404);
				
			}
			
		}
		if(e.getSource() == btnBorrar){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				curConsole.alumnoDel(givenID);
				new MyDialog(0);
				
			} catch (NumberFormatException ex) {
				
				txtCodigo.setText("");
				new MyDialog(404);
				
			}
			
		}
		
	}
}