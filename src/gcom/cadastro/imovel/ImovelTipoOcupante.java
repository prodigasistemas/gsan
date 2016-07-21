package gcom.cadastro.imovel;

import java.util.Date;

import gcom.util.tabelaauxiliar.TabelaAuxiliar;

public class ImovelTipoOcupante extends TabelaAuxiliar{
    private static final long serialVersionUID = 1199546479830159223L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    @Override
    public String[] retornaCamposChavePrimaria() {
        return new String[]{ "id" };
    }
}
