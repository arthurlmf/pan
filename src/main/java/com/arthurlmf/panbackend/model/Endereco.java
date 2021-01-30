package com.arthurlmf.panbackend.model;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Endereco {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String cep;
    @NotNull
    private String logradouro;
    private String complemento;
    @NotNull
    private String bairro;
    private String localidade;
    @NotNull
    private String uf;
    @NotNull
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;


    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getId(), endereco.getId()) &&
                Objects.equals(getCep(), endereco.getCep()) &&
                Objects.equals(getLogradouro(), endereco.getLogradouro()) &&
                Objects.equals(getComplemento(), endereco.getComplemento()) &&
                Objects.equals(getBairro(), endereco.getBairro()) &&
                Objects.equals(getLocalidade(), endereco.getLocalidade()) &&
                Objects.equals(getUf(), endereco.getUf()) &&
                Objects.equals(getIbge(), endereco.getIbge()) &&
                Objects.equals(getGia(), endereco.getGia()) &&
                Objects.equals(getDdd(), endereco.getDdd()) &&
                Objects.equals(getSiafi(), endereco.getSiafi());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCep(), getLogradouro(), getComplemento(), getBairro(), getLocalidade(), getUf(), getIbge(), getGia(), getDdd(), getSiafi());
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }


}
