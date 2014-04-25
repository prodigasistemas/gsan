package gcom.arrecadacao;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MetasArrecadacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal metaFaturamento;

    /** persistent field */
    private BigDecimal metaPendencia;

    /** persistent field */
    private BigDecimal valorArrecadacaoGlobal;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** full constructor */
    public MetasArrecadacao(Integer id, BigDecimal metaFaturamento, BigDecimal metaPendencia, BigDecimal valorArrecadacaoGlobal, Date ultimaAlteracao, UnidadeNegocio unidadeNegocio, GerenciaRegional gerenciaRegional, Localidade localidade) {
        this.id = id;
        this.metaFaturamento = metaFaturamento;
        this.metaPendencia = metaPendencia;
        this.valorArrecadacaoGlobal = valorArrecadacaoGlobal;
        this.ultimaAlteracao = ultimaAlteracao;
        this.unidadeNegocio = unidadeNegocio;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
    }

    /** default constructor */
    public MetasArrecadacao() {
    }

  
    /**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo metaFaturamento.
	 */
	public BigDecimal getMetaFaturamento() {
		return metaFaturamento;
	}

	/**
	 * @param metaFaturamento O metaFaturamento a ser setado.
	 */
	public void setMetaFaturamento(BigDecimal metaFaturamento) {
		this.metaFaturamento = metaFaturamento;
	}

	/**
	 * @return Retorna o campo metaPendencia.
	 */
	public BigDecimal getMetaPendencia() {
		return metaPendencia;
	}

	/**
	 * @param metaPendencia O metaPendencia a ser setado.
	 */
	public void setMetaPendencia(BigDecimal metaPendencia) {
		this.metaPendencia = metaPendencia;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoGlobal.
	 */
	public BigDecimal getValorArrecadacaoGlobal() {
		return valorArrecadacaoGlobal;
	}

	/**
	 * @param valorArrecadacaoGlobal O valorArrecadacaoGlobal a ser setado.
	 */
	public void setValorArrecadacaoGlobal(BigDecimal valorArrecadacaoGlobal) {
		this.valorArrecadacaoGlobal = valorArrecadacaoGlobal;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
