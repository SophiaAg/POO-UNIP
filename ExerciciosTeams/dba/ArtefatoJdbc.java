package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.dao.ArtefatoDao;
import br.unip.sicc.exercicio.view.model.Artefato;
import br.unip.sicc.exercicio.view.view.Categoria;
import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ArtefatoJdbc implements ArtefatoDao {
    private static final String SQL_INCLUIR =
            "INSERT INTO TB_ARTEFATO (NOME, CATEGORIA, NOME_IMAGEM, FORCA) VALUES (?, ?, ?, ?)";
    private static final String SQL_EXCLUIR =
            "DELETE FROM TB_ARTEFATO WHERE ID = ?;";

    @Override
    public void atualizar(Artefato artefato) throws DadosException {

    }

    @Override
    public void incluir(Artefato artefato) throws DadosException {
        if (artefato == null || artefato.getCategoria() == null) {
            throw new DadosException("Artefato e categoria são obrigatórios");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_INCLUIR);
            statement.setString(1, artefato.getNome());
            statement.setString(2, artefato.getCategoria().name());
            statement.setString(3, artefato.getNomeImagem());
            statement.setInt(4, artefato.getForca());
            int registrosIncluidos = statement.executeUpdate();
            if (registrosIncluidos == 0) {
                throw new DadosException("Nenhum artefato foi incluído");
            }
        } catch (SQLException ex) {
            throw new DadosException("Não foi possível incluir o artefato", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }

    @Override
    public void excluir(Artefato artefato) throws DadosException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_EXCLUIR);
            statement.setLong(1, artefato.getId());
            int registrosExcluidos = statement.executeUpdate();
            if (registrosExcluidos == 0) {
                throw new DadosException("Nenhum artefato encontrado para o ID " + artefato.getId());
            }
        } catch (SQLException ex) {
            throw new DadosException("Não foi possível excluir", ex);
        } finally {
            GerenciadorConexao.fechar(connection, statement);
        }
    }

    @Override
    public Artefato getPorId(Long id) throws DadosException {
        return null;
    }

    @Override
    public List<Artefato> getPorCategoria(Categoria categoria) throws DadosException {
        return null;
    }

    @Override
    public List<Artefato> getTodos() throws DadosException {
        return null;
    }
}
