package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.*;

public class GerenciadorConexao {
	private static final String Servidor = "127.0.0.1";
	private static final String Porta = "3306";
	private static final String Schema = "jogo";//dps mudar para o meu
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/jogo";
	private static final String Usuario = "root";//dps mudar para o meu
	private static final String Senha = "R@oberto2";//dps mudar para o meu

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
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel desconectar ao banco de dados", ex);
		}
	}

	public static void fechar(Connection connection, Statement statement) throws DadosException {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel desconectar ao banco de dados", ex);
		} finally {
			fechar(connection);
		}
	}

	public static void fechar(Connection connection, PreparedStatement statement, ResultSet rst) throws DadosException {
		try {
			if (rst != null) {
				rst.close();
			}
		} catch (SQLException ex) {
			throw new DadosException("Não foi possivel desconectar ao banco de dados", ex);
		} finally {
			fechar(connection, statement);
		}
	}
}
