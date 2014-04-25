package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1185] Informar Calibragem
 * 
 * @author Thúlio Araújo
 *
 * @date 20/06/2011
 */
public class InformarCalibragemActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String grauImportancia;
	private String faixaInicial;
	private String faixaFinal;
	private String peso;
	private String fator;
	private String idPriorizacaoTipo;
	
	public String getFaixaFinal() {
		return faixaFinal;
	}
	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	public String getFaixaInicial() {
		return faixaInicial;
	}
	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	public String getFator() {
		return fator;
	}
	public void setFator(String fator) {
		this.fator = fator;
	}
	public String getGrauImportancia() {
		return grauImportancia;
	}
	public void setGrauImportancia(String grauImportancia) {
		this.grauImportancia = grauImportancia;
	}
	public String getIdPriorizacaoTipo() {
		return idPriorizacaoTipo;
	}
	public void setIdPriorizacaoTipo(String idPriorizacaoTipo) {
		this.idPriorizacaoTipo = idPriorizacaoTipo;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
}
