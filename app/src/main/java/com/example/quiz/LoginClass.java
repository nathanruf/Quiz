package com.example.quiz;

public class LoginClass {

   public LoginClass(String email, String login){
       this.email = email;
       this.senha_cod = senha_cod;
   }
    private String senha_cod;
    private String email;

    public void setSenha_cod(String senha_cod){
        this.senha_cod = senha_cod;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getSenha_cod(){
        return this.senha_cod;
    }
    public String getEmail(){
        return this.email;
    }


}
