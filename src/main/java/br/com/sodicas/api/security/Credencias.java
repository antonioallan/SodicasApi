package br.com.sodicas.api.security;

import java.security.NoSuchAlgorithmException;

import br.com.sodicas.api.util.Cripto;
import java.io.Serializable;

public class Credencias implements Serializable {

    private String username;
    private String senha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCriptSenha() throws NoSuchAlgorithmException {
        return Cripto.MD5(this.senha);
    }

}
