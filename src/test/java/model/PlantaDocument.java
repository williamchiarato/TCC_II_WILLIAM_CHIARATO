package model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "planta_document")
public class PlantaDocument {

    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "tipo")
    private int tipo;

    @Column(name = "extinto")
    private Integer extinto;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "ordem")
    private int ordem;

    @Column(name = "fumavel")
    private Integer fumavel;

    @Column(name = "cor")
    private String cor;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public int getTipo() {
        return tipo;
    }

    public Integer getExtinto() {
        return extinto;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public int getOrdem() {
        return ordem;
    }

    public Integer isFumavel() {
        return fumavel;
    }

    public String getCor() {
        return cor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setExtinto(Integer extinto) {
        this.extinto = extinto;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public void setFumavel(Integer fumavel) {
        this.fumavel = fumavel;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
