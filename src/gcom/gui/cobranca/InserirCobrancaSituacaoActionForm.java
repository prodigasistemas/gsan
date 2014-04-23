package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 04/09/2008
 */
public class InserirCobrancaSituacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String descricao;
	
	String contaMotivoRevisao;
	
	Short indicadorExigenciaAdvogado;
	
	Short indicadorBloqueioParcelamento;
	
	Short indicadorUso;
	
	String profissao;
	
	String ramoAtividade;

	Short indicadorBloqueioRetirada;
	
	Short indicadorBloqueioInclusao;
	
	Short indicadorSelecaoApenasComPermissao;
	
	private Integer indicadorPrescricaoImoveisParticulares;
	
	Integer indicadorNaoIncluirImoveisEmCobrancaResultado;
	
	public String getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(String contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorBloqueioParcelamento() {
		return indicadorBloqueioParcelamento;
	}

	public void setIndicadorBloqueioParcelamento(Short indicadorBloqueioParcelamento) {
		this.indicadorBloqueioParcelamento = indicadorBloqueioParcelamento;
	}

	public Short getIndicadorExigenciaAdvogado() {
		return indicadorExigenciaAdvogado;
	}

	public void setIndicadorExigenciaAdvogado(Short indicadorExigenciaAdvogado) {
		this.indicadorExigenciaAdvogado = indicadorExigenciaAdvogado;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Short getIndicadorBloqueioRetirada() {
		return indicadorBloqueioRetirada;
	}

	public void setIndicadorBloqueioRetirada(Short indicadorBloqueioRetirada) {
		this.indicadorBloqueioRetirada = indicadorBloqueioRetirada;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public Short getIndicadorBloqueioInclusao() {
		return indicadorBloqueioInclusao;
	}

	public void setIndicadorBloqueioInclusao(Short indicadorBloqueioInclusao) {
		this.indicadorBloqueioInclusao = indicadorBloqueioInclusao;
	}

	public Short getIndicadorSelecaoApenasComPermissao() {
		return indicadorSelecaoApenasComPermissao;
	}

	public void setIndicadorSelecaoApenasComPermissao(
			Short indicadorSelecaoApenasComPermissao) {
		this.indicadorSelecaoApenasComPermissao = indicadorSelecaoApenasComPermissao;
	}

	public Integer getIndicadorPrescricaoImoveisParticulares() {
		return indicadorPrescricaoImoveisParticulares;
	}

	public void setIndicadorPrescricaoImoveisParticulares(
			Integer indicadorPrescricaoImoveisParticulares) {
		this.indicadorPrescricaoImoveisParticulares = indicadorPrescricaoImoveisParticulares;
	}

	public Integer getIndicadorNaoIncluirImoveisEmCobrancaResultado() {
		return indicadorNaoIncluirImoveisEmCobrancaResultado;
	}

	public void setIndicadorNaoIncluirImoveisEmCobrancaResultado(
			Integer indicadorNaoIncluirImoveisEmCobrancaResultado) {
		this.indicadorNaoIncluirImoveisEmCobrancaResultado = indicadorNaoIncluirImoveisEmCobrancaResultado;
	}

}
