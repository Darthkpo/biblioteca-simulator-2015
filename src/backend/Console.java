package backend;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Console extends JFrame implements KeyListener{
	
	private JTextField inputField;
	private JTextPane outputField;
	private String lastCommand;
	
	public Console(boolean startVisible) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Console.class.getResource("/resources/favicon.png")));
		getContentPane().setFont(new Font("Lucida Console", Font.PLAIN, 11));
		getContentPane().setBackground(Color.BLACK);
		setBackground(Color.BLACK);
		setTitle("Console");
		getContentPane().setLayout(null);
		
		inputField = new JTextField();
		inputField.addKeyListener(this);
		inputField.setForeground(Color.GREEN);
		inputField.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		inputField.setBackground(Color.BLACK);
		inputField.setBounds(20, 521, 414, 20);
		getContentPane().add(inputField);
		inputField.setColumns(10);
		this.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		    	inputField.requestFocus();
		    }
		}); 
		
		outputField = new JTextPane();
		outputField.setEditable(false);
		outputField.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		outputField.setBackground(Color.BLACK);
		outputField.setForeground(Color.GREEN);
		outputField.setBounds(0, 0, 434, 510);
		getContentPane().add(outputField);
		
		JLabel label = new JLabel("-$");
		label.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		label.setForeground(Color.GREEN);
		label.setBounds(0, 524, 46, 14);
		getContentPane().add(label);
		setSize(439,571);
		cargarDriver();
		setVisible(startVisible);
		
	}
	
	public String[] libroSearch(int id){
		
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
			ResultSet registro = comando.executeQuery("select titulo,descripcion from libro where id=" + id);
			registro.next();

			String[] datos = new String[2];
			datos[0] = registro.getString("titulo");
			datos[1] = registro.getString("descripcion");
			
			conexion.close();
			
			return datos;

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public String[] alumnoSearch(int id){
		
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
			ResultSet registro = comando.executeQuery("select nombre,apellido from alumno where id=" + id);
			registro.next();

			String[] datos = new String[2];
			datos[0] = registro.getString("nombre");
			datos[1] = registro.getString("apellido");
			
			conexion.close();
			
			return datos;

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
		
	}

	public int libroAdd (String titulo, String descripcion, int id) {
		
		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
	        comando.executeUpdate("insert into libro(titulo,descripcion,id) values ('"+titulo+"','"+descripcion+"',"+id+")");
	        conexion.close();
	        return 0;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 2;
			
		}
		
	}
	
	public int alumnoAdd (String nombre, String apellido, int id) {
		
		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
	        comando.executeUpdate("insert into alumno(nombre,apellido,id) values ('"+nombre+"','"+apellido+"',"+id+")");
	        conexion.close();
	        return 0;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 2;
			
		}
		
	}

	public int libroDel (int id) {
		
		try {
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
			int cantidad = comando.executeUpdate("delete from libro where id="+id);
			conexion.close();
			if (cantidad==1) {

				return 0;
				
			} else {

				return 3;
				
			}
			
		} catch(SQLException ex){

			ex.printStackTrace();
			return 2;
			
		}
		
	}
	
	public int alumnoDel (int id) {
		
		try {
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
			int cantidad = comando.executeUpdate("delete from alumno where id="+id);
			conexion.close();
			if (cantidad==1) {

				return 0;
				
			} else {

				return 3;
				
			}
			
		} catch(SQLException ex){

			ex.printStackTrace();
			return 2;
			
		}
		
	}
	
	public int prestamoAdd (int idAlumno, int idLibro, String fecha){
		
		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date fechaBD = new java.sql.Date(dt.getTime());
        
        java.util.Date givendt;
		try {
			
			givendt = sdf.parse(fecha);
			
		} catch (ParseException ex) {

			return 3;
			
		}
        java.sql.Date fechaDBD = new java.sql.Date(givendt.getTime());
		
		try {
			
	          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
	          Statement comando=conexion.createStatement();
	          comando.executeUpdate("insert into libros_prestados(id_alumno,id_libro,fecha_prestamo,fecha_devolucion) values ("+idAlumno+","+idLibro+",'"+fechaBD+"','" + fechaDBD + "')");
	          conexion.close();
	          return 0;
	          
	    } catch(SQLException ex){
	          
	    	return 2;
	    	
	    }
		
	}
	
	public int prestamoDel (int idAlumno, int idLibro) {
		
		try {
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
			Statement comando=conexion.createStatement();
			int cantidad = comando.executeUpdate("delete from libros_prestados where id_alumno="+idAlumno+" and id_libro=" + idLibro);
			conexion.close();
			if (cantidad==1) {
				return 0;
			} else {
				return 3;
			}
		} catch(SQLException ex){

			return 2;
			
		}
		
	}
	
	public void executeCommand(){
	
		String command = inputField.getText();
		lastCommand = command;
		inputField.setText("");
		cout("-$ " + command);
		
		String[] splitedCommand = command.split(" ");
		switch(splitedCommand[0]){
		
			case "clear" : {
				
				outputField.setText("");
				break;
				
			}
			case "libros" : {
				
				if(splitedCommand.length < 3){
				
					cout("                                                           ");
					cout("-----------------------------------------------------------");
					cout("Error: sintaxis no reconocida");
					cout("-----------------------------------------------------------");
					cout("                                                           ");
					showHelp("libros");
					break;
					
				}
				
				String parameter = splitedCommand[1];
				
				switch(parameter){
				
					case "add" : {
						
						if(splitedCommand.length == 5){
							try {
								
						          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
						          Statement comando=conexion.createStatement();
						          comando.executeUpdate("insert into libro(titulo,descripcion,id) values ('"+splitedCommand[2]+"','"+splitedCommand[3]+"',"+splitedCommand[4]+")");
						          conexion.close();
						          cout("                                                           ");
						          cout("-----------------------------------------------------------");
						          cout("Se a agregado a el libro a la base de datos.");
						          cout("-----------------------------------------------------------");
						          cout("                                                           ");
						          
						    } catch(SQLException ex){
						          
						    	cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
						    	
						    }
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("libros");
							
						}
						break;
						
					}
					case "del" : {
						
						if(splitedCommand.length == 3){
							
							try {
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								int cantidad = comando.executeUpdate("delete from libro where id="+splitedCommand[2]);
								if (cantidad==1) {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("Se a borrado a un alumno de la base de datos.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								} else {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("No se a encontrado ningun alumno con esa id.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								}
								conexion.close();
							} catch(SQLException ex){

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}      
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("libros");
							
						}
						break;
						
					}
					case "ver" : {
						
						if(splitedCommand.length == 3){
							
							try {
								
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								ResultSet registro = comando.executeQuery("select titulo,descripcion from libro where id=" + splitedCommand[2]);
								registro.next();
								cout("                                                           ");
								cout("-----------------------------------------------------------");
								cout("El libro con la id " + splitedCommand[2] + " tiene estos datos: " + registro.getString("titulo") + ", " + registro.getString("descripcion"));
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								conexion.close();
								
							} catch (SQLException ex) {

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("libros");
							
						}
						break;
						
					}
					case "mod" : {
						
						if(splitedCommand.length == 5){
							
							try {
								
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								
								int cantidad = comando.executeUpdate("update libro set titulo='" + splitedCommand[3] + "'," +
                                        "descripcion='" + splitedCommand[4] + "' where id="+splitedCommand[2]);
							    if (cantidad==1) {
							    	
							    	cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("Se han modificado los datos del libro correctamente.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
							         
							    } else {
							    	
							    	cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("No se a encontrado ningun libro con esa id.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
							         
							    }
								
								conexion.close();
								
							} catch(SQLException ex){

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}      
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("libros");
							
						}
						break;
						
					}
					default : {
						
						cout("                                                           ");
						cout("-----------------------------------------------------------");
						cout("Error: sintaxis no reconocida");
						cout("                                                           ");
						showHelp("libros");
						break;
						
					}
				
				}
				break;
				
			}
			case "plibro" : {
				
				if(splitedCommand.length < 3){
					
					cout("                                                           ");
					cout("-----------------------------------------------------------");
					cout("Error: sintaxis no reconocida");
					cout("                                                           ");
					showHelp("plibro");
					break;
					
				}
				
				String parameter = splitedCommand[1];
				
				switch(parameter){
				
					case "add" : {
						
						if(splitedCommand.length == 5){
						
							java.util.Date dt = new java.util.Date();
					        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
					        java.sql.Date fechaBD = new java.sql.Date(dt.getTime());
					        
					        java.util.Date givendt;
							try {
								givendt = sdf.parse(splitedCommand[4]);
							} catch (ParseException ex) {
								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								break;
							}
					        java.sql.Date fechaDBD = new java.sql.Date(givendt.getTime());
							
							try {
								
						          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
						          Statement comando=conexion.createStatement();
						          comando.executeUpdate("insert into libros_prestados(id_alumno,id_libro,fecha_prestamo,fecha_devolucion) values ("+splitedCommand[2]+","+splitedCommand[3]+",'"+fechaBD+"','" + fechaDBD + "')");
						          conexion.close();
						          cout("                                                           ");
						          cout("-----------------------------------------------------------");
						          cout("Se a agregado un prestamo a la basde de datos.");
						          cout("                                                           ");
						          
						    } catch(SQLException ex){
						          
						    	cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
						    	
						    }
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("-----------------------------------------------------------");
							cout("                                                           ");
							showHelp("plibro");
							break;
							
						}
						break;
						
					}
					case "del" : {
						
						if(splitedCommand.length == 4){
							
							try {
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								int cantidad = comando.executeUpdate("delete from libros_prestados where id_alumno="+splitedCommand[2]+" and id_libro=" + splitedCommand[3]);
								if (cantidad==1) {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("Se a borrado un prestamo de la base de datos.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								} else {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("No se a encontrado ningun prestamo con esas ids.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								}
								conexion.close();
							} catch(SQLException ex){

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}
							
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("-----------------------------------------------------------");
							cout("                                                           ");
							showHelp("plibro");
							break;
							
						}
						break;
						
					}
					default : {
						
						cout("                                                           ");
						cout("-----------------------------------------------------------");
						cout("Error: sintaxis no reconocida");
						cout("                                                           ");
						showHelp("plibro");
						break;
						
					}
					
				}
				
				break;
				
			}
			case "alumnos" : {
				
				if(splitedCommand.length < 3){
				
					cout("                                                           ");
					cout("-----------------------------------------------------------");
					cout("Error: sintaxis no reconocida");
					cout("-----------------------------------------------------------");
					cout("                                                           ");
					showHelp("alumnos");
					break;
					
				}
				
				String parameter = splitedCommand[1];
				
				switch(parameter){
				
					case "add" : {
						
						if(splitedCommand.length == 5){
							
							try {
								
						          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
						          Statement comando=conexion.createStatement();
						          comando.executeUpdate("insert into alumno(nombre,apellido,id) values ('"+splitedCommand[2]+"','"+splitedCommand[3]+"',"+splitedCommand[4]+")");
						          conexion.close();
						          cout("                                                           ");
						          cout("-----------------------------------------------------------");
						          cout("Se a agregado a el alumno a la base de datos.");
						          cout("-----------------------------------------------------------");
						          cout("                                                           ");
						          
						    } catch(SQLException ex){
						          
						    	cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
						    	
						    }
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("alumnos");
							
						}
						break;
						
					}
					case "del" : {
						
						if(splitedCommand.length == 3){
							
							try {
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								int cantidad = comando.executeUpdate("delete from alumno where id="+splitedCommand[2]);
								if (cantidad==1) {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("Se a borrado a un alumno de la base de datos.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								} else {
									cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("No se a encontrado ningun alumno con esa id.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
								}
								conexion.close();
							} catch(SQLException ex){

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}      
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("alumnos");
							
						}
						break;
						
					}
					case "ver" : {
						
						if(splitedCommand.length == 3){
							
							try {
								
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								ResultSet registro = comando.executeQuery("select nombre,apellido from alumno where id=" + splitedCommand[2]);
								registro.next();
								cout("                                                           ");
								cout("-----------------------------------------------------------");
								cout("El alumno con la id " + splitedCommand[2] + " tiene estos datos: " + registro.getString("nombre") + ", " + registro.getString("apellido"));
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								conexion.close();
								
							} catch (SQLException ex) {

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("alumnos");
							
						}
						break;
						
					}
					case "mod" : {
						
						if(splitedCommand.length == 5){
							
							try {
								
								Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root" ,"");
								Statement comando=conexion.createStatement();
								
								int cantidad = comando.executeUpdate("update alumno set nombre='" + splitedCommand[3] + "'," +
                                        "apellido='" + splitedCommand[4] + "' where id="+splitedCommand[2]);
							    if (cantidad==1) {
							    	
							    	cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("Se han modificado los datos del alumno correctamente.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
							         
							    } else {
							    	
							    	cout("                                                           ");
									cout("-----------------------------------------------------------");
									cout("No se a encontrado ningun alumno con esa id.");
									cout("-----------------------------------------------------------");
									cout("                                                           ");
							         
							    }
								
								conexion.close();
								
							} catch(SQLException ex){

								cout("                                                           ");
								cout(ex.getMessage());
								cout("-----------------------------------------------------------");
								cout("                                                           ");
								
							}      
						
						} else {
							
							cout("                                                           ");
							cout("-----------------------------------------------------------");
							cout("Error: sintaxis no reconocida");
							cout("                                                           ");
							showHelp("alumnos");
							
						}
						break;
						
					}
					default : {
						
						cout("                                                           ");
						cout("-----------------------------------------------------------");
						cout("Error: sintaxis no reconocida");
						cout("                                                           ");
						showHelp("alumnos");
						break;
						
					}
				
				}
				break;
				
			}
			
			default : {
				
				cout("                                                           ");
				cout("-----------------------------------------------------------");
				cout("Error: sintaxis no reconocida");
				cout("-----------------------------------------------------------");
				cout("                                                           ");
				break;
				
			}
		
		}
		
	}
	
	//Console OUT
	private void cout(String text) {
		
		//string lenght 60
		for(int i = 0; i < text.length(); i++){
			
			if(i % 60 == 0)
				outputField.setText(outputField.getText() + "\n");
			outputField.setText(outputField.getText() + text.charAt(i));
			
		}
		
		int totalCharacters = outputField.getText().length(); 
		int lineCount = (totalCharacters == 0) ? 1 : 0;

		try {
		   int offset = totalCharacters; 
		   while (offset > 0) {
		      offset = Utilities.getRowStart(outputField, offset) - 1;
		      lineCount++;
		   }
		} catch (BadLocationException e) {
		    e.printStackTrace();
		}
		if(lineCount >= 42){
			
			outputField.setText(outputField.getText().substring(61, outputField.getText().length()));
			
		}
		
	}
	
	private void cargarDriver() {
		
	    try {
	    	
	      Class.forName("com.mysql.jdbc.Driver");
	      
	    }catch(Exception ex) {
	    	
	      setTitle(ex.toString());
	      
	    }
	}
	
	private void showHelp(String command){
		
		switch(command){
		
			case "alumnos" : {
				
				cout("[AYUDA] - Comando 'alumnos'                                ");
				cout("Descripcion: para hacer consultas o modificaciones en la tabla 'alumno' en la base de datos de la biblioteca.");
				cout("                                                           ");
				cout("Formato: alumnos + [parametro] + [variables]");
				cout("                                                           ");
				cout("[Parametros] - add nombre apellido id");
				cout("             - del id");
				cout("             - ver id");
				cout("-----------------------------------------------------------");
				cout("                                                           ");
				break;
				
			}
			case "plibro" : {
				
				cout("[AYUDA] - Comando 'plibro'                                ");
				cout("Descripcion:  para insertar un prestamo de un libro en la tabla 'libros_prestados' en la base de datos de la biblioteca.");
				cout("                                                           ");
				cout("Formato: plibro + [parametro] + [variables]");
				cout("                                                           ");
				cout("[Parametros] - add id_alumno id_libro fecha_devolucion");
				cout("             - del id_alumno id_libro");
				cout("-----------------------------------------------------------");
				cout("                                                           ");
				break;
				
			}
			case "libros" : {
				
				cout("[AYUDA] - Comando 'libros'                                ");
				cout("Descripcion: para hacer consultas o modificaciones en la tabla 'libro' en la base de datos de la biblioteca.");
				cout("                                                           ");
				cout("Formato: libros + [parametro] + [variables]");
				cout("                                                           ");
				cout("[Parametros] - add titulo descripcion id");
				cout("             - del id");
				cout("             - ver id");
				cout("-----------------------------------------------------------");
				cout("                                                           ");
				break;
				
			}
		
		}
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		
	}

	@Override
	public void keyReleased(KeyEvent key) {

		if(key.getKeyCode() == KeyEvent.VK_ENTER){
			
			executeCommand();
			
		}
		if(key.getKeyCode() == KeyEvent.VK_UP){
			
			inputField.setText(lastCommand);
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}
}
