package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorRetornoMotivo extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRetornocodigo;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Short indicadorRegistroAceito;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private Set negativadorMovimentoRegRetMot;
    
    private Short codigoRetornoMotivo;

	/** full constructor */
    public NegativadorRetornoMotivo(Integer id, String descricaoRetornocodigo, Short indicadorUso, Short indicadorRegistroAceito, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoRegRetMot, Short codigoRetornoMotivo) {
        this.id = id;
        this.descricaoRetornocodigo = descricaoRetornocodigo;
        this.indicadorUso = indicadorUso;
        this.indicadorRegistroAceito = indicadorRegistroAceito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoRegRetMot = negativadorMovimentoRegRetMot;
        this.codigoRetornoMotivo = codigoRetornoMotivo;
    }

    public NegativadorRetornoMotivo(Integer id, String descricaoRetornocodigo, Short indicadorUso, Short indicadorRegistroAceito, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoRegRetMot) {
        this.id = id;
        this.descricaoRetornocodigo = descricaoRetornocodigo;
        this.indicadorUso = indicadorUso;
        this.indicadorRegistroAceito = indicadorRegistroAceito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoRegRetMot = negativadorMovimentoRegRetMot;
    }
    
    /** default constructor */
    public NegativadorRetornoMotivo() {
    }

    /** minimal constructor */
    public NegativadorRetornoMotivo(Integer id, Short indicadorUso, Short indicadorRegistroAceito, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoRegRetMot) {
        this.id = id;
        this.indicadorUso = indicadorUso;
        this.indicadorRegistroAceito = indicadorRegistroAceito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoRegRetMot = negativadorMovimentoRegRetMot;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoRetornocodigo() {
        return this.descricaoRetornocodigo;
    }

    public void setDescricaoRetornocodigo(String descricaoRetornocodigo) {
        this.descricaoRetornocodigo = descricaoRetornocodigo;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Short getIndicadorRegistroAceito() {
        return this.indicadorRegistroAceito;
    }

    public void setIndicadorRegistroAceito(Short indicadorRegistroAceito) {
        this.indicadorRegistroAceito = indicadorRegistroAceito;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public Set getNegativadorMovimentoRegRetMot() {
        return this.negativadorMovimentoRegRetMot;
    }

    public void setNegativadorMovimentoRegRetMot(Set negativadorMovimentoRegRetMot) {
        this.negativadorMovimentoRegRetMot = negativadorMovimentoRegRetMot;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    /**
	 * @return Retorna o campo codigoRetornoMotivo.
	 */
	public Short getCodigoRetornoMotivo() {
		return codigoRetornoMotivo;
	}

	/**
	 * @param codigoRetornoMotivo O codigoRetornoMotivo a ser setado.
	 */
	public void setCodigoRetornoMotivo(Short codigoRetornoMotivo) {
		this.codigoRetornoMotivo = codigoRetornoMotivo;
	}

	public Filtro retornaFiltro() {
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
		filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.ID,this.getId()));

		return filtroNegativadorRetornoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}
