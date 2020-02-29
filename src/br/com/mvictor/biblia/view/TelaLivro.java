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
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author mvictor
 */
public class TelaLivro extends javax.swing.JFrame {
    
    private DefaultListModel <String> modelo;
    private DefaultListModel <String> modelo2;
    private String nome = null;
    private String campo;
    private int capitulo;
    
    
    public boolean verificarCapitulo(String livro, int capitulo)
   {
       Conexao conexao = new Conexao();
       conexao.conectar();
       
       ResultSet resultSet = null;
       PreparedStatement pr = null;
       
       boolean estado = false;
       
       
       String sql = "SELECT DISTINCT chapter FROM verses  JOIN books ON books.book_number = verses.book_number WHERE books.long_name = ?";
       
       try {
           
            this.nome = livro;
            pr  = conexao.criarPreparedStatement(sql);
            pr.setString(1, nome);
            
            resultSet = pr.executeQuery();
            
            while (resultSet.next()) {
                if(resultSet.getInt("chapter") == capitulo)
                {
                    estado = true;
                }
                
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
        return estado;
   }
    
    /**
     * Creates new form TelaVersiculo
     */
    public TelaLivro() {
        setExtendedState(this.MAXIMIZED_BOTH);
        initComponents();
        lista.setFixedCellHeight(40);
        lista.setBackground(Color.WHITE);
        lista.setSelectionBackground(Color.YELLOW);
        lista.setSelectionForeground(Color.BLACK);
        lista.addListSelectionListener(new  ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
               lista.getSelectedValue().toString();
            }
        });
    }
    
    public void copiarDados(final String nome, final int capitulo)
    {
        lblLivro.setText(nome.toUpperCase());
        modelo = new DefaultListModel<>();
        modelo2 = new DefaultListModel<>();
        Conexao conexao = new Conexao();
        
        
        conexao.conectar();
        
        
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        
        ResultSet r2 = null;
        PreparedStatement pr2 = null;
        
        String sql = "SELECT *FROM verses  JOIN books ON books.book_number = verses.book_number WHERE books.long_name = ? AND verses.chapter = ?;";
        String sqlChapter = "SELECT DISTINCT chapter FROM verses  JOIN books ON books.book_number = verses.book_number WHERE books.long_name = ?";
        try {
           
            //campo = nome;
            //int cap = capitulo;
            
            this.setNome(nome);
            this.setCapitulo(capitulo);
            
            pr  = conexao.criarPreparedStatement(sql);

            pr.setString(1, nome);
            pr.setInt(2, capitulo);

            
            resultSet = pr.executeQuery();
            
            while (resultSet.next()) {
               String texto = resultSet.getString("text");
               
               modelo.addElement(resultSet.getInt("verse")+" - "+texto);
               
            }
            
            this.lblCapitulo.setText("Capítulo".toUpperCase()+"- "+this.getCapitulo());
            pr2  = conexao.criarPreparedStatement(sqlChapter);
            pr2.setString(1, nome);
            
            r2= pr2.executeQuery();
            
            while (r2.next()) {
               modelo2.addElement(String.valueOf(r2.getInt("chapter")));
            }

        } catch (SQLException e) {
            System.out.println("Erro misteriosos");
        } 
        
        finally {
            try {
                resultSet.close();
                r2.close();
                pr.close();
                pr2.close();
                conexao.desconectar();
            } catch (SQLException ex) {
                System.out.println("Erro misterioso de fechamentos");
            }
        }
        lista.setModel(modelo);
        listaChapter.setModel(modelo2);
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
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaChapter = new javax.swing.JList<>();
        btnChapter = new javax.swing.JButton();
        lblCapitulo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSobre = new javax.swing.JMenu();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(lista);

        lblLivro.setBackground(new java.awt.Color(255, 255, 255));
        lblLivro.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N

        btnBack.setText("Voltar");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        listaChapter.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaChapter);

        btnChapter.setText("Abrir");
        btnChapter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChapterActionPerformed(evt);
            }
        });

        menuInicio.setText("Inicio");

        jMenuItem1.setText("Livro");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuInicio.add(jMenuItem1);

        jMenuBar1.add(menuInicio);

        menuSobre.setText("Sobre");

        menuItemSobre.setText("sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuSobre.add(menuItemSobre);

        jMenuBar1.add(menuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(lblLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblCapitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addComponent(btnChapter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChapter)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack)
                        .addComponent(btnNext)))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
       
        if(this.getCapitulo() == 1)
        {
            JOptionPane.showMessageDialog(null,"Você já chegou no inicio do livro!");
        }
        else
        {
            this.copiarDados(this.getNome(), this.getCapitulo()-1);
        }
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        int cap = this.getCapitulo();
        
        if(!this.verificarCapitulo(this.getNome(), this.getCapitulo()+1))
        {
            JOptionPane.showMessageDialog(null,"Você já chegou no final do livro!");
        }
        else
        {
            this.copiarDados(this.getNome(), this.getCapitulo()+1);
        }
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnChapterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChapterActionPerformed
        // TODO add your handling code here:
        if(this.listaChapter.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,"Porfavor, Selecione um Capítulo.");
        }
        else
        {
            this.copiarDados(this.getNome(), this.listaChapter.getSelectedIndex()+1);
        }
        
    }//GEN-LAST:event_btnChapterActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Tela tela = new Tela();
        tela.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        // TODO add your handling code here:
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
        this.setDefaultCloseOperation(Tela.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_menuItemSobreActionPerformed

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
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChapter;
    private javax.swing.JButton btnNext;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCapitulo;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JList<String> lista;
    private javax.swing.JList<String> listaChapter;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenu menuSobre;
    // End of variables declaration//GEN-END:variables

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }
}
