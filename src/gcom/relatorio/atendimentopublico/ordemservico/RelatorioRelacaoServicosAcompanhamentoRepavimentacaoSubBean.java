package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.relatorio.RelatorioBean;

/**
 * [UC ] 
 * @author Yara Taciane , Vivi.
 * @date 12/06/2008
 */
public class RelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean implements RelatorioBean {
	
	private String tipoPavimento; 
	private String descricao; 
	private String valor; 

	
	public RelatorioRelacaoServicosAcompanhamentoRepavimentacaoSubBean(String tipoPavimento,
																	   String descricao, 
																	   String valor) {
		super();
		
		this.tipoPavimento = tipoPavimento;
		this.descricao = descricao;
		this.valor = valor;

	}


	/**
	 * @return Retorna o campo tipoPavimento.
	 */
	public String getTipoPavimento() {
		return tipoPavimento;
	}

	/**
	 * @param tipoPavimento O tipoPavimento a ser setado.
	 */
	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns the valor.
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor The valor to set.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}
