package br.com.sodicas.api.dica;

import java.util.List;

import br.com.sodicas.api.tag.Tag;
import java.io.Serializable;

public class Dados implements Serializable {

    private String titulo;

    private List<Tag> tags;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
