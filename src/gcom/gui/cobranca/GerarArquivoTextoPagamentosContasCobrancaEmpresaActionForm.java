package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class GerarArquivoTextoPagamentosContasCobrancaEmpresaActionForm extends
		ActionForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String nomeEmpresa;
	
	private String referenciaInicial;
	
	private String referenciaFinal;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}
	
	
}
