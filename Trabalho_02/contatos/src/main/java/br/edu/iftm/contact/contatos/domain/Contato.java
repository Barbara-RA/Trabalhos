package br.edu.iftm.contact.contatos.domain;

import lombok.Data;

@Data
public class Contato {

    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public Contato() {
        
    }
    public Contato(String nome, String email, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    
    
}