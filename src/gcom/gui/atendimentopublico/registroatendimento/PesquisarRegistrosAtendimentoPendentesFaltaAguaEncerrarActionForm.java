package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Esta classe tem por finalidade gerar o formulário que exibirá os registros de atendimento
 * pendentes para falta de água generalizada
 *
 * @author Raphael Rossiter
 * @date 17/08/2006
 */
public class PesquisarRegistrosAtendimentoPendentesFaltaAguaEncerrarActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idSolicitacaoTipo;
	
	private String descricaoSolicitacaoTipo;
	
	private String idSolicitacaoTipoEspecificacao;
	
	private String descricaoSolicitacaoTipoEspecificacao;
	
	private String codigoBairro;
	
	private String descricaoBairro;
	
	private String idBairroArea;
	
	private String descricaoBairroArea;
	
	private String raSelected;
	
	

	public String getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public String getDescricaoBairroArea() {
		return descricaoBairroArea;
	}

	public void setDescricaoBairroArea(String descricaoBairroArea) {
		this.descricaoBairroArea = descricaoBairroArea;
	}

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public String getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(String idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public String getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(String idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public String getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			String idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public String getRaSelected() {
		return raSelected;
	}

	public void setRaSelected(String raSelected) {
		this.raSelected = raSelected;
	}
	
	

}
