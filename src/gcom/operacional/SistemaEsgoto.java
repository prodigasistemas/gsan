package gcom.operacional;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SistemaEsgoto extends ObjetoTransacao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.operacional.SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo;

    /** persistent field */
    private gcom.operacional.DivisaoEsgoto divisaoEsgoto;

    /** full constructor */
    public SistemaEsgoto(
            String descricao,
            String descricaoAbreviada,
            Short indicadorUso,
            Date ultimaAlteracao,
            gcom.operacional.SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo,
            gcom.operacional.DivisaoEsgoto divisaoEsgoto) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaEsgotoTratamentoTipo = sistemaEsgotoTratamentoTipo;
        this.divisaoEsgoto = divisaoEsgoto;
    }

    /** default constructor */
    public SistemaEsgoto() {
    }

    /** minimal constructor */
    public SistemaEsgoto(
            String descricao,
            String descricaoAbreviada,
            gcom.operacional.SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo,
            gcom.operacional.DivisaoEsgoto divisaoEsgoto) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.sistemaEsgotoTratamentoTipo = sistemaEsgotoTratamentoTipo;
        this.divisaoEsgoto = divisaoEsgoto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.operacional.SistemaEsgotoTratamentoTipo getSistemaEsgotoTratamentoTipo() {
        return this.sistemaEsgotoTratamentoTipo;
    }

    public void setSistemaEsgotoTratamentoTipo(
            gcom.operacional.SistemaEsgotoTratamentoTipo sistemaEsgotoTratamentoTipo) {
        this.sistemaEsgotoTratamentoTipo = sistemaEsgotoTratamentoTipo;
    }

    public gcom.operacional.DivisaoEsgoto getDivisaoEsgoto() {
        return this.divisaoEsgoto;
    }

    public void setDivisaoEsgoto(gcom.operacional.DivisaoEsgoto divisaoEsgoto) {
        this.divisaoEsgoto = divisaoEsgoto;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    public Filtro retornaFiltro(){
    	FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();

    	filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID,this.getId()));
    	filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("divisaoEsgoto");
    	filtroSistemaEsgoto.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgotoTratamentoTipo");
		
		return filtroSistemaEsgoto;
	}

}
