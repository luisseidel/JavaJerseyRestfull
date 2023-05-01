package com.seidelsoft.model;

public class Ebook {

    private Long id;
    private String nome;
    private String editora;

    public Ebook() {
    }

    public Ebook(Long id) {
        this.id = id;
    }

    public Ebook(Long id, String nome, String editora) {
        this.id = id;
        this.nome = nome;
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Ebook{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", editora='" + editora + '\'' +
                '}';
    }
}
