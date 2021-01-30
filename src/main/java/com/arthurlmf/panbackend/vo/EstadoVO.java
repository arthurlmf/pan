package com.arthurlmf.panbackend.vo;

import java.util.Objects;

public class EstadoVO{

    private Integer id;
    private String sigla;
    private String nome;

    public EstadoVO(Integer id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    public EstadoVO(String sigla) {
        this.sigla = sigla;
    }

    public EstadoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
        EstadoVO estadoVO = (EstadoVO) o;
        return
                Objects.equals(getSigla(), estadoVO.getSigla()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSigla(), getNome());
    }

    @Override
    public String toString() {
        return "EstadoVO{" +
                "id=" + id +
                ", sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }


}
