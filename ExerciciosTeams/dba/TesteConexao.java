package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

	public static void main(String[] args) throws Exception {
		testarAberturaEFechamento();
		testarSenhaInvalida();
		System.out.println("Testes de conexão concluídos com sucesso.");
	}

	private static void testarAberturaEFechamento() throws DadosException, SQLException {
		Connection conexao = GerenciadorConexao.getConnection();

		if (conexao == null) {
			throw new AssertionError("getConnection() retornou null");
		}

		GerenciadorConexao.fechar(conexao);

		if (!conexao.isClosed()) {
			throw new AssertionError("A conexão não foi fechada");
		}
	}

	private static void testarSenhaInvalida() {
		try {
			GerenciadorConexao.getConnection("aluno", "senha-invalida");
			throw new AssertionError("Uma senha inválida não deveria abrir a conexão");
		} catch (DadosException ex) {
			String mensagemEsperada = "Não foi possivel conectar ao banco de dados";
			if (!mensagemEsperada.equals(ex.getMessage())) {
				throw new AssertionError("Mensagem inesperada: " + ex.getMessage(), ex);
			}
			if (ex.getCause() == null) {
				throw new AssertionError("A SQLException original não foi preservada", ex);
			}
		}
	}
}
