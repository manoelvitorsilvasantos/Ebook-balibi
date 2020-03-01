/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvictor.biblia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bruno
 */
public class Conexao {

    private Connection conexao;

    /**
     * Conecta a um banco de dados (cria o banco se ele não existir)
     *
     * @return
     */
    public boolean conectar() {

        try {

            String url = "jdbc:sqlite:db/jfa-biblia.db";

            this.conexao = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        System.out.println("conectou!!!");

        return true;
    }

    public boolean desconectar() {

        try {
            if (this.conexao.isClosed() == false) {
                this.conexao.close();
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou!!!");
        return true;
    }

    /**
     * Criar os statements para nossos sqls serem executados
     *
     * @return
     */
    public Statement criarStatement() {
        try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }
    
    /**
     * Criar os statements para nossos sqls serem executados
     *
     * @return
     */
    public PreparedStatement criarPreparedStatement(String sql) {
        try {
            return conexao.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }
    }
    
    

    public Connection getConexao() {
        return this.conexao;
    }
    
    
    public void query(String sql)
    {
        boolean conectou = false;

        try {
            conectou = conectar();
            
            Statement stmt = criarStatement();
            
            stmt.execute(sql);
            
            System.out.println("Tabela pessoa criada!");

        } catch (SQLException e) {
            //mensagem de erro na criação da tabela
        } finally {
            if(conectou){
                this.desconectar();
            }
        }
        
    }

}
