package gcom.relatorio.faturamento;

import gcom.faturamento.bean.AtualizarContaPreFaturadaHelper;
import gcom.relatorio.RelatorioBean;

public class RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean implements RelatorioBean {

	private String codigoAnormalidade;
	private String descricaoAnormalidade;
	private Integer qtdAnormalidade;
	private String idImovel;
	private String verificarContaImovel;
	
	
	public String getCodigoAnormalidade() {
		return codigoAnormalidade;
	}
	public void setCodigoAnormalidade(String codigoAnormalidade) {
		this.codigoAnormalidade = codigoAnormalidade;
	}
	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}
	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}
	public Integer getQtdAnormalidade() {
		return qtdAnormalidade;
	}
	public void setQtdAnormalidade(Integer qtdAnormalidade) {
		this.qtdAnormalidade = qtdAnormalidade;
	}
	
	public boolean equals( Object object ){
		if ( object instanceof AtualizarContaPreFaturadaHelper ){
			AtualizarContaPreFaturadaHelper atualizarContaPreFaturadaHelper = ( AtualizarContaPreFaturadaHelper ) object;
			
			return this.getCodigoAnormalidade().equals( atualizarContaPreFaturadaHelper.getAnormalidadeLeitura() );
			
		} else if (object instanceof RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean) {
			RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean relatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean = (RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean) object;
			
			return relatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean.getCodigoAnormalidade().equals( this.getCodigoAnormalidade() );			
		}
		
		return false;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getVerificarContaImovel() {
		return verificarContaImovel;
	}
	public void setVerificarContaImovel(String verificarContaImovel) {
		this.verificarContaImovel = verificarContaImovel;
	}
}
