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
    private static final String SQL_EXCLUIR =
            "DELETE FROM TB_ARTEFATO WHERE ID = ?;";

    @Override
    public void atualizar(Artefato artefato) throws DadosException {

    }

    @Override
    public void incluir(Artefato artefato) throws DadosException {

    }

    @Override
    public void excluir(Artefato artefato) throws DadosException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_EXCLUIR);
            statement.setLong(1, artefato.getId());
            statement.executeUpdate();
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
