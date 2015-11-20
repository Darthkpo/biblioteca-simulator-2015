package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Login  extends JFrame implements ActionListener{
	
	private JLabel lblNomb, lblPass,lblError;
	private JTextField txtNomb;
	private JPasswordField txtPass;
	private JButton btnEntrar;
	
	private String user = "root";
	
	public Login(){
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		lblNomb= new JLabel("Nombre");
		lblPass= new JLabel("Contraseña");
		lblError= new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomb=new JTextField();
		txtPass=new JPasswordField();
		btnEntrar= new JButton("Ingresar");
		lblNomb.setBounds(47,91,100,18);
		lblPass.setBounds(36,110,100,18);
		lblError.setBounds(10,139,274,30);
		txtNomb.setBounds(111,90,100,20);
		txtPass.setBounds(111,109,100,20);
		btnEntrar.setBounds(90,180,100,20);
		btnEntrar.addActionListener(this);
		getContentPane().add(lblNomb);
		getContentPane().add(lblPass);
		getContentPane().add(lblError);
		getContentPane().add(txtNomb);
		getContentPane().add(txtPass);
		getContentPane().add(btnEntrar);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(300,300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Login");
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String [] args){
		
		new Login();
		
	}
	
	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { '1', '2', '3', '4' };

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnEntrar) {
			
			if(txtNomb.getText().equals(user) && isPasswordCorrect(txtPass.getPassword())){
			
				new Inicio();
				this.dispose();
			
			} else {
				
				lblError.setText("Usuario o contraseña incorrectos");
				
			}
		}
		
	}

}
