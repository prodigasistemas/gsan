package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;


public class RelatorioCertidaoNegativaItemBean implements RelatorioBean {
	
	private String descricaoServico;
	private String descricaoFatura;
	private String referenciaFatura;
	private String vencimentoFatura;
	private String valorFatura;
	private String validadeFatura;
	
	public RelatorioCertidaoNegativaItemBean(
			String descricaoServico, 
			String descricaoFatura, 
			String referenciaFatura, 
			String vencimentoFatura,
			String valorFatura, 
			String validadeFatura) {
		super();
		this.descricaoServico = descricaoServico;
		this.descricaoFatura = descricaoFatura;
		this.referenciaFatura = referenciaFatura;
		this.vencimentoFatura = vencimentoFatura;
		this.valorFatura = valorFatura;
		this.validadeFatura = validadeFatura;
	}

	public String getDescricaoFatura() {
	
		return descricaoFatura;
	}
	
	public String getDescricaoServico() {
	
		return descricaoServico;
	}
	
	public String getReferenciaFatura() {
	
		return referenciaFatura;
	}
	
	public String getValidadeFatura() {
	
		return validadeFatura;
	}
	
	public String getValorFatura() {
	
		return valorFatura;
	}
	
	public String getVencimentoFatura() {
	
		return vencimentoFatura;
	}	
	
}
