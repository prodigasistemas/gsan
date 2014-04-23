package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 06/11/2006
 */
public class FiltrarTipoSolicitacaoEspecificacaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String idgrupoTipoSolicitacao;

	private String indicadorFaltaAgua;

	private String indicadorTarifaSocial;

	private String indicadorUso;

	private String especificacaoTipoSolicitacao;
	
	private String atualizar;
	
	private String nomeTipoSolicitacao;

	/**
	 * @return Retorna o campo nomeTipoSolicitacao.
	 */
	public String getNomeTipoSolicitacao() {
		return nomeTipoSolicitacao;
	}

	/**
	 * @param nomeTipoSolicitacao O nomeTipoSolicitacao a ser setado.
	 */
	public void setNomeTipoSolicitacao(String nomeTipoSolicitacao) {
		this.nomeTipoSolicitacao = nomeTipoSolicitacao;
	}

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
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
	 * @return Retorna o campo especificacaoTipoSolicitacao.
	 */
	public String getEspecificacaoTipoSolicitacao() {
		return especificacaoTipoSolicitacao;
	}

	/**
	 * @param especificacaoTipoSolicitacao O especificacaoTipoSolicitacao a ser setado.
	 */
	public void setEspecificacaoTipoSolicitacao(String especificacaoTipoSolicitacao) {
		this.especificacaoTipoSolicitacao = especificacaoTipoSolicitacao;
	}

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

}
