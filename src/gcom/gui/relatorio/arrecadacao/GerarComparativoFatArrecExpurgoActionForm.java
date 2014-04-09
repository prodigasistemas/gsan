package gcom.gui.relatorio.arrecadacao;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
 * 
 * @author Sávio Luiz
 *
 * @date 09/12/2008
 */

public class GerarComparativoFatArrecExpurgoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String idUnidadeNegocio;
	private String idGerenciaRegional;
	private String opcaoTotalizacao;
	

	

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	
	
	
	
	

	
	
}
