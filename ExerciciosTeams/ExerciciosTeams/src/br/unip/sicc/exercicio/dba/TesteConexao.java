package br.unip.sicc.exercicio.dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
 public static void main(String[] args) {
	 String URL = "jdbc:mysql://192.168.56.1:3306/sakila";
	 String USER = "aluno";
	 String SENHA = "unip";
	 Connection connection = null;
	 
	 try {
		 System.out.println("Abrindo Conexao");
		connection = DriverManager.getConnection(URL, USER, SENHA);
		System.out.println("Conectado");
	} catch (SQLException e) {
		System.out.println("Erro ao conectar");
		e.printStackTrace();
	}finally {
		if(connection != null) {
			try {
				System.out.println("Fechando conexao");
				connection.close();
				System.out.println("Conexao fechada");
			} catch (SQLException e) {
				System.out.println("Erro ao desconectar");
				e.printStackTrace();
			}
		}
	}
 }
}
