package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarImovelProgramaEspecialActionForm extends ValidatorActionForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String matriculaImovel;
	private String inscricaoImovel;
	private String dataApresentacaoDocumentosInicial;
	private String dataApresentacaoDocumentosFinal;
	private String mesAnoReferenciaEntradaPrograma;
	private String mesAnoReferenciaSaidaPrograma;
	private String atualizar;
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getDataApresentacaoDocumentosInicial() {
		return dataApresentacaoDocumentosInicial;
	}
	public void setDataApresentacaoDocumentosInicial(
			String dataApresentacaoDocumentosInicial) {
		this.dataApresentacaoDocumentosInicial = dataApresentacaoDocumentosInicial;
	}
	public String getDataApresentacaoDocumentosFinal() {
		return dataApresentacaoDocumentosFinal;
	}
	public void setDataApresentacaoDocumentosFinal(
			String dataApresentacaoDocumentosFinal) {
		this.dataApresentacaoDocumentosFinal = dataApresentacaoDocumentosFinal;
	}
	public String getMesAnoReferenciaEntradaPrograma() {
		return mesAnoReferenciaEntradaPrograma;
	}
	public void setMesAnoReferenciaEntradaPrograma(
			String mesAnoReferenciaEntradaPrograma) {
		this.mesAnoReferenciaEntradaPrograma = mesAnoReferenciaEntradaPrograma;
	}
	public String getMesAnoReferenciaSaidaPrograma() {
		return mesAnoReferenciaSaidaPrograma;
	}
	public void setMesAnoReferenciaSaidaPrograma(
			String mesAnoReferenciaSaidaPrograma) {
		this.mesAnoReferenciaSaidaPrograma = mesAnoReferenciaSaidaPrograma;
	}
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	
	
	
	

}
