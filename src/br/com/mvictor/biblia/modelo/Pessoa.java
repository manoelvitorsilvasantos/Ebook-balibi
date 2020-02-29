/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvictor.biblia.modelo;

/**
 *
 * @author mvictor
 */
public class Pessoa {
    
    private String titulo;
    private String mensagem;
    
    public String getTitulo(){ 
        return this.titulo;
    }
    public void setTituto(String titulo){ 
        this.titulo = titulo;
    }
    public String getMensagem(){ 
        return this.mensagem;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
}
