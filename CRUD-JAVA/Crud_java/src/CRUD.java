import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;


public class CRUD {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtNomeID;
	private JTable table;
	private JTable table_funcionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUD window = new CRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CRUD() {
		initialize();
		listarValores();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 473, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AGENDA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(150, 0, 150, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Adicionar contato", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 42, 290, 129);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 23, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 51, 64, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Telefone:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 76, 74, 14);
		panel.add(lblNewLabel_1_2);
		
		txtNome = new JTextField();
		txtNome.setBounds(86, 22, 164, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(86, 50, 164, 20);
		panel.add(txtEmail);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(86, 75, 164, 20);
		panel.add(txtTelefone);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome, telefone, email;
				nome = txtNome.getText();
				telefone = txtTelefone.getText();
				email = txtEmail.getText();
				
				FunciDTO objfunciDTO = new FunciDTO();
				objfunciDTO.setNome(nome);
				objfunciDTO.setTelefone(telefone);
				objfunciDTO.setEmail(email);
				
				FuncionarioDAO objfuncidao = new FuncionarioDAO();
				objfuncidao.cadastrarFuncionario(objfunciDTO);
			}
		});
		btnNewButton.setBounds(10, 182, 89, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpar.setBounds(109, 182, 89, 33);
		frame.getContentPane().add(btnLimpar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 213, 290, 58);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtNomeID = new JTextField();
		txtNomeID.setColumns(10);
		txtNomeID.setBounds(89, 27, 164, 20);
		panel_1.add(txtNomeID);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nome ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 30, 69, 14);
		panel_1.add(lblNewLabel_1_3);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(315, 213, 132, 33);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(315, 257, 132, 33);
		frame.getContentPane().add(btnDeletar);
		
		table = new JTable();
		table.setBounds(336, 151, 55, -81);
		frame.getContentPane().add(table);
		
		table_funcionario = new JTable();
		table_funcionario.setBounds(299, 42, 158, 161);
		frame.getContentPane().add(table_funcionario);
	}
	private void listarValores()
	{
		try {
		FuncionarioDAO objFuncionarioDAO = new FuncionarioDAO();
		DefaultTableModel model = (DefaultTableModel) table_funcionario.getModel();
		model.setNumRows(0);
		ArrayList<FunciDTO> lista = objFuncionarioDAO.PesquisarContato();
		for(int num = 0; num < lista.size(); num ++)
		{
			model.addRow(new Object[] {
				lista.get(num).getId(),
				lista.get(num).getNome(),
				lista.get(num).getTelefone(),
				lista.get(num).getEmail()
			});
		}
		}catch (Exception erro) {
		JOptionPane.showMessageDialog(null, "Lista erro" + erro);
	}
	}
	}
