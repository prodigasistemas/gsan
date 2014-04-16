package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Sávio Luiz
 * @since 14/05/2007
 */
public class DadosPesquisaOSCobrancaDocumentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private Integer idOrdemServico;

	private Integer idDocumento;

	private short situacaoOrdemServico;

	private Date dataFiscalizacaoSituacao;

	private Integer idFiscalizacao;

	private Date dataEncerramento;

	private Short indicadorExecucaoMotivoEncerramento;

	private Date dataEmissao;

	private BigDecimal valorDocumento;

	private BigDecimal percentualValorMinimoCobrancaCriterio;

	private BigDecimal percentualQuantidadeMinimaCobrancaCriterio;

	private BigDecimal valorLimitePrioridadeCobrancaCriterio;

	private Integer idImovel;

	private Integer idLocalidade;

	/**
	 * 
	 */
	public DadosPesquisaOSCobrancaDocumentoHelper() {

	}

	/**
	 * @param conta
	 * @param valorPago
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valoratualizacaoMonetaria
	 */
	public DadosPesquisaOSCobrancaDocumentoHelper(Integer idOrdemServico,
			short situacaoOrdemServico, Date dataFiscalizacaoSituacao,
			Date dataEncerramento, Integer idDocumento, Date dataEmissao,
			BigDecimal valorDocumento,
			BigDecimal percentualValorMinimoCobrancaCriterio,
			BigDecimal percentualQuantidadeMinimaCobrancaCriterio,
			BigDecimal valorLimitePrioridadeCobrancaCriterio,
			Integer idFiscalizacao, Short indicadorExecucaoMotivoEncerramento,
			Integer idImovel, Integer idLocalidade) {
		this.idOrdemServico = idOrdemServico;
		this.situacaoOrdemServico = situacaoOrdemServico;
		this.idFiscalizacao = idFiscalizacao;
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
		this.dataEncerramento = dataEncerramento;
		this.indicadorExecucaoMotivoEncerramento = indicadorExecucaoMotivoEncerramento;
		this.idDocumento = idDocumento;
		this.dataEmissao = dataEmissao;
		this.valorDocumento = valorDocumento;
		this.percentualValorMinimoCobrancaCriterio = percentualValorMinimoCobrancaCriterio;
		this.percentualQuantidadeMinimaCobrancaCriterio = percentualQuantidadeMinimaCobrancaCriterio;
		this.valorLimitePrioridadeCobrancaCriterio = valorLimitePrioridadeCobrancaCriterio;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataFiscalizacaoSituacao() {
		return dataFiscalizacaoSituacao;
	}

	public void setDataFiscalizacaoSituacao(Date dataFiscalizacaoSituacao) {
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdFiscalizacao() {
		return idFiscalizacao;
	}

	public void setIdFiscalizacao(Integer idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}

	public Short getIndicadorExecucaoMotivoEncerramento() {
		return indicadorExecucaoMotivoEncerramento;
	}

	public void setIndicadorExecucaoMotivoEncerramento(
			Short indicadorExecucaoMotivoEncerramento) {
		this.indicadorExecucaoMotivoEncerramento = indicadorExecucaoMotivoEncerramento;
	}

	public short getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(short situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getPercentualQuantidadeMinimaCobrancaCriterio() {
		return percentualQuantidadeMinimaCobrancaCriterio;
	}

	public void setPercentualQuantidadeMinimaCobrancaCriterio(
			BigDecimal percentualQuantidadeMinimaCobrancaCriterio) {
		this.percentualQuantidadeMinimaCobrancaCriterio = percentualQuantidadeMinimaCobrancaCriterio;
	}

	public BigDecimal getPercentualValorMinimoCobrancaCriterio() {
		return percentualValorMinimoCobrancaCriterio;
	}

	public void setPercentualValorMinimoCobrancaCriterio(
			BigDecimal percentualValorMinimoCobrancaCriterio) {
		this.percentualValorMinimoCobrancaCriterio = percentualValorMinimoCobrancaCriterio;
	}

	public BigDecimal getValorLimitePrioridadeCobrancaCriterio() {
		return valorLimitePrioridadeCobrancaCriterio;
	}

	public void setValorLimitePrioridadeCobrancaCriterio(
			BigDecimal valorLimitePrioridadeCobrancaCriterio) {
		this.valorLimitePrioridadeCobrancaCriterio = valorLimitePrioridadeCobrancaCriterio;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

}
