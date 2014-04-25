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

	private Integer numeroOrdemServico;
	private String situacao;
	private Date dataGeracao;
	private Integer idServicoTipo;
	private String descricaoServicoTipo;
	private Date dataEncerramento;
	private String parecerEncerramento;

	

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public Integer getNumeroOrdemServico() {
		return numeroOrdemServico;
	}

	public void setNumeroOrdemServico(Integer numeroOrdemServico) {
		this.numeroOrdemServico = numeroOrdemServico;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	

}
