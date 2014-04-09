package gcom.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rômulo Aurélio
 *
 */
public class FiltrarInformarLeituraFiscalizacaoActionForm extends ValidatorActionForm {
	


	/**
	 * @since 23/05/2007
	 */
	private static final long serialVersionUID = 1L;


	private String matricula;
	
	private String inscricaoImovel;
	
	private String dataLeituraNormal;
	
	private String medicaoTipo;
	
	private String mesAnoReferencia;
	
	private String leituraNormal;
	
	private String anormalidadeNormal;
	
	private String matriculaLeituristaNormal;
	
	private String leituraFiscalizacao;
	
	private String dataLeituraFiscalizacao;
	
	private String anormalidadeFiscalizacao;
	
	private String matriculaLeituristaFiscalizacao;

	public String getAnormalidadeFiscalizacao() {
		return anormalidadeFiscalizacao;
	}

	public void setAnormalidadeFiscalizacao(String anormalidadeFiscalizacao) {
		this.anormalidadeFiscalizacao = anormalidadeFiscalizacao;
	}

	public String getAnormalidadeNormal() {
		return anormalidadeNormal;
	}

	public void setAnormalidadeNormal(String anormalidadeNormal) {
		this.anormalidadeNormal = anormalidadeNormal;
	}

	public String getDataLeituraFiscalizacao() {
		return dataLeituraFiscalizacao;
	}

	public void setDataLeituraFiscalizacao(String dataLeituraFiscalizacao) {
		this.dataLeituraFiscalizacao = dataLeituraFiscalizacao;
	}

	public String getDataLeituraNormal() {
		return dataLeituraNormal;
	}

	public void setDataLeituraNormal(String dataLeituraNormal) {
		this.dataLeituraNormal = dataLeituraNormal;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLeituraFiscalizacao() {
		return leituraFiscalizacao;
	}

	public void setLeituraFiscalizacao(String leituraFiscalizacao) {
		this.leituraFiscalizacao = leituraFiscalizacao;
	}

	public String getLeituraNormal() {
		return leituraNormal;
	}

	public void setLeituraNormal(String leituraNormal) {
		this.leituraNormal = leituraNormal;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatriculaLeituristaFiscalizacao() {
		return matriculaLeituristaFiscalizacao;
	}

	public void setMatriculaLeituristaFiscalizacao(
			String matriculaLeituristaFiscalizacao) {
		this.matriculaLeituristaFiscalizacao = matriculaLeituristaFiscalizacao;
	}

	public String getMatriculaLeituristaNormal() {
		return matriculaLeituristaNormal;
	}

	public void setMatriculaLeituristaNormal(String matriculaLeituristaNormal) {
		this.matriculaLeituristaNormal = matriculaLeituristaNormal;
	}

	public String getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(String medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	
	

}
