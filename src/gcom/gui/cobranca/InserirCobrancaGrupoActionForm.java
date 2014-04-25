package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho, Mariana Victor
 * @date 14/08/2009, 18/11/2010
 */
public class InserirCobrancaGrupoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	Integer id;
	
	String descricao;
	
	String descricaoAbreviada;
	
	String anoMesReferencia;
	
	String indicadorUso;
	
	//Contrato de Cobrança
	String idContratoCobranca;
	
	String empresa;
	
	String contratoFornecedor;
	
	String idNumeroContrato;
	
	String emailResponsavel;
	
	String indicadorExecucaoAutomatica;
	
	
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	public  String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}
	public String getIndicadorExecucaoAutomatica() {
		return indicadorExecucaoAutomatica;
	}
	public void setIndicadorExecucaoAutomatica(String indicadorExecucaoAutomatica) {
		this.indicadorExecucaoAutomatica = indicadorExecucaoAutomatica;
	}
	
	
}
