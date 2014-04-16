package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 07/11/2006
 */
public class AtualizarTipoSolicitacaoEspecificacaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;
	
	private String tamanhoColecao;

	private String idgrupoTipoSolicitacao;

	private String indicadorFaltaAgua;

	private String indicadorTarifaSocial;
	
	private String idTipoSolicitacao;
	
	private String indicadorUso;
	
	private String indicadorUsoSistema;
	
	private String indicadorUrgencia;

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo idTipoSolicitacao.
	 */
	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	/**
	 * @param idTipoSolicitacao O idTipoSolicitacao a ser setado.
	 */
	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdgrupoTipoSolicitacao() {
		return idgrupoTipoSolicitacao;
	}

	public void setIdgrupoTipoSolicitacao(String idgrupoTipoSolicitacao) {
		this.idgrupoTipoSolicitacao = idgrupoTipoSolicitacao;
	}

	public String getIndicadorFaltaAgua() {
		return indicadorFaltaAgua;
	}

	public void setIndicadorFaltaAgua(String indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	public String getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(String indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}

	/**
	 * @return Retorna o campo tamanhoColecao.
	 */
	public String getTamanhoColecao() {
		return tamanhoColecao;
	}

	/**
	 * @param tamanhoColecao O tamanhoColecao a ser setado.
	 */
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}

	/**
	 * @return Retorna o campo indicadorUsoSistema.
	 */
	public String getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	/**
	 * @param indicadorUsoSistema O indicadorUsoSistema a ser setado.
	 */
	public void setIndicadorUsoSistema(String indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}

	public String getIndicadorUrgencia() {
		return indicadorUrgencia;
	}

	public void setIndicadorUrgencia(String indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}

}
