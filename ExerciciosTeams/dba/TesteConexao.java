package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.model.Artefato;
import br.unip.sicc.exercicio.view.view.Categoria;
import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TesteConexao {

	public static void main(String[] args) throws Exception {
		testarAberturaEFechamento();
		testarSenhaInvalida();
		testarGetPorIdArtefato();
		//testarGetTodosArtefatos();
		//testarAtualizarArtefato();
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

	private static void testarGetTodosArtefatos() throws DadosException {
		List<Artefato> artefatos = new ArtefatoJdbc().getTodos();

		if (artefatos == null) {
			throw new AssertionError("getTodos() não deveria retornar null");
		}

		if (artefatos.isEmpty()) {
			System.out.println("Nenhum artefato encontrado.");
			return;
		}

		for (Artefato artefato : artefatos) {
			System.out.println(artefato);
		}
	}

	private static void testarGetPorIdArtefato() throws DadosException {
		ArtefatoJdbc dao = new ArtefatoJdbc();
		List<Artefato> artefatos = dao.getTodos();

		if (!artefatos.isEmpty()) {
			Artefato esperado = artefatos.get(0);
			Artefato encontrado = dao.getPorId(esperado.getId());
			if (!esperado.equals(encontrado)) {
				throw new AssertionError("O artefato retornado não corresponde ao registro com ID "
						+ esperado.getId());
			}
			System.out.println("Consulta por ID existente: " + encontrado);
		} else {
			System.out.println("Sem registros para testar a consulta por ID existente.");
		}

		long idInexistente = 99999L;
		boolean idJaExiste = true;
		while (idJaExiste) {
			idJaExiste = false;
			for (Artefato artefato : artefatos) {
				if (artefato.getId().equals(idInexistente)) {
					idInexistente++;
					idJaExiste = true;
					break;
				}
			}
		}

		Artefato naoEncontrado = dao.getPorId(idInexistente);
		if (naoEncontrado != null) {
			throw new AssertionError("Era esperado null para o ID inexistente " + idInexistente);
		}
		System.out.println("Consulta por ID inexistente (" + idInexistente + ") retornou null.");
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
