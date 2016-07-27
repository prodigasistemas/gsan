package gcom.cadastro.endereco.to;

import java.io.Serializable;

public class LogradouroTO implements Serializable{
    private static final long serialVersionUID = 1157837891460979562L;

    private Integer id;
    
    private String nome;
    
    private Integer idTipo;
    
    private String descricaoTipo;
    
    private Integer idTitulo;
    
    private String descricaoTitulo;
    
    private Integer idMunicipio;
    
    private String descricaoMunicipio;
    
    private Integer idUf;
    
    private String siglaUf;

    public LogradouroTO(Integer id, String nome, Integer idTipo, String descricaoTipo, Integer idTitulo, String descricaoTitulo, Integer idMunicipio,
            String descricaoMunicipio, Integer idUf, String siglaUf) {
        super();
        this.id = id;
        this.nome = nome;
        this.idTipo = idTipo;
        this.descricaoTipo = descricaoTipo;
        this.idTitulo = idTitulo;
        this.descricaoTitulo = descricaoTitulo;
        this.idMunicipio = idMunicipio;
        this.descricaoMunicipio = descricaoMunicipio;
        this.idUf = idUf;
        this.siglaUf = siglaUf;
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

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescricaoTipo() {
        return descricaoTipo;
    }

    public void setDescricaoTipo(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }

    public Integer getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(Integer idTitulo) {
        this.idTitulo = idTitulo;
    }

    public String getDescricaoTitulo() {
        return descricaoTitulo;
    }

    public void setDescricaoTitulo(String descricaoTitulo) {
        this.descricaoTitulo = descricaoTitulo;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDescricaoMunicipio() {
        return descricaoMunicipio;
    }

    public void setDescricaoMunicipio(String descricaoMunicipio) {
        this.descricaoMunicipio = descricaoMunicipio;
    }

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(Integer idUf) {
        this.idUf = idUf;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }
}
