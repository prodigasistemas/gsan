package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0403] Filtrar Espeficicao da Situacao do Imóvel
 *
 * @author Rafael Francisco Pinto
 * @date 08/11/2006
 */
public class FiltrarEspecificacaoSituacaoImovelActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String indicadorAtualizar;
	private String idEspecificacao;
	private String descricaoEspecificacao;
	private String tipoPesquisa;
	
	public String getDescricaoEspecificacao() {
		return descricaoEspecificacao;
	}
	
	public void setDescricaoEspecificacao(String descricaoEspecificacao) {
		this.descricaoEspecificacao = descricaoEspecificacao;
	}
	
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	
}
