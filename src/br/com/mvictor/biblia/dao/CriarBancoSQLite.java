/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvictor.biblia.dao;


import br.com.mvictor.biblia.dao.Conexao;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bruno
 */
public class CriarBancoSQLite {

    private final Conexao conexaoSQLite;

    public CriarBancoSQLite(Conexao pConexaoSQLite) {
        this.conexaoSQLite = pConexaoSQLite;
    }

    public void criarTabelaPessoa() {

        String sql = "CREATE TABLE IF NOT EXISTS tbl_pessoa"
                + "("
                + "id integer PRIMARY KEY,"
                + "nome text NOT NULL,"
                + "idade integer"
                + ");";

        //executando o sql de criar tabelas
        boolean conectou = false;

        try {
            conectou = this.conexaoSQLite.conectar();
            
            Statement stmt = this.conexaoSQLite.criarStatement();
            
            stmt.execute(sql);
            
            System.out.println("Tabela pessoa criada!");

        } catch (SQLException e) {
            //mensagem de erro na criação da tabela
        } finally {
            if(conectou){
                this.conexaoSQLite.desconectar();
            }
        }

    }

}
