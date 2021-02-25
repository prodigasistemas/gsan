package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC0488] - Consultar Dados do Registro de Atendimento 
 * 
 * Dados da Os Associadas
 * 
 * @author Arthur Carvalho
 * @date 17/02/2009
 */
public class OrdemServicoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String numeroOrdemServico;
	private String situacao;
	private String dataGeracao;
	private String idServicoTipo;
	private String descricaoServicoTipo;
	private String dataEncerramento;
	private String parecerEncerramento;
	
	
	public String getNumeroOrdemServico() {
		return numeroOrdemServico;
	}
	public void setNumeroOrdemServico(String numeroOrdemServico) {
		this.numeroOrdemServico = numeroOrdemServico;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}
	

}
