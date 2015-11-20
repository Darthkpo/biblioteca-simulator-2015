package frontend;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyDialog extends JFrame implements ActionListener{
	
	private JButton btnOk;
	
	public MyDialog(int type) {
		setResizable(false);
		
		setSize(300,150);
		
		JLabel lblText = new JLabel("");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblText, BorderLayout.CENTER);
		
		switch(type){
		
			case 0 : {
				
				lblText.setText("Se a completado la sulicitud correctamente");
				break;
				
			}
			case 1 : {
				
				lblText.setText("Error de sintaxis.");
				break;
				
			}
			case 2 : {
				
				lblText.setText("Error de conexion con la base de datos.");
				break;
				
			}
			case 3 : {
				
				lblText.setText("No se a encontrado una entrada con esos datos.");
				break;
				
			}
			case 404 : {
				
				lblText.setText("Debe introducir un numero entero en ese campo.");
				break;
				
			}
			case 405 : {
				
				lblText.setText("No se han encontrado coincidencias.");
				break;
				
			}
			case 406 : {
				
				lblText.setText("Todos los campos son requeridos.");
				break;
				
			}
		
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		getContentPane().add(btnOk, BorderLayout.SOUTH);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnOk){
			
			this.dispose();
			
		}
		
	}

}
