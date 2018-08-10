package br.com.atlas.desafiokanamobi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable{

    private Long id;
    private String node_id;
    @SerializedName("name")
    private String nome;
    private String full_name;
    private Owner owner;
    private String description;
    private Long stargazers_count;
    private Long forks_count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Long getForks_count() {
        return forks_count;
    }

    public void setForks_count(Long forks_count) {
        this.forks_count = forks_count;
    }
}
