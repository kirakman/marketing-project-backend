package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// **Módulo de conexão**//
	/** The driver. */
	// parâmetros de conexao//
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbevento?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "narutoekill123";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// métodos de conexao
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD CREATE *.
	 *
	 * @param contato the contato
	 */
	public void adicionarEvento(JavaBeans evento) {
		String create = "insert into evento (nome,endereco,imagem) vaLues (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execucao no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Suvstituir os parametros pelo conteudo das variaveis JavaBeans
			pst.setString(1, evento.getNome());
			pst.setString(2, evento.getEndereco());
			pst.setString(3, evento.getImagem());
			// Executar a query
			pst.executeUpdate();
			// encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarEvento() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> evento = new ArrayList<>();
		String read = "select * from eventos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idev = rs.getString(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String imagem = rs.getString(4);
				// populando o ArrayList
				evento.add(new JavaBeans(idev, nome, endereco, imagem));
			}
			con.close();
			return evento;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	// selecionar o contato
	public void selecionarEvento(JavaBeans evento) {
		String read2 = "select * from evento where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, evento.getIdev());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variáveis JavaBeans
				evento.setIdev(rs.getString(1));
				evento.setNome(rs.getString(2));
				evento.setEndereco(rs.getString(3));
				evento.setImagem(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	// editar contato
	public void alterarEvento(JavaBeans evento) {
		String update = "update evento set nome=?,endereco=?,imagem=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, evento.getNome());
			pst.setString(2, evento.getEndereco());
			pst.setString(3, evento.getImagem());
			pst.setString(4, evento.getIdev());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	//CRUD DELETE
	public void deletarEvento(JavaBeans evento) {
		String delete = "delete from evento where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, evento.getIdev());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// teste de conexao
	// **public void testeConexao() {
	// try {
	// Connection con = conectar();
	// System.out.println(con);
	// con.close();
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }

}
