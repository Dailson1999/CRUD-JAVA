	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;
public class CRUD_banco {

	public static void main(String[] args) {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/crud";
	String user = "user";
	String password = "12344321";
		try {
			//Registrar o driver
			Class.forName(driver);
			String query = "SELECT * FROM contatos";
			try {
				//Estabelecer conexão
				Connection con = DriverManager.getConnection(url, user, password);
		 // Criando o objeto statement - usado para executar consultas
		Statement st = con.createStatement();
	// O método executeQuery retorna um objeto ResultSet object o qual
		//representa o resultado da consulta.
		ResultSet rs = st.executeQuery(query);
		int colNum = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			for (int i=1; i<=colNum; i++)
			{
				System.out.print(rs.getString(i)+" ");
			}
			System.out.println();
		}
		rs.close();
		st.close();
		con.close();
			} catch (SQLException e) {
				System.out.println(e);
			} 
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		}

	}
