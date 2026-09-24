package br.unip.sicc.exercicio.view.dao;

import br.unip.sicc.exercicio.view.model.Artefato;
import  br.unip.sicc.exercicio.view.view.Categoria;
import br.unip.sicc.exercicio.view.view.DadosException;

import java.util.List;

/*
    private static final String SQL_DELETE
            = "DELETE FROM TB_ARTEFATO WHERE ID = ?;";
    private static final String SQL_INSERT
            = "INSERT INTO TB_ARTEFATO (NOME, CATEGORIA, NOME_IMAGEM, FORCA) "
            + "VALUES (?, ?, ?, ?);";
                    // 1  2  3  4
    private static final String SQL_UPDATE
            = "UPDATE TB_ARTEFATO SET NOME = ?, CATEGORIA = ? , NOME_IMAGEM = ? , FORCA = ?  WHERE ID = ?;";
                                        //1          2            3           4            5
    private static final String SQL_SELECT_ALL
            = "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA "
            + " FROM TB_ARTEFATO;";
    private static final String SQL_SELECT_POR_ID
            = "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA "
            + " FROM TB_ARTEFATO WHERE ID = ?;";
    private static final String SQL_SELECT_POR_CATEGORIA
            = "SELECT ID, NOME, CATEGORIA, NOME_IMAGEM, FORCA "
            + " FROM TB_ARTEFATO WHERE CATEGORIA = ?;";

*/

public interface ArtefatoDao {

    void atualizar(Artefato artefato) throws DadosException;

    void incluir(Artefato artefato) throws DadosException;

    void excluir(Artefato artefato) throws DadosException;

    Artefato getPorId(Long id) throws DadosException;

    List<Artefato> getPorCategoria(Categoria categoria) throws DadosException;

    List<Artefato> getTodos() throws DadosException;
}
