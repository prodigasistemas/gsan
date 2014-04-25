package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0482] Emitir 2ª Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class RelatorioConsultarRegistroAtendimentoDetailBean implements RelatorioBean {
	
	private String dataHora; 
	private String nomeSolicitante; 
	private String cliente;
	private String unidade;
	private String fone;
	
	public RelatorioConsultarRegistroAtendimentoDetailBean(String dataHora,
			String nomeSolicitante, String cliente, String unidade, String fone) {
		this.dataHora = dataHora;
		this.nomeSolicitante = nomeSolicitante;
		this.cliente = cliente;
		this.unidade = unidade;
		this.fone = fone;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	

	
}
