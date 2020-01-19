/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvictor.biblia.view;

import br.com.mvictor.biblia.dao.Conexao;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 *
 * @author mvictor
 */
public class TelaLivro extends javax.swing.JFrame {
    
    private DefaultListModel <String> modelo;
    private String nome = null;
    
    
    /**
     * Creates new form TelaVersiculo
     */
    public TelaLivro() {
        initComponents();
        lista.setFixedCellHeight(40);
        lista.setBackground(Color.WHITE);
        lista.setSelectionBackground(Color.YELLOW);
        lista.setSelectionForeground(Color.BLACK);
    }
    
    public void copiarDados(final String nome, final int capitulo)
    {
        lblLivro.setText(nome);
        modelo = new DefaultListModel<>();
        Conexao conexao = new Conexao();
        
        
        conexao.conectar();
        
        
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        
        String sql = "SELECT *FROM verses  JOIN books ON books.book_number = verses.book_number WHERE books.long_name = ? AND verses.chapter = ?;";

        try {
           
            String campo = nome;
            int cap = capitulo;
            pr  = conexao.criarPreparedStatement(sql);
            pr.setString(1, campo);
            pr.setInt(2, cap);
            resultSet = pr.executeQuery();
            
            while (resultSet.next()) {
               String texto = resultSet.getString("text");
               
               modelo.addElement(resultSet.getInt("verse")+" - "+texto);
            }

        } catch (SQLException e) {
            System.out.println("Erro misteriosos");
        } 
        
        finally {
            try {
                resultSet.close();
                pr.close();
                conexao.desconectar();
            } catch (SQLException ex) {
                System.out.println("Erro misterioso de fechamentos");
            }
        }
        lista.setModel(modelo);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        lblLivro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(lista);

        lblLivro.setBackground(new java.awt.Color(255, 255, 255));
        lblLivro.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(lblLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JList<String> lista;
    // End of variables declaration//GEN-END:variables
}