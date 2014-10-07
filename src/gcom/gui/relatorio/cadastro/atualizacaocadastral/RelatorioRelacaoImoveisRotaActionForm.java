package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.SituacaoAtualizacaoCadastral;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioRelacaoImoveisRotaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idLocalidadeInicial;	

	private String nomeLocalidadeInicial;	
	
	private String cdSetorComercialInicial;	
	
	private String nomeSetorComercialInicial;	
	
	private String cdRotaInicial;

	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getCdSetorComercialInicial() {
		return cdSetorComercialInicial;
	}

	public void setCdSetorComercialInicial(String cdSetorComercialInicial) {
		this.cdSetorComercialInicial = cdSetorComercialInicial;
	}

	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String cdRotaInicial) {
		this.cdRotaInicial = cdRotaInicial;
	}
	
}
