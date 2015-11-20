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
public class BorrarP extends JFrame implements ActionListener{
	
	private Console curConsole;
	private JButton btnOk;
	private JTextField txtIDalumno;
	private JTextField txtIDlibro;
	
	public BorrarP (Console console) {
		
		this.curConsole = console;
		setSize(210,300);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblIdDeAlumno = new JLabel("ID de alumno");
		lblIdDeAlumno.setBounds(22, 112, 90, 14);
		getContentPane().add(lblIdDeAlumno);
		
		JLabel lblIdDeLibro = new JLabel("ID de libro");
		lblIdDeLibro.setBounds(122, 112, 72, 14);
		getContentPane().add(lblIdDeLibro);
		
		txtIDalumno = new JTextField();
		txtIDalumno.setBounds(10, 124, 86, 20);
		getContentPane().add(txtIDalumno);
		txtIDalumno.setColumns(10);
		
		txtIDlibro = new JTextField();
		txtIDlibro.setBounds(106, 124, 86, 20);
		getContentPane().add(txtIDlibro);
		txtIDlibro.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(65, 237, 89, 23);
		getContentPane().add(btnOk);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk){
			
			if(!txtIDalumno.getText().equals("") && !txtIDlibro.getText().equals("")){
				
				try{
					
					int idAlumno = Integer.parseInt(txtIDalumno.getText());
					int idLibro = Integer.parseInt(txtIDlibro.getText());
					
					int result = curConsole.prestamoDel(idAlumno, idLibro);
					
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