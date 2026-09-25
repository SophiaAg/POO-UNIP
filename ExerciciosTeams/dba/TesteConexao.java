package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.model.Artefato;
import br.unip.sicc.exercicio.view.view.Categoria;
import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteConexao {

	public static void main(String[] args) throws Exception {
		testarAberturaEFechamento();
		testarSenhaInvalida();
		testarAtualizarArtefato();
		//testarInclusaoArtefato();
		//testarExclusaoArtefato();
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

	private static void testarInclusaoArtefato() throws DadosException {
		String nome = "Artefato de teste " + System.currentTimeMillis();
		Artefato artefato = new Artefato(
				nome,
				Categoria.ATAQUE,
				"artefato-teste.png",
				7
		);

		new ArtefatoJdbc().incluir(artefato);
		System.out.println("Artefato incluído com sucesso: " + nome);
	}

	private static void testarAtualizarArtefato() throws DadosException {
		String nome = "Artefato Atualizado ";
		Artefato artefato = new Artefato(
                8L,
				nome,
				Categoria.ATAQUE,
				"artefato-teste.png",
				7
		);

		new ArtefatoJdbc().atualizar(artefato);
		System.out.println("Artefato atualizado com sucesso: " + nome);
	}

	private static void testarExclusaoArtefato() throws DadosException, SQLException {
		Connection conexao = null;
		Statement consulta = null;
		ResultSet resultado = null;

		try {
			conexao = GerenciadorConexao.getConnection();
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(
					"SELECT ID FROM TB_ARTEFATO ORDER BY ID LIMIT 1");

			if (!resultado.next()) {
				throw new AssertionError("A tabela TB_ARTEFATO não possui artefatos para excluir");
			}

			Artefato artefato = new Artefato();
			artefato.setId(resultado.getLong("ID"));
			new ArtefatoJdbc().excluir(artefato);

			System.out.println("Artefato com ID " + artefato.getId() + " excluído com sucesso.");
		} finally {
			if (resultado != null) {
				resultado.close();
			}
			GerenciadorConexao.fechar(conexao, consulta);
		}
	}
}
