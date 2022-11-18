import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexaoDAO {
 public Connection conectaBD ()
 {
	 Connection conn = null;
	 
	 try {
		 String url = "jdbc:mysql://localhost:3306/crud?user=root&password=12344321";
		 conn = DriverManager.getConnection(url);
	 }catch (Exception erro) {
		 JOptionPane.showMessageDialog(null, "ConexaoDAO" + erro.getMessage());
	 }
	 return conn;
 }
}
