package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho, Mariana Victor
 * @date  	14/08/2009, 22/11/2010
 */

public class AtualizarCobrancaGrupoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;	
	private String descricaoAbreviada;
	private String anoMesReferencia;
	private Short indicadorUso;	 
	
	//Contrato de Cobrança
	private String idContratoCobranca;
	
	private String empresa;
	
	private String contratoFornecedor;
	
	private String idNumeroContrato;
	
	private String emailResponsavel;
	
	private Short indicadorExecucaoAutomatica;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getContratoFornecedor() {
		return contratoFornecedor;
	}
	public void setContratoFornecedor(String contratoFornecedor) {
		this.contratoFornecedor = contratoFornecedor;
	}
	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getIdContratoCobranca() {
		return idContratoCobranca;
	}
	public void setIdContratoCobranca(String idContratoCobranca) {
		this.idContratoCobranca = idContratoCobranca;
	}
	public String getIdNumeroContrato() {
		return idNumeroContrato;
	}
	public void setIdNumeroContrato(String idNumeroContrato) {
		this.idNumeroContrato = idNumeroContrato;
	}
	public Short getIndicadorExecucaoAutomatica() {
		return indicadorExecucaoAutomatica;
	}
	public void setIndicadorExecucaoAutomatica(Short indicadorExecucaoAutomatica) {
		this.indicadorExecucaoAutomatica = indicadorExecucaoAutomatica;
	}

	
	
}
