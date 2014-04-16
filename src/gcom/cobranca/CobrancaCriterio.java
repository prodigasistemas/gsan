package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
//@ControleAlteracao
public class CobrancaCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private static final int ATRIBUTOS_INSERIR_CRITERIO = 139; 

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private String descricaoCobrancaCriterio;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Date dataInicioVigencia;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short numeroContaAntiga;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoImovelParalisacao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoImovelSituacaoCobranca;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoDebitoContaMes;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoContaRevisao;    

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoInquilinoDebitoContaMes;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private Short indicadorEmissaoDebitoContaAntiga;

    /** persistent field */
    //private gcom.cobranca.CobrancaAcao cobrancaAcao;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal percentualValorMinimoPagoParceladoCancelado;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_CRITERIO})
    private BigDecimal valorLimitePrioridade;
    
    private Set cobrancaCriterioLinhas;
    
    private Set criteriosSituacaoCobranca;
    
    private Set criteriosSituacaoLigacaoAgua;
    
    private Set criteriosSituacaoLigacaoEsgoto;

    public Set getCobrancaCriterioLinhas() {
		return cobrancaCriterioLinhas;
	}

	public void setCobrancaCriterioLinhas(Set cobrancaCriterioLinhas) {
		this.cobrancaCriterioLinhas = cobrancaCriterioLinhas;
	}

	/** full constructor */
    public CobrancaCriterio(String descricaoCobrancaCriterio, Date dataInicioVigencia, Short indicadorUso, Short numeroContaAntiga, Short indicadorEmissaoImovelParalisacao, Date ultimaAlteracao, Short indicadorEmissaoImovelSituacaoCobranca, Short indicadorEmissaoDebitoContaMes, Short indicadorEmissaoContaRevisao, Short indicadorEmissaoInquilinoDebitoContaMes, Short indicadorEmissaoDebitoContaAntiga) {
        this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
        this.dataInicioVigencia = dataInicioVigencia;
        this.indicadorUso = indicadorUso;
        this.numeroContaAntiga = numeroContaAntiga;
        this.indicadorEmissaoImovelParalisacao = indicadorEmissaoImovelParalisacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorEmissaoImovelSituacaoCobranca = indicadorEmissaoImovelSituacaoCobranca;
        this.indicadorEmissaoDebitoContaMes = indicadorEmissaoDebitoContaMes;
        this.indicadorEmissaoContaRevisao = indicadorEmissaoContaRevisao;
        this.indicadorEmissaoInquilinoDebitoContaMes = indicadorEmissaoInquilinoDebitoContaMes;
        this.indicadorEmissaoDebitoContaAntiga = indicadorEmissaoDebitoContaAntiga;
//        this.cobrancaAcao = cobrancaAcao;
    }

    /** default constructor */
    public CobrancaCriterio() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCobrancaCriterio() {
        return this.descricaoCobrancaCriterio;
    }

    public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
        this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
    }

    public Date getDataInicioVigencia() {
        return this.dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Short getNumeroContaAntiga() {
        return this.numeroContaAntiga;
    }

    public void setNumeroContaAntiga(Short numeroContaAntiga) {
        this.numeroContaAntiga = numeroContaAntiga;
    }

    public Short getIndicadorEmissaoImovelParalisacao() {
        return this.indicadorEmissaoImovelParalisacao;
    }

    public void setIndicadorEmissaoImovelParalisacao(Short indicadorEmissaoImovelParalisacao) {
        this.indicadorEmissaoImovelParalisacao = indicadorEmissaoImovelParalisacao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorEmissaoImovelSituacaoCobranca() {
        return this.indicadorEmissaoImovelSituacaoCobranca;
    }

    public void setIndicadorEmissaoImovelSituacaoCobranca(Short indicadorEmissaoImovelSituacaoCobranca) {
        this.indicadorEmissaoImovelSituacaoCobranca = indicadorEmissaoImovelSituacaoCobranca;
    }

    public Short getIndicadorEmissaoDebitoContaMes() {
        return this.indicadorEmissaoDebitoContaMes;
    }

    public void setIndicadorEmissaoDebitoContaMes(Short indicadorEmissaoDebitoContaMes) {
        this.indicadorEmissaoDebitoContaMes = indicadorEmissaoDebitoContaMes;
    }

    public Short getIndicadorEmissaoContaRevisao() {
        return this.indicadorEmissaoContaRevisao;
    }

    public void setIndicadorEmissaoContaRevisao(Short indicadorEmissaoContaRevisao) {
        this.indicadorEmissaoContaRevisao = indicadorEmissaoContaRevisao;
    }

    public Short getIndicadorEmissaoInquilinoDebitoContaMes() {
        return this.indicadorEmissaoInquilinoDebitoContaMes;
    }

    public void setIndicadorEmissaoInquilinoDebitoContaMes(Short indicadorEmissaoInquilinoDebitoContaMes) {
        this.indicadorEmissaoInquilinoDebitoContaMes = indicadorEmissaoInquilinoDebitoContaMes;
    }

    public Short getIndicadorEmissaoDebitoContaAntiga() {
        return this.indicadorEmissaoDebitoContaAntiga;
    }

    public void setIndicadorEmissaoDebitoContaAntiga(Short indicadorEmissaoDebitoContaAntiga) {
        this.indicadorEmissaoDebitoContaAntiga = indicadorEmissaoDebitoContaAntiga;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
	public Filtro retornaFiltro(){
		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();

		filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID,
				this.getId()));
//		filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterioLinhas");
		
		return filtroCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo percentualQuantidadeMinimoPagoParceladoCancelado.
	 */
	public BigDecimal getPercentualQuantidadeMinimoPagoParceladoCancelado() {
		return percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	/**
	 * @param percentualQuantidadeMinimoPagoParceladoCancelado O percentualQuantidadeMinimoPagoParceladoCancelado a ser setado.
	 */
	public void setPercentualQuantidadeMinimoPagoParceladoCancelado(
			BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado) {
		this.percentualQuantidadeMinimoPagoParceladoCancelado = percentualQuantidadeMinimoPagoParceladoCancelado;
	}

	/**
	 * @return Retorna o campo percentualValorMinimoPagoParceladoCancelado.
	 */
	public BigDecimal getPercentualValorMinimoPagoParceladoCancelado() {
		return percentualValorMinimoPagoParceladoCancelado;
	}

	/**
	 * @param percentualValorMinimoPagoParceladoCancelado O percentualValorMinimoPagoParceladoCancelado a ser setado.
	 */
	public void setPercentualValorMinimoPagoParceladoCancelado(
			BigDecimal percentualValorMinimoPagoParceladoCancelado) {
		this.percentualValorMinimoPagoParceladoCancelado = percentualValorMinimoPagoParceladoCancelado;
	}

	/**
	 * @return Retorna o campo valorLimitePrioridade.
	 */
	public BigDecimal getValorLimitePrioridade() {
		return valorLimitePrioridade;
	}

	/**
	 * @param valorLimitePrioridade O valorLimitePrioridade a ser setado.
	 */
	public void setValorLimitePrioridade(BigDecimal valorLimitePrioridade) {
		this.valorLimitePrioridade = valorLimitePrioridade;
	}

	public Set getCriteriosSituacaoCobranca() {
		return criteriosSituacaoCobranca;
	}

	public void setCriteriosSituacaoCobranca(Set criteriosSituacaoCobranca) {
		this.criteriosSituacaoCobranca = criteriosSituacaoCobranca;
	}

	public Set getCriteriosSituacaoLigacaoAgua() {
		return criteriosSituacaoLigacaoAgua;
	}

	public void setCriteriosSituacaoLigacaoAgua(Set criteriosSituacaoLigacaoAgua) {
		this.criteriosSituacaoLigacaoAgua = criteriosSituacaoLigacaoAgua;
	}

	public Set getCriteriosSituacaoLigacaoEsgoto() {
		return criteriosSituacaoLigacaoEsgoto;
	}

	public void setCriteriosSituacaoLigacaoEsgoto(Set criteriosSituacaoLigacaoEsgoto) {
		this.criteriosSituacaoLigacaoEsgoto = criteriosSituacaoLigacaoEsgoto;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoCobrancaCriterio();
	}
}
