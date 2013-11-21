package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Descrição da classe
 * Classe responsável pela descricão dos beans do relatório
 * 
 * @author Anderson Italo
 * @date 26/11/2009
 */
public class RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean implements RelatorioBean {
	
	private String descricaoMotivo;
	private Integer quantidadeImoveisPorMotivo;
	
	public RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean(){}
	
	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}
	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}
	public Integer getQuantidadeImoveisPorMotivo() {
		return quantidadeImoveisPorMotivo;
	}
	public void setQuantidadeImoveisPorMotivo(Integer quantidadeImoveisPorMotivo) {
		this.quantidadeImoveisPorMotivo = quantidadeImoveisPorMotivo;
	} 

}
