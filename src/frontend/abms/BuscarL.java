package frontend.abms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import backend.Console;
import frontend.MyDialog;

import java.awt.SystemColor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BuscarL extends JFrame implements ActionListener{
	
	private JTextField txtCodigo;
	private JButton btnBuscar;
	private Console curConsole;
	private JTextArea textArea;
	private JLabel lblgTitulo;
	
	public BuscarL (Console console) {
		setResizable(false);
		
		this.curConsole = console;
		
		setSize(210,300);
		getContentPane().setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(50, 11, 90, 24);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(50, 36, 90, 24);
		getContentPane().add(btnBuscar);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(78, 84, 46, 14);
		getContentPane().add(lblTitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 175, 2);
		getContentPane().add(separator);
		
		lblgTitulo = new JLabel("");
		lblgTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblgTitulo.setForeground(SystemColor.textHighlight);
		lblgTitulo.setBounds(10, 98, 174, 14);
		getContentPane().add(lblgTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(65, 109, 73, 14);
		getContentPane().add(lblDescripcion);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(SystemColor.textHighlight);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setEditable(false);
		textArea.setBounds(10, 123, 174, 127);
		getContentPane().add(textArea);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnBuscar){
			
			try{
				
				int givenID = Integer.parseInt(txtCodigo.getText());
				String[] datos = curConsole.libroSearch(givenID);
				
				if(datos != null){
					
					lblgTitulo.setText(datos[0]);
					
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
		
	}
}
