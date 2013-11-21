package gcom.relatorio.atendimentopublico.ordemservico;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC99999] Emitir Relatorio de Ordem de Serviço de Fiscalizacao
 * 
 * 
 * @author Paulo Diniz
 *
 * @date 15/08/2011
 */
public class RelatorioOrdemServicoFiscalizacaoSinteticoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String unidadeNegocio;
	private String gerenciaRegional;
	private String localidade;
	private BigDecimal quantidadeTipoRetorno;
	private BigDecimal quantidadeMotivo;
	private String nomeColuna;
	private String itemDescricao;
	private String mesAnoReferencia;
	private String nomeTotal;

	
	public RelatorioOrdemServicoFiscalizacaoSinteticoBean() {
		super();
	}


	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}


	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


	public String getGerenciaRegional() {
		return gerenciaRegional;
	}


	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	public String getLocalidade() {
		return localidade;
	}


	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeColuna() {
		return nomeColuna;
	}


	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}


	public String getItemDescricao() {
		return itemDescricao;
	}


	public void setItemDescricao(String itemDescricao) {
		this.itemDescricao = itemDescricao;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}


	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}


	public String getNomeTotal() {
		return nomeTotal;
	}


	public void setNomeTotal(String nomeTotal) {
		this.nomeTotal = nomeTotal;
	}


	public BigDecimal getQuantidadeTipoRetorno() {
		return quantidadeTipoRetorno;
	}


	public void setQuantidadeTipoRetorno(BigDecimal quantidadeTipoRetorno) {
		this.quantidadeTipoRetorno = quantidadeTipoRetorno;
	}


	public BigDecimal getQuantidadeMotivo() {
		return quantidadeMotivo;
	}


	public void setQuantidadeMotivo(BigDecimal quantidadeMotivo) {
		this.quantidadeMotivo = quantidadeMotivo;
	}


}
