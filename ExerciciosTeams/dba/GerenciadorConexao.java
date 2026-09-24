package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorConexao {
	private static final String Servidor = "127.0.0.1";
	private static final String Porta = "3306";
	private static final String Schema = "sakila";//dps mudar para o meu
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/sakila";
	private static final String Usuario = "aluno";//dps mudar para o meu
	private static final String Senha = "unip";//dps mudar para o meu

	public static Connection getConnection() throws DadosException {
		return getConnection(Usuario, Senha);
	}

	static Connection getConnection(String usuario, String senha) throws DadosException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel conectar ao banco de dados", ex);
		}
		return connection;
	}

	public static void fechar(Connection connection) throws DadosException {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel desconectar ao banco de dados", ex);
		}
	}
}
