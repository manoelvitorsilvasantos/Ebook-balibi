/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvictor.biblia.view;

import conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();

        ResultSet resultSet = null;
        Statement statement = null;

        conexaoSQLite.conectar();

        String query = "SELECT * FROM verses;";

        statement = conexaoSQLite.criarStatement();

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("text"));
                System.out.println("-------------");

            }
        } catch (SQLException e) {
            System.out.println("Erro misteriosos");
        } finally {
            try {
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex) {
                System.out.println("Erro misterioso de fechamentos");
            }
        }

    }

}
