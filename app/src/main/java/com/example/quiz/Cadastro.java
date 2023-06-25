package com.example.quiz;

public class Cadastro {
    private String email;
    private String senha_cod;

    public Cadastro( String email, String senha_cod){
        this.email = email;
        this.senha_cod = senha_cod;
    }
    public String getEmail() {
        return this.email;
    }
    public String getSenha_cod(){
        return this.senha_cod; 
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setSenha_cod(String senha){
        this.senha_cod = senha;
    }
}
