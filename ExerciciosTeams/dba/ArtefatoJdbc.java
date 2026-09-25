package br.unip.sicc.exercicio.view.dba;

import br.unip.sicc.exercicio.view.dao.ArtefatoDao;
import br.unip.sicc.exercicio.view.model.Artefato;
import br.unip.sicc.exercicio.view.view.Categoria;
import br.unip.sicc.exercicio.view.view.DadosException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtefatoJdbc implements ArtefatoDao {
    private static final String SQL_INCLUIR =
            "INSERT INTO TB_ARTEFATO (NOME, CATEGORIA, NOME_IMAGEM, FORCA) VALUES (?, ?, ?, ?)";
    private static final String SQL_EXCLUIR =
            "DELETE FROM TB_ARTEFATO WHERE ID = ?;";
    private static final String SQL_ATUALIZAR =
            "UPDATE TB_ARTEFATO SET NOME = ?, CATEGORIA = ? , NOME_IMAGEM = ? , FORCA = ? WHERE ID = ?";
    private static final String SQL_CONSULTAR =
            "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA FROM TB_ARTEFATO";
    private static final String SQL_CONSULTAR_POR_ID =
            "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA FROM TB_ARTEFATO WHERE ID = ?;";
    private static final String SQL_CONSULTAR_POR_CATEGORIA =
            "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA FROM TB_ARTEFATO WHERE CATEGORIA = ?;";
    @Override
    public void atualizar(Artefato artefato) throws DadosException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_ATUALIZAR);
            statement.setString(1, artefato.getNome());
            statement.setString(2, artefato.getCategoria().name());
            statement.setString(3, artefato.getNomeImagem());
            statement.setInt(4, artefato.getForca());
            statement.setLong(5, artefato.getId());
            int registrosAtualizados = statement.executeUpdate();
            if (registrosAtualizados == 0) {
                throw new DadosException("Nenhum artefato foi atualizado");
            }

        } catch (SQLException e) {
            throw new RuntimeException( "Não foi possível atualizar", e);
        }finally {
            GerenciadorConexao.fechar(connection, statement);
        }
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
        Artefato artefato = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rst = null;
        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_CONSULTAR_POR_ID);
            statement.setLong(1, id);
            rst = statement.executeQuery();
            if (rst.next()) {
                long artefatoId = rst.getLong("ID");
                String nome = rst.getString("NOME");
                String categoriaStr = rst.getString("CATEGORIA");
                Categoria categoria = Categoria.valueOf(categoriaStr);
                String nomeImagem = rst.getString("NOME_IMAGEM");
                int forca = rst.getInt("FORCA");
                artefato = new Artefato(artefatoId, nome, categoria, nomeImagem, forca);
            }
        } catch (SQLException e) {
            throw new DadosException("Não foi possível selecionar", e);
        } finally {
            GerenciadorConexao.fechar(connection, statement, rst);
        }
        return artefato;
    }

    @Override
    public List<Artefato> getPorCategoria(Categoria categoria) throws DadosException {
        List<Artefato> listaArtefatos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rst = null;
        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_CONSULTAR_POR_CATEGORIA);
            statement.setString(1, categoria.name());
            rst = statement.executeQuery();
            while (rst.next()) {
                long id = rst.getLong("ID");
                String nome = rst.getString("NOME");
                String nomeImagem = rst.getString("NOME_IMAGEM");
                int forca = rst.getInt("FORCA");
                Artefato artefato = new Artefato(id, nome, categoria, nomeImagem, forca);
                listaArtefatos.add(artefato);
            }
        } catch (SQLException e) {
            throw new DadosException("Não foi possível selecionar", e);
        } finally {
            GerenciadorConexao.fechar(connection, statement, rst);
        }
        return listaArtefatos;
    }

    @Override
    public List<Artefato> getTodos() throws DadosException {
        List<Artefato> listaArtefatos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rst = null;

        try {
            connection = GerenciadorConexao.getConnection();
            statement = connection.prepareStatement(SQL_CONSULTAR);
            rst = statement.executeQuery();
            while (rst.next()){
                long id = rst.getLong("ID");
                String nome = rst.getString("NOME");
                String categoriaStr = rst.getString("CATEGORIA");
                Categoria categoria = Categoria.valueOf(categoriaStr);
                String nomeImagem = rst.getString("NOME_IMAGEM");
                int forca = rst.getInt("FORCA");
                Artefato artefato = new Artefato(id, nome, categoria, nomeImagem, forca);
                listaArtefatos.add(artefato);
            }
        } catch (SQLException e) {
            throw new DadosException("Não foi possível selecionar" , e);
        }finally {
            GerenciadorConexao.fechar(connection, statement, rst);
        }
        return listaArtefatos;

    }
}
