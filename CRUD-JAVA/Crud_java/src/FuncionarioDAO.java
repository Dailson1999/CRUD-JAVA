

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class FuncionarioDAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	//Criando a o array tipo lista para pegar informaçoes do banco quantas linhas tiver.
	ArrayList<FunciDTO> lista = new ArrayList<>();
	
	public void cadastrarFuncionario(FunciDTO objf)
	{
		String query = "INSERT INTO crud.contatos (id, nome, telefone, email) VALUES (?, ?, ?, ?)";
		conn = new ConexaoDAO().conectaBD();
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(0, objf.getId());
			pstm.setString(1, objf.getNome());
			pstm.setString(2, objf.getTelefone());
			pstm.setString(3, objf.getEmail());
			

			pstm.execute();
			pstm.close();
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "FuncionarioDAO Cadastrar" + erro);
		}
	}
	//é tipo arraylist porque para retorna uma lista o metodo precissa ser do tipo arraylist.
	public ArrayList<FunciDTO> PesquisarContato()
	{
		String sql = "select * from crud.contatos";
		conn = new ConexaoDAO().conectaBD();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			//estrutura de repetição para trazer todos registros
			//para depois ser adicionado na lista criada
			while(rs.next()) {
				FunciDTO objfDTO = new FunciDTO();
				//Pega informação do banco passa para classe FunciDTO.
				//objfDTO.setId(rs.getInt("Id"));
				objfDTO.setNome(rs.getString("Nome"));
				objfDTO.setTelefone(rs.getString("Telefone"));
				objfDTO.setEmail(rs.getString("E-mail"));
				//está adicionando os obj tirados do banco linha por linha na lista.
				//Cada linha que tiver será adicionada na lista, todos registros diferente que tiver
				//será adicionado na lista
				lista.add(objfDTO);
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: " + erro);
		}
		//retorna a lista pronta com todas informaçoes.
		return lista;
	}
}
