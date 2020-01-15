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
public class Livro {
    
    private String texto;
    private int versiculo;
    private int capitulo;
    private String livroNome;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(int versiculo) {
        this.versiculo = versiculo;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public String getLivroNome() {
        return livroNome;
    }

    public void setLivroNome(String livroNome) {
        this.livroNome = livroNome;
    }
    
}
