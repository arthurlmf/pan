package com.arthurlmf.panbackend.vo;

import java.util.Objects;

public class MunicipioVO {

    private Integer id;
    private String nome;

    public MunicipioVO( String nome) {
        this.nome = nome;
    }

    public MunicipioVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipioVO that = (MunicipioVO) o;
        return
                Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    @Override
    public String toString() {
        return "MunicipioVO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
