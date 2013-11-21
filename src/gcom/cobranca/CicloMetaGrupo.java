package gcom.cobranca;

import gcom.cadastro.localidade.Localidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CicloMetaGrupo implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer quantidadeImoveisSituacaoAgua;

    /** nullable persistent field */
    private Integer metaCalculada;

    /** nullable persistent field */
    private Integer metaAjustada;

    /** nullable persistent field */
    private Integer quantidadeRealizada = 0;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.CicloMeta cicloMeta;

    /** persistent field */
    private Localidade localidade;
    
    private CobrancaGrupo cobrancaGrupo;
    
    private Integer quantidadeDocumentosRestantes = 0;
    
    private BigDecimal valorTotalDocumentosRestantes = new BigDecimal(0);
    
    private BigDecimal valorRealizado = new BigDecimal(0);

    public CicloMetaGrupo(Integer id, Integer quantidadeImoveisSituacaoAgua, Integer metaCalculada, Integer metaAjustada, Integer quantidadeRealizada, Date ultimaAlteracao, CicloMeta cicloMeta, Localidade localidade, CobrancaGrupo cobrancaGrupo) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.quantidadeImoveisSituacaoAgua = quantidadeImoveisSituacaoAgua;
		this.metaCalculada = metaCalculada;
		this.metaAjustada = metaAjustada;
		this.quantidadeRealizada = quantidadeRealizada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cicloMeta = cicloMeta;
		this.localidade = localidade;
		this.cobrancaGrupo = cobrancaGrupo;
	}

	/** default constructor */
    public CicloMetaGrupo() {
    }

    /** minimal constructor */
    public CicloMetaGrupo(Date ultimaAlteracao, gcom.cobranca.CicloMeta cicloMeta, Localidade localidade) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.cicloMeta = cicloMeta;
        this.localidade = localidade;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeImoveisSituacaoAgua() {
        return this.quantidadeImoveisSituacaoAgua;
    }

    public void setQuantidadeImoveisSituacaoAgua(Integer quantidadeImoveisSituacaoAgua) {
        this.quantidadeImoveisSituacaoAgua = quantidadeImoveisSituacaoAgua;
    }

    public Integer getMetaCalculada() {
        return this.metaCalculada;
    }

    public void setMetaCalculada(Integer metaCalculada) {
        this.metaCalculada = metaCalculada;
    }

    public Integer getMetaAjustada() {
        return this.metaAjustada;
    }

    public void setMetaAjustada(Integer metaAjustada) {
        this.metaAjustada = metaAjustada;
    }

    public Integer getQuantidadeRealizada() {
        return this.quantidadeRealizada;
    }

    public void setQuantidadeRealizada(Integer quantidadeRealizada) {
        this.quantidadeRealizada = quantidadeRealizada;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.CicloMeta getCicloMeta() {
        return this.cicloMeta;
    }

    public void setCicloMeta(gcom.cobranca.CicloMeta cicloMeta) {
        this.cicloMeta = cicloMeta;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentosRestantes.
	 */
	public Integer getQuantidadeDocumentosRestantes() {
		return quantidadeDocumentosRestantes;
	}

	/**
	 * @param quantidadeDocumentosRestantes O quantidadeDocumentosRestantes a ser setado.
	 */
	public void setQuantidadeDocumentosRestantes(
			Integer quantidadeDocumentosRestantes) {
		this.quantidadeDocumentosRestantes = quantidadeDocumentosRestantes;
	}

	/**
	 * @return Retorna o campo valorTotalDocumentosRestantes.
	 */
	public BigDecimal getValorTotalDocumentosRestantes() {
		return valorTotalDocumentosRestantes;
	}

	/**
	 * @param valorTotalDocumentosRestantes O valorTotalDocumentosRestantes a ser setado.
	 */
	public void setValorTotalDocumentosRestantes(
			BigDecimal valorTotalDocumentosRestantes) {
		this.valorTotalDocumentosRestantes = valorTotalDocumentosRestantes;
	}

	public BigDecimal getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

}
