package frontend.abms.extras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import backend.Console;
import frontend.MyDialog;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AgregarP extends JFrame implements ActionListener{
	
	private Console curConsole;
	private JButton btnOk;
	private JTextField txtIDalumno;
	private JTextField txtIDlibro;
	private JTextField txtFecha;
	
	public AgregarP (Console console) {
		
		this.curConsole = console;
		setSize(210,300);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblIdDeAlumno = new JLabel("ID de alumno");
		lblIdDeAlumno.setBounds(20, 40, 90, 14);
		getContentPane().add(lblIdDeAlumno);
		
		JLabel lblIdDeLibro = new JLabel("ID de libro");
		lblIdDeLibro.setBounds(120, 40, 72, 14);
		getContentPane().add(lblIdDeLibro);
		
		txtIDalumno = new JTextField();
		txtIDalumno.setBounds(10, 53, 86, 20);
		getContentPane().add(txtIDalumno);
		txtIDalumno.setColumns(10);
		
		txtIDlibro = new JTextField();
		txtIDlibro.setBounds(106, 53, 86, 20);
		getContentPane().add(txtIDlibro);
		txtIDlibro.setColumns(10);
		
		JLabel lblFechaDeDevolucion = new JLabel("Fecha de devolucion:");
		lblFechaDeDevolucion.setBounds(55, 117, 149, 14);
		getContentPane().add(lblFechaDeDevolucion);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(65, 147, 86, 20);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblFormatoDdmmaaaa = new JLabel("Formato: dd-MM-aaaa");
		lblFormatoDdmmaaaa.setBounds(44, 178, 137, 14);
		getContentPane().add(lblFormatoDdmmaaaa);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(65, 237, 89, 23);
		getContentPane().add(btnOk);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk){
			
			if(!txtIDalumno.getText().equals("") && !txtIDlibro.getText().equals("") && !txtFecha.getText().equals("")){
				
				try{
					
					int idAlumno = Integer.parseInt(txtIDalumno.getText());
					int idLibro = Integer.parseInt(txtIDlibro.getText());
					
					int result = curConsole.prestamoAdd(idAlumno, idLibro, txtFecha.getText());
					
					new MyDialog(result);
					this.dispose();
					
				} catch(NumberFormatException exe){
					
					new MyDialog(404);
					
				}
				
			} else {
				
				new MyDialog(406);
				
			}
			
		}
		
	}

}
